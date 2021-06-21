package model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class User extends PhysicalPerson{
    
    public User() {
    }

    public User(Integer id, String name, String fone, String fax, String email, String password, Boolean logged, String cpf) {
        super(id, name, fone, fax, email, password, logged, cpf);
    }

    ConnectionWithBreeding cwb = new ConnectionWithBreeding();
    
    public void saveUser(User user){
        cwb.connect();
        try {
            PreparedStatement pst = cwb.connection.prepareStatement("INSERT INTO person(name, fone, fax, email, password, uservalidated, cpf, cnpj) VALUES(?,?,?,?,?,?,?,?)");
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
        cwb.disconnect();
    }
    
    public User searchUser(User user){
        cwb.connect();
        cwb.executeQuery("SELECT * FROM person WHERE name LIKE '%" + user.getName() + "%'");
        try {
            cwb.resultSet.first();
            do {
                user.setId(cwb.resultSet.getInt("id"));
                user.setName(cwb.resultSet.getString("name"));
                user.setCpf(cwb.resultSet.getString("cpf"));
                user.setPassword(cwb.resultSet.getString("password"));
            } while (cwb.resultSet.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar usuário no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    return user;
    }
    
    public void editUser(User user){
        cwb.connect();
        try {
            PreparedStatement pst = cwb.connection.prepareStatement("UPDATE person SET name = ?, fone = ?, fax = ?, email = ?, password = ?, uservalidated = ?, cpf = ?, cnpj = ? WHERE id = ?");
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
        cwb.disconnect();
    }
    
    public void deleteUser(User user){
        cwb.connect();
        try {
            PreparedStatement pst = cwb.connection.prepareStatement("DELETE FROM person WHERE id = ?");
            pst.setInt(1, user.getId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o usuário no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    }
    
//    public void generatePdf(){
//        cwb.connect();
//        cwb.executeQuery("SELECT * FROM person");
//        Document doc = new Document();
//        File arq = new File("C:\\Windows\\Temp\\Relatório.pdf");
//        try {
//            PdfWriter.getInstance(doc, new FileOutputStream(arq));
//            doc.open();
//            Paragraph p1 = new Paragraph("Usuários cadastrados");
//            p1.setAlignment(1);
//            doc.add(p1);
//            cwb.resultSet.first();
//            do {
//                Paragraph p2 = new Paragraph(cwb.resultSet.getInt("id") + " - " + cwb.resultSet.getString("name") + " - " + cwb.resultSet.getString("cpf"));
//                doc.add(p2);
//                  Paragraph p3 = new Paragraph("                                 ");
//                  doc.add(p3);
//            } while (cwb.resultSet.next());
//            doc.close();
//            cwb.disconnect();
//        } catch (FileNotFoundException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao procurar arquivo:\n" + ex.getMessage());
//        } catch (DocumentException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao escrever em arquivo:\n" + ex.getMessage());
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao percorrer resultSet:\n" + ex.getMessage());
//        }
//        try {
//            Desktop.getDesktop().open(arq);
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo:\n" + ex.getMessage());
//        }
//    }
    
    public void generatePdf() throws FileNotFoundException, DocumentException, IOException{
        cwb.connect();
        Document doc = new Document();
        List<User> users = null;
        File arq = new File("C:\\Windows\\Temp\\Relatório.pdf");
        try {
            users = cwb.selectAllUsers();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao percorrer resultSet:\n" + ex.getMessage());
        }
        PdfWriter.getInstance(doc, new FileOutputStream(arq));
        doc.open();
        Paragraph p = new Paragraph("Usuários cadastrados");
        p.setAlignment(1);
        doc.add(p);
        p = new Paragraph(" ");
        doc.add(p);
        PdfPTable table = new PdfPTable(3);
        
        PdfPCell cel1 = new PdfPCell(new Paragraph("ID"));
        PdfPCell cel2 = new PdfPCell(new Paragraph("NOME"));
        PdfPCell cel3 = new PdfPCell(new Paragraph("CPF"));
        
        table.addCell(cel1);
        table.addCell(cel2);
        table.addCell(cel3);
        
        for(User user: users){
            cel1 = new PdfPCell(new Paragraph(user.getId() + ""));
            cel2 = new PdfPCell(new Paragraph(user.getName()));
            cel3 = new PdfPCell(new Paragraph(user.getCpf()));

            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);
        }
        doc.add(table);
        doc.close();
        cwb.disconnect();
        Desktop.getDesktop().open(arq);
    }
}