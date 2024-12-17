package org.example.controllers;

import org.example.Services.ElementGroupServices;
import org.example.dtos.ElementGroupRequest;
import org.example.dtos.ElementGroupResponse;
import org.example.entities.ElementGroup;
import org.example.repositories.ElementGroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elementGroups")
public class ElementGroupController {
    private final ElementGroupServices groupServices;
    private final ElementGroupRepository elementGroupRepository;

    public ElementGroupController(ElementGroupServices groupServices,
                                  ElementGroupRepository elementGroupRepository) {
        this.groupServices = groupServices;
        this.elementGroupRepository = elementGroupRepository;
    }

    @PostMapping
    public ResponseEntity<ElementGroupResponse> createGroup(@RequestBody ElementGroupRequest elementGroupRequest){

        ElementGroupResponse elementGroup = groupServices.addGroup(elementGroupRequest);
        return new ResponseEntity<>(elementGroup,HttpStatus.CREATED);
    }

    @GetMapping
    public List<ElementGroupResponse> getAllGroups(){
        List<ElementGroupResponse> groupList = groupServices.getAllGroups();
        return groupList;
    }

    @GetMapping("/{id}")
    public ElementGroupResponse getGroupById (@PathVariable Long id){
        return groupServices.findGroupByid(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementGroupResponse> updateElementGroup(@PathVariable Long id, @RequestBody ElementGroupRequest elementGroupRequest){
        ElementGroupResponse elementGroup = groupServices.updateGroup(id, elementGroupRequest);
        return new ResponseEntity<>(elementGroup, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteElementGroup(@PathVariable Long id){
        groupServices.deleteGroupById(id);
    }

}
