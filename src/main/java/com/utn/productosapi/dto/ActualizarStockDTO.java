package com.utn.productosapi.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar el stock de un producto")
public class ActualizarStockDTO {
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Schema(description = "Nueva cantidad en stock", example = "50")
    private Integer stock;
}
