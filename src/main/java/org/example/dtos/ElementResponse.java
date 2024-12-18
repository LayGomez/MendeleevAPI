package org.example.dtos;

import java.time.LocalDate;

public record ElementResponse(
        Long id,
        String name,
        int atomicNumber,
        String symbol,
        LocalDate discoveryDate,
        String discoveredBy,
        ElementGroupResponse elementGroup
){
}
