package org.example.controllers;

import org.example.Services.ElementGroupServices;
import org.example.dtos.ElementGroupRequest;
import org.example.dtos.ElementGroupResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elementGroups")
public class ElementGroupController {
    private final ElementGroupServices groupServices;

    public ElementGroupController(ElementGroupServices groupServices) {
        this.groupServices = groupServices;
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
}