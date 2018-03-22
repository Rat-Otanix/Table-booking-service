package com.javarightnow.reservation.converter;

import com.javarightnow.reservation.dataobject.BaseDto;
import com.javarightnow.reservation.dataobject.BaseEntity;

/**
 * It is the the base class for converting Entity to Dto.
 * We convert the {@link BaseEntity} fields here, and other UseCases just need to implement their own fields in doAudit() method.
 *
 * @author hadi
 */
public abstract class BaseEntityToDtoConverter< ENT extends BaseEntity, DTO extends BaseDto>
        extends Converter<ENT, DTO> {

    @Override
    public final DTO convert(final ENT entity) {
        if (entity == null) {
            return null;
        }
        return doBase(entity);
    }

    /**
     * dade <tt>In</tt> ra be <tt>Out</tt> convert mikonad.
     *
     * @param entity noe dadeh voroudi
     * @return dadeh convert shode
     */
    protected final DTO doBase(final ENT entity) {

        final DTO dto = doAudit(entity);

        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setCreator(entity.getCreator());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModifier(entity.getModifier());
        dto.setModifyDate(entity.getModifyDate());

        return dto;
    }

    protected abstract DTO doAudit(final ENT entity);

}
