package com.javarightnow.reservation.customer;

import com.javarightnow.reservation.dataobject.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RES_CUSTOMER")
public class CustomerEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RES_CUST_ID")
    private  Long id;

    @NotNull
    @NotBlank
    @Size(max = 64)
    @Column(name = "RES_CUST_FIRSTNAME")
    private String name;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
//    private List<ReservationEnt> reservations;

    public CustomerEntity(Long id) {
        super(id);
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}