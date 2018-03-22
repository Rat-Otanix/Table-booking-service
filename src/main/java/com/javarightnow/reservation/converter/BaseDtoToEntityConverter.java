package com.javarightnow.reservation.converter;

import com.javarightnow.reservation.dataobject.BaseDto;
import com.javarightnow.reservation.dataobject.BaseEntity;

/**
 * It is the the base class for converting Dto to Entity.
 * We convert the {@link BaseEntity} fields here, and other UseCases just need to implement their own fields in doAudit() method.
 *
 * @author hadi
 */
public abstract class BaseDtoToEntityConverter<DTO extends BaseDto, ENT extends BaseEntity>
        extends Converter<DTO, ENT> {

    @Override
    public final ENT convert(final DTO dto) {
        if (dto == null) {
            return null;
        }
        return doDtoToEntity(dto);
    }

    /**
     * @param dto
     * @return
     */
    protected ENT doDtoToEntity(final DTO dto) {

        final ENT entity = doAudit(dto);

        dto.setId(entity.getId());
        entity.setVersion(dto.getVersion());
        entity.setCreationDate(dto.getCreationDate());
        entity.setCreator(dto.getCreator());
        entity.setModifier(dto.getModifier());
        entity.setModifyDate(dto.getModifyDate());

        return entity;
    }

    protected abstract ENT doAudit(final DTO dto);

}
