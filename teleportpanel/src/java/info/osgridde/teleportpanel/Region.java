/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package info.osgridde.teleportpanel;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Akira Sonoda
 */
@Entity
@Table(name = "Region")
@NamedQueries({
    @NamedQuery(name = "Region.findAllName", query = "SELECT r.name FROM Region r"),
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r"),
    @NamedQuery(name = "Region.findByName", query = "SELECT r FROM Region r WHERE r.name = :name"),
    @NamedQuery(name = "Region.findByUuid", query = "SELECT r FROM Region r WHERE r.uuid = :uuid"),
    @NamedQuery(name = "Region.findByCoord", query = "SELECT r FROM Region r WHERE r.xcoord = :xcoord AND r.ycoord = :ycoord"),
    @NamedQuery(name = "Region.findByOwner", query = "SELECT r FROM Region r WHERE r.owner = :owner"),
    @NamedQuery(name = "Region.findByGrid", query = "SELECT r FROM Region r WHERE r.grid = :grid"),
    @NamedQuery(name = "Region.deleteAll", query = "DELETE FROM Region r")})

public class Region implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "xcoord")
    private int xcoord;
    @Basic(optional = false)
    @Column(name = "ycoord")
    private int ycoord;
    @Basic(optional = false)
    @Column(name = "owner")
    private String owner;
    @Basic(optional = false)
    @Column(name = "grid")
    private String grid;

    public Region() {
    }

    public Region(String name) {
        this.name = name;
    }

    public Region(String name, String uuid, int xcoord, int ycoord, String owner, String grid) {
        this.name = name;
        this.uuid = uuid;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.owner = owner;
        this.grid = grid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.osgridde.tptable.Region[name=" + name + "]";
    }

}
