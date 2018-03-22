package com.javarightnow.reservation.converter;

import java.util.List;

/**
 * It is used for converting Dto and Entity to each other.
 *
 * @param <In>
 * @param <Out>
 * @author hadi
 */
public interface IConverter<In, Out> {

    Out convert(In in);

    List<Out> convert(Iterable<In> iterable);

    List<Out> convert(List<In> list);
}