package org.example.repositories;

import org.example.entities.Element;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ElementRepository extends JpaRepository<Element, Long> {

}
