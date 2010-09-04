/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package info.osgridde.teleportpanel;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Akira Sonoda
 */
public class ConfigurationService {

    protected EntityManager em;

    public ConfigurationService() {
    }

    public ConfigurationService(EntityManager em) {
        this.em = em;
    }

    public Configuration createConfiguration(String continent,
                               String region,
                               String schluessel,
                               String value) {


        Configuration aConfiguration = new Configuration(0,
                continent,
                schluessel,
                value,
                region);

        return(aConfiguration);
    }

    public void deleteContinentConfig(String continent) {
          em.createNamedQuery("Configuration.deleteContinentConfig",Configuration.class)
                  .setParameter("continent", continent).executeUpdate();
    }

    public Configuration getConfigItem( String continent,
                String region,
                String schluessel)  {

        return( em.createNamedQuery( "Configuration.findConfiguration", Configuration.class)
                .setParameter("continent", continent)
                .setParameter("region", region)
                .setParameter("schluessel", schluessel)
                .getSingleResult()
                );
    }

}
