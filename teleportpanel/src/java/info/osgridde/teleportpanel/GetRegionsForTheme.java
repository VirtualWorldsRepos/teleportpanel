/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.osgridde.teleportpanel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akira Sonoda
 */
@WebServlet(name = "GetRegionsForTheme", urlPatterns = {"/GetRegionsForTheme"})
public class GetRegionsForTheme extends HttpServlet {

    @PersistenceUnit
    //The emf corresponding to
    private EntityManagerFactory emf;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        assert emf != null;
        EntityManager em = null;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // HTMLHelper.printHTMLHeader(out);

            Map<String, String[]> paramMap = request.getParameterMap();
            if (paramMap.size() != 2) {
                out.println("Wrong number of parameters received");
            } else {
                String controlRegion = paramMap.get("cr")[0];
                String theme = paramMap.get("th")[0];

                em = emf.createEntityManager();

                TpTableService tpTableService = new TpTableService(em);

                List<TpTable> tpTableForTheme =
                        tpTableService.getTpTableForTheme(controlRegion, theme);

                if (tpTableForTheme.size() > 0) {
                    for (int i = 0; i < tpTableForTheme.size(); i++) {
                        TpTable tpt = tpTableForTheme.get(i);
                        out.println(
                                tpt.getId() + ";"
                                + Integer.toString(tpt.getBoardcoordx()) + ";"
                                + Integer.toString(tpt.getBoardcoordy()));
                    }
                }

            }

            // HTMLHelper.printHTMLFooter(out);
        } catch (NoResultException ex) {

            out.println(ex.getMessage());
            // HTMLHelper.printHTMLFooter(out);

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
