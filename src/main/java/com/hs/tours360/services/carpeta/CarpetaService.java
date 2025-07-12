package com.hs.tours360.services.carpeta;

import com.hs.tours360.dto.CustomResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarpetaService {
    CustomResponse<String> registrarFileDestino(Integer idDestino, List<MultipartFile> files);
}
