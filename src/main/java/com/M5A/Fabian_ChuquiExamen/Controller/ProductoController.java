package com.M5A.Fabian_ChuquiExamen.Controller;


import com.M5A.Fabian_ChuquiExamen.Entity.Producto;
import com.M5A.Fabian_ChuquiExamen.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> crearProductos(@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?>ConsultarporId(@PathVariable(value="codigo")Long codigoId){
        Optional<Producto> optionalProducto=productoService.findById(codigoId);
        if(!optionalProducto.isPresent()){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalProducto);
    }
    @PutMapping("/{codigo}")
    public ResponseEntity<?> ActualizarProducto(@RequestBody Producto productoDatails,@PathVariable(value = "codigo") Long codigoId){
        Optional<Producto> producto =productoService.findById(codigoId);
        if(!producto.isPresent()){
            return ResponseEntity.notFound().build();
        }
        producto.get().setDescripcion(productoDatails.getDescripcion());
        producto.get().setPrecio(productoDatails.getPrecio());
        producto.get().setCantidad(productoDatails.getCantidad());
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto.get()));
    }
  @DeleteMapping("/{codigo}")
    public ResponseEntity<?> EliminarProducto(@PathVariable(value = "codigo") Long codigoId){
        if(!productoService.findById(codigoId).isPresent()){
            return ResponseEntity.notFound().build();
        }
        productoService.deleteById(codigoId);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public List<Producto> MostrarProductos(){
        List<Producto> productos= StreamSupport.stream(productoService.findAll().spliterator(),false).collect(Collectors.toList());
        return productos;
    }
}
