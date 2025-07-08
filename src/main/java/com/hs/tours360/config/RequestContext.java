package com.hs.tours360.config;

public class RequestContext {

    private static final ThreadLocal<Integer> agenciaIdHolder = new ThreadLocal<>();
    private static final ThreadLocal<Integer> usuarioIdHolder = new ThreadLocal<>();

    public static void setAgenciaId(Integer idAgencia) {
        agenciaIdHolder.set(idAgencia);
    }

    public static void setUsuarioId(Integer idUsuario) {
        usuarioIdHolder.set(idUsuario);
    }

    public static Integer getAgenciaId() {
        return agenciaIdHolder.get();
    }

    public static Integer getUsuarioId() {
        return usuarioIdHolder.get();
    }

    public static void clear() {
        agenciaIdHolder.remove();
        usuarioIdHolder.remove();
    }
}

