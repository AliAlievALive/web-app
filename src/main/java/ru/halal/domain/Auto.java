package ru.halal.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auto {
    private String id;
    private String name;
    private String description;
    private String image;
}
