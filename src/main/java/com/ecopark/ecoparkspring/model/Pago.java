package com.ecopark.ecoparkspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;

    @NotBlank(message = "La referencia es obligatoria")
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_movimiento")
    private IngresoSalida movimiento;

    @NotNull(message = "El subtotal es obligatorio")
    private BigDecimal subtotal;

    @NotNull(message = "El IVA es obligatorio")
    private BigDecimal iva;

    @NotNull(message = "El monto total es obligatorio")
    @Column(name = "monto_total")
    private BigDecimal montoTotal;

    @NotBlank(message = "El método de pago es obligatorio")
    @Column(name = "metodo_pago")
    private String metodoPago;

    @NotNull(message = "La fecha de pago es obligatoria")
    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    public Pago() {
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public IngresoSalida getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(IngresoSalida movimiento) {
        this.movimiento = movimiento;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }
}