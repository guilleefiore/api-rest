package com.utn.productosapi.dto;
import com.utn.productosapi.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta con información completa del producto")
public class ProductoResponseDTO {
    @Schema(description = "ID del producto", example = "1")
    private Long id;
    @Schema(description = "Nombre del producto", example = "Laptop HP")
    private String nombre;
    @Schema(description = "Descripción del producto", example = "Laptop HP Pavilion 15, Intel Core i5, 8GB RAM")
    private String descripcion;
    @Schema(description = "Precio del producto", example = "45999.99")
    private Double precio;
    @Schema(description = "Cantidad en stock", example = "15")
    private Integer stock;
    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private Categoria categoria;
}
