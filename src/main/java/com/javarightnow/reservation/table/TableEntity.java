package com.javarightnow.reservation.table;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RES_TABLE",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"RES_TABL_NAME"}, name = "RES_TABLE_NAME_UNIQUE")},
        indexes = {@Index(columnList = "RES_TABL_NAME", name = "RES_TABLE_NAME_INDEX")}
)
public class TableEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RES_TABL_ID")
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 64)
    @Column(name = "RES_TABL_NAME")
    private String name;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "table")
//    private List<ReservationEnt> reservations;

    public TableEntity(Long id) {
        super(id);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}