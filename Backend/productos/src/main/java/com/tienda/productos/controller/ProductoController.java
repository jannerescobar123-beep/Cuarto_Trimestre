package com.tienda.productos.controller;


import com.tienda.productos.model.Producto;
import com.tienda.productos.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/servicio")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // http://localhost:8080/servicio/productos
    @GetMapping("productos")
    public ResponseEntity<?> listarProductos() {

        List<Producto> lista =
                productoService.listarProductos();

        return ResponseEntity.ok(lista);
    }

    // http://localhost:8080/servicio/productos/1
    @GetMapping("productos/{id}")
    public ResponseEntity<?> buscarProducto(
            @PathVariable("id") Long id) {

        Producto producto =
                productoService.buscarProducto(id);

        if (producto == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado");
        }

        return ResponseEntity.ok(producto);
    }

    // http://localhost:8080/servicio/guardar
    @PostMapping("guardar")
    public ResponseEntity<?> guardarProducto(
            @RequestBody Producto producto) {

        if (producto == null) {

            return ResponseEntity
                    .badRequest()
                    .body("El producto es obligatorio");
        }

        Producto productoGuardado =
                productoService.guardarProducto(producto);

        return ResponseEntity.ok(productoGuardado);
    }

    // http://localhost:8080/servicio/actualizar
    @PutMapping("actualizar")
    public ResponseEntity<?> actualizarProducto(
            @RequestBody Producto producto) {

        Producto productoActualizado =
                productoService.actualizarProducto(producto);

        if (productoActualizado == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado");
        }

        return ResponseEntity.ok(productoActualizado);
    }

    // http://localhost:8080/servicio/eliminar/1
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(
            @PathVariable("id") Long id) {

        boolean eliminado =
                productoService.eliminarProducto(id);

        if (!eliminado) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado");
        }

        return ResponseEntity.ok(
                "Producto eliminado correctamente");
    }

    @GetMapping("validar-codigo")
    public ResponseEntity<?> validarCodigo(
            @RequestParam String codigo) {

        boolean existe =
                productoService.existeCodigo(codigo);

        return ResponseEntity.ok(existe);
    }
}
