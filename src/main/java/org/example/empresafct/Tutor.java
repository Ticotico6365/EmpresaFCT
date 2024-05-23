package org.example.empresafct;

import java.util.Objects;

public class Tutor {
    private int id;
    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String telefono;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return id == tutor.id && Objects.equals(nombre, tutor.nombre) && Objects.equals(apellidos, tutor.apellidos) && Objects.equals(correoElectronico, tutor.correoElectronico) && Objects.equals(telefono, tutor.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, correoElectronico, telefono);
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}