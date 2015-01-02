/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Dariusz
 */
public class KategorieProducent {
    private int kategoriaID;
    private int producentID;

    public void setKategoriaID(int kategoriaID) {
        this.kategoriaID = kategoriaID;
    }

    public void setProducentID(int producentID) {
        this.producentID = producentID;
    }

    public int getKategoriaID() {
        return kategoriaID;
    }

    public int getProducentID() {
        return producentID;
    }
    
}
