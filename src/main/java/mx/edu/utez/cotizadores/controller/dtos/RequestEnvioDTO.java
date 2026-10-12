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
public class RequestEnvioDTO {
    @NotBlank(message = "El codigo postal es obligatorio")
    @Pattern(regexp = "^\\d{5}$", message = "El codigo postal debe tener 5 digitos")
    private String codigoPostal;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    private Double pesoKg;

    @NotNull(message = "El largo es obligatorio")
    @Positive(message = "El largo debe ser mayor a 0")
    private Double largoCm;

    @NotNull(message = "El ancho es obligatorio")
    @Positive(message = "El ancho debe ser mayor a 0")
    private Double anchoCm;

    @NotNull(message = "El alto es obligatorio")
    @Positive(message = "El alto debe ser mayor a 0")
    private Double altoCm;

    @NotBlank(message = "El tipo de envio es obligatorio")
    @Pattern(regexp = "^(ESTANDAR|EXPRESS|MISMO_DIA)$",
            message = "El tipo de envio debe ser ESTANDAR, EXPRESS o MISMO_DIA")
    private String tipoEnvio;

    @NotNull(message = "El valor declarado es obligatorio")
    @PositiveOrZero(message = "El valor declarado no puede ser negativo")
    private Double valorDeclarado;
}
