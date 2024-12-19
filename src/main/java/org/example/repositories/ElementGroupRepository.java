package org.example.repositories;

import org.example.dtos.ElementGroupResponse;
import org.example.entities.ElementGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ElementGroupRepository extends JpaRepository<ElementGroup, Long> {

    @Query(value = "SELECT g FROM ElementGroup g WHERE LOWER(g.name) LIKE LOWER(CONCAT ('%',:name, '%'))")
    List<ElementGroup> findLikeName(String name);

}
