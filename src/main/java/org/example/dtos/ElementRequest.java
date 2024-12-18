package org.example.dtos;
import java.time.LocalDate;

public record ElementRequest(
        String name,
        int atomicNumber,
        String symbol,
        LocalDate discoveryDate,
        String discoveredBy,
        Long groupId
) {
}
