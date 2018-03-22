package com.javarightnow.reservation.reservation;

import com.javarightnow.reservation.table.TableEntity;
import com.javarightnow.reservation.customer.CustomerEntity;
import com.javarightnow.reservation.dataobject.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RES_RESERVATION")
public class ReservationEntity extends BaseEntity<Long> {

    @Id
    @Column(name = "RES_RSRV_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    public TimesLot timesLot;

    @JoinColumn(foreignKey = @ForeignKey(name = "RES_CUST_RSRV_ID"), name = "RES_CUST_ID", referencedColumnName = "RES_CUST_ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CustomerEntity customer;

    @JoinColumn(foreignKey = @ForeignKey(name = "RES_TABL_RSRV_ID"), name = "RES_TABL_ID", referencedColumnName = "RES_TABL_ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TableEntity table;

}