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
public class RegionService {

    protected EntityManager em;

    public RegionService() {
    }

    public RegionService(EntityManager em) {
        this.em = em;
    }

    public Region createRegion(String name,
                               int xcoord,
                               int ycoord,
                               String owner,
                               String grid ) {

        // String uuid = "a25f1fb0-ad31-11df-94e2-0800200c9a66";
        String uuid = "00000000-0000-0000-0000-000000000000";

        Region aRegion = new Region(name, uuid, xcoord, ycoord, owner, grid );

        return(aRegion);
    }

    public void removeAllRegions() {
          em.createNamedQuery("Region.deleteAll",Region.class).executeUpdate();
    }

    public Region getRegionByName( String name )  {
        return( em.find( Region.class, name ) );
    }

    public List<Region> getAllRegionNames() {
        return( em.createNamedQuery("Region.findAll",Region.class)
                .getResultList() );
    }

    public Region getRegionByCoord(int xcoord, int ycoord) {

        return( em.createNamedQuery("Region.findByCoord", Region.class)
                .setParameter("xcoord", xcoord)
                .setParameter("ycoord", ycoord)
                .getSingleResult() );
    }
}
