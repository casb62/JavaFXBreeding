package model;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    
    private Integer id;
    private SimpleStringProperty name;
    private SimpleStringProperty fone;
    private SimpleStringProperty fax;
    private SimpleStringProperty email;
    private SimpleStringProperty password;
    private Boolean logged;

    public Person() {
    }

    public Person(Integer id, String name, String fone, String fax, String email, String password, Boolean logged) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.fone = new SimpleStringProperty(fone);
        this.fax = new SimpleStringProperty(fax);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.logged = logged;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getFone() {
        return fone.get();
    }

    public void setFone(String fone) {
        this.fone = new SimpleStringProperty(fone);
    }

    public String getFax() {
        return fax.get();
    }

    public void setFax(String fax) {
        this.fax = new SimpleStringProperty(fax);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", fone=" + fone + ", fax=" + fax + ", email=" + email + ", password=" + password + ", logged=" + logged + '}';
    }
}