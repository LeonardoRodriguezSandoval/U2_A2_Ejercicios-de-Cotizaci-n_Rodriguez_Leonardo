package mx.edu.utez.cotizadores.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEnvioDTO {
    private String codigoPostal;
    private String tipoEnvio;
    private double volumenCm3;
    private double costoBase;
    private double costoPorPeso;
    private double cargoPorVolumen;
    private double recargoTipoEnvio;
    private double seguro;
    private double costoTotal;
}
