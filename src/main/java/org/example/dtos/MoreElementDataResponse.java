package org.example.dtos;

import org.example.entities.PhysicalState;

public record MoreElementDataResponse(
        float molecularWeight,
        PhysicalState physicalState,
        String naturalLocation,
        ElementResponse element
) {
}