package com.javarightnow.reservation.customer;

import com.javarightnow.reservation.customer.converter.CustomerDtoToEntityConverter;
import com.javarightnow.reservation.customer.converter.CustomerEntityToDtoConverter;
import com.javarightnow.reservation.exception.enums.Model;
import com.javarightnow.reservation.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
class CustomerServiceImpl extends GenericService<CustomerEntity, CustomerDto, Long> implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerEntityToDtoConverter customerEntityToDtoConverter,
                               CustomerDtoToEntityConverter customerDtoToEntityConverter) {
        super(customerRepository, customerEntityToDtoConverter, customerDtoToEntityConverter, Model.CUSTOMER);
        this.customerRepository = customerRepository;
    }
}