package com.utn.productosapi.dto;
import com.utn.productosapi.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear o actualizar un producto")
public class ProductoDTO {
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre del producto", example = "Laptop HP")
    private String nombre;
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Schema(description = "Descripción del producto", example = "Laptop HP Pavilion 15, Intel Core i5, 8GB RAM")
    private String descripcion;
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Schema(description = "Precio del producto", example = "45999.99")
    private Double precio;
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Schema(description = "Cantidad en stock", example = "15")
    private Integer stock;
    @NotNull(message = "La categoría no puede ser nula")
    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private Categoria categoria;
}
