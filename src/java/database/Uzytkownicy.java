/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Date;

/**
 *
 * @author Dariusz
 */
public class Uzytkownicy {
    private int uzytkownikID;
    private String imie;
    private String nazwisko;
    private String plec;
    private Date dataUrodzenia;
    private String adres;
    private String kodPocztowy;
    private String miasto;
    private String nrTelefonu;
    private String login;
    private String haslo;
    private String email;
    private String aktywnosc;

    public void setUzytkownikID(int uzytkownikID) {
        this.uzytkownikID = uzytkownikID;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAktywnosc(String aktywnosc) {
        this.aktywnosc = aktywnosc;
    }

    public int getUzytkownikID() {
        return uzytkownikID;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPlec() {
        return plec;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getAdres() {
        return adres;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getEmail() {
        return email;
    }

    public String getAktywnosc() {
        return aktywnosc;
    }
    
}
