package ru.halal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private String description;
    private String image;

    public String getDecoding(String s) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("ISO-8859-1");
        return new String(bytes, "UTF-8");
    }
}
