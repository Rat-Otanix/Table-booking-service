package com.javarightnow.reservation.table.converter;

import com.javarightnow.reservation.converter.BaseEntityToDtoConverter;
import com.javarightnow.reservation.table.TableDto;
import com.javarightnow.reservation.table.TableEntity;
import org.springframework.stereotype.Component;

/**
 * @author hadi
 */
@Component
public final class TableEntityToDtoConverter
        extends BaseEntityToDtoConverter<TableEntity, TableDto> {

    @Override
    protected TableDto doAudit(final TableEntity entity) {

        return TableDto.builder()
                .name(entity.getName())
                .build();
    }

}