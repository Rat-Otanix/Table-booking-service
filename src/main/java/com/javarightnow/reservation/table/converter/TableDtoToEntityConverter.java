package com.javarightnow.reservation.table.converter;

import com.javarightnow.reservation.converter.BaseDtoToEntityConverter;
import com.javarightnow.reservation.table.TableDto;
import com.javarightnow.reservation.table.TableEntity;
import org.springframework.stereotype.Component;

/**
 * @author hadi
 */
@Component
public final class TableDtoToEntityConverter
        extends BaseDtoToEntityConverter<TableDto, TableEntity> {

    @Override
    public TableEntity doAudit(final TableDto dto) {

        final TableEntity entity = TableEntity.builder()
                .name(dto.getName())
                .build();
        return entity;

    }
}
