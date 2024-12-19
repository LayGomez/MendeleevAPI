package org.example.config;

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
                    new ElementGroup(1, "Reactive Nonmetals", "Reactive nonmetals tend to gain electrons."),
                    new ElementGroup(1, "Reactive Nonmetals", "Reactive nonmetals tend to gain electrons."),


                    );
            List<ElementGroup> savedGroups = elementGroupRepository.saveAll(elementGroups);


            Guardian guardian1 = savedGuardians.get(0);
            Guardian guardian2 = savedGuardians.get(1);

            List<Pet> petList = List.of(
                    new Pet("Bobby", "gato", "", 5, guardian1),
                    new Pet("Mike", "gato", "", 3, guardian2));
            List<Pet> savedPets = petRepository.saveAll(petList);

            Pet pet1 = savedPets.get(0);
            Pet pet2 = savedPets.get(1);

            List<Appointment> appointmentList = List.of(
                    new Appointment(
                            LocalDate.of(2024, 12, 11),
                            LocalTime.of(10, 25),
                            "Vaccination",
                            pet1
                    ),
                    new Appointment(
                            LocalDate.of(2024, 12, 12),
                            LocalTime.of(11, 0),
                            "Follow-Up",
                            pet2
                    )
            );
            appointmentRepository.saveAll(appointmentList);
        };
    }
}
