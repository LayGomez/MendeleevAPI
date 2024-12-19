package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MoreElementData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private float molecularWeight;

    @Enumerated(EnumType.STRING)
    private PhysicalState physicalState;

    private String naturalLocation;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "element_id", referencedColumnName = "id")
    Element element;

    public MoreElementData(float molecularWeight, PhysicalState physicalState, String naturalLocation, Element element) {
        this.molecularWeight = molecularWeight;
        this.physicalState = physicalState;
        this.naturalLocation = naturalLocation;
        this.naturalLocation = naturalLocation;
        this.element = element;

    }
}

