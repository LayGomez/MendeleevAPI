package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "athomicNumber")
    private int atomicNumber;

    @Column(name = "symbol")
    private String symbol;

    @Column(name= "discoveryDate")
    private LocalDate discoveryDate;

    @Column(name = "discoveredBy")
    private String discoveredBy;

    @ManyToOne
    @JoinColumn(name = "elementGroup")
    private ElementGroup elementGroup;


    public Element(String name, int atomicNumber, String symbol, String discoveredBy, LocalDate discoveryDate, ElementGroup elementGroup) {
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.discoveredBy = discoveredBy;
        this.discoveryDate = discoveryDate;
        this.elementGroup = elementGroup;
    }
}
