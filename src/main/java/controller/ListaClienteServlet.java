package controller;

import data.DBGenerator;
import data.dao.ClienteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "listaClienteServlet", value = "/mostrarClientes")
public class ListaClienteServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("ClientesBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("clientes",agregarUsuariosEjemplo());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher respuesta = req.getRequestDispatcher("/mostrarUsuarios.jsp");
        respuesta.forward(req,resp);
    }

    private List agregarUsuariosEjemplo() throws ClassNotFoundException {
        List<Cliente> clientes = new ArrayList<>();
        clientes = ClienteDAO.obtenerClientes(DBGenerator.conectarBD("ClientesBD"));
        return clientes;
    }
}