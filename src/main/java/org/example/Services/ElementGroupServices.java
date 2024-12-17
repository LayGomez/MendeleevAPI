package org.example.Services;

import org.example.dtos.ElementGroupRequest;
import org.example.dtos.ElementGroupResponse;
import org.example.entities.ElementGroup;
import org.example.mappers.ElementGroupMapper;
import org.example.repositories.ElementGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementGroupServices {
    private ElementGroupRepository elementGroupRepository;

    public ElementGroupServices(ElementGroupRepository elementGroupRepository) {
        this.elementGroupRepository = elementGroupRepository;
    }

    public ElementGroupResponse addGroup(ElementGroupRequest elementGroupRequest) {
        ElementGroup elementGroup = ElementGroupMapper.fromRequest(elementGroupRequest);
        ElementGroup savedElementGroup = elementGroupRepository.save(elementGroup);
        return ElementGroupMapper.toResponse(savedElementGroup);
    }

    public List<ElementGroupResponse> getAllGroups() {
        List<ElementGroup> groupList = elementGroupRepository.findAll();
        return groupList.stream()
                .map(elementGroup -> ElementGroupMapper.toResponse(elementGroup))
                .toList();
    }
}
