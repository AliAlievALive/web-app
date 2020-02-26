package ru.halal.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auto {
    private String id;
    private String name;
    private String description;
    private String image;
}
