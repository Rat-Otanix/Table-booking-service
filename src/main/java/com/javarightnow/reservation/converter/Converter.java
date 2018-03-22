package com.javarightnow.reservation.converter;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * It is used for converting Dto and Entity to each other.
 * We have implemented list converters but the converter() method has implemented in BaseDtoToEntityConverter
 * and BaseEntityToDtoConverter classes.
 *
 * @param <In>
 * @param <Out>
 * @author hadi
 */
public abstract class Converter<In, Out> implements IConverter<In, Out> {

    @Override
    public List<Out> convert(Iterable<In> iterable) {
        if (iterable == null) {
            return null;
        }

        if (!iterable.iterator().hasNext()) {
            return new ArrayList<Out>(0);
        }

        final List<Out> outList = new ArrayList<Out>();
        for (final In in : iterable) {
            outList.add(convert(in));
        }

        return outList;
    }

    @Override
    public List<Out> convert(List<In> list) {

        if (list == null) {
            return null;
        }

        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<Out>(0);
        }

        final List<Out> outList = new ArrayList<Out>(list.size());
        for (final In in : list) {
            outList.add(convert(in));
        }

        return outList;
    }
}