package org.example.Services;

import org.example.dtos.ElementGroupRequest;
import org.example.dtos.ElementGroupResponse;
import org.example.entities.ElementGroup;
import org.example.repositories.ElementGroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ElementGroupServicesTest {
    @Mock
    ElementGroupRepository elementGroupRepository;

    @Test
    void addGroup() {
        // GIVEN
        ElementGroupRequest elementGroupRequest = new ElementGroupRequest(1, "reactive Nonmetals", "Reactive nonmetals tend to gain electrons.");
        ElementGroup savedGroup = new ElementGroup(1, "reactive Nonmetals", "Reactive nonmetals tend to gain electrons.");
        savedGroup.setId(1L);

        ElementGroupServices elementGroupServices = new ElementGroupServices(elementGroupRepository);

        ElementGroupResponse expectedResponse = new ElementGroupResponse(1L, 1, "reactive Nonmetals", "Reactive nonmetals tend to gain electrons.");
        Mockito.when(elementGroupRepository.save(any(ElementGroup.class))).thenReturn(savedGroup);

        // WHEN
        ElementGroupResponse actualResponse = elementGroupServices.addGroup(elementGroupRequest);

        // THEN
        verify(elementGroupRepository).save(any(ElementGroup.class));
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void listAllGroups() {
        // GIVEN
        List<ElementGroup> groupsFromRepository = List.of(
                new ElementGroup(1, "reactive Nonmetals", "Reactive nonmetals tend to gain electrons."),
                new ElementGroup(2, "noble Gases", "Noble gases are inert.")
        );
        groupsFromRepository.get(0).setId(1L);
        groupsFromRepository.get(1).setId(2L);

        Mockito.when(elementGroupRepository.findAll()).thenReturn(groupsFromRepository);

        List<ElementGroupResponse> expectedResponse = List.of(
                new ElementGroupResponse(1L, 1, "reactive Nonmetals", "Reactive nonmetals tend to gain electrons."),
                new ElementGroupResponse(2L, 2, "noble Gases", "Noble gases are inert.")
        );

        ElementGroupServices elementGroupServices = new ElementGroupServices(elementGroupRepository);

        // WHEN
        List<ElementGroupResponse> actualResponse = elementGroupServices.getAllGroups();

        // THEN
        verify(elementGroupRepository).findAll();
        assertEquals(expectedResponse, actualResponse);
    }


    @Test
    void findGroupById() {

        // GIVEN
        ElementGroup groupFromRepository = new ElementGroup(1, "reactive Nonmetals", "Reactive nonmetals tend to gain electrons.");
        groupFromRepository.setId(1L);
        Mockito.when(elementGroupRepository.findById(1L)).thenReturn(Optional.of(groupFromRepository));

        ElementGroupResponse expectedResponse = new ElementGroupResponse(1L, 1, "reactive Nonmetals", "Reactive nonmetals tend to gain electrons.");

        ElementGroupServices elementGroupServices = new ElementGroupServices(elementGroupRepository);

        ElementGroupResponse actualResponse = elementGroupServices.findGroupById(1L);

        verify(elementGroupRepository).findById(1L);
        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void updateGroup() {
        ElementGroupRequest updateRequest = new ElementGroupRequest(1, "Updated Name", "Updated Description");
        ElementGroup existingGroup = new ElementGroup(1, "reactive Nonmetals", "Reactive nonmetals tend to gain electrons.");
        existingGroup.setId(1L);

        ElementGroup updatedGroup = new ElementGroup(1, "Updated Name", "Updated Description");
        updatedGroup.setId(1L);

        Mockito.when(elementGroupRepository.findById(1L)).thenReturn(Optional.of(existingGroup));
        Mockito.when(elementGroupRepository.save(any(ElementGroup.class))).thenReturn(updatedGroup);

        ElementGroupResponse expectedResponse = new ElementGroupResponse(1L, 1, "Updated Name", "Updated Description");

        ElementGroupServices elementGroupServices = new ElementGroupServices(elementGroupRepository);
        ElementGroupResponse actualResponse = elementGroupServices.updateGroup(1L, updateRequest);

        verify(elementGroupRepository).findById(1L);
        verify(elementGroupRepository).save(any(ElementGroup.class));
        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void deleteGroupById() {
        Long groupIdToDelete = 1L;
        ElementGroup group = new ElementGroup(1, "reactive Nonmetals", "Reactive nonmetals tend to gain electrons.");
        group.setId(groupIdToDelete);

        Mockito.when(elementGroupRepository.findById(groupIdToDelete)).thenReturn(Optional.of(group));
        Mockito.doNothing().when(elementGroupRepository).deleteById(groupIdToDelete);

        ElementGroupServices elementGroupServices = new ElementGroupServices(elementGroupRepository);

        // WHEN
        elementGroupServices.deleteGroupById(groupIdToDelete);

        // THEN
        verify(elementGroupRepository).deleteById(groupIdToDelete);
    }
}