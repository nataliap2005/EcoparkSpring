package com.ecopark.ecoparkspring.model;

import com.ecopark.ecoparkspring.model.enums.TipoParqueadero;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "parqueadero")
public class Parqueadero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parqueadero")
    private int idParqueadero;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Column(name = "gps_lat")
    private Double gpsLat;

    @Column(name = "gps_lng")
    private Double gpsLng;

    @NotNull(message = "El tipo de parqueadero es obligatorio")
    @Enumerated(EnumType.STRING)
    private TipoParqueadero tipo;

    @NotNull(message = "La capacidad de carros es obligatoria")
    @Column(name = "capacidad_carros")
    private Integer capacidadCarros;

    @NotNull(message = "La capacidad de motos es obligatoria")
    @Column(name = "capacidad_motos")
    private Integer capacidadMotos;

    public Parqueadero() {
    }

    public int getIdParqueadero() {
        return idParqueadero;
    }

    public void setIdParqueadero(int idParqueadero) {
        this.idParqueadero = idParqueadero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(Double gpsLat) {
        this.gpsLat = gpsLat;
    }

    public Double getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(Double gpsLng) {
        this.gpsLng = gpsLng;
    }

    public TipoParqueadero getTipo() {
        return tipo;
    }

    public void setTipo(TipoParqueadero tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidadCarros() {
        return capacidadCarros;
    }

    public void setCapacidadCarros(Integer capacidadCarros) {
        this.capacidadCarros = capacidadCarros;
    }

    public Integer getCapacidadMotos() {
        return capacidadMotos;
    }

    public void setCapacidadMotos(Integer capacidadMotos) {
        this.capacidadMotos = capacidadMotos;
    }
}