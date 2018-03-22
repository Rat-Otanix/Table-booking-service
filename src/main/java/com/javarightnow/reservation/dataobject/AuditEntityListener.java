package com.javarightnow.reservation.dataobject;

import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * It sets the base fields before each save/update in entities.
 *
 * @author hadi
 */
public class AuditEntityListener {

    @PrePersist
    public void prePersist(final BaseEntity baseEntity) {
        baseEntity.setCreationDate(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(final BaseEntity baseEntity) {
        baseEntity.setModifyDate(LocalDateTime.now());
    }
}
