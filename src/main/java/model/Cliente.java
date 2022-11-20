package model;

public class Cliente {
    String nombre;
    String rut;
    String email;

    public Cliente(String nombre, String rut, String email) {
        this.nombre = nombre;
        this.rut = rut;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
