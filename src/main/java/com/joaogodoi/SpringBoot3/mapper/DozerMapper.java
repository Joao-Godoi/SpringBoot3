package com.joaogodoi.SpringBoot3.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <Origin, Destination> Destination parseObject(Origin origin, Class<Destination> destination) {
        return mapper.map(origin, destination);
    }

    public static <Origin, Destination> List<Destination> parseListObjects(List<Origin> origin, Class<Destination> destination) {
        List<Destination> destinationObjects = new ArrayList<Destination>();
        for (Origin originObject : origin) {
            destinationObjects.add(mapper.map(originObject, destination));
        }
        return destinationObjects;
    }
}
