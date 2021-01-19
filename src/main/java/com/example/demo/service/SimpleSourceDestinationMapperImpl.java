package com.example.demo.service;

import com.example.demo.entity.SimpleSource;
import com.example.demo.model.SimpleDTODestination;

public class SimpleSourceDestinationMapperImpl implements SimpleSourceDestination{

    @Override
    public SimpleDTODestination sourceToDestination(SimpleSource simpleSource) {
        if (simpleSource == null){
            return null;
        }
        SimpleDTODestination simpleDTODestination = new SimpleDTODestination();
        simpleDTODestination.setName(simpleSource.getName());
        simpleDTODestination.setDescription(simpleSource.getDescription());
        return simpleDTODestination;

    }

    @Override
    public SimpleSource destinationToSource(SimpleDTODestination simpleDTODestination) {
        if ( simpleDTODestination == null ) {
            return null;
        }
        SimpleSource simpleSource = new SimpleSource();
        simpleSource.setName( simpleDTODestination.getName() );
        simpleSource.setDescription( simpleDTODestination.getDescription() );
        return simpleSource;
    }
}
