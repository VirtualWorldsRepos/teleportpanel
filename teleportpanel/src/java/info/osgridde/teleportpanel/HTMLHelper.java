/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package info.osgridde.teleportpanel;

import java.io.PrintWriter;

/**
 *
 * @author Akira Sonoda
 */
public class HTMLHelper {

    public static void printHTMLHeader(PrintWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Teleport Table</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Teleport Table</h1>");
    }

    public static void printHTMLFooter(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
}
