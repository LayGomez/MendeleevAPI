package org.example.dtos;

public record ElementGroupResponse(
        Long id,
        int groupNumber,
        String name,
        String description
) {
}
