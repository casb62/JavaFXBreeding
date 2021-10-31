package model;

import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javax.swing.JOptionPane;

public class Person {
    
    private Integer id;
    private SimpleStringProperty name;
    private SimpleStringProperty fone;
    private SimpleStringProperty fax;
    private SimpleStringProperty email;
    private SimpleStringProperty password;
    private Boolean logged;
    private SimpleStringProperty cpf;
    private SimpleStringProperty cnpj;

    public Person() {
    }

    public Person(Integer id, String name, String fone, String fax, String email, String password, Boolean logged, String cpf, String cnpj) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.fone = new SimpleStringProperty(fone);
        this.fax = new SimpleStringProperty(fax);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.logged = logged;
        this.cpf = new SimpleStringProperty(cpf);
        this.cnpj = new SimpleStringProperty(cnpj);
    }

    ConnectionWithBreeding cwb = new ConnectionWithBreeding();

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

    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String cpf) {
        this.cpf = new SimpleStringProperty(cpf);
    }

    public String getCnpj() {
        return cnpj.get();
    }

    public void setCnpj(String cnpj) {
        this.cnpj = new SimpleStringProperty(cnpj);
    }
    
    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", fone=" + fone + ", fax=" + fax + ", email=" + email + ", password=" + password + ", logged=" + logged + '}';
    }
    
    public Person searchPerson(Integer personId) {
        Person person = new Person();
        cwb.connect();
        cwb.executeQuery("SELECT * FROM person WHERE id='" + personId + "'");
        try{
            cwb.resultSet.first();
            do {
                person.setId(cwb.resultSet.getInt("id"));
                person.setName(cwb.resultSet.getString("name"));
                person.setFone(cwb.resultSet.getString("fone"));
                person.setFax(cwb.resultSet.getString("fax"));
                person.setEmail(cwb.resultSet.getString("email"));
                person.setCpf(cwb.resultSet.getString("cpf"));
                person.setCnpj(cwb.resultSet.getString("cnpj"));
            } while (cwb.resultSet.next());
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar pessoa no banco de dados.\nErro: " + ex);
        } 
        cwb.disconnect();
        return person;
    }
}