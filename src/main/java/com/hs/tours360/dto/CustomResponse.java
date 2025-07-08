package com.hs.tours360.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> {
    private boolean success = true;
    private Short code = 200;
    private String message = "Operación exitosa";
    private T data;
}