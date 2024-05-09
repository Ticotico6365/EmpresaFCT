package org.example.empresafct;

public class Empresa {
    private String codigoEmpresa;
    private String CIF;
    private String nombre;
    private String direccion;
    private String CP;
    private String localidad;
    private String jornada;
    private String modalidad;
    private String mail;
    private String dniResponsable;
    private String nombreResponsable;
    private String apellidosResponsable;
    private String dniTutor;
    private String nombreTutor;
    private String apellidosTutor;
    private String telefonoTutor;

    // Aquí van los getters y setters

    public static boolean validarDNI(String dni) {
        // Implementa la lógica para validar el DNI
        return true; // Cambia esto por tu lógica de validación
    }

    public static boolean validarCIF(String cif) {
        // Implementa la lógica para validar el CIF
        return true; // Cambia esto por tu lógica de validación
    }
}