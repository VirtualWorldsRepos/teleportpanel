/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.osgridde.teleportpanel;

import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Akira Sonoda
 */
public class TpTableService {

    protected EntityManager em;

    public TpTableService() {
    }

    public TpTableService(EntityManager em) {
        this.em = em;
    }

    public TpTable createTpTable(String controlregion,
            String theme,
            int boardcoordx,
            int boardcoordy,
            String destregion,
            int destcoordx,
            int destcoordy,
            int destcoordz) {

        TpTable aTpTable = new TpTable(0,
                destcoordy,
                boardcoordx,
                boardcoordy,
                theme,
                destcoordx,
                destregion,
                controlregion,
                destcoordz
                );

        return (aTpTable);
    }

    public void removeTpTable(Integer id) {

        TpTable aTpTable = getTpTableById(id);

        if (aTpTable != null) {
            em.remove(aTpTable);
        }
    }

    public void deleteFromTpTable(String controlregion, String destregion) {
        em.createNamedQuery("TpTable.deleteByRegion")
                .setParameter("controlregion", controlregion)
                .setParameter("destregion", destregion)
                .executeUpdate();

    }

    public TpTable getTpTableById(Integer id) {
        return (em.find(TpTable.class, id));
    }

    public TpTable getTpTableByName(String controlRegion,
            String theme,
            String destRegion) {

        try {

            TpTable aTpTable = em.createNamedQuery("TpTable.findByName",
                TpTable.class)
                .setParameter("controlregion", controlRegion)
                .setParameter("theme", theme)
                .setParameter("destregion", destRegion)
                .getSingleResult();

            return (aTpTable);
            
        } catch (NoResultException ex) {
            return (null);
        }


    }

    public List<TpTable> getTpTableForTheme(String controlRegion,
            String theme) throws NoResultException {

        return (em.createNamedQuery("TpTable.findByTheme", TpTable.class)
                .setParameter("controlregion", controlRegion)
                .setParameter("theme", theme)
                .getResultList());
    }
}
