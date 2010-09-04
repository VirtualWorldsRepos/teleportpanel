/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author Akira Sonoda <akira.sonoda.1 at gmail.com>
 */
public class XmlRPCTest {

    public static void main(String[] args) {
        try {

            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://suai.dyndns.org:20800"));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            Hashtable theData = new Hashtable();
            theData.put("Channel", "63d2e5ce-6065-472c-bc97-459bcdf26eea");
            theData.put("IntValue", 2483);
            theData.put("StringValue", "Hello XMLRPC World");
            Vector params = new Vector();
            params.add(theData);

            Object result = client.execute("llRemoteData", params);
                        
        } catch (Exception exception) {
            System.err.println("SL_Client: " + exception);
            exception.printStackTrace();
        }
    }
}
