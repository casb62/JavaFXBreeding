package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javax.swing.JOptionPane;

public class Location {
    
    private Integer id;
    private SimpleStringProperty type;
    private SimpleStringProperty publicPlace;
    private SimpleStringProperty number;
    private SimpleStringProperty complement;
    private SimpleStringProperty mailbox;
    private SimpleStringProperty neighborhood;
    private SimpleStringProperty city;
    private SimpleStringProperty state;
    private SimpleStringProperty country;
    private SimpleStringProperty zipcode;
    private SimpleStringProperty latitude;
    private SimpleStringProperty longitude;
    private SimpleStringProperty altitude;

    public Location() {
    }

    public Location(Integer id, String type, String publicPlace, String number, String complement, String mailbox, String neighborhood, String city, String state, String country, String zipcode, String latitude, String longitude, String altitude) {
        this.id = id;
        this.type = new SimpleStringProperty(type);
        this.publicPlace = new SimpleStringProperty(publicPlace);
        this.number = new SimpleStringProperty(number);
        this.complement = new SimpleStringProperty(complement);
        this.mailbox = new SimpleStringProperty(mailbox);
        this.neighborhood = new SimpleStringProperty(neighborhood);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.country = new SimpleStringProperty(country);
        this.zipcode = new SimpleStringProperty(zipcode);
        this.latitude = new SimpleStringProperty(latitude);
        this.longitude = new SimpleStringProperty(longitude);
        this.altitude = new SimpleStringProperty(altitude);
    }
    
    ConnectionWithBreeding cwb = new ConnectionWithBreeding();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    public String getPublicPlace() {
        return publicPlace.get();
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = new SimpleStringProperty(publicPlace);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number = new SimpleStringProperty(number);
    }

    public String getComplement() {
        return complement.get();
    }

    public void setComplement(String complement) {
        this.complement = new SimpleStringProperty(complement);
    }

    public String getMailbox() {
        return mailbox.get();
    }

    public void setMailbox(String mailbox) {
        this.mailbox = new SimpleStringProperty(mailbox);
    }

    public String getNeighborhood() {
        return neighborhood.get();
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = new SimpleStringProperty(neighborhood);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city = new SimpleStringProperty(city);
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state = new SimpleStringProperty(state);
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country = new SimpleStringProperty(country);
    }

    public String getZipcode() {
        return zipcode.get();
    }

    public void setZipcode(String zipcode) {
        this.zipcode = new SimpleStringProperty(zipcode);
    }

    public String getLatitude() {
        return latitude.get();
    }

    public void setLatitude(String latitude) {
        this.latitude = new SimpleStringProperty(latitude);
    }

    public String getLongitude() {
        return longitude.get();
    }

    public void setLongitude(String longitude) {
        this.longitude = new SimpleStringProperty(longitude);
    }

    public String getAltitude() {
        return altitude.get();
    }

    public void setAltitude(String altitude) {
        this.altitude = new SimpleStringProperty(altitude);
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", type=" + type + ", publicPlace=" + publicPlace + ", number=" + number + ", complement=" + complement + ", mailbox=" + mailbox + ", neighborhood=" + neighborhood + ", city=" + city + ", state=" + state + ", country=" + country + ", zipcode=" + zipcode + ", latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + '}';
    }
    
    public void saveLocation(Location location){
        cwb.connect();
        try{
           PreparedStatement pst = cwb.connection.prepareStatement("INSERT INTO location(type, publicplace, number, complement, mailbox, neighborhood, city, state, country, zipcode, latitude, longitude, altitude) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
           pst.setString(1, location.getType());
           pst.setString(2, location.getPublicPlace());
           pst.setString(3, location.getNumber());
           pst.setString(4, location.getComplement());
           pst.setString(5, location.getMailbox());
           pst.setString(6, location.getNeighborhood());
           pst.setString(7, location.getCity());
           pst.setString(8, location.getState());
           pst.setString(9, location.getCountry());
           pst.setString(10, location.getZipcode());
           pst.setString(11, location.getLatitude());
           pst.setString(12, location.getLongitude());
           pst.setString(13, location.getAltitude());
           pst.execute();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao inserir localização no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    }
    
    public Location searchLocationOfFarm(Integer farmId){
        Location location = new Location();
        int locationId = 0;
        cwb.connect();
        cwb.executeQuery("SELECT * FROM location WHERE location.id IN (SELECT fk_location_id FROM need WHERE fk_farm_id='"+farmId+"')");
        try {
            cwb.resultSet.first();
            do {
                location.setId(cwb.resultSet.getInt("id"));
                location.setType(cwb.resultSet.getString("type"));
                location.setPublicPlace(cwb.resultSet.getString("publicPlace"));
                location.setNumber(cwb.resultSet.getString("number"));
                location.setComplement(cwb.resultSet.getString("complement"));
                location.setMailbox(cwb.resultSet.getString("mailbox"));
                location.setNeighborhood(cwb.resultSet.getString("neighborhood"));
                location.setCity(cwb.resultSet.getString("city"));
                location.setState(cwb.resultSet.getString("state"));
                location.setCountry(cwb.resultSet.getString("country"));
                location.setZipcode(cwb.resultSet.getString("zipcode"));
                location.setLatitude(cwb.resultSet.getString("latitude"));
                location.setLongitude(cwb.resultSet.getString("longitude"));
                location.setAltitude(cwb.resultSet.getString("altitude"));
            } while (cwb.resultSet.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar endereço no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    return location;
    }
    
    public Location searchLocationOfPerson(Integer personId){
        Location location = new Location();
        int locationId = 0;
        cwb.connect();
        cwb.executeQuery("SELECT * FROM location WHERE location.id IN (SELECT fk_location_id FROM has WHERE fk_person_id='"+personId+"')");
        try {
            cwb.resultSet.first();
            do {
                location.setId(cwb.resultSet.getInt("id"));
                location.setType(cwb.resultSet.getString("type"));
                location.setPublicPlace(cwb.resultSet.getString("publicPlace"));
                location.setNumber(cwb.resultSet.getString("number"));
                location.setComplement(cwb.resultSet.getString("complement"));
                location.setMailbox(cwb.resultSet.getString("mailbox"));
                location.setNeighborhood(cwb.resultSet.getString("neighborhood"));
                location.setCity(cwb.resultSet.getString("city"));
                location.setState(cwb.resultSet.getString("state"));
                location.setCountry(cwb.resultSet.getString("country"));
                location.setZipcode(cwb.resultSet.getString("zipcode"));
                location.setLatitude(cwb.resultSet.getString("latitude"));
                location.setLongitude(cwb.resultSet.getString("longitude"));
                location.setAltitude(cwb.resultSet.getString("altitude"));
            } while (cwb.resultSet.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar endereço no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    return location;
    }
    
    public void editLocation(Location location){
        cwb.connect();
        try {
            PreparedStatement pst = cwb.connection.prepareStatement("UPDATE location SET type = ?, publicplace = ?, number = ?, complement = ?, mailbox = ?, neighborhood = ?, city = ?, state = ?, country = ?, zipcode = ?, latitude = ?, longitude = ?, altitude = ? WHERE id = ?");
            pst.setString(1, location.getType());
            pst.setString(2, location.getPublicPlace());
            pst.setString(3, location.getNumber());
            pst.setString(4, location.getComplement());
            pst.setString(5, location.getMailbox());
            pst.setString(6, location.getNeighborhood());
            pst.setString(7, location.getCity());
            pst.setString(8, location.getState());
            pst.setString(9, location.getCountry());
            pst.setString(10, location.getZipcode());
            pst.setString(11, location.getLatitude());
            pst.setString(12, location.getLongitude());
            pst.setString(13, location.getAltitude());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados da localização no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    }
}
