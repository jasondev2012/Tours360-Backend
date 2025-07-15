package com.hs.tours360.services.carpeta;

import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.carpeta.ImagenDestinoListaResponse;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

public interface CarpetaService {
    CustomResponse<String> registraImagenDestino(Integer idDestino, List<MultipartFile> files);
    CustomResponse<List<ImagenDestinoListaResponse>> eliminarImagenDestino(String codigo, BigInteger idImagenDestino);
}
