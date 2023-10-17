package web;

import datos.TareaDao;
import dominio.Tarea;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "listar":
                    this.accionDefault(request, response);
                    break;
                case "completar":
                    this.completarTarea(request, response);
                    break;
                case "cancelar":
                    this.cancelarTarea(request, response);
                    break;
                case "eliminar":
                    this.eliminarTarea(request, response);
                    break;
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tarea> tareas;
        try {
            tareas = new TareaDao().listar();
            request.setAttribute("tareas", tareas);
            request.getRequestDispatcher("WEB-INF/tareas/listarTareas.jsp").forward(request, response);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void completarTarea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Tarea tarea = new TareaDao().buscar(new Tarea(Integer.parseInt(request.getParameter("idTarea"))));

            tarea.setEstado(1); // Se cambia estado a completo
            tarea.setFechaFin(new Date(new java.util.Date().getTime())); // Se pone fecha fin

            new TareaDao().actualizar(tarea); // Se actualiza BBD

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        this.accionDefault(request, response); // Listar Tareas

    }

    private void cancelarTarea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Se busca tarea a cancelar
            Tarea tarea = new TareaDao().buscar(new Tarea(Integer.parseInt(request.getParameter("idTarea"))));

            tarea.setEstado(2); // Se cambia estado a cancelado
            tarea.setFechaFin(new Date(new java.util.Date().getTime())); // Se pone fecha fin

            new TareaDao().actualizar(tarea); // Se actualiza BBD

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        this.accionDefault(request, response); // Listar Tareas
    }

    private void eliminarTarea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Se busca tarea a elimina
            Tarea tarea = new TareaDao().buscar(new Tarea(Integer.parseInt(request.getParameter("idTarea"))));

            new TareaDao().eliminar(tarea); // Se elimina de la BBDD

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        this.accionDefault(request, response); // Listar Tareas
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarTarea(request, response);
                    break;
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarTarea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Tarea tarea = new Tarea();
            tarea.setTitulo(request.getParameter("tituloNuevo"));
            tarea.setDescripcion(request.getParameter("descripcionNuevo"));
            tarea.setEstado(0); // PNDTE
            tarea.setFechaInicio(new Date(new java.util.Date().getTime()));

            new TareaDao().insertar(tarea);
        } catch (SQLException ex) {
            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.accionDefault(request, response); // Listar Tareas
    }

}
