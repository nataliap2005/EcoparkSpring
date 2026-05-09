package com.ecopark.ecoparkspring.service;

import com.ecopark.ecoparkspring.model.IngresoSalida;
import com.ecopark.ecoparkspring.model.Pago;
import com.ecopark.ecoparkspring.model.Reserva;
import com.ecopark.ecoparkspring.repository.IngresoSalidaRepository;
import com.ecopark.ecoparkspring.repository.PagoRepository;
import com.ecopark.ecoparkspring.repository.ReservaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private IngresoSalidaRepository ingresoSalidaRepository;

    public Pago guardarPago(Pago pago) {

        validarOrigenPago(pago);
        validarMontos(pago);

        if (pago.getReserva() != null) {
            int idReserva = pago.getReserva().getIdReserva();

            Reserva reserva = reservaRepository.findById(idReserva)
                    .orElseThrow(() -> new RuntimeException(
                            "No se puede crear el pago. La reserva con ID "
                            + idReserva + " no existe"
                    ));

            pago.setReserva(reserva);
        }

        if (pago.getMovimiento() != null) {
            int idMovimiento = pago.getMovimiento().getIdMovimiento();

            IngresoSalida movimiento = ingresoSalidaRepository.findById(idMovimiento)
                    .orElseThrow(() -> new RuntimeException(
                            "No se puede crear el pago. El movimiento con ID "
                            + idMovimiento + " no existe"
                    ));

            pago.setMovimiento(movimiento);
        }

        return pagoRepository.save(pago);
    }

    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> buscarPorId(int id) {
        return pagoRepository.findById(id);
    }

    public Pago actualizarPago(int id, Pago pagoActualizado) {

        Optional<Pago> pagoExistente = pagoRepository.findById(id);

        if (pagoExistente.isPresent()) {

            validarOrigenPago(pagoActualizado);
            validarMontos(pagoActualizado);

            Pago pago = pagoExistente.get();

            pago.setReferencia(pagoActualizado.getReferencia());
            pago.setSubtotal(pagoActualizado.getSubtotal());
            pago.setIva(pagoActualizado.getIva());
            pago.setMontoTotal(pagoActualizado.getMontoTotal());
            pago.setMetodoPago(pagoActualizado.getMetodoPago());
            pago.setFechaPago(pagoActualizado.getFechaPago());

            pago.setReserva(null);
            pago.setMovimiento(null);

            if (pagoActualizado.getReserva() != null) {
                int idReserva = pagoActualizado.getReserva().getIdReserva();

                Reserva reserva = reservaRepository.findById(idReserva)
                        .orElseThrow(() -> new RuntimeException(
                                "No se puede actualizar el pago. La reserva con ID "
                                + idReserva + " no existe"
                        ));

                pago.setReserva(reserva);
            }

            if (pagoActualizado.getMovimiento() != null) {
                int idMovimiento = pagoActualizado.getMovimiento().getIdMovimiento();

                IngresoSalida movimiento = ingresoSalidaRepository.findById(idMovimiento)
                        .orElseThrow(() -> new RuntimeException(
                                "No se puede actualizar el pago. El movimiento con ID "
                                + idMovimiento + " no existe"
                        ));

                pago.setMovimiento(movimiento);
            }

            return pagoRepository.save(pago);
        }

        return null;
    }

    public boolean eliminarPago(int id) {

        Optional<Pago> pagoExistente = pagoRepository.findById(id);

        if (pagoExistente.isPresent()) {
            pagoRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private void validarOrigenPago(Pago pago) {

        if (pago.getReserva() == null && pago.getMovimiento() == null) {
            throw new RuntimeException(
                    "Debe asociar el pago a una reserva o a un movimiento de ingreso/salida"
            );
        }
    }

    private void validarMontos(Pago pago) {

        if (pago.getSubtotal() != null && pago.getSubtotal().signum() < 0) {
            throw new RuntimeException("El subtotal no puede ser negativo");
        }

        if (pago.getIva() != null && pago.getIva().signum() < 0) {
            throw new RuntimeException("El IVA no puede ser negativo");
        }

        if (pago.getMontoTotal() != null && pago.getMontoTotal().signum() < 0) {
            throw new RuntimeException("El monto total no puede ser negativo");
        }
    }
}