package com.hs.tours360.repositories.gestion;

import com.hs.tours360.entities.gestion.DestinoEntity;
import com.hs.tours360.projection.gestion.DestinoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinoRepository extends JpaRepository<DestinoEntity, Integer> {
    List<DestinoEntity> findAllByAgenciaId(Integer idAgencia);

    @Query(value = "SELECT id, titulo, precio_venta_soles, precio_venta_dolares, eventos_abiertos, eventos_cerrados, imagen_referencia, activo, total FROM gestion.fn_destinos_paginado(:idAgencia, :filtro, :offset, :limit, :order, :orderDirection)", nativeQuery = true)
    List<DestinoProjection> listarPaginado(
            @Param("idAgencia") Integer idAgencia,
            @Param("filtro") String filtro,
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("order") String order,
            @Param("orderDirection") String orderDirection
    );
}
