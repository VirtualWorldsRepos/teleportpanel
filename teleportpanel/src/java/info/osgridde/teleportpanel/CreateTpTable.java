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
@WebServlet(name = "CreateTpTable", urlPatterns = {"/CreateTpTable"})
public class CreateTpTable extends HttpServlet {

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
            Map<String, String[]> paramMap = request.getParameterMap();
            if (paramMap.size() != 8) {
                out.println("Wrong number of parameters received");
            } else {
                String controlRegion = paramMap.get("cr")[0];
                String theme = paramMap.get("th")[0];
                String boardCoordX = paramMap.get("bx")[0];
                String boardCoordY = paramMap.get("by")[0];
                String destRegion = paramMap.get("dr")[0];
                String destCoordX = paramMap.get("dx")[0];
                String destCoordY = paramMap.get("dy")[0];
                String destCoordZ = paramMap.get("dz")[0];

                utx.begin();

                em = emf.createEntityManager();

                TpTableService tpTableService = new TpTableService(em);

                tpElement = tpTableService.getTpTableByName(controlRegion,
                        theme,
                        destRegion);

                if (tpElement == null) {



                    tpElement = tpTableService.createTpTable(controlRegion,
                            theme,
                            Integer.parseInt(boardCoordX),
                            Integer.parseInt(boardCoordY),
                            destRegion,
                            Integer.parseInt(destCoordX),
                            Integer.parseInt(destCoordY),
                            Integer.parseInt(destCoordZ));


                    em.persist(tpElement);


                } else {


                    tpElement.setBoardcoordx(Integer.parseInt(boardCoordX));
                    tpElement.setBoardcoordy(Integer.parseInt(boardCoordY));
                    tpElement.setDestcoordx(Integer.parseInt(destCoordX));
                    tpElement.setDestcoordy(Integer.parseInt(destCoordY));
                    tpElement.setDestcoordz(Integer.parseInt(destCoordY));

                    em.flush();
                    
                }

                utx.commit();

                out.print("ok");

            }

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
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(CreateTpTable.class.getName()).log(Level.SEVERE, null, ex);
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
