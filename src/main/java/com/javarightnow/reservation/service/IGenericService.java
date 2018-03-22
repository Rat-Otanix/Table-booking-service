package com.javarightnow.reservation.service;

import com.javarightnow.reservation.dataobject.BaseDto;
import com.javarightnow.reservation.dataobject.dto.SimplePageRequestDTO;
import com.javarightnow.reservation.exception.EmptyInputException;
import com.javarightnow.reservation.exception.GeneralException;
import com.javarightnow.reservation.exception.NoSuchResourceException;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * If you want to add a common method between all your services, you can add it here.
 * So your services don't need to implement these methods.
 * <p>
 * Actually These methods are the simplest implementation for common methods. And of course you can
 * always override them and change them depends on you business.
 * <p>
 * <b>Your services should not return Entity. Always try to return Dto.</b>
 *
 * @param <Dto>
 * @param <ID>
 * @author hadi
 */
public interface IGenericService<Dto extends BaseDto, ID extends Object> {

    boolean exists(ID id) throws EmptyInputException;

    Dto getById(final ID id) throws NoSuchResourceException;

    Dto save(Dto dto) throws GeneralException;

    Dto update(Dto dto);

    Iterable<Dto> saveAll(Iterable<Dto> dto) throws EmptyInputException;

    List<Dto> findAll();

    /**
     * * It throws exception if it can not find the entity with the specific ID.
     *
     * @param id
     * @throws GeneralException
     */
    void validate(ID id) throws NoSuchResourceException, EmptyInputException ;

    long count();

    PageRequest preparePageRequest(SimplePageRequestDTO simplePageRequestDTO);

}