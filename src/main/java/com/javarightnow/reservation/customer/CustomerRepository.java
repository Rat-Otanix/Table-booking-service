package com.javarightnow.reservation.customer;

import com.javarightnow.reservation.repository.IGenericRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CustomerRepository extends IGenericRepository<CustomerEntity, Long> {

} 