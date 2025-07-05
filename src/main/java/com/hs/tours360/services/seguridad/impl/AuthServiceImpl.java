package com.hs.tours360.services.seguridad.impl;

import com.hs.tours360.constants.RolConstans;
import com.hs.tours360.dto.CustomResponse;
import com.hs.tours360.dto.seguridad.auth.AuthRequest;
import com.hs.tours360.dto.seguridad.auth.AuthResponse;
import com.hs.tours360.dto.seguridad.auth.RegistroRequest;
import com.hs.tours360.entities.catalogo.*;
import com.hs.tours360.entities.seguridad.*;
import com.hs.tours360.repositories.seguridad.AgenciaRepository;
import com.hs.tours360.repositories.seguridad.EmpresaRepository;
import com.hs.tours360.repositories.seguridad.PersonaRepository;
import com.hs.tours360.repositories.seguridad.UsuarioRepository;
import com.hs.tours360.services.seguridad.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepo;
    private final JwtServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;
    private final PersonaRepository personaRepo;
    private final EmpresaRepository empresaRepo;
    private final AgenciaRepository agenciaRepo;

    public AuthServiceImpl(UsuarioRepository repo,
                           JwtServiceImpl jwtService,
                           PasswordEncoder passwordEncoder,
                           PersonaRepository personaRepo,
                           EmpresaRepository empresaRepo,
                           AgenciaRepository agenciaRepo) {
        this.usuarioRepo = repo;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.personaRepo = personaRepo;
        this.empresaRepo = empresaRepo;
        this.agenciaRepo = agenciaRepo;
    }

    @Override
    public CustomResponse<AuthResponse> authenticate(AuthRequest request) {
        CustomResponse<AuthResponse> response = new CustomResponse<>();

        UsuarioEntity usuario = usuarioRepo.findFirstByUsuario(request.usuario).orElse(null);
        if (usuario == null) {
            response.setSuccess(false);
            response.setMessage("Usuario no encontrado");
            return response;
        }
        if (!passwordEncoder.matches(request.contrasenia, usuario.getContrasenia())) {
            response.setSuccess(false);
            response.setMessage("Usuario y/o contraseña inválida");
            return response;
        }

        AuthResponse data = jwtService.generateToken(usuario);
        response.setSuccess(true);
        response.setData(data);
        response.setMessage("Autenticación exitosa");
        return response;
    }

    @Override
    public CustomResponse<Integer> registrar(RegistroRequest req) {

        CustomResponse<Integer> response = new CustomResponse<>();
        try {
            LocalDateTime fechaAlta = LocalDateTime.now();
            short estatusId = 1;
            int usuarioAlta = 1;

            EstatusEntity estatus = new EstatusEntity();
            estatus.setId(estatusId);

            EmpresaEntity empresa = mapToEmpresaEntity(req, estatus, fechaAlta, usuarioAlta);
            EmpresaEntity empresaGuardada = empresaRepo.save(empresa);

            AgenciaEntity agencia = mapToAgenciaEntity(req, empresaGuardada, estatus, fechaAlta, usuarioAlta);
            AgenciaEntity agenciaGuardada = agenciaRepo.save(agencia);

            PersonaEntity persona = mapToPersonaEntity(req, estatus, fechaAlta, usuarioAlta);
            PersonaEntity personaGuardada = personaRepo.save(persona);

            UsuarioEntity usuario = mapToUsuarioEntity(req, personaGuardada, agenciaGuardada, estatus, fechaAlta, usuarioAlta);
            usuarioRepo.save(usuario);


            response.setSuccess(true);
            response.setCode((short)200);
            response.setData(agenciaGuardada.getId());
            response.setMessage("Datos guardados correctamente");
        } catch(Exception ex){
            response.setSuccess(false);
            response.setCode((short) 500);
            response.setMessage("Ocurrió un error al registrar los datos: " + ex.getMessage());
        }
        return response;

    }
    private EmpresaEntity mapToEmpresaEntity(RegistroRequest req, EstatusEntity estatus, LocalDateTime fechaAlta, int idUsuarioAlta) {
        EmpresaEntity empresa = new EmpresaEntity();

        DepartamentoEntity departamento = new DepartamentoEntity();
        departamento.setCodigo(req.getEmpresa().getCodigoDepartamento());

        ProvinciaEntity provincia = new ProvinciaEntity();
        provincia.setCodigo(req.getEmpresa().getCodigoProvincia());

        DistritoEntity distrito = new DistritoEntity();
        distrito.setCodigo(req.getEmpresa().getCodigoDistrito());

        empresa.setDepartamento(departamento);
        empresa.setProvincia(provincia);
        empresa.setDistrito(distrito);

        empresa.setRuc(req.getEmpresa().getRuc());
        empresa.setRazonSocial(req.getEmpresa().getRazonSocial());
        empresa.setNombreComercial(req.getEmpresa().getNombreComercial());
        empresa.setTipoEmpresa(req.getEmpresa().getTipoEmpresa());
        empresa.setRepresentante(req.getEmpresa().getRepresentante());
        empresa.setDniRepresentante(req.getEmpresa().getDniRepresentante());
        empresa.setDireccion(req.getEmpresa().getDireccion());
        empresa.setTelefono(req.getEmpresa().getTelefono());
        empresa.setEmail(req.getEmpresa().getEmail());
        empresa.setPaginaWeb(req.getEmpresa().getPaginaWeb());
        empresa.setLogoUrl(req.getEmpresa().getLogoUrl());

        empresa.setEstatus(estatus);
        empresa.setFechaAlta(fechaAlta);
        empresa.setIdUsuarioAlta(idUsuarioAlta);

        return empresa;
    }
    private AgenciaEntity mapToAgenciaEntity(RegistroRequest req, EmpresaEntity empresa, EstatusEntity estatus, LocalDateTime fechaAlta, int idUsuarioAlta) {
        AgenciaEntity agencia = new AgenciaEntity();
        agencia.setNombreUrl(req.getAgencia().getNombreUrl());
        agencia.setEmpresa(empresa);
        agencia.setEstatus(estatus);
        agencia.setFechaAlta(fechaAlta);
        agencia.setIdUsuarioAlta(idUsuarioAlta);
        return agencia;
    }
    private PersonaEntity mapToPersonaEntity(RegistroRequest req, EstatusEntity estatus, LocalDateTime fechaAlta, int idUsuarioAlta) {
        PersonaEntity persona = new PersonaEntity();

        DepartamentoEntity departamento = new DepartamentoEntity();

        ProvinciaEntity provincia = new ProvinciaEntity();
        provincia.setCodigo(req.getUsuario().getCodigoProvinciaNacimiento());

        DistritoEntity distrito = new DistritoEntity();
        distrito.setCodigo(req.getUsuario().getCodigoDistritoNacimiento());
        if(req.getUsuario().getCodigoDepartamentoNacimiento() != null && req.getUsuario().getCodigoDepartamentoNacimiento().isEmpty()){
            departamento.setCodigo(req.getUsuario().getCodigoDepartamentoNacimiento());
            persona.setDepartamentoNacimiento(departamento);
        }
        if(req.getUsuario().getCodigoProvinciaNacimiento() != null && !req.getUsuario().getCodigoProvinciaNacimiento().isEmpty()){
            provincia.setCodigo(req.getUsuario().getCodigoProvinciaNacimiento());
            persona.setProvinciaNacimiento(provincia);
        }
        if(req.getUsuario().getCodigoDistritoNacimiento() != null && req.getUsuario().getCodigoDistritoNacimiento().isEmpty()){
            distrito.setCodigo(req.getUsuario().getCodigoDistritoNacimiento());
            persona.setDistritoNacimiento(distrito);
        }

        PaisEntity pais = new PaisEntity();
        pais.setCodigo(req.getUsuario().getCodigoNacionalidad());

        DocumentoIdentidadEntity documento = new DocumentoIdentidadEntity();
        documento.setId(req.getUsuario().getIdDocumentoIdentidad());

        persona.setNombres(req.getUsuario().getNombres());
        persona.setPrimerApellido(req.getUsuario().getPrimerApellido());
        persona.setSegundoApellido(req.getUsuario().getSegundoApellido());
        persona.setCorreo(req.getUsuario().getCorreo());
        persona.setCelularUno(req.getUsuario().getCelularUno());
        persona.setCelularDos(req.getUsuario().getCelularDos());
        persona.setDocumentoIdentidad(documento);
        persona.setNumeroDocumento(req.getUsuario().getNumeroDocumento());
        persona.setFechaNacimiento(req.getUsuario().getFechaNacimiento());
        persona.setNacionalidad(pais);
        persona.setFechaAlta(fechaAlta);
        persona.setIdUsuarioAlta(idUsuarioAlta);
        persona.setEstatus(estatus);

        return persona;
    }
    private UsuarioEntity mapToUsuarioEntity(RegistroRequest req, PersonaEntity persona, AgenciaEntity agencia, EstatusEntity estatus, LocalDateTime fechaAlta, int idUsuarioAlta) {
        UsuarioEntity usuario = new UsuarioEntity();

        RolEntity rol = new RolEntity();
        rol.setId(RolConstans.AGENCIA);

        usuario.setPersona(persona);
        usuario.setAgencia(agencia);
        usuario.setRol(rol);
        usuario.setUsuario(req.getUsuario().getUsuario());
        usuario.setContrasenia(passwordEncoder.encode(req.getUsuario().getContrasenia()));
        usuario.setFechaAlta(fechaAlta);
        usuario.setIdUsuarioAlta(idUsuarioAlta);
        usuario.setEstatus(estatus);

        return usuario;
    }

}
