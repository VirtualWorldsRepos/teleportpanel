/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.osgridde.teleportpanel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author Akira Sonoda
 */
@WebServlet(name = "UpdateMapImage", urlPatterns = {"/UpdateMapImage"})
public class UpdateMapImage extends HttpServlet {

    @PersistenceUnit
    //The emf corresponding to
    private EntityManagerFactory emf;
    private String serverName = "suai.dyndns.org";
    private String channel = null;
    private String port = null;

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
        Configuration aConfiguration = null;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // HTMLHelper.printHTMLHeader(out);
            Map<String, String[]> paramMap = request.getParameterMap();
            if (paramMap.size() != 2) {
                out.println("Wrong number of parameters received");
            } else {
                String continent = paramMap.get("co")[0];
                String region = paramMap.get("re")[0];

                em = emf.createEntityManager();

                ConfigurationService configurationService = new ConfigurationService(em);

                aConfiguration = configurationService.getConfigItem(continent,
                        region,
                        "XMLRPC_PORT");

                port = aConfiguration.getValue();

                aConfiguration = configurationService.getConfigItem(continent,
                        region,
                        "XMLRPC_TPPANEL_CONFIG_CHANNEL");

                channel = aConfiguration.getValue();

                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
                config.setServerURL(new URL("http://" + serverName + ":" + port));
                XmlRpcClient client = new XmlRpcClient();
                client.setConfig(config);

                Hashtable theData = new Hashtable();
                theData.put("Channel", channel);
                theData.put("IntValue", 2483);
                theData.put("StringValue", "start");
                Vector params = new Vector();
                params.add(theData);

                Object result = client.execute("llRemoteData", params);

                out.print("ok");

            }

            // HTMLHelper.printHTMLFooter(out);
        } catch (XmlRpcException ex) {
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(UpdateMapImage.class.getName()).log(Level.SEVERE, null, ex);
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
