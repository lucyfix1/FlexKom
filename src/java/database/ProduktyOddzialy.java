/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dariusz
 */
@XmlRootElement
public class ProduktyOddzialy {
    private int oddzialID;
    private int produktID;
    private int ilosc;
    
    public void setOddzialID(int oddzialID) {
        this.oddzialID = oddzialID;
    }

    public void setProduktID(int produktID) {
        this.produktID = produktID;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getOddzialID() {
        return oddzialID;
    }

    public int getProduktID() {
        return produktID;
    }

    public int getIlosc() {
        return ilosc;
    }
    
}
