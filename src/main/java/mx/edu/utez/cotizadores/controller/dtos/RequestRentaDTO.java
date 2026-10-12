package mx.edu.utez.cotizadores.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestRentaDTO {
    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    @Positive(message = "La edad del conductor debe ser mayor a 0")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehiculo es obligatorio")
    @Pattern(regexp = "^(COMPACTO|SEDAN|SUV|CAMIONETA)$",
            message = "El tipo de vehiculo debe ser COMPACTO, SEDAN, SUV o CAMIONETA")
    private String tipoVehiculo;

    @NotNull(message = "Los dias de renta son obligatorios")
    @Positive(message = "Los dias de renta deben ser mayor a 0")
    private Integer diasRenta;

    @NotNull(message = "Los kilometros estimados son obligatorios")
    @PositiveOrZero(message = "Los kilometros estimados no pueden ser negativos")
    private Integer kilometrosEstimados;

    @NotNull(message = "Debes indicar si contrata el seguro completo")
    private Boolean seguroCompleto;
}
