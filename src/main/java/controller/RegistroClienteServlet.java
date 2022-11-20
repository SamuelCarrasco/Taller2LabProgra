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
import org.jooq.DSLContext;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "registroClienteServlet", value = "/registroCliente")
public class RegistroUsuarioServlet extends HttpServlet {
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
        //La respuesta que le vamos a devolver, va a ser la dirección del archivo JSP registroUsuario.jsp
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroCliente.jsp");
        //envía la respuesta
        respuesta.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");
        if(req.getParameter("email").length()!=0 && req.getParameter("nombre").length()!=0  &&
                req.getParameter("rut").length()!=0){
            String nombre = req.getParameter("nombre");
            String email = req.getParameter("email");
            String rut = req.getParameter("rut");
            Cliente cliente = new Cliente(nombre, email, rut);
            try {
                if(agregarCliente(cliente)){
                    req.setAttribute("cliente",cliente);
                    respuesta = req.getRequestDispatcher("/registroExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req,resp);
    }
    private boolean agregarCliente(Cliente cliente) throws ClassNotFoundException {
        DSLContext query= DBGenerator.conectarBD("ClienteBD");
        List<Cliente> clientes = ClienteDAO.obtenerClientes(query,"rut",cliente.getRut());
        if(clientes.size()!=0){
            return false;
        }
        else{
            ClienteDAO.agregarCliente(query,cliente);
            return true;
        }
    }
}
