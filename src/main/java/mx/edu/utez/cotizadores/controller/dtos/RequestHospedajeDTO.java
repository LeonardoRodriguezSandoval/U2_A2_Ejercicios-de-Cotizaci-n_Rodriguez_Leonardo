package mx.edu.utez.cotizadores.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestHospedajeDTO {
    @NotBlank(message = "El nombre del huesped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitacion es obligatorio")
    @Pattern(regexp = "^(INDIVIDUAL|DOBLE|SUITE)$",
            message = "El tipo de habitacion debe ser INDIVIDUAL, DOBLE o SUITE")
    private String tipoHabitacion;

    @NotNull(message = "El numero de noches es obligatorio")
    @Positive(message = "El numero de noches debe ser mayor a 0")
    private Integer numeroNoches;

    @NotNull(message = "El numero de huespedes es obligatorio")
    @Positive(message = "El numero de huespedes debe ser mayor a 0")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    @Pattern(regexp = "^(BAJA|REGULAR|ALTA)$",
            message = "La temporada debe ser BAJA, REGULAR o ALTA")
    private String temporada;

    @NotNull(message = "Debes indicar si incluye desayuno")
    private Boolean incluyeDesayuno;

    @NotNull(message = "Debes indicar si incluye estacionamiento")
    private Boolean incluyeEstacionamiento;
}
