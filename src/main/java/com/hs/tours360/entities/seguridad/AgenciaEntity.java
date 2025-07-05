package com.hs.tours360.entities.seguridad;

import com.hs.tours360.entities.AuditoriaEstatusEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "agencia", schema = "seguridad")
public class AgenciaEntity extends AuditoriaEstatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", referencedColumnName = "id")
    private EmpresaEntity empresa;

    private String nombreUrl;
    private String logo;

}
