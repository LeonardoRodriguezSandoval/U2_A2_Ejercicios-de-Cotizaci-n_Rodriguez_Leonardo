package mx.edu.utez.cotizadores.service;

import mx.edu.utez.cotizadores.controller.dtos.RequestRentaDTO;
import mx.edu.utez.cotizadores.controller.dtos.ResponseRentaDTO;
import mx.edu.utez.cotizadores.exception.customException.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class RentaService {

    public ResponseRentaDTO cotizar(RequestRentaDTO payload) {
        int edad = payload.getEdadConductor();
        int dias = payload.getDiasRenta();
        int km = payload.getKilometrosEstimados();
        String tipo = payload.getTipoVehiculo();

        if (edad < 18) {
            throw new CustomBadRequestException("El conductor debe ser mayor de 18 anios");
        }
        if (dias > 30) {
            throw new CustomBadRequestException("La renta no puede durar mas de 30 dias");
        }
        if (km > 5000) {
            throw new CustomBadRequestException("Los kilometros estimados no pueden superar 5,000");
        }
        if (tipo.equals("CAMIONETA") && edad < 25) {
            throw new CustomBadRequestException("Para rentar una CAMIONETA el conductor debe tener al menos 25 anios");
        }

        double costoDiario = 0;
        switch (tipo) {
            case "COMPACTO":
                costoDiario = 550;
                break;
            case "SEDAN":
                costoDiario = 700;
                break;
            case "SUV":
                costoDiario = 950;
                break;
            case "CAMIONETA":
                costoDiario = 1200;
                break;
        }

        double costoRenta = costoDiario * dias;

        int kmIncluidos = dias * 100;
        int kmAdicionales = 0;
        if (km > kmIncluidos) {
            kmAdicionales = km - kmIncluidos;
        }
        double cargoKm = kmAdicionales * 4;

        double cargoEdad = 0;
        if (edad >= 18 && edad <= 24) {
            cargoEdad = (costoRenta + cargoKm) * 0.15;
        }

        double seguro = 0;
        if (payload.getSeguroCompleto()) {
            seguro = 180 * dias;
        }

        double descuento = 0;
        if (dias >= 7) {
            descuento = costoRenta * 0.10;
        }

        double total = costoRenta + cargoKm + cargoEdad + seguro - descuento;

        return new ResponseRentaDTO(
                payload.getNombreCliente(),
                tipo,
                dias,
                redondear(costoRenta),
                kmIncluidos,
                kmAdicionales,
                redondear(cargoKm),
                redondear(cargoEdad),
                redondear(seguro),
                redondear(descuento),
                redondear(total)
        );
    }

    private double redondear(double numero) {
        return Math.round(numero * 100.0) / 100.0;
    }
}
