package com.javarightnow.reservation.dataobject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * It is the parent of all other DTOs. we have put common fields here.
 *
 * @param <ID>
 * @author hadi
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class BaseDto<ID> extends Dto {

    private ID id;
    private Long version;
    private String creator;
    private LocalDateTime creationDate;
    private String modifier;
    private LocalDateTime modifyDate;

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseDto baseDto = (BaseDto) o;
        return !(id != null ? !id.equals(baseDto.id) : baseDto.id != null);
    }

    @Override
    public final int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

} 