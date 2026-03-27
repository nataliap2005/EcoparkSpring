package com.ecopark.ecoparkspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa la tabla usuario en la base de datos.
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String nombre;
    private String email;
    private String contrasena;
    private String celular;
    private boolean discapacidad;
    private String metodoPago;

    public Usuario() {
    }

    public Usuario(String nombre, String email, String contrasena, String celular, boolean discapacidad, String metodoPago) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.celular = celular;
        this.discapacidad = discapacidad;
        this.metodoPago = metodoPago;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}