package mx.edu.utez.cotizadores.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHospedajeDTO {
    private String nombreHuesped;
    private String tipoHabitacion;
    private int numeroNoches;
    private double costoHospedaje;
    private double ajusteTemporada;
    private double descuentoEstancia;
    private double desayuno;
    private double estacionamiento;
    private double subtotal;
    private double impuesto;
    private double total;
}
