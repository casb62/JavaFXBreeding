package model;

import javafx.beans.property.SimpleStringProperty;

public class PhysicalPerson extends Person {
    
    private SimpleStringProperty cpf;

    public PhysicalPerson() {
    }

    public PhysicalPerson(Integer id, String name, String fone, String fax, String email, String password, Boolean logged, String cpf) {
        super(id, name, fone, fax, email, password, logged);
        this.cpf = new SimpleStringProperty(cpf);
    }

    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String cpf) {
        this.cpf = new SimpleStringProperty(cpf);
    }

    @Override
    public String toString() {
        return "PhysicalPerson{" + "Id=" + super.getId() + "Nome=" + super.getName() + "Fone=" + super.getFone() + "Fax=" + super.getFax() + "Email=" + super.getEmail() + "Logado=" + super.getLogged() + "cpf=" + cpf + '}';
    }
}