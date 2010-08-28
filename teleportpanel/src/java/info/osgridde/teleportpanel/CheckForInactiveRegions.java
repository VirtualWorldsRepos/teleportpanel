/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.osgridde.teleportpanel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.im4java.core.IM4JavaException;

/**
 *
 * @author Akira Sonoda
 */
@WebServlet(name = "CheckForInactiveRegions", urlPatterns = {"/CheckForInactiveRegions"})
public class CheckForInactiveRegions extends HttpServlet {

    @PersistenceUnit
    //The emf corresponding to
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException, InterruptedException, IM4JavaException {
        assert emf != null;
        EntityManager em = null;
        Region aRegion = null;
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;

        String filePath = "/srv/www/htdocs/webassets/cache/pic/";
        String picPath = "/srv/www/htdocs/jpeg/";
        String serverName = "akitest.dyndns.info";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HTMLHelper.printHTMLHeader(out);

            Map<String, String[]> paramMap = request.getParameterMap();
            if (paramMap.size() != 4) {
                out.println("Wrong number of parameters received");
            } else {
                minX = Integer.parseInt(paramMap.get("minx")[0]);
                maxX = Integer.parseInt(paramMap.get("maxx")[0]);
                minY = Integer.parseInt(paramMap.get("miny")[0]);
                maxY = Integer.parseInt(paramMap.get("maxy")[0]);

                em = emf.createEntityManager();

                RegionService regionService = new RegionService(em);

                out.println("<h2>The following Regions are no longer registered</h2>");

                for (int i = minX; i <= maxX; i++) {
                    for (int j = minY; j <= maxY; j++) {
                        try {
                            aRegion = regionService.getRegionByCoord(i, j);
                        } catch (NoResultException ex) {
                            out.println(Integer.toString(i) +" "+ Integer.toString(j) + "<br>" );
                        }
                    }
                }


                HTMLHelper.printHTMLFooter(out);
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">"
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IM4JavaException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IM4JavaException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(CheckForInactiveRegions.class.getName()).log(Level.SEVERE, null, ex);
        }
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
