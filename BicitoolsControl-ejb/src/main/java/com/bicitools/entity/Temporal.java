/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhony
 */
@Entity
@Table(name = "Temporal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temporal.findAll", query = "SELECT t FROM Temporal t"),
    @NamedQuery(name = "Temporal.findByColumnpk", query = "SELECT t FROM Temporal t WHERE t.columnpk = :columnpk"),
    @NamedQuery(name = "Temporal.findByEstring", query = "SELECT t FROM Temporal t WHERE t.estring = :estring"),
    @NamedQuery(name = "Temporal.findByEstring2", query = "SELECT t FROM Temporal t WHERE t.estring2 = :estring2"),
    @NamedQuery(name = "Temporal.findByEdate", query = "SELECT t FROM Temporal t WHERE t.edate = :edate")})
public class Temporal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "columnpk")
    private Integer columnpk;
    @Size(max = 45)
    @Column(name = "estring")
    private String estring;
    @Size(max = 45)
    @Column(name = "estring2")
    private String estring2;
    @Column(name = "edate")
    @javax.persistence.Temporal(TemporalType.TIMESTAMP)
    private Date edate;

    public Temporal() {
    }

    public Temporal(Integer columnpk) {
        this.columnpk = columnpk;
    }

    public Integer getColumnpk() {
        return columnpk;
    }

    public void setColumnpk(Integer columnpk) {
        this.columnpk = columnpk;
    }

    public String getEstring() {
        return estring;
    }

    public void setEstring(String estring) {
        this.estring = estring;
    }

    public String getEstring2() {
        return estring2;
    }

    public void setEstring2(String estring2) {
        this.estring2 = estring2;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (columnpk != null ? columnpk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Temporal)) {
            return false;
        }
        Temporal other = (Temporal) object;
        if ((this.columnpk == null && other.columnpk != null) || (this.columnpk != null && !this.columnpk.equals(other.columnpk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bicitools.entity.Temporal[ columnpk=" + columnpk + " ]";
    }
    
}
