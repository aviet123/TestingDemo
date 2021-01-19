package com.example.demo.service;

import com.example.demo.entity.SimpleSource;
import com.example.demo.model.SimpleDTODestination;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface SimpleSourceDestination {

    SimpleDTODestination sourceToDestination(SimpleSource simpleSource);
    SimpleSource destinationToSource(SimpleDTODestination simpleDTODestination);
}
