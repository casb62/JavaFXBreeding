package model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class User extends PhysicalPerson{
    
    public User() {
    }

    public User(Integer id, String name, String fone, String fax, String email, String password, Boolean logged, String cpf) {
        super(id, name, fone, fax, email, password, logged, cpf);
    }

    ConnectionWithBreeding connection = new ConnectionWithBreeding();
    
    public void saveUser(User user){
        connection.connect();
        try {
            PreparedStatement pst = connection.connection.prepareStatement("INSERT INTO person(name, fone, fax, email, password, uservalidated, cpf, cnpj) VALUES(?,?,?,?,?,?,?,?)");
            pst.setString(1, user.getName());
            pst.setString(2, null);
            pst.setString(3, null);
            pst.setString(4, null);
            pst.setString(5, user.getPassword());
            pst.setBoolean(6, false);
            pst.setString(7, user.getCpf());
            pst.setString(8, null);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuário no banco de dados.\nErro: " + ex);
        }
        connection.disconnect();
    }
    
    public User searchUser(User user){
        connection.connect();
        connection.executeQuery("SELECT * FROM person WHERE name LIKE '%" + user.getName() + "%'");
        try {
            connection.resultSet.first();
            do {
                user.setId(connection.resultSet.getInt("id"));
                user.setName(connection.resultSet.getString("name"));
                user.setCpf(connection.resultSet.getString("cpf"));
                user.setPassword(connection.resultSet.getString("password"));
            } while (connection.resultSet.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar usuário no banco de dados.\nErro: " + ex);
        }
        connection.disconnect();
    return user;
    }
    
    public void editUser(User user){
        connection.connect();
        try {
            PreparedStatement pst = connection.connection.prepareStatement("UPDATE person SET name = ?, fone = ?, fax = ?, email = ?, password = ?, uservalidated = ?, cpf = ?, cnpj = ? WHERE id = ?");
            pst.setString(1, user.getName());
            pst.setString(2, user.getFone());
            pst.setString(3, user.getFax());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPassword());
            pst.setBoolean(6, false);
            pst.setString(7, user.getCpf());
            pst.setString(8, null);
            pst.setInt(9, user.getId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados do usuário no banco de dados.\nErro: " + ex);
        }
        connection.disconnect();
    }
    
    public void deleteUser(User user){
        connection.connect();
        try {
            PreparedStatement pst = connection.connection.prepareStatement("DELETE FROM person WHERE id = ?");
            pst.setInt(1, user.getId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o usuário no banco de dados.\nErro: " + ex);
        }
        connection.disconnect();
    }
    
    public void generatePdf(){
        Document doc = new Document();
        try {
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:/Teste.pdf"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            doc.open();
            doc.add(new Paragraph("Primeiro parágrafo do pdf"));
            doc.close();
        } catch (DocumentException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
