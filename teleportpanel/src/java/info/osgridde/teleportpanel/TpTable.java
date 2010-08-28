/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package info.osgridde.teleportpanel;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Akira Sonoda
 */
@Entity
@Table(name = "TpTable")
@NamedQueries({
    //
    @NamedQuery(name = "TpTable.deleteByRegion", query = "DELETE FROM TpTable t WHERE t.controlregion = :controlregion AND t.destregion = :destregion"),
    @NamedQuery(name = "TpTable.findByTheme", query = "SELECT t FROM TpTable t WHERE t.controlregion = :controlregion AND t.theme = :theme"),
    @NamedQuery(name = "TpTable.findByName", query = "SELECT t FROM TpTable t WHERE t.controlregion = :controlregion AND t.theme = :theme AND t.destregion = :destregion"),
    //
    //
    @NamedQuery(name = "TpTable.findAll", query = "SELECT t FROM TpTable t"),
    @NamedQuery(name = "TpTable.findById", query = "SELECT t FROM TpTable t WHERE t.id = :id"),
    @NamedQuery(name = "TpTable.findByDestcoordy", query = "SELECT t FROM TpTable t WHERE t.destcoordy = :destcoordy"),
    @NamedQuery(name = "TpTable.findByBoardcoordx", query = "SELECT t FROM TpTable t WHERE t.boardcoordx = :boardcoordx"),
    @NamedQuery(name = "TpTable.findByBoardcoordy", query = "SELECT t FROM TpTable t WHERE t.boardcoordy = :boardcoordy"),
    @NamedQuery(name = "TpTable.findByDestcoordx", query = "SELECT t FROM TpTable t WHERE t.destcoordx = :destcoordx"),
    @NamedQuery(name = "TpTable.findByDestregion", query = "SELECT t FROM TpTable t WHERE t.destregion = :destregion"),
    @NamedQuery(name = "TpTable.findByControlregion", query = "SELECT t FROM TpTable t WHERE t.controlregion = :controlregion"),
    @NamedQuery(name = "TpTable.findByDestcoordz", query = "SELECT t FROM TpTable t WHERE t.destcoordz = :destcoordz")})


public class TpTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "destcoordy")
    private int destcoordy;
    @Basic(optional = false)
    @Column(name = "boardcoordx")
    private int boardcoordx;
    @Basic(optional = false)
    @Column(name = "boardcoordy")
    private int boardcoordy;
    @Basic(optional = false)
    @Column(name = "theme")
    private String theme;
    @Basic(optional = false)
    @Column(name = "destcoordx")
    private int destcoordx;
    @Basic(optional = false)
    @Column(name = "destregion")
    private String destregion;
    @Basic(optional = false)
    @Column(name = "controlregion")
    private String controlregion;
    @Basic(optional = false)
    @Column(name = "destcoordz")
    private int destcoordz;

    public TpTable() {
    }

    public TpTable(Integer id) {
        this.id = id;
    }

    public TpTable(Integer id, int destcoordy, int boardcoordx, int boardcoordy, String theme, int destcoordx, String destregion, String controlregion, int destcoordz) {
        this.id = id;
        this.destcoordy = destcoordy;
        this.boardcoordx = boardcoordx;
        this.boardcoordy = boardcoordy;
        this.theme = theme;
        this.destcoordx = destcoordx;
        this.destregion = destregion;
        this.controlregion = controlregion;
        this.destcoordz = destcoordz;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDestcoordy() {
        return destcoordy;
    }

    public void setDestcoordy(int destcoordy) {
        this.destcoordy = destcoordy;
    }

    public int getBoardcoordx() {
        return boardcoordx;
    }

    public void setBoardcoordx(int boardcoordx) {
        this.boardcoordx = boardcoordx;
    }

    public int getBoardcoordy() {
        return boardcoordy;
    }

    public void setBoardcoordy(int boardcoordy) {
        this.boardcoordy = boardcoordy;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getDestcoordx() {
        return destcoordx;
    }

    public void setDestcoordx(int destcoordx) {
        this.destcoordx = destcoordx;
    }

    public String getDestregion() {
        return destregion;
    }

    public void setDestregion(String destregion) {
        this.destregion = destregion;
    }

    public String getControlregion() {
        return controlregion;
    }

    public void setControlregion(String controlregion) {
        this.controlregion = controlregion;
    }

    public int getDestcoordz() {
        return destcoordz;
    }

    public void setDestcoordz(int destcoordz) {
        this.destcoordz = destcoordz;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TpTable)) {
            return false;
        }
        TpTable other = (TpTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.osgridde.tptable.TpTable[id=" + id + "]";
    }

}
