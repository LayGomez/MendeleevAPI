package org.example.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ElementRequest(
        String name,
        int atomicNumber,
        String symbol,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate discoveryDate,
        String discoveredBy,
        Long groupId
) {
}
