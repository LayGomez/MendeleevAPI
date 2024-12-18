package org.example.Services;

import org.example.dtos.ElementRequest;
import org.example.dtos.ElementResponse;
import org.example.entities.Element;
import org.example.entities.ElementGroup;
import org.example.exceptions.ElementNotFoundException;
import org.example.exceptions.GroupNotFoundException;
import org.example.mappers.ElementMapper;
import org.example.repositories.ElementGroupRepository;
import org.example.repositories.ElementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementServices {
    private ElementRepository elementRepository;
    private ElementGroupRepository groupRepository;

    public ElementServices(ElementRepository elementRepository, ElementGroupRepository groupRepository) {
        this.elementRepository = elementRepository;
        this.groupRepository = groupRepository;
    }

    public ElementResponse addNewElement(ElementRequest elementRequest) {
        ElementGroup group = groupRepository.findById(elementRequest.groupId())
                .orElseThrow(()-> new GroupNotFoundException("Element group not found"));

        Element element = ElementMapper.fromRequest(elementRequest, group);

        Element savedElement = elementRepository.save(element);

        return ElementMapper.toResponse(savedElement);
    }

    public List<ElementResponse> getAllElements(){
        List<Element> elementList = elementRepository.findAll();
        return elementList.stream()
                .map(ElementMapper::toResponse)
                .toList();
    }

    public ElementResponse findElementById(Long id){
        Optional<Element> optionalElement = elementRepository.findById(id);
        if (optionalElement.isEmpty()){
            throw new ElementNotFoundException("Element not found");
        }
        return  ElementMapper.toResponse(optionalElement.get());
    }

    public ElementResponse updateElement(Long id, ElementRequest elementRequest){
        Element elementToUpdate = elementRepository.findById(id)
                .orElseThrow(()-> new ElementNotFoundException("Element not found"));

        ElementGroup elementGroup = elementToUpdate.getElementGroup();
        if (elementRequest.groupId() != elementGroup.getId()){
            elementGroup = groupRepository.findById(elementRequest.groupId()).get();
        }

        elementToUpdate.setName(elementRequest.name());
        elementToUpdate.setAtomicNumber(elementRequest.atomicNumber());
        elementToUpdate.setSymbol(elementRequest.symbol());
        elementToUpdate.setDiscoveryDate(elementRequest.discoveryDate());
        elementToUpdate.setDiscoveredBy(elementRequest.discoveredBy());
        elementToUpdate.setElementGroup(elementGroup);

        Element updatedElement = elementRepository.save(elementToUpdate);

        return ElementMapper.toResponse(updatedElement);
    }

    public void deleteElementById(Long id){
        Optional<Element> optionalElement = elementRepository.findById(id);
        if (optionalElement.isEmpty()){
            throw new ElementNotFoundException("Element not found");
        }
        elementRepository.deleteById(id);
    }
}
