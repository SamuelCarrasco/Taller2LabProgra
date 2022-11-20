package data.dao;

import com.sun.tools.javac.util.Name;
import model.Cliente;
import org.jooq.*;
import org.jooq.impl.DSL;

import javax.xml.transform.Result;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class ClienteDAO {
    public static void agregarCliente(DSLContext query, Cliente cliente){
        Name.Table tablaUsuario= table(name("Cliente"));
        Field[] columnas = tablaUsuario.fields("rut","nombre","email");
        query.insertInto(tablaUsuario, columnas[0], columnas[1],columnas[2])
                .values(cliente.getRut(),cliente.getNombre(),cliente.getEmail())
                .execute();
    }
    public static void modificarCliente(DSLContext query, String rut, String columnaTabla, Object dato){
        query.update(DSL.table("Cliente")).set(DSL.field(columnaTabla),dato).
                where(DSL.field("rut").eq(rut)).execute();
    }
    public static List obtenerCliente(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Cliente")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaClientes(resultados);
    }
    public static List obtenerClientes(DSLContext query){
        Result resultados = query.select().from(DSL.table("Cliente")).fetch();
        return obtenerListaClientes(resultados);
    }
    public static void eliminarCliente(DSLContext query, String rut){
        Table tablaCliente= table(name("Usuario"));
        query.delete(DSL.table("Usuario")).where(DSL.field("rut").eq(rut)).execute();
    }
    private static List obtenerListaClientes(Result resultados){
        List<Cliente> clientes= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            String rut = (String) resultados.getValue(fila,"rut");
            String nombre = (String) resultados.getValue(fila,"nombre");
            String email = (String) resultados.getValue(fila,"email");
            clientes.add(new Cliente(nombre,rut,email));
        }
        return clientes;
    }


}