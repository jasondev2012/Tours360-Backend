package com.hs.tours360.services.carpeta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.carpeta.ImagenDestinoListaResponse;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

public interface CarpetaService {
    CustomResponse<String> registraImagenDestino(String data, List<MultipartFile> files) throws JsonProcessingException;
    CustomResponse<String> registraImagenEvento(String data, List<MultipartFile> files) throws JsonProcessingException;
    CustomResponse<List<ImagenDestinoListaResponse>> eliminarImagenDestino(String codigo, BigInteger idImagenDestino);
}
