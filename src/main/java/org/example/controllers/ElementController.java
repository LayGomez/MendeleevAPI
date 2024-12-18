package org.example.controllers;

import org.example.Services.ElementServices;
import org.example.dtos.ElementRequest;
import org.example.dtos.ElementResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elements")
public class ElementController {
    private final ElementServices elementServices;

    public ElementController(ElementServices elementServices) {
        this.elementServices = elementServices;
    }

    @PostMapping
    public ResponseEntity<ElementResponse> addNewElement(@RequestBody ElementRequest elementRequest){
        ElementResponse element = elementServices.addNewElement(elementRequest);
        return new ResponseEntity<>(element, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ElementResponse> getAllElements(){
        List<ElementResponse> elementList = elementServices.getAllElements();
        return elementList;
    }

    @GetMapping("/{id}")
    public ElementResponse getElementById(@PathVariable Long id){
        return elementServices.findElementById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementResponse> updateElement(@PathVariable Long id, @RequestBody ElementRequest elementRequest){
        ElementResponse element = elementServices.updateElement(id, elementRequest);
        return new ResponseEntity<>(element, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteElement(@PathVariable Long id){
        elementServices.deleteElementById(id);
    }
}
