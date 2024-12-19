package org.example.Services;

import org.example.dtos.MoreElementDataRequest;
import org.example.dtos.MoreElementDataResponse;
import org.example.entities.Element;
import org.example.entities.ElementGroup;
import org.example.entities.MoreElementData;
import org.example.exceptions.ElementDataNotFoundException;
import org.example.exceptions.ElementNotFoundException;
import org.example.mappers.ElementMapper;
import org.example.mappers.MoreElementDataMapper;
import org.example.repositories.ElementRepository;
import org.example.repositories.MoreElementDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoreElementDataServices {
    private MoreElementDataRepository moreElementDataRepository;
    private ElementRepository elementRepository;

    public MoreElementDataServices(MoreElementDataRepository moreElementDataRepository, ElementRepository elementRepository) {
        this.moreElementDataRepository = moreElementDataRepository;
        this.elementRepository = elementRepository;
    }

    public MoreElementDataResponse addNewElementData(MoreElementDataRequest elementDataRequest) {
        Element element = elementRepository.findByAtomicNumber(elementDataRequest.atomicNumber())
                .orElseThrow(()-> new ElementNotFoundException("Element not found"));

        MoreElementData elementData = MoreElementDataMapper.fromRequest(elementDataRequest, element);

        MoreElementData savedElement = moreElementDataRepository.save(elementData);

        return MoreElementDataMapper.toResponse(savedElement);
    }

    public List<MoreElementDataResponse> getAllElementData() {
        List<MoreElementData> elementDataList = moreElementDataRepository.findAll();
        return elementDataList.stream()
                .map(MoreElementDataMapper::toResponse)
                .toList();
    }

    public MoreElementDataResponse findElementDataById(Long id) {
        Optional<MoreElementData> optionalElementData = moreElementDataRepository.findById(id);
        if (optionalElementData.isEmpty()){
            throw new ElementDataNotFoundException("Element not found");
        }
        return  MoreElementDataMapper.toResponse(optionalElementData.get());
    }

    public MoreElementDataResponse updateElementData(Long id, MoreElementDataRequest elementDataRequest) {
        MoreElementData elementDataToUpdate = moreElementDataRepository.findById(id)
                .orElseThrow(()-> new ElementDataNotFoundException("Element Data not found"));

        Element element = elementDataToUpdate.getElement();
        if (elementDataRequest.atomicNumber() != element.getId()){
            element = elementRepository.findByAtomicNumber(elementDataRequest.atomicNumber()).get();
        }

        elementDataToUpdate.setMolecularWeight(elementDataRequest.molecularWeight());
        elementDataToUpdate.setPhysicalState(elementDataRequest.physicalState());
        elementDataToUpdate.setNaturalLocation(elementDataRequest.naturalLocation());
        elementDataToUpdate.setElement(element);

        MoreElementData updatedElementData = moreElementDataRepository.save(elementDataToUpdate);

        return MoreElementDataMapper.toResponse(updatedElementData);
    }

    public void deleteElementDataById(Long id) {
        Optional<MoreElementData> optionalMoreElementData = moreElementDataRepository.findById(id);
        if (optionalMoreElementData.isEmpty()){
            throw new ElementDataNotFoundException("Element Data not found");
        }
        moreElementDataRepository.deleteById(id);
    }

    public MoreElementDataResponse findByAthomicNumber(int atomicNumber) {
        MoreElementData elementData = moreElementDataRepository.findByAtomicNumber(atomicNumber);
        return MoreElementDataMapper.toResponse(elementData);
    }
}
