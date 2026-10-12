package mx.edu.utez.cotizadores.service;

import mx.edu.utez.cotizadores.controller.dtos.RequestHospedajeDTO;
import mx.edu.utez.cotizadores.controller.dtos.ResponseHospedajeDTO;
import mx.edu.utez.cotizadores.exception.customException.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public ResponseHospedajeDTO cotizar(RequestHospedajeDTO payload) {
        String habitacion = payload.getTipoHabitacion();
        int noches = payload.getNumeroNoches();
        int huespedes = payload.getNumeroHuespedes();

        double costoNoche = 0;
        int capacidad = 0;
        switch (habitacion) {
            case "INDIVIDUAL":
                costoNoche = 700;
                capacidad = 1;
                break;
            case "DOBLE":
                costoNoche = 1100;
                capacidad = 2;
                break;
            case "SUITE":
                costoNoche = 1800;
                capacidad = 4;
                break;
        }

        if (noches > 30) {
            throw new CustomBadRequestException("No se aceptan reservaciones de mas de 30 noches");
        }
        if (huespedes > capacidad) {
            throw new CustomBadRequestException("La habitacion " + habitacion
                    + " solo admite maximo " + capacidad + " persona(s)");
        }

        double costoHospedaje = costoNoche * noches;

        double ajusteTemporada = 0;
        if (payload.getTemporada().equals("BAJA")) {
            ajusteTemporada = -(costoHospedaje * 0.10);
        } else if (payload.getTemporada().equals("ALTA")) {
            ajusteTemporada = costoHospedaje * 0.25;
        }

        double desayuno = 0;
        if (payload.getIncluyeDesayuno()) {
            desayuno = huespedes * noches * 150;
        }

        double estacionamiento = 0;
        if (payload.getIncluyeEstacionamiento()) {
            estacionamiento = noches * 100;
        }

        double descuentoEstancia = 0;
        if (noches >= 7) {
            descuentoEstancia = costoHospedaje * 0.08;
        }

        double subtotal = costoHospedaje + ajusteTemporada - descuentoEstancia + desayuno + estacionamiento;
        double impuesto = subtotal * 0.04;

        double total = subtotal + impuesto;

        return new ResponseHospedajeDTO(
                payload.getNombreHuesped(),
                habitacion,
                noches,
                redondear(costoHospedaje),
                redondear(ajusteTemporada),
                redondear(descuentoEstancia),
                redondear(desayuno),
                redondear(estacionamiento),
                redondear(subtotal),
                redondear(impuesto),
                redondear(total)
        );
    }

    private double redondear(double numero) {
        return Math.round(numero * 100.0) / 100.0;
    }
}
