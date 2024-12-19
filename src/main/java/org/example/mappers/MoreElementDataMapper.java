package org.example.mappers;

import org.example.dtos.MoreElementDataRequest;
import org.example.dtos.MoreElementDataResponse;
import org.example.entities.Element;
import org.example.entities.MoreElementData;

public class MoreElementDataMapper {
    public static MoreElementData fromRequest(MoreElementDataRequest moreElementDataRequest, Element element){
        return new MoreElementData(
                moreElementDataRequest.molecularWeight(),
                moreElementDataRequest.physicalState(),
                moreElementDataRequest.naturalLocation(),
                element
        );
    }
    public static MoreElementDataResponse toResponse(MoreElementData moreElementData){
        return new MoreElementDataResponse(
                moreElementData.getMolecularWeight(),
                moreElementData.getPhysicalState(),
                moreElementData.getNaturalLocation(),
                ElementMapper.toResponse(moreElementData.getElement())
        );
    }
}
