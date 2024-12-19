package org.example.config;

import org.example.dtos.ElementRequest;
import org.example.entities.Element;
import org.example.entities.ElementGroup;
import org.example.repositories.ElementGroupRepository;
import org.example.repositories.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
@Profile("!test")
public class InitFakeData {
    @Autowired
    ElementGroupRepository elementGroupRepository;
    @Autowired
    ElementRepository elementRepository;


    @Bean
    public CommandLineRunner initData() {
        return args -> {
            List<ElementGroup> elementGroups = List.of(
                    new ElementGroup(1,
                            "Alkali Metals",
                            "The alkali metals are a group of highly reactive metals in the periodic table. They are characterized by having one electron in their outermost shell, making them very electropositive and reactive, especially with water."),
                    new ElementGroup(2,
                            "Alkaline Earth Metals",
                            "The alkaline earth metals are a group of elements in the periodic table that are somewhat reactive, but less so than the alkali metals. They have two electrons in their outermost shell"),
                    new ElementGroup(3,
                            "Scandium and Yttrium Group",
                            "Group 3 of the periodic table consists of elements that are transition metals with three electrons in their outer shell." ));
            List<ElementGroup> savedGroups = elementGroupRepository.saveAll(elementGroups);

            ElementGroup elementGroup1 = savedGroups.get(0);
            ElementGroup elementGroup2 = savedGroups.get(1);
            ElementGroup elementGroup3 = savedGroups.get(2);

            List<Element> elementList = List.of(
                    new Element("Hydrogen", 1,"H", LocalDate.of(1766, 1, 1),"Henry Cavendish",elementGroup1),
                    new Element("Beryllium", 4, "Be", LocalDate.of(1798, 01, 01), "Louis-Nicolas Vauquelin", elementGroup2),
                    new Element("Scandium", 21, "Sc",LocalDate.of(1879, 01, 01), "Lars Fredrik Nilson", elementGroup3 ));
            elementRepository.saveAll(elementList);
        };
    }
}
