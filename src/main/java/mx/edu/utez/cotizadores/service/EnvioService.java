package mx.edu.utez.cotizadores.service;

import mx.edu.utez.cotizadores.controller.dtos.RequestEnvioDTO;
import mx.edu.utez.cotizadores.controller.dtos.ResponseEnvioDTO;
import mx.edu.utez.cotizadores.exception.customException.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    public ResponseEnvioDTO cotizar(RequestEnvioDTO payload) {
        double volumen = payload.getLargoCm() * payload.getAnchoCm() * payload.getAltoCm();

        if (payload.getPesoKg() > 50) {
            throw new CustomBadRequestException("No se aceptan paquetes de mas de 50 kg");
        }
        if (payload.getLargoCm() > 150 || payload.getAnchoCm() > 150 || payload.getAltoCm() > 150) {
            throw new CustomBadRequestException("No se aceptan paquetes con una dimension mayor a 150 cm");
        }
        if (volumen > 1000000) {
            throw new CustomBadRequestException("No se aceptan paquetes con un volumen mayor a 1,000,000 cm3");
        }

        double costoBase = 80;

        double costoPorPeso = payload.getPesoKg() * 12;

        double cargoVolumen = 0;
        if (volumen > 50000) {
            cargoVolumen = 100;
        }

        double acumulado = costoBase + costoPorPeso + cargoVolumen;

        double recargo = 0;
        if (payload.getTipoEnvio().equals("EXPRESS")) {
            recargo = acumulado * 0.40;
        } else if (payload.getTipoEnvio().equals("MISMO_DIA")) {
            recargo = acumulado * 0.70;
        }
        acumulado = acumulado + recargo;

        double seguro = 0;
        if (payload.getValorDeclarado() > 10000) {
            seguro = payload.getValorDeclarado() * 0.02;
        }

        double total = acumulado + seguro;

        return new ResponseEnvioDTO(
                payload.getCodigoPostal(),
                payload.getTipoEnvio(),
                redondear(volumen),
                redondear(costoBase),
                redondear(costoPorPeso),
                redondear(cargoVolumen),
                redondear(recargo),
                redondear(seguro),
                redondear(total)
        );
    }

    private double redondear(double numero) {
        return Math.round(numero * 100.0) / 100.0;
    }
}
