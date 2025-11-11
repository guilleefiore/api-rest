package com.utn.productosapi.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    @Column(nullable = false)
    private Double precio;
    @Column(nullable = false)
    private Integer stock;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;
}
