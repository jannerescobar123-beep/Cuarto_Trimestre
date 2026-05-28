package com.tienda.productos.service;

import com.tienda.productos.model.Producto;
import com.tienda.productos.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // LISTAR
    public List<Producto> listarProductos() {

        return productoRepository.findAll();
    }

    // BUSCAR
    public Producto buscarProducto(Long id) {

        return productoRepository
                .findById(id)
                .orElse(null);
    }

    // GUARDAR
    public Producto guardarProducto(Producto producto) {

        return productoRepository.save(producto);
    }

    // ACTUALIZAR
    public Producto actualizarProducto(Producto productoActualizado) {

        Producto producto =
                productoRepository
                        .findById(productoActualizado.getId())
                        .orElse(null);

        if (producto == null) {
            return null;
        }

        producto.setCodigo(productoActualizado.getCodigo());
        producto.setNombre(productoActualizado.getNombre());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setCantidad(productoActualizado.getCantidad());
        producto.setEstado(productoActualizado.getEstado());

        return productoRepository.save(producto);
    }

    // ELIMINAR
    public boolean eliminarProducto(Long id) {

        Producto producto =
                productoRepository
                        .findById(id)
                        .orElse(null);

        if (producto == null) {
            return false;
        }

        productoRepository.delete(producto);

        return true;
    }
    public boolean existeCodigo(String codigo) {

        return productoRepository.existsByCodigo(codigo);
    }
}