package model;

public class Articulos {
    String nombre;
    String tipoArticulo;
    Double peso;
    Double precio;

    public Articulos(String nombre, String tipoArticulo, Double peso, Double precio) {
        this.nombre = nombre;
        this.tipoArticulo = tipoArticulo;
        this.peso = peso;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
