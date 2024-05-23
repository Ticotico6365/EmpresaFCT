package org.example.empresafct;

import java.util.Objects;

public class Empresa {
    private Integer id;
    private String codigo_emrpresa;
    private String cif;
    private String nombre_empresa;
    private String direccion;
    private String codigo_postal;
    private String localidad;
    private String jornada;
    private String modalidad;
    private String mail;
    private String dni_responsable_legal;
    private String dni_tutor_legal;
    private String nombre_responsable_legal;
    private String nombre_tutor_laboral;
    private String apellidos_responsable_legal;
    private String apellidos_tutor_laboral;
    private String telefono_tutor_laboral;

    public Empresa() {
    }

    public Empresa(Integer id, String codigo_emrpresa, String cif, String nombre_empresa, String direccion, String codigo_postal, String localidad, String jornada, String modalidad, String mail, String dni_responsable_legal, String dni_tutor_legal, String nombre_responsable_legal, String nombre_tutor_laboral, String apellidos_responsable_legal, String apellidos_tutor_laboral, String telefono_tutor_laboral) {
        this.id = id;
        this.codigo_emrpresa = codigo_emrpresa;
        this.cif = cif;
        this.nombre_empresa = nombre_empresa;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.localidad = localidad;
        this.jornada = jornada;
        this.modalidad = modalidad;
        this.mail = mail;
        this.dni_responsable_legal = dni_responsable_legal;
        this.dni_tutor_legal = dni_tutor_legal;
        this.nombre_responsable_legal = nombre_responsable_legal;
        this.nombre_tutor_laboral = nombre_tutor_laboral;
        this.apellidos_responsable_legal = apellidos_responsable_legal;
        this.apellidos_tutor_laboral = apellidos_tutor_laboral;
        this.telefono_tutor_laboral = telefono_tutor_laboral;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo_emrpresa() {
        return codigo_emrpresa;
    }

    public void setCodigo_emrpresa(String codigo_emrpresa) {
        this.codigo_emrpresa = codigo_emrpresa;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDni_responsable_legal() {
        return dni_responsable_legal;
    }

    public void setDni_responsable_legal(String dni_responsable_legal) {
        this.dni_responsable_legal = dni_responsable_legal;
    }

    public String getDni_tutor_legal() {
        return dni_tutor_legal;
    }

    public void setDni_tutor_legal(String dni_tutor_legal) {
        this.dni_tutor_legal = dni_tutor_legal;
    }

    public String getNombre_responsable_legal() {
        return nombre_responsable_legal;
    }

    public void setNombre_responsable_legal(String nombre_responsable_legal) {
        this.nombre_responsable_legal = nombre_responsable_legal;
    }

    public String getNombre_tutor_laboral() {
        return nombre_tutor_laboral;
    }

    public void setNombre_tutor_laboral(String nombre_tutor_laboral) {
        this.nombre_tutor_laboral = nombre_tutor_laboral;
    }

    public String getApellidos_responsable_legal() {
        return apellidos_responsable_legal;
    }

    public void setApellidos_responsable_legal(String apellidos_responsable_legal) {
        this.apellidos_responsable_legal = apellidos_responsable_legal;
    }

    public String getApellidos_tutor_laboral() {
        return apellidos_tutor_laboral;
    }

    public void setApellidos_tutor_laboral(String apellidos_tutor_laboral) {
        this.apellidos_tutor_laboral = apellidos_tutor_laboral;
    }

    public String getTelefono_tutor_laboral() {
        return telefono_tutor_laboral;
    }

    public void setTelefono_tutor_laboral(String telefono_tutor_laboral) {
        this.telefono_tutor_laboral = telefono_tutor_laboral;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id) && Objects.equals(codigo_emrpresa, empresa.codigo_emrpresa) && Objects.equals(cif, empresa.cif) && Objects.equals(nombre_empresa, empresa.nombre_empresa) && Objects.equals(direccion, empresa.direccion) && Objects.equals(codigo_postal, empresa.codigo_postal) && Objects.equals(localidad, empresa.localidad) && Objects.equals(jornada, empresa.jornada) && Objects.equals(modalidad, empresa.modalidad) && Objects.equals(mail, empresa.mail) && Objects.equals(dni_responsable_legal, empresa.dni_responsable_legal) && Objects.equals(dni_tutor_legal, empresa.dni_tutor_legal) && Objects.equals(nombre_responsable_legal, empresa.nombre_responsable_legal) && Objects.equals(nombre_tutor_laboral, empresa.nombre_tutor_laboral) && Objects.equals(apellidos_responsable_legal, empresa.apellidos_responsable_legal) && Objects.equals(apellidos_tutor_laboral, empresa.apellidos_tutor_laboral) && Objects.equals(telefono_tutor_laboral, empresa.telefono_tutor_laboral);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo_emrpresa, cif, nombre_empresa, direccion, codigo_postal, localidad, jornada, modalidad, mail, dni_responsable_legal, dni_tutor_legal, nombre_responsable_legal, nombre_tutor_laboral, apellidos_responsable_legal, apellidos_tutor_laboral, telefono_tutor_laboral);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", codigo_emrpresa='" + codigo_emrpresa + '\'' +
                ", cif='" + cif + '\'' +
                ", nombre_empresa='" + nombre_empresa + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codigo_postal='" + codigo_postal + '\'' +
                ", localidad='" + localidad + '\'' +
                ", jornada='" + jornada + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", mail='" + mail + '\'' +
                ", dni_responsable_legal='" + dni_responsable_legal + '\'' +
                ", dni_tutor_legal='" + dni_tutor_legal + '\'' +
                ", nombre_responsable_legal='" + nombre_responsable_legal + '\'' +
                ", nombre_tutor_laboral='" + nombre_tutor_laboral + '\'' +
                ", apellidos_responsable_legal='" + apellidos_responsable_legal + '\'' +
                ", apellidos_tutor_laboral='" + apellidos_tutor_laboral + '\'' +
                ", telefono_tutor_laboral='" + telefono_tutor_laboral + '\'' +
                '}';
    }
}
