package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConnectionWithBreeding {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/breeding";
    public Connection connection = null;//manage the connection
    public Statement statement = null;//query instruction
    public ResultSet resultSet = null;//manage results
        
    public void connect(){
        try {
            connection = DriverManager.getConnection(DATABASE_URL, "postgres", "breeding");
                
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados:\n" + sqlException.getMessage());
        }
    }
    
    public void disconnect(){
        try {
            connection.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão com o banco de dados:\n" + exception.getMessage());
        }
    }
    
    public void executeQuery(String sql){
        try {
            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados:\n" + ex.getMessage());
        }
    }
    
    public List<User> selectAllUsers() throws SQLException{
        List<User> list = new ArrayList();
        this.connect();
        this.executeQuery("SELECT * FROM person");
        this.resultSet.first();
            do {
                int id = this.resultSet.getInt("id"); 
                String name = this.resultSet.getString("name");
                String cpf = this.resultSet.getString("cpf");
                String password = this.resultSet.getString("password");
                User user = new User(id, name, null, null, null, password, false, cpf);
                list.add(user);
            } while (this.resultSet.next());
        this.disconnect();
        return list;
    }
    
    public List<Farm> selectAllFarms() throws SQLException{
        List<Farm> list = new ArrayList();
        this.connect();
        this.executeQuery("SELECT * FROM farm");
        this.resultSet.first();
            do {
                int id = this.resultSet.getInt("id"); 
                String name = this.resultSet.getString("name");
                String stateRegistry = this.resultSet.getString("stateRegistry");
                int personId = this.resultSet.getInt("personId");
                Farm farm = new Farm(id, name, stateRegistry, personId);
                list.add(farm);
            } while (this.resultSet.next());
        this.disconnect();
        return list;
    }
}