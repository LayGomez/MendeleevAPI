package org.example.repositories;

import org.example.entities.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ElementRepository extends JpaRepository<Element, Long> {

    @Query("SELECT e FROM Element e WHERE e.atomicNumber = :atomicNumber")
    Optional<Element> findByAtomicNumber(@Param("atomicNumber") int atomicNumber);

}
