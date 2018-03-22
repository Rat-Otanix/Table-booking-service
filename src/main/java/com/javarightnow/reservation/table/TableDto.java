package com.javarightnow.reservation.table;

import com.javarightnow.reservation.dataobject.BaseDto;
import com.javarightnow.reservation.reservation.ReservationDto;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TableDto extends BaseDto<Long> {

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String name;

    @Valid
    private List<ReservationDto> reservations;

}