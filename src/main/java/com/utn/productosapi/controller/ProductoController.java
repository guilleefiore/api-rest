package com.utn.productosapi.controller;
import com.utn.productosapi.dto.ActualizarStockDTO;
import com.utn.productosapi.dto.ProductoDTO;
import com.utn.productosapi.dto.ProductoResponseDTO;
import com.utn.productosapi.model.Categoria;
import com.utn.productosapi.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "API para gestión de productos del e-commerce")
public class ProductoController {
    private final ProductoService productoService;
    @GetMapping
    @Operation(
        summary = "Listar todos los productos",
        description = "Obtiene una lista completa de todos los productos disponibles en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        )
    })
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos() {
        List<ProductoResponseDTO> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener producto por ID",
        description = "Busca y retorna un producto específico según su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto encontrado exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado"
        )
    })
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(
            @Parameter(description = "ID del producto a buscar", example = "1")
            @PathVariable Long id) {
        ProductoResponseDTO producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }
    @GetMapping("/categoria/{categoria}")
    @Operation(
        summary = "Filtrar productos por categoría",
        description = "Obtiene todos los productos que pertenecen a una categoría específica"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos filtrada exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        )
    })
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(
            @Parameter(description = "Categoría a filtrar", example = "ELECTRONICA")
            @PathVariable Categoria categoria) {
        List<ProductoResponseDTO> productos = productoService.obtenerPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }
    @PostMapping
    @Operation(
        summary = "Crear nuevo producto",
        description = "Crea un nuevo producto en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Producto creado exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos"
        )
    })
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Valid @RequestBody ProductoDTO productoDTO) {
        ProductoResponseDTO nuevoProducto = productoService.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar producto completo",
        description = "Actualiza todos los campos de un producto existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos"
        )
    })
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @Parameter(description = "ID del producto a actualizar", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO productoDTO) {
        ProductoResponseDTO productoActualizado = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }
    @PatchMapping("/{id}/stock")
    @Operation(
        summary = "Actualizar solo el stock",
        description = "Actualiza únicamente la cantidad en stock de un producto"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Stock actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Stock inválido"
        )
    })
    public ResponseEntity<ProductoResponseDTO> actualizarStock(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ActualizarStockDTO stockDTO) {
        ProductoResponseDTO productoActualizado = productoService.actualizarStock(id, stockDTO.getStock());
        return ResponseEntity.ok(productoActualizado);
    }
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar producto",
        description = "Elimina un producto del sistema de forma permanente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Producto eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado"
        )
    })
    public ResponseEntity<Void> eliminarProducto(
            @Parameter(description = "ID del producto a eliminar", example = "1")
            @PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
