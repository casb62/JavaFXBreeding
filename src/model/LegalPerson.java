package model;

import javafx.beans.property.SimpleStringProperty;

public class LegalPerson extends Person {
    
    private SimpleStringProperty cnpj;

    public LegalPerson() {
    }

    public LegalPerson(Integer id, String name, String fone, String fax, String email, String password, Boolean loggedUser, String cnpj) {
        super(id, name, fone, fax, email, password, loggedUser);
        this.cnpj = new SimpleStringProperty(cnpj);
    }

    public String getCnpj() {
        return cnpj.get();
    }

    public void setCnpj(String cnpj) {
        this.cnpj = new SimpleStringProperty(cnpj);
    }

    @Override
    public String toString() {
        return "LegalPerson{" + "Id=" + super.getId() + "Nome=" + super.getName() + "Fone=" + super.getFone() + "Fax=" + super.getFax() + "Email=" + super.getEmail() + "Logado=" + super.getLogged() + "cnpj=" + cnpj + '}';
    }
}