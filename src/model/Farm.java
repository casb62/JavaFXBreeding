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
import javafx.beans.property.SimpleStringProperty;
import javax.swing.JOptionPane;

public class Farm {
    
    private Integer id;
    private SimpleStringProperty name;
    private SimpleStringProperty stateRegistry;
    private Integer personId;

    public Farm() {
    }

    public Farm(Integer id, String name, String stateRegistry, Integer personId) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.stateRegistry = new SimpleStringProperty(stateRegistry);
        this.personId = personId;
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

    public String getStateRegistry() {
        return stateRegistry.get();
    }

    public void setStateRegistry(String stateRegistry) {
        this.stateRegistry = new SimpleStringProperty(stateRegistry);
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Farm{" + "id=" + id + ", name=" + name + ", stateRegistry=" + stateRegistry + ", personId=" + personId + '}';
    }
    
    public void saveFarm(Farm farm){
        cwb.connect();
        try{
           PreparedStatement pst = cwb.connection.prepareStatement("INSERT INTO farm(name, stateregistry, fk_person_Id) VALUES(?,?,?)");
           pst.setString(1, farm.getName());
           pst.setString(2, farm.getStateRegistry());
           pst.setInt(3, farm.getPersonId());
           pst.execute();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao inserir propriedade no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    }
    
    public Farm searchFarm(Farm farm){
        cwb.connect();
        cwb.executeQuery("SELECT * FROM farm WHERE name LIKE '%" + farm.getName() + "%'");
        try {
            cwb.resultSet.first();
            do {
                farm.setId(cwb.resultSet.getInt("id"));
                farm.setName(cwb.resultSet.getString("name"));
                farm.setStateRegistry(cwb.resultSet.getString("stateRegistry"));
                farm.setPersonId(cwb.resultSet.getInt("personId"));
            } while (cwb.resultSet.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar propriedade no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    return farm;
    }
    
    public void editFarm(Farm farm){
        cwb.connect();
        try {
            PreparedStatement pst = cwb.connection.prepareStatement("UPDATE farm SET name = ?, stateRegistry = ?, personId = ? WHERE id = ?");
            pst.setString(1, farm.getName());
            pst.setString(2, farm.getStateRegistry());
            pst.setInt(3, farm.getPersonId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados da propriedade no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    }
    
    public void deleteFarm(Farm farm){
        cwb.connect();
        try {
            PreparedStatement pst = cwb.connection.prepareStatement("DELETE FROM farm WHERE id = ?");
            pst.setInt(1, farm.getId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a propriedade no banco de dados.\nErro: " + ex);
        }
        cwb.disconnect();
    }
    
    public void generatePdf() throws FileNotFoundException, DocumentException, IOException{
        cwb.connect();
        Document doc = new Document();
        List<Farm> farms = null;
        File arq = new File("C:\\Windows\\Temp\\Relatório.pdf");
        try {
            farms = cwb.selectAllFarms();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao percorrer resultSet:\n" + ex.getMessage());
        }
        PdfWriter.getInstance(doc, new FileOutputStream(arq));
        doc.open();
        Paragraph p = new Paragraph("Propriedades cadastradas");
        p.setAlignment(1);
        doc.add(p);
        p = new Paragraph(" ");
        doc.add(p);
        PdfPTable table = new PdfPTable(3);
        
        PdfPCell cel1 = new PdfPCell(new Paragraph("ID"));
        PdfPCell cel2 = new PdfPCell(new Paragraph("NOME"));
        PdfPCell cel3 = new PdfPCell(new Paragraph("STATE REGISTRY"));
        PdfPCell cel4 = new PdfPCell(new Paragraph("ID DO PROPRIETÁRIO"));
        
        table.addCell(cel1);
        table.addCell(cel2);
        table.addCell(cel3);
        table.addCell(cel4);
        
        for(Farm farm: farms){
            cel1 = new PdfPCell(new Paragraph(farm.getId() + ""));
            cel2 = new PdfPCell(new Paragraph(farm.getName()));
            cel3 = new PdfPCell(new Paragraph(farm.getStateRegistry()));
            cel4 = new PdfPCell(new Paragraph(farm.getPersonId()));

            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);
            table.addCell(cel4);
        }
        doc.add(table);
        doc.close();
        cwb.disconnect();
        Desktop.getDesktop().open(arq);
    }
}
