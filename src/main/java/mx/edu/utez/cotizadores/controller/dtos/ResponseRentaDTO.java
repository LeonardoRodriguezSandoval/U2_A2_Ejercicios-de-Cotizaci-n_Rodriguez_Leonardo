package mx.edu.utez.cotizadores.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRentaDTO {
    private String nombreCliente;
    private String tipoVehiculo;
    private int diasRenta;
    private double costoRenta;
    private int kilometrosIncluidos;
    private int kilometrosAdicionales;
    private double cargoKilometros;
    private double cargoPorEdad;
    private double seguro;
    private double descuento;
    private double total;
}
