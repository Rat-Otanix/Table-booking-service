package com.javarightnow.reservation.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * It is the parent of all other entities. we have put common fields here.
 *
 * @param <ID>
 * @author hadi
 */
@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditEntityListener.class)
public abstract class BaseEntity<ID> extends Entity {

    @Column(name = "RES_BASE_VERSION")
    @Version
    @NotNull
    private Long version;

    @Size(max = 64)
    @Column(name = "RES_BASE_CREATOR")
    private String creator;

    @Column(name = "RES_BASE_CREATION_DATE")
    @NotNull
    private LocalDateTime creationDate;

    @Size(max = 64)
    @Column(name = "RES_BASE_MODIFIER")
    private String modifier;

    @Column(name = "RES_BASE_MODIFY_DATE")
    private LocalDateTime modifyDate;

    public BaseEntity() {
        super();
    }

    public BaseEntity(final ID id) {
        super();
        setId(id);
    }

    public abstract ID getId();

    public abstract void setId(final ID id);

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);
    }

    @Override
    public final int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

} 