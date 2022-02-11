package com.M5A.Fabian_ChuquiExamen.Entity;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
@Column(length = 100)
@Size(max=100,message = "El campo descripcion no puede sobrepasar los 100 caracteres")
@NotBlank
    private String descripcion;
    @Min(value = 0,message = "El campo precio tiene que ser mayor a 0")
    @NotBlank
    private Double precio;
    @NotBlank
    @Min(value = 0,message = "El campo cantidad tiene que ser mayor a 0")
    private int cantidad;



    private Double compra;



    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    /*public Double CompraRealizada(){
      double compra=getCantidad()*getPrecio();
      double total,total1;
      if(compra>50){
          total=(compra*10)/100;
          total1=compra-total;
      }
    }

     */
}
