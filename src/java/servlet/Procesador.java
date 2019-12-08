package servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Administrador;

public class Procesador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String evento = request.getParameter("evento");
        
        try (PrintWriter out = response.getWriter()) {
            switch (evento) {
                case "insertarLugar":
                    insertarLugar(request, out);
                    response.sendRedirect("index.html");
                    break;
                    
                case "insertarTramo":
                    insertarTramo(request, out);
                    response.sendRedirect("index.html");
                    break;
                    
                case "eliminarLugar":
                    eliminarLugar(request, out);
                    response.sendRedirect("index.html");
                    break;
                    
                case "obtenerPaises":
                    obtenerPaises(request, out);
                    break;
                    
                case "opcionRapida":
                    obtenerOpcionRapida(request, out);
                    break;
                    
                case "opcionBarata":
                    obtenerOpcionBarata(request, out);
                    break;
                    
                default:
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void obtenerPaises(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        List<String> paises = Administrador.getInstancia().obtenerPaises();
        
        out.print(gson.toJson(paises));
    }

    private void obtenerOpcionRapida(HttpServletRequest request, PrintWriter out) {
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        
        out.print("8");
    }

    private void obtenerOpcionBarata(HttpServletRequest request, PrintWriter out) {
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        
        out.print("600");
    }

    private void insertarLugar(HttpServletRequest request, PrintWriter out) {
        String ID = request.getParameter("id");
        String lugar = request.getParameter("lugar");
        
        Administrador.getInstancia().insertarLugar(ID, lugar);
    }

    private void insertarTramo(HttpServletRequest request, PrintWriter out) {
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        String tiempo = request.getParameter("tiempo");
        String costo = request.getParameter("costo");
        
        Administrador administrador = Administrador.getInstancia();
        administrador.insertarTramo(origen, destino, tiempo, costo);
    }

    private void eliminarLugar(HttpServletRequest request, PrintWriter out) {
        String ID = request.getParameter("id");
        
        Administrador.getInstancia().eliminarLugar(ID);
    }

}
