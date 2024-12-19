package org.example.controllers;

import org.example.Services.MoreElementDataServices;
import org.example.dtos.ElementRequest;
import org.example.dtos.ElementResponse;
import org.example.dtos.MoreElementDataRequest;
import org.example.dtos.MoreElementDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moreElementData")
public class MoreElementDataController {
    private final MoreElementDataServices moreElementDataServices;

    public MoreElementDataController(MoreElementDataServices moreElementDataServices) {
        this.moreElementDataServices = moreElementDataServices;
    }

    @PostMapping
    public ResponseEntity<MoreElementDataResponse> addNewElementData(@RequestBody MoreElementDataRequest elementDataRequest){
        MoreElementDataResponse elementData = moreElementDataServices.addNewElementData(elementDataRequest);
        return new ResponseEntity<>(elementData, HttpStatus.CREATED);
    }

    @GetMapping
    public List<MoreElementDataResponse> getAllElementData(){
        List<MoreElementDataResponse> elementDataList = moreElementDataServices.getAllElementData();
        return elementDataList;
    }

    @GetMapping("/atomicNumber")
    public MoreElementDataResponse getByAthomicNumber(@RequestParam int atomicNumber) {
        return moreElementDataServices.findByAthomicNumber(atomicNumber);
    }

    @GetMapping("/{id}")
    public MoreElementDataResponse getElementDataById(@PathVariable Long id){
        return moreElementDataServices.findElementDataById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoreElementDataResponse> updateElementData(@PathVariable Long id, @RequestBody MoreElementDataRequest elementDataRequest){
        MoreElementDataResponse elementData = moreElementDataServices.updateElementData(id, elementDataRequest);
        return new ResponseEntity<>(elementData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteElementData(@PathVariable Long id){
        moreElementDataServices.deleteElementDataById(id);
    }

}
