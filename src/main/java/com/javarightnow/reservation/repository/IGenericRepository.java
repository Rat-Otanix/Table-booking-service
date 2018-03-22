package com.javarightnow.reservation.repository;

import com.javarightnow.reservation.dataobject.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * if you want to add a common method between all your repositories, you can add it here.
 * <b>By the way make you repository with package access modifier.</b>
 *
 * @author hadi
 */
@NoRepositoryBean
public interface IGenericRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {

}
