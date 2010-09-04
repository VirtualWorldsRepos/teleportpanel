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
 * @author Akira Sonoda <akira.sonoda.1 at gmail.com>
 */
@Entity
@Table(name = "Configuration")
@NamedQueries({
    @NamedQuery(name = "Configuration.deleteContinentConfig", query = "DELETE FROM Configuration c WHERE c.continent = :continent"),
    @NamedQuery(name = "Configuration.findAll", query = "SELECT c FROM Configuration c"),
    @NamedQuery(name = "Configuration.findConfiguration", query = "SELECT c FROM Configuration c WHERE c.continent = :continent AND c.region = :region AND c.schluessel = :schluessel"),
    @NamedQuery(name = "Configuration.findByContinent", query = "SELECT c FROM Configuration c WHERE c.continent = :continent"),
    @NamedQuery(name = "Configuration.findByschluessel", query = "SELECT c FROM Configuration c WHERE c.schluessel = :schluessel"),
    @NamedQuery(name = "Configuration.findByValue", query = "SELECT c FROM Configuration c WHERE c.value = :value"),
    @NamedQuery(name = "Configuration.findByRegion", query = "SELECT c FROM Configuration c WHERE c.region = :region")})
public class Configuration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "continent")
    private String continent;
    @Basic(optional = false)
    @Column(name = "schluessel")
    private String schluessel;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "region")
    private String region;

    public Configuration() {
    }

    public Configuration(Integer id) {
        this.id = id;
    }

    public Configuration(Integer id, String continent, String schluessel, String value, String region) {
        this.id = id;
        this.continent = continent;
        this.schluessel = schluessel;
        this.value = value;
        this.region = region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getschluessel() {
        return schluessel;
    }

    public void setschluessel(String schluessel) {
        this.schluessel = schluessel;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
        if (!(object instanceof Configuration)) {
            return false;
        }
        Configuration other = (Configuration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.osgridde.teleportpanel.Configuration[id=" + id + "]";
    }

}
