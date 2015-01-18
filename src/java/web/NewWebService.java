/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import database.Kategorie;
import database.KategorieProducent;
import database.Klienci;
import database.Pracownicy;
import database.Producenci;
import database.Produkt;
import database.ProduktOddzialy;
import database.Produkty;
import database.ProduktyOddzialy;
import database.Uprawnienia;
import database.Uzytkownicy;
import database.UzytkownicyUprawnienia;
import database.Zamowienie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.jws.WebResult;

/**
 *
 * @author Dariusz
 */
@WebService(serviceName = "NewWebService")
@Stateless()
public class NewWebService {
    private final String dbAddress = "jdbc:mysql://localhost:3306/flexkom";
    private final String user = "root";
    private final String password = "mysql";

    /**
     * Web service operation
     * @param oddzialID
     * @param produktID
     * @param ilosc
     * @return 
     */
    @WebMethod(operationName = "uaktualnijProduktyOddzialy")
    public String uaktualnijProduktyOddzialy(@WebParam(name = "oddzialID") int oddzialID, @WebParam(name = "produktID") int produktID, @WebParam(name = "ilosc") int ilosc){
        //TODO write your implementation code here:
        Connection con = MysqlConnection.connect(dbAddress,user,password);

        String insertQuery = "update produktyoddzialy set ilosc = ? where oddzialID = ? and produktID = ?";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(2, oddzialID);
            preparedStatement.setInt(3, produktID);
            preparedStatement.setInt(1, ilosc);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "true";
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "0";
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getOddzialyProdukty")
    @WebResult(name = "bidNumber")
    public List<ProduktyOddzialy> getOddzialyProdukty() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<ProduktyOddzialy> list = new ArrayList<>();
        try {
            st = con.createStatement();
            String sql = ("SELECT * FROM produktyoddzialy");
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                ProduktyOddzialy pro = new ProduktyOddzialy();
                pro.setOddzialID(rs.getInt("oddzialID"));
                pro.setProduktID(rs.getInt("produktID"));
                pro.setIlosc(rs.getInt("ilosc"));
                list.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "dodajNowegoKlienta")
    public String dodajNowegoKlienta(@WebParam(name = "imie") String imie, @WebParam(name = "nazwisko") String nazwisko, @WebParam(name = "plec") String plec, @WebParam(name = "dataUrodzenia") String dataUrodzenia, @WebParam(name = "adres") String adres, @WebParam(name = "kodPocztowy") String kodPocztowy, @WebParam(name = "miasto") String miasto, @WebParam(name = "nrTelefonu") String nrTelefonu, @WebParam(name = "login") String login, @WebParam(name = "haslo") String haslo, @WebParam(name = "email") String email) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        System.out.println(ourJavaTimestampObject.toString());
        String insertQuery = "insert into uzytkownicy (imie, nazwisko, plec, dataUrodzenia, adres, kodPocztowy, miasto, nrTelefonu, login, haslo, email, aktywnosc) values(?,?,?,?,?,?,?,?,?,?,?,'nieaktywny')";
        
        PreparedStatement preparedStatement;
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataUrodzenia);
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, imie);
            preparedStatement.setString(2, nazwisko);
            preparedStatement.setString(3, plec);
            preparedStatement.setDate(4,new java.sql.Date(utilDate.getTime()));
            preparedStatement.setString(5, adres);
            preparedStatement.setString(6, kodPocztowy);
            preparedStatement.setString(7, miasto);
            preparedStatement.setString(8, nrTelefonu);
            preparedStatement.setString(9, login);
            preparedStatement.setString(10, haslo);
            preparedStatement.setString(11, email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "dodano użytkownika";
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "użytkownik nie został dodany";
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dodajZamowienie")
    public String dodajZamowienie(@WebParam(name = "produktID") int produktID, @WebParam(name = "klientID") int klientID, @WebParam(name = "oddzialID") int oddzialID, @WebParam(name = "pracownikID") int pracownikID) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        System.out.println(ourJavaTimestampObject.toString());
        String insertQuery = "insert into zamowienia (produktID,klientID,oddzialID,status,dataZlozeniaZamowienia,pracownikID) values(?,?,?,'oczekujacy',?,?)";
        
        PreparedStatement preparedStatement;
        try {
            java.util.Date date = new java.util.Date();
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(1, produktID);
            if(klientID != 0){
                preparedStatement.setInt(2, klientID);
            }
            else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }
            if(oddzialID != 0){
                preparedStatement.setInt(3, oddzialID);
            }
            else {
                preparedStatement.setNull(3, java.sql.Types.INTEGER);
            }
            preparedStatement.setDate(4,new java.sql.Date(date.getTime()));
            if(pracownikID != 0){
                preparedStatement.setInt(5, pracownikID);
            }
            else {
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "true";
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "0";
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "uaktualnijZamownienia")
    public String uaktualnijZamownienia(@WebParam(name = "zamowienieID") int zamowienieID, @WebParam(name = "newStatus") String newStatus) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        String insertQuery = "update zamowienia set status = ? where zamowienieID = ?";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(2, zamowienieID);
            preparedStatement.setString(1, newStatus);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "zaktualizowano";
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "dane nie zostały zaktualizowane";
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Web service operation
     */
    /*@WebMethod(operationName = "getOddzialOstatniaModyfikacja")
    public java.util.Date getOddzialOstatniaModyfikacja(@WebParam(name = "oddzialID") String oddzialID) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        java.sql.Timestamp datetime = null;
        try {
            st = con.createStatement();
            String sql = ("SELECT ostatniaModyfikacja FROM oddzialyostatniamodyfikacja where oddzialID =" + oddzialID);
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
                datetime = rs.getTimestamp("ostatniaModyfikacja");
            return datetime;
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/

    /**
     * Web service operation
     */
    /*@WebMethod(operationName = "updateDataOstatniejModyfikacji")
    public String updateDataOstatniejModyfikacji(@WebParam(name = "oddzialID") int oddzialID) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        String insertQuery = "update oddzialyostatniamodyfikacja set ostatniaModyfikacja = ? where oddzialID = ?";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(2, oddzialID);
            preparedStatement.setTimestamp(1, ourJavaTimestampObject);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "zaktualizowano date";
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "data nie została zaktualizowana";
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getIloscProduktuInneOddzialy")
    public List<ProduktOddzialy> getIloscProduktuInneOddzialy(@WebParam(name = "oddzialID") int oddzialID, @WebParam(name = "produktID") int produktID){
        
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<ProduktOddzialy> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT oddzialy.adres, oddzialy.miasto, produktyoddzialy.ilosc FROM produktyoddzialy " +
                    "inner join oddzialy on produktyoddzialy.oddzialID = oddzialy.oddzialID where produktyoddzialy.oddzialID <> ? and produktID = ?");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, oddzialID);
            preparedStatement.setInt(2, produktID);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                ProduktOddzialy pro = new ProduktOddzialy();
                pro.setAdres(rs.getString("adres"));
                pro.setMiasto(rs.getString("miasto"));
                pro.setIlosc(rs.getInt("ilosc"));
                list.add(pro);
            }
            preparedStatement.close();
            
            
            con.close();
            
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getKategorie")
    public List<Kategorie> getKategorie() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Kategorie> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from kategorie");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Kategorie kategoria = new Kategorie();
                kategoria.setKategoriaID(rs.getInt("kategoriaID"));
                kategoria.setNazwa(rs.getString("nazwa"));
                list.add(kategoria);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getKategorieProdukty")
    public List<KategorieProducent> getKategorieProducent() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<KategorieProducent> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from kategorieproducent");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                KategorieProducent katpro = new KategorieProducent();
                katpro.setKategoriaID(rs.getInt("kategoriaID"));
                katpro.setProducentID(rs.getInt("producentID"));
                list.add(katpro);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getKlienci")
    public List<Klienci> getKlienci() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Klienci> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from klienci");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Klienci klient = new Klienci();
                klient.setKlientID(rs.getInt("klientID"));
                klient.setUzytkownikID(rs.getInt("uzytkownikID"));
                klient.setStanKonta(rs.getFloat("stanKonta"));
                klient.setDataZalozenia(rs.getDate("dataZalozenia"));
                list.add(klient);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getPracownicy")
    public List<Pracownicy> getPracownicy() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Pracownicy> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from pracownicy");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Pracownicy pracownik = new Pracownicy();
                pracownik.setPracownikID(rs.getInt("pracownikID"));
                pracownik.setUzytkownikID(rs.getInt("uzytkownikID"));
                pracownik.setOddzialID(rs.getInt("oddzialID"));
                pracownik.setDataZatrudnienia(rs.getDate("dataZatrudnienia"));
                pracownik.setPensja(rs.getFloat("pensja"));
                pracownik.setStanowisko(rs.getString("stanowisko"));
                list.add(pracownik);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getProducenci")
    public List<Producenci> getProducenci() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Producenci> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from producenci");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Producenci producent = new Producenci();
                producent.setProducentID(rs.getInt("producentID"));
                producent.setNazwa(rs.getString("nazwa"));
                list.add(producent);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getProdukty")
    public List<Produkty> getProdukty() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Produkty> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from produkty");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Produkty produkt = new Produkty();
                produkt.setProduktID(rs.getInt("produktID"));
                produkt.setNazwa(rs.getString("nazwa"));
                produkt.setProducentID(rs.getInt("producentID"));
                produkt.setKategoriaID(rs.getInt("kategoriaID"));
                produkt.setCena(rs.getFloat("cena"));
                list.add(produkt);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getUprawnienia")
    public List<Uprawnienia> getUprawnienia() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Uprawnienia> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from uprawnienia");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Uprawnienia uprawnienie = new Uprawnienia();
                uprawnienie.setUprawnienieID(rs.getInt("uprawnienieID"));
                uprawnienie.setNazwa(rs.getString("nazwa"));
                list.add(uprawnienie);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getUzytkownicy")
    public List<Uzytkownicy> getUzytkownicy() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Uzytkownicy> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from uzytkownicy");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Uzytkownicy uzytkownik = new Uzytkownicy();
                uzytkownik.setUzytkownikID(rs.getInt("uzytkownikID"));
                uzytkownik.setImie(rs.getString("imie"));
                uzytkownik.setNazwisko(rs.getString("nazwisko"));
                uzytkownik.setPlec(rs.getString("plec"));
                uzytkownik.setAdres(rs.getString("adres"));
                uzytkownik.setKodPocztowy(rs.getString("kodPocztowy"));
                uzytkownik.setMiasto(rs.getString("miasto"));
                uzytkownik.setLogin(rs.getString("login"));
                uzytkownik.setHaslo(rs.getString("haslo"));
                uzytkownik.setEmail(rs.getString("email"));
                uzytkownik.setAktywnosc(rs.getString("aktywnosc"));
                uzytkownik.setDataUrodzenia(rs.getDate("dataUrodzenia"));
                uzytkownik.setNrTelefonu(rs.getString("nrTelefonu"));
                list.add(uzytkownik);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getUzytkownicyUprawnienia")
    public List<UzytkownicyUprawnienia> getUzytkownicyUprawnienia() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<UzytkownicyUprawnienia> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from uzytkownicyuprawnienia");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                UzytkownicyUprawnienia uzytkownikUprawnienie = new UzytkownicyUprawnienia();
                uzytkownikUprawnienie.setUzytkownikID(rs.getInt("uzytkownikID"));
                uzytkownikUprawnienie.setUprawnienieID(rs.getInt("uprawnienieID"));
                list.add(uzytkownikUprawnienie);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getZamowienia")
    public List<Zamowienie> getZamowienia() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Zamowienie> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("SELECT * from zamowienia");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Zamowienie zamowienie = new Zamowienie();
                zamowienie.setZamowienieID(rs.getInt("zamowienieID"));
                zamowienie.setProduktID(rs.getInt("produktID"));
                zamowienie.setKlientOD(rs.getInt("klientID"));
                zamowienie.setOddzialID(rs.getInt("oddzialID"));
                zamowienie.setStatus(rs.getString("status"));
                zamowienie.setDataZlozeniaZamowienia(rs.getTimestamp("dataZlozeniaZamowienia").toString());
                zamowienie.setPracownikID(rs.getInt("pracownikID"));
                list.add(zamowienie);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getZamowieniaUzytkownika")
    public List<Zamowienie> getZamowieniaUzytkownika(@WebParam(name = "username") String username) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Zamowienie> list = new ArrayList<>();
        ResultSet rs = null;
            
        try {

            st = con.createStatement();
            String sql = ("select * from zamowienia " +
                          "inner join klienci on zamowienia.klientID = klienci.klientID " +
                          "inner join uzytkownicy on  klienci.uzytkownikID = uzytkownicy.uzytkownikID " +
                          "where uzytkownicy.login = '" + username + "'");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            if(!rs.first()){
                    sql = ("select * from zamowienia " +
                               "inner join pracownicy on zamowienia.pracownikID = pracownicy.pracownikID " +
                               "inner join uzytkownicy on  pracownicy.uzytkownikID = uzytkownicy.uzytkownikID " +
                               "where uzytkownicy.login = '" + username + "'"); 
                     preparedStatement = con.prepareStatement(sql);
                     rs = preparedStatement.executeQuery();
            }
            rs.beforeFirst();
            while(rs.next()){
                Zamowienie zamowienie = new Zamowienie();
                zamowienie.setZamowienieID(rs.getInt("zamowienieID"));
                zamowienie.setProduktID(rs.getInt("produktID"));
                zamowienie.setKlientOD(rs.getInt("klientID"));
                zamowienie.setOddzialID(rs.getInt("oddzialID"));
                zamowienie.setStatus(rs.getString("status"));
                zamowienie.setPracownikID(rs.getInt("pracownikID"));
                zamowienie.setDataZlozeniaZamowienia(rs.getDate("dataZlozeniaZamowienia").toString());
                list.add(zamowienie);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "sprawdzLogin")
    public boolean sprawdzLogin(@WebParam(name = "username") String username) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Zamowienie> list = new ArrayList<>();
        ResultSet rs = null;
        int state = 0;
        try {

            st = con.createStatement();
            String sql = ("select count(*) from uzytkownicy where login = '" + username + "'");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                state = rs.getInt(1);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(state == 0){
            return false;
        } else
            return true;
    }
    
    @WebMethod(operationName = "getKategoriaProducenci")
    public List<Producenci> getKategoriaProducenci(@WebParam(name = "kategoria") String kategoria) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Producenci> list = new ArrayList<>();
        ResultSet rs = null;
        try {

            st = con.createStatement();
            String sql = (   " SELECT producenci.producentID,producenci.nazwa from kategorieproducent " +
                "inner join kategorie on kategorie.kategoriaID = kategorieproducent.kategoriaID " + 
                "inner join producenci on producenci.producentID = kategorieproducent.producentID " +
                "where kategorie.nazwa = '" + kategoria + "';");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Producenci producent = new Producenci();
                producent.setProducentID(rs.getInt("producentID"));
                producent.setNazwa(rs.getString("nazwa"));
                list.add(producent);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getProduktyProducent")
    public List<Produkty> getProduktyProducent(@WebParam(name = "producentID") int producentID,@WebParam(name = "poczatek") int poczatek,@WebParam(name = "ilosc") int ilosc) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Produkty> list = new ArrayList<>();
        ResultSet rs = null;
        try {

            st = con.createStatement();
            String sql = ( "select * from produkty where producentID = " + Integer.toString(producentID) + " limit " + Integer.toString(poczatek) + "," + Integer.toString(ilosc) +";");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Produkty produkt = new Produkty();
                produkt.setProduktID(rs.getInt("produktID"));
                produkt.setProducentID(rs.getInt("producentID"));
                produkt.setNazwa(rs.getString("nazwa"));
                produkt.setKategoriaID(rs.getInt("kategoriaID"));
                produkt.setCena(rs.getFloat("cena"));
                list.add(produkt);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @WebMethod(operationName = "getProdukt")
    public Produkt getProdukt(@WebParam(name = "produktID") int produktID) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        Produkt produkt = new Produkt();
        ResultSet rs = null;
        try {

            st = con.createStatement();
            String sql = ( "select producenci.nazwa,produkty.nazwa,produkty.cena from produkty,producenci where produktID = " + produktID +" && producenci.producentID = produkty.producentID;");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                produkt.setNazwaProducenta(rs.getString(1));
                produkt.setNazwaProduktu(rs.getString(2));
                produkt.setCena(rs.getFloat("cena"));
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produkt;
    }
    
    @WebMethod(operationName = "getKlientPrzezUzytkownikID")
    public Klienci getKlientPrzezUzytkownikID(@WebParam(name = "uzytkownikID") int uzytkownikID) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        Klienci klient = new Klienci();
        ResultSet rs = null;
        try {

            st = con.createStatement();
            String sql = ( "select klienci.klientID, klienci.uzytkownikID,klienci.stanKonta,klienci.dataZalozenia from klienci " +
                        "inner join uzytkownicy on uzytkownicy.uzytkownikID  = klienci.uzytkownikID  where uzytkownicy.uzytkownikID = " + uzytkownikID + ";");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                klient.setKlientID(rs.getInt("klientID"));
                klient.setUzytkownikID(rs.getInt("uzytkownikID"));
                klient.setStanKonta(rs.getFloat("stanKonta"));
                klient.setDataZalozenia(rs.getDate("dataZalozenia"));
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return klient;
    }
    
   
    @WebMethod(operationName = "get3NajgorzejSprzedajaceProdukty")
    public List<Produkty> get3NajgorzejSprzedajaceProdukty() {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        List<Produkty> list = new ArrayList<>();
        ResultSet rs = null;
        try {

            st = con.createStatement();
            String sql = ( "select produkty.produktID, produkty.producentID, produkty.kategoriaID, produkty.nazwa, produkty.cena from zamowienia " +
                "inner join produkty on produkty.produktID = zamowienia.produktID group by zamowienia.produktID order by count(*) limit 0,3;");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Produkty produkt = new Produkty();
                produkt.setProduktID(rs.getInt("produktID"));
                produkt.setProducentID(rs.getInt("producentID"));
                produkt.setNazwa(rs.getString("nazwa"));
                produkt.setKategoriaID(rs.getInt("kategoriaID"));
                produkt.setCena(rs.getFloat("cena"));
                list.add(produkt);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     @WebMethod(operationName = "loginHaslo")
    public boolean loginHaslo(@WebParam(name = "login") String login,@WebParam(name = "haslo")String haslo) {
        Connection con = MysqlConnection.connect(dbAddress,user,password);
        Statement st;
        ResultSet rs = null;
        int state = 0;
        try {

            st = con.createStatement();
            String sql = ("select * from uzytkownicy where login = '" +  login + "' and haslo = '" + haslo + "' ;");
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
             while(rs.next()){
                state = rs.getInt(1);
            }
            preparedStatement.close();
 
            con.close();          
           
        } catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(state == 0){
            return false;
        } else
            return true;
    }
}
