package org.example.repositories;

import org.example.entities.Element;
import org.example.entities.MoreElementData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MoreElementDataRepository extends JpaRepository<MoreElementData, Long> {
    @Query(value = "SELECT d FROM MoreElementData d WHERE d.element.atomicNumber = :atomicNumber")
    MoreElementData findByAtomicNumber(@Param("atomicNumber") int atomicNumber);

}
