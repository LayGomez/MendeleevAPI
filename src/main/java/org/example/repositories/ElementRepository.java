package org.example.repositories;

import org.example.entities.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface ElementRepository extends JpaRepository<Element, Long> {

}
