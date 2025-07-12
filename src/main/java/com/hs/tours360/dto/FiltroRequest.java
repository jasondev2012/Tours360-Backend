package com.hs.tours360.dto;

import lombok.Data;

@Data
public class FiltroRequest {
    private Integer pageIndex;
    private Integer pageSize;
    private String filtro;
    private String order;
    private String orderDirection;

    public int getOffset() {
        if (pageIndex == null || pageSize == null) {
            return 0; // o puedes lanzar una excepción si lo prefieres
        }
        return pageIndex * pageSize;
    }
    public int getLimit() {
        return pageSize;
    }
}
