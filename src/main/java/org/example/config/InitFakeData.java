package org.example.config;

import org.example.dtos.ElementRequest;
import org.example.entities.Element;
import org.example.entities.ElementGroup;
import org.example.entities.MoreElementData;
import org.example.repositories.ElementGroupRepository;
import org.example.repositories.ElementRepository;
import org.example.repositories.MoreElementDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.example.entities.PhysicalState.GAS;
import static org.example.entities.PhysicalState.SOLID;

@Profile("!test")
@Configuration
public class InitFakeData {
    @Autowired
    private ElementGroupRepository elementGroupRepository;
    @Autowired
    private ElementRepository elementRepository;
    @Autowired
    private MoreElementDataRepository dataRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            List<ElementGroup> elementGroups = List.of(
                    new ElementGroup(1, "Alkali Metals", "The alkali metals are highly reactive..."),
                    new ElementGroup(2, "Alkaline Earth Metals", "The alkaline earth metals are..."),
                    new ElementGroup(3, "Scandium and Yttrium Group", "Group 3 consists of transition metals..."));

            List<ElementGroup> savedGroups = elementGroupRepository.saveAll(elementGroups);

            ElementGroup elementGroup1 = savedGroups.get(0);
            ElementGroup elementGroup2 = savedGroups.get(1);
            ElementGroup elementGroup3 = savedGroups.get(2);

            List<Element> elementList = List.of(
                    new Element("Hydrogen", 1, "H", LocalDate.of(1766, 1, 1), "Henry Cavendish", elementGroup1),
                    new Element("Beryllium", 4, "Be", LocalDate.of(1798, 1, 1), "Louis-Nicolas Vauquelin", elementGroup2),
                    new Element("Scandium", 21, "Sc", LocalDate.of(1879, 1, 1), "Lars Fredrik Nilson", elementGroup3));

            List<Element> savedElements = elementRepository.saveAll(elementList);

            /*
            Element element1 = savedElements.get(0);
            Element element2 = savedElements.get(1);
            Element element3 = savedElements.get(2);

            List<MoreElementData> elementData = List.of(
                    new MoreElementData(1.008F, GAS, "Atmosphere", element1),
                    new MoreElementData(9.0122F, SOLID, "Earth's crust", element2),
                    new MoreElementData(44.9555F, SOLID, "Earth's crust", element3));

            dataRepository.saveAll(elementData);*/
        };
    }
}
