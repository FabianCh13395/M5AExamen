package com.M5A.Fabian_ChuquiExamen.Services;

import com.M5A.Fabian_ChuquiExamen.Entity.Producto;
import com.M5A.Fabian_ChuquiExamen.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service

public class ProductoServiceImp implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        Double precio=producto.getPrecio();
        int cantidad=producto.getCantidad();
        Double subtotal1=precio*cantidad;
        Double compra=0.0;
        if(compra>50) {
            Double descuento=subtotal1*0.10;
            Double iva=subtotal1*0.12;
            compra=(subtotal1-descuento+iva);
        }else {
            Double iva=subtotal1*0.12;
            compra=(subtotal1+iva);
        }
        producto.setCompra(compra);

        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long codigo) {
        productoRepository.deleteById(codigo);
    }
}
