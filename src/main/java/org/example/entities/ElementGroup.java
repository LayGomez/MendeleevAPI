package org.example.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElementGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group", nullable = false)
    private Long id;

    @Column(name = "groupNumber")
    private int groupNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "elementGroup", cascade = CascadeType.ALL)
    private List<Element> listElement;

    public ElementGroup(int groupNumber, String name, String description) {
        this.groupNumber = groupNumber;
        this.name = name;
        this.description = description;
    }

}
