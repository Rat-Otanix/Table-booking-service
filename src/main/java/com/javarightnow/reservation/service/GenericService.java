package com.javarightnow.reservation.service;

import com.javarightnow.reservation.dataobject.BaseDto;
import com.javarightnow.reservation.converter.Converter;
import com.javarightnow.reservation.dataobject.BaseEntity;
import com.javarightnow.reservation.dataobject.dto.SimplePageRequestDTO;
import com.javarightnow.reservation.exception.EmptyInputException;
import com.javarightnow.reservation.exception.NoSuchResourceException;
import com.javarightnow.reservation.exception.GeneralException;
import com.javarightnow.reservation.exception.enums.Model;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * If you want to add a common method between all your services, you can add it here.
 * So your services don't need to implement these methods.
 * <p>
 * Your services should not return Entity. Always try to return Dto.
 *
 * @param <Ent>
 * @param <Dto>
 * @param <ID>
 * @author hadi
 */
public abstract class GenericService<Ent extends BaseEntity, Dto extends BaseDto, ID>
        implements IGenericService<Dto, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final JpaRepository<Ent, ID> jpaRepository;
    protected final Converter<Ent, Dto> entityToDtoConverter;
    protected final Converter<Dto, Ent> dtoToEntityConverter;
    private final Model model;

    public GenericService(JpaRepository<Ent, ID> jpaRepository,
                          Converter<Ent, Dto> entityToDtoConverter,
                          Converter<Dto, Ent> dtoToEntityConverter,
                          Model model) {
        this.jpaRepository = jpaRepository;
        this.entityToDtoConverter = entityToDtoConverter;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.model = model;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exists(ID id) throws EmptyInputException {
        if (id == null) {
            throw EmptyInputException.builder()
                    .code(10004L)
                    .message("id is null.")
                    .build();
        }
        return jpaRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Dto getById(ID id) throws NoSuchResourceException {
        Optional<Ent> optional = jpaRepository.findById(id);

        String[] searchParamsMap = {"id", id.toString()};

        if (!optional.isPresent()) {
            throw NoSuchResourceException.builder()
                    .model(model)
                    .code(10005L)
                    .searchParamsMap(searchParamsMap)
                    .build();
        }
        return entityToDtoConverter.convert(optional.get());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Dto> findAll() {
        return entityToDtoConverter.convert(jpaRepository.findAll());
    }

    @Override
    public Dto save(Dto dto) throws GeneralException {
        Ent ent = dtoToEntityConverter.convert(dto);
        Ent save = jpaRepository.save(ent);
        return entityToDtoConverter.convert(save);
    }

    @Override
    public Dto update(Dto dto) {
        Ent ent = dtoToEntityConverter.convert(dto);
        Ent save = jpaRepository.save(ent);
        return entityToDtoConverter.convert(save);
    }

    @Override
    public Iterable<Dto> saveAll(Iterable<Dto> dtoList) throws EmptyInputException {
        if (IterableUtils.isEmpty(dtoList)) {
            throw EmptyInputException.builder()
                    .code(10006L)
                    .message("while saveAll, iterable list is null")
                    .build();
        }
        List<Ent> entList = dtoToEntityConverter.convert(dtoList);
        List<Ent> saveList = jpaRepository.saveAll(entList);
        return entityToDtoConverter.convert(saveList);
    }

    /**
     * It throws exception if it can not find the entity with the specific ID.
     *
     * @param id
     * @throws GeneralException
     */
    @Override
    public void validate(ID id) throws NoSuchResourceException, EmptyInputException {
        String[] searchParamsMap = {"id", id.toString()};

        if (!exists(id)) {
            throw NoSuchResourceException.builder()
                    .model(model)
                    .code(10007L)
                    .searchParamsMap(searchParamsMap)
                    .build();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public PageRequest preparePageRequest(SimplePageRequestDTO simplePageRequestDTO) {
        if (simplePageRequestDTO == null) {
            return PageRequest.of(SimplePageRequestDTO.DEFAULT_PAGE, SimplePageRequestDTO.DEFAULT_SIZE);
        } else {
            return PageRequest.of(simplePageRequestDTO.getPage(), simplePageRequestDTO.getSize());
        }
    }

}