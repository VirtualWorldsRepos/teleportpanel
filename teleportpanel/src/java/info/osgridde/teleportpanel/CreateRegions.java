/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.osgridde.teleportpanel;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

/**
 *
 * @author Akira Sonoda
 */
@WebServlet(name = "CreateRegions", urlPatterns = {"/CreateRegions"})
public class CreateRegions extends HttpServlet {

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
            throws ServletException, IOException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        assert emf != null;
        EntityManager em = null;
        TpTable tpElement = null;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // HTMLHelper.printHTMLHeader(out);
            utx.begin();

            em = emf.createEntityManager();

            RegionService regionService = new RegionService(em);

            regionService.removeAllRegions();

            URL myUrl = new URL("http://www.osgrid.org/index.php/regionlist");
            URLConnection conn = null;
            DataInputStream data = null;
            String line;
            conn = myUrl.openConnection();
            conn.setDoInput(true);
            conn.setUseCaches(false);

            conn.connect();

            data = new DataInputStream(conn.getInputStream());

            while ((line = data.readLine()) != null) {
                if (line.startsWith("</tr><center><tr><td>")) {
                    line = line.replaceAll("<tr>", "");
                    line = line.replaceAll("</tr>", "");
                    line = line.replaceAll("<center>", "");
                    line = line.replaceAll(" align=center", "");
                    line = line.replaceAll("/", "");
                    line = line.replaceAll("<td><td>", ";");
                    line = line.replaceAll("<td>", "");
                    StringTokenizer aTokenizer = new StringTokenizer(line, ";");
                    while (aTokenizer.hasMoreElements()) {
                        String regionName = (String) aTokenizer.nextElement();
                        try {
                            int xCoord = Integer.parseInt((String) aTokenizer.nextElement());
                            int yCoord = Integer.parseInt((String) aTokenizer.nextElement());
                            String regionOwner = (String) aTokenizer.nextElement();

                             Region aRegion = regionService.createRegion(regionName,
                                    xCoord,
                                    yCoord,
                                    regionOwner,
                                    "OSgrid");

                            em.persist(aRegion);
                        } catch (NumberFormatException e) {
                            LOG.log(Level.WARNING, e.toString());
                        }
                    }
                }

            }

            data.close();

            utx.commit();

            out.print("ok");

            // HTMLHelper.printHTMLFooter(out);

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






        } catch (NotSupportedException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
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






        } catch (NotSupportedException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(CreateRegions.class.getName()).log(Level.SEVERE, null, ex);
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
    private static final Logger LOG = Logger.getLogger(CreateRegions.class.getName());
}
