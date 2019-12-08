package servlet;

import diccionarios.Diccionario;
import grafo.Arista;
import grafo.Nodo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Administrador;

public class ServletVuelos extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Vuelos</title>");            
            out.println("</head>");
            out.println("<body>");
            
            Administrador administrador = Administrador.getInstancia();
            List<String> listaPaises = administrador.obtenerPaises();
            List<String> listaIDsPaises = administrador.obtenerIDsPaises();
            
            out.println("<form action='Procesador'>");
            out.println("<input type='hidden' name='evento' value='insertarLugar'/>");
            out.println("<input type='text' name='id' placeholder='ID'/>");
            out.println("<input type='text' name='lugar' placeholder='lugar'/>");
            out.println("<input type='submit' value='guardar'/>");
            out.println("</form>");
            
            out.println("<h4>Lugares</h4>");
            out.println("<table>");
            out.println("<thead>");
            out.println("</thead>");
            out.println("<tbody>");
            for (int i = 0; i < listaPaises.size(); i++) {
                String pais = listaPaises.get(i);
                String idPais = listaIDsPaises.get(i);
                
                out.println("<tr>");
                
                out.println("<td>");
                out.println("<form action='Procesador'>");
                out.println("<button name='id' value='" + idPais + "'/>");
                out.println("<input type='hidden' name='evento' value='eliminarLugar'/>");
                out.println("</button>");
                out.println("</form>");
                out.println("</td>");
                
                out.println("<td>" + pais + "</td>");
                
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            
            out.println("<form action='Procesador'>");
            out.println("<input type='hidden' name='evento' value='insertarTramo'/>");
            out.println("<input type='text' name='origen' placeholder='origen'/>");
            out.println("<input type='text' name='destino' placeholder='destino'/>");
            out.println("<input type='text' name='tiempo' placeholder='tiempo'/>");
            out.println("<input type='text' name='costo' placeholder='costo'/>");
            out.println("<input type='submit' value='guardar'/>");
            out.println("</form>");
            
            out.println("<table>");
            out.println("<thead>");
            out.println("</thead>");
            out.println("<tr>");
            out.println("<th>Tramos</th>");
            out.println("<th>Tiempo</th>");
            out.println("<th>Costo</th>");
            out.println("</tr>");
            out.println("<tbody>");
            
            List<String> idsVertices = Administrador.getInstancia().getVuelosPrecio().getIdsVertices();
            for (String ID : idsVertices) {
                Nodo<String, String, Integer> nodoPaisPrecio =
                        administrador.getVuelosPrecio().getVertices().obtener(ID);
                Nodo<String, String, Integer> nodoPaisTiempo =
                        administrador.getVuelosTiempo().getVertices().obtener(ID);

                Diccionario<String, Arista<String, String, Integer>> aristasPaisPrecio =
                        nodoPaisPrecio.getAristas();
                Diccionario<String, Arista<String, String, Integer>> aristasPaisTiempo =
                        nodoPaisTiempo.getAristas();

                List<String> listaIDsDestinos = aristasPaisPrecio.obtenerLlaves();
                
                for (String idDestino : listaIDsDestinos) {
                    out.println("<tr>");
                    
                    out.println("<td>");
                    out.println(ID + " - " + idDestino);
                    out.println("</td>");

                    out.println("<td>");
                    int tiempo = aristasPaisTiempo.obtener(idDestino).getCosto();
                    out.println(tiempo + "hrs.");
                    out.println("</td>");

                    out.println("<td>");
                    int precio = aristasPaisPrecio.obtener(idDestino).getCosto();
                    out.println(precio + "$");
                    out.println("</td>");
                    
                    out.print("</tr>");
                }
                
            }
            out.print("</tbody>");
            out.print("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
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

}
