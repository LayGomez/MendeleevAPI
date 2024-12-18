package org.example.mappers;

import org.example.dtos.ElementRequest;
import org.example.dtos.ElementResponse;
import org.example.entities.Element;
import org.example.entities.ElementGroup;

public class ElementMapper {
    public static Element fromRequest(ElementRequest elementRequest, ElementGroup elementGroup){
         return new Element (
                 elementRequest.name(),
                 elementRequest.atomicNumber(),
                 elementRequest.symbol(),
                 elementRequest.discoveryDate(),
                 elementRequest.discoveredBy(),
                 elementGroup
         );
    }
    public static ElementResponse toResponse(Element element){
        return new ElementResponse(
                element.getId(),
                element.getName(),
                element.getAtomicNumber(),
                element.getSymbol(),
                element.getDiscoveryDate(),
                element.getDiscoveredBy(),
                ElementGroupMapper.toResponse(element.getElementGroup())
        );

    }
}
