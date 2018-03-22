package com.javarightnow.reservation.customer;

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
public class CustomerDto extends BaseDto<Long> {

    /**
     * I deliberately chose this name because you mentioned it in the use case.
     */
    @NotNull
    @NotBlank
    @Size(max = 64)
    private String customer_name;

    @Valid
    private List<ReservationDto> reservations;
}