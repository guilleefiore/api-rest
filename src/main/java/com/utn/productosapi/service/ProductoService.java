package com.utn.productosapi.service;
import com.utn.productosapi.dto.ProductoDTO;
import com.utn.productosapi.dto.ProductoResponseDTO;
import com.utn.productosapi.exception.ProductoNotFoundException;
import com.utn.productosapi.model.Categoria;
import com.utn.productosapi.model.Producto;
import com.utn.productosapi.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;
    private ProductoResponseDTO convertirAResponseDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria()
        );
    }
    private Producto convertirDTOAProducto(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }
    @Transactional
    public ProductoResponseDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = convertirDTOAProducto(productoDTO);
        Producto productoGuardado = productoRepository.save(producto);
        return convertirAResponseDTO(productoGuardado);
    }
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return convertirAResponseDTO(producto);
    }
    @Transactional(readOnly = true)
    public Optional<Producto> obtenerPorIdOptional(Long id) {
        return productoRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria)
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public ProductoResponseDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());
        Producto productoActualizado = productoRepository.save(producto);
        return convertirAResponseDTO(productoActualizado);
    }
    @Transactional
    public ProductoResponseDTO actualizarStock(Long id, Integer nuevoStock) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        producto.setStock(nuevoStock);
        Producto productoActualizado = productoRepository.save(producto);
        return convertirAResponseDTO(productoActualizado);
    }
    @Transactional
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        productoRepository.deleteById(id);
    }
}
