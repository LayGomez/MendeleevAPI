package org.example.Services;

import org.example.dtos.ElementGroupRequest;
import org.example.dtos.ElementGroupResponse;
import org.example.entities.ElementGroup;
import org.example.exceptions.GroupNotFoundException;
import org.example.mappers.ElementGroupMapper;
import org.example.repositories.ElementGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ElementGroupResponse findGroupById(Long id){
        Optional<ElementGroup> optionalGroup = elementGroupRepository.findById(id);

        if (optionalGroup.isEmpty()){
            throw new GroupNotFoundException("Element Group not found");
        }

        return ElementGroupMapper.toResponse(optionalGroup.get());
    }

    public ElementGroupResponse updateGroup(Long id, ElementGroupRequest elementGroupRequest) {
        Optional<ElementGroup> groupToUpdate = elementGroupRepository.findById(id);
        if (groupToUpdate.isEmpty()) {
            throw new GroupNotFoundException("Element group not found");
        }
        ElementGroup elementGroup = groupToUpdate.get();
        elementGroup.setGroupNumber(elementGroupRequest.groupNumber());
        elementGroup.setName(elementGroupRequest.name());
        elementGroup.setDescription(elementGroupRequest.description());

        ElementGroup updatedGroup = elementGroupRepository.save(elementGroup);
        return ElementGroupMapper.toResponse(updatedGroup);
    }

    public void deleteGroupById(Long id){
        Optional<ElementGroup> optionalGroup= elementGroupRepository.findById(id);

        if (optionalGroup.isEmpty()){
            throw new GroupNotFoundException("Element group not found");
        }

        elementGroupRepository.deleteById(id);
    }
}
