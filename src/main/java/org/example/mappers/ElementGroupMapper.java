package org.example.mappers;

import org.example.entities.ElementGroup;
import org.example.dtos.ElementGroupRequest;
import org.example.dtos.ElementGroupResponse;

public class ElementGroupMapper {

    public static ElementGroup fromRequest(ElementGroupRequest elementGroupRequest){
        return new ElementGroup(elementGroupRequest.groupNumber(), elementGroupRequest.name(),
                elementGroupRequest.description());
    }
    public static ElementGroupResponse toResponse(ElementGroup elementGroup){
        return new ElementGroupResponse(
                elementGroup.getGroupNumber(),
                elementGroup.getName(),
                elementGroup.getDescription());
    }
}