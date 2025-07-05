package com.hs.tours360.validations.auth;

import com.hs.tours360.constants.PaisConstants;
import com.hs.tours360.dto.seguridad.auth.AgenciaAuthRequest;
import com.hs.tours360.dto.seguridad.auth.EmpresaAuthRequest;
import com.hs.tours360.dto.seguridad.auth.RegistroRequest;
import com.hs.tours360.dto.seguridad.auth.UsuarioAuthRequest;
import com.hs.tours360.entities.seguridad.AgenciaEntity;
import com.hs.tours360.entities.seguridad.PersonaEntity;
import com.hs.tours360.entities.seguridad.UsuarioEntity;
import com.hs.tours360.repositories.seguridad.AgenciaRepository;
import com.hs.tours360.repositories.seguridad.EmpresaRepository;
import com.hs.tours360.repositories.seguridad.PersonaRepository;
import com.hs.tours360.repositories.seguridad.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthRegisterValidator implements ConstraintValidator<ValidRegistroAuth, RegistroRequest> {

    private final EmpresaRepository empresaRepository;
    private final AgenciaRepository agenciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public AuthRegisterValidator(EmpresaRepository empresaRepository,
                                 AgenciaRepository agenciaRepository,
                                 UsuarioRepository usuarioRepository,
                                 PersonaRepository personaRepository) {
        this.empresaRepository = empresaRepository;
        this.agenciaRepository = agenciaRepository;
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public boolean isValid(RegistroRequest request, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation(); // se hace una sola vez

        boolean valido = true;

        valido &= validarEmpresa(request.getEmpresa(), context);
        valido &= validarAgencia(request.getAgencia(), context);
        valido &= validarUsuario(request.getUsuario(), context);

        return valido;
    }

    // 🔹 Validar empresa
    private boolean validarEmpresa(EmpresaAuthRequest empresa, ConstraintValidatorContext context) {
        boolean valido = true;

        if (empresa == null) {
            context.buildConstraintViolationWithTemplate("Debe ingresar los datos de la empresa")
                    .addPropertyNode("empresa")
                    .addConstraintViolation();
            return false;
        }

        valido &= validarCampo(empresa.getRuc(), "empresa.ruc", "Debe ingresar el RUC de la empresa.", context);

        if (empresa.getRuc() != null && empresaRepository.findFirstByRuc(empresa.getRuc()).isPresent()) {
            context.buildConstraintViolationWithTemplate("El RUC de la empresa ya se encuentra registrado.")
                    .addPropertyNode("empresa.ruc")
                    .addConstraintViolation();
            valido = false;
        }

        valido &= validarCampo(empresa.getRazonSocial(), "empresa.razonSocial", "Debe ingresar la razón social.", context);
        valido &= validarCampo(empresa.getNombreComercial(), "empresa.nombreComercial", "Debe ingresar el nombre comercial.", context);
        valido &= validarCampo(empresa.getTipoEmpresa(), "empresa.tipoEmpresa", "Debe ingresar el tipo de empresa.", context);
        valido &= validarCampo(empresa.getCodigoDepartamento(), "empresa.codigoDepartamento", "Debe ingresar el departamento de la empresa.", context);
        valido &= validarCampo(empresa.getCodigoProvincia(), "empresa.codigoProvincia", "Debe ingresar la provincia de la empresa.", context);
        valido &= validarCampo(empresa.getCodigoDistrito(), "empresa.codigoDistrito", "Debe ingresar el distrito de la empresa.", context);
        valido &= validarCampo(empresa.getTelefono(), "empresa.telefono", "Debe ingresar el teléfono de la empresa.", context);
        valido &= validarCampo(empresa.getRepresentante(), "empresa.representante", "Debe ingresar el representante legal.", context);
        valido &= validarCampo(empresa.getDniRepresentante(), "empresa.dniRepresentante", "Debe ingresar el DNI del representante legal.", context);

        return valido;
    }

    // 🔹 Validar agencia
    private boolean validarAgencia(AgenciaAuthRequest agencia, ConstraintValidatorContext context) {
        boolean valido = true;

        if (agencia == null) {
            context.buildConstraintViolationWithTemplate("Debe ingresar los datos de la agencia")
                    .addPropertyNode("agencia")
                    .addConstraintViolation();
            return false;
        }

        valido = validarCampo(agencia.getNombreUrl(), "agencia.nombreUrl", "Debe ingresar el nombre URL de la agencia.", context);
        //valido &= validarCampo(agencia.getLogo(), "agencia.logoUrl", "Debe ingresar la URL del logo de la agencia.", context);
        if(agencia.getNombreUrl() != null && !agencia.getNombreUrl().isBlank()){
            AgenciaEntity agenciaEncontrada = agenciaRepository.findFirstByNombreUrl(agencia.getNombreUrl().replaceAll(" ", "")).orElse(null);
            if(agenciaEncontrada != null){
                context.buildConstraintViolationWithTemplate("El nombre de la URL de la agencia ya está siendo usado, por favor, elija otro.")
                        .addPropertyNode("agencia.nombreUrl")
                        .addConstraintViolation();
                return false;
            }
        }
        return valido;
    }

    // 🔹 Validar usuario
    private boolean validarUsuario(UsuarioAuthRequest usuario, ConstraintValidatorContext context) {
        boolean valido = true;

        if (usuario == null) {
            context.buildConstraintViolationWithTemplate("Debe ingresar los datos del usuario")
                    .addPropertyNode("usuario")
                    .addConstraintViolation();
            return false;
        }
        if(usuario.getUsuario() != null && !usuario.getUsuario().isBlank()){
            UsuarioEntity usuarioEncontrado = usuarioRepository.findFirstByUsuario(usuario.getUsuario()).orElse(null);
            if(usuarioEncontrado != null){
                context.buildConstraintViolationWithTemplate("El nombre de usuario para el inicio de sesiòn ya se encuentra en uso, por favor, elija otro.")
                        .addPropertyNode("usuario.usuario")
                        .addConstraintViolation();
                return false;
            }
        }
        if(usuario.getNumeroDocumento() != null && !usuario.getNumeroDocumento().isBlank()){
            PersonaEntity personaEncontrada = personaRepository.findFirstByNumeroDocumento(usuario.getNumeroDocumento()).orElse(null);
            if(personaEncontrada != null){
                context.buildConstraintViolationWithTemplate("El nùmero de documento del usuario ya se encuentra en uso, por favor, elija otro.")
                        .addPropertyNode("usuario.numeroDocumento")
                        .addConstraintViolation();
                return false;
            }
        }
        valido &= validarCampo(usuario.getNombres(), "usuario.nombres", "Debe ingresar sus nombres.", context);
        valido &= validarCampo(usuario.getPrimerApellido(), "usuario.primerApellido", "Debe ingresar al menos un apellido.", context);
        valido &= validarCampo(usuario.getCorreo(), "usuario.correo", "Debe ingresar un correo para notificaciones.", context);
        valido &= validarCampo(usuario.getCelularUno(), "usuario.celularUno", "Debe ingresar un número de celular.", context);
        valido &= validarCampo(usuario.getUsuario(), "usuario.usuario", "Debe ingresar su usuario de inicio de sesión.", context);
        valido &= validarCampo(usuario.getContrasenia(), "usuario.contrasenia", "Debe ingresar su contraseña.", context);
        valido &= validarCampo(usuario.getCodigoNacionalidad(), "usuario.codigoNacionalidad", "Debe ingresar su país de nacimiento.", context);

        if (usuario.getIdDocumentoIdentidad() == null || usuario.getIdDocumentoIdentidad() == 0) {
            context.buildConstraintViolationWithTemplate("Debe ingresar su tipo de documento de identidad.")
                    .addPropertyNode("usuario.idDocumentoIdentidad")
                    .addConstraintViolation();
            valido = false;
        }

        valido &= validarCampo(usuario.getNumeroDocumento(), "usuario.numeroDocumento", "Debe ingresar su número de documento.", context);
        valido &= validarCampo(usuario.getFechaNacimiento(), "usuario.fechaNacimiento", "Debe ingresar su fecha de nacimiento.", context);

        // Validación específica para nacionalidad PE
        if (PaisConstants.PERU.equalsIgnoreCase(usuario.getCodigoNacionalidad())) {
            valido &= validarCampo(usuario.getCodigoDepartamentoNacimiento(), "usuario.codigoDepartamentoNacimiento", "El departamento de nacimiento es obligatorio para Perú.", context);
            valido &= validarCampo(usuario.getCodigoProvinciaNacimiento(), "usuario.codigoProvinciaNacimiento", "La provincia de nacimiento es obligatoria para Perú.", context);
            valido &= validarCampo(usuario.getCodigoDistritoNacimiento(), "usuario.codigoDistritoNacimiento", "El distrito de nacimiento es obligatorio para Perú.", context);
        }

        return valido;
    }

    // 🔹 Utilitario genérico para validar campos tipo String
    private boolean validarCampo(String valor, String propiedad, String mensaje, ConstraintValidatorContext context) {
        if (valor == null || valor.isBlank()) {
            context.buildConstraintViolationWithTemplate(mensaje)
                    .addPropertyNode(propiedad)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    // 🔹 Utilitario genérico para objetos (como fecha)
    private boolean validarCampo(Object valor, String propiedad, String mensaje, ConstraintValidatorContext context) {
        if (valor == null) {
            context.buildConstraintViolationWithTemplate(mensaje)
                    .addPropertyNode(propiedad)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
