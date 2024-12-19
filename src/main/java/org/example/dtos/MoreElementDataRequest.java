package org.example.dtos;

import org.example.entities.Element;
import org.example.entities.PhysicalState;

public record MoreElementDataRequest(
        float molecularWeight,
        PhysicalState physicalState,
        String naturalLocation,
        int atomicNumber
) {
}
