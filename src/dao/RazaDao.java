
package dao;

import connection.MySQLConnection;
//import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Raza;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



public class RazaDao extends MySQLConnection{
    
    
    public void buscar(){
        
    }
    
    public boolean buscarId( Raza raz){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conectar();
 
        String sql = "SELECT raza_id, raza_descripcion, raza_estado, esp_descripcion, esp_id FROM raza NATURAL JOIN especie WHERE raza_id=?;";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, raz.getRazaId());
            rs = ps.executeQuery();
 
            
            if (rs.next()) {
                raz.setRazaId(rs.getString("raza_id"));
                raz.setRazaDescrip(rs.getString("raza_descripcion"));
                raz.setRazaEst(rs.getString("raza_estado"));
                raz.setRazaEspID(rs.getString("esp_id"));
                raz.setRazaDesEspecie(rs.getString("esp_descripcion"));
                
                return true;
                
            }
            
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    
    public boolean add(Raza raz) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = conectar();
    
    try {
        
        String sql = "INSERT INTO raza (raza_descripcion, raza_estado, esp_id) VALUES (?, ?, ?)";
        ps = con.prepareStatement(sql);
        ps.setString(1, raz.getRazaDescrip());
        ps.setString(2, raz.getRazaEst());
        int idEspecie = Integer.parseInt(raz.getRazaEspID());
        ps.setInt(3, idEspecie);
        
        int rowsAffected = ps.executeUpdate();
        
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.err.println("Error al insertar datos en la tabla: " + e.getMessage());
        return false;
    } 
}
    
    public boolean existeDescripcion(String descripcion) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = conectar();
    
    try {
        String sql = "SELECT COUNT(*) AS total FROM raza WHERE raza_descripcion = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, descripcion);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            int total = rs.getInt("total");
            return total > 0; 
        }
    } catch (SQLException e) {
        System.err.println("Error al verificar si la descripción existe: " + e.getMessage());
    }
    
    return false;
}
    
    public boolean Update(Raza raz){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conectar();
         
        try {
            String sql = "UPDATE raza r INNER JOIN especie e ON r.esp_id = e.esp_id SET r.raza_descripcion = ?, r.raza_estado = ?, r.esp_id = ? WHERE r.raza_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, raz.getRazaDescrip());
            ps.setString(2, raz.getRazaEst());
            ps.setString(3, raz.getRazaEspID());
            ps.setString(4, raz.getRazaId());
            ps.execute();

            return true;
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el registro: " + e.getMessage());
             return false;
        }
        
    }
    
    public boolean Eliminar(int id) {
    PreparedStatement ps = null;
    Connection con = conectar();
    try {
        String sql = "DELETE FROM raza WHERE raza_id = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + e.getMessage());
        return false;
    }
}
   
    public Raza botonBuscar(int id) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = conectar();
    Raza raz = null;

    String sql = "SELECT raza_id, raza_descripcion, raza_estado, esp_descripcion, esp_id FROM raza NATURAL JOIN especie WHERE raza_id = ?";
    
    try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            raz = new Raza();
            raz.setRazaId(rs.getString("raza_id"));
            raz.setRazaDescrip(rs.getString("raza_descripcion"));
            raz.setRazaEst(rs.getString("raza_estado"));
            raz.setRazaEspID(rs.getString("esp_id"));
            raz.setRazaDesEspecie(rs.getString("esp_descripcion"));
            
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar por ID: " + e.getMessage());
    }

    return raz;
}
    
    
    public List<Raza> getAllRaza(int activo) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Raza> raza = new ArrayList<>();
    Connection con = conectar();
    
    try {
        String sql;
        if (activo == 1) {
            sql = "SELECT raza_id, raza_descripcion, raza_estado, esp_descripcion, esp_id FROM raza NATURAL JOIN especie WHERE raza_estado = 'Activo' ORDER BY raza_id";
        } else {
            sql = "SELECT raza_id, raza_descripcion, raza_estado, esp_descripcion, esp_id FROM raza NATURAL JOIN especie ORDER BY raza_id";
        }
        
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Raza ra = new Raza();
            ra.setRazaId(rs.getString("raza_id"));
            ra.setRazaDescrip(rs.getString("raza_descripcion"));
            ra.setRazaEst(rs.getString("raza_estado"));
            ra.setRazaEspID(rs.getString("esp_id"));
            ra.setRazaDesEspecie(rs.getString("esp_descripcion"));
            raza.add(ra);
        }
    } catch (SQLException e) {
        System.err.println(e);
    }
    
    return raza;
}
    
    public int getIdEspecie(String nombreEspecie) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = conectar();
    int idEspecie = -1; // Valor predeterminado si no se encuentra la especie
    
    String sql = "SELECT esp_id FROM especie WHERE esp_descripcion = ?";
    
    try {
        ps = con.prepareStatement(sql);
        ps.setString(1, nombreEspecie);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            idEspecie = rs.getInt("esp_id");
        }
    } catch (SQLException e) {
        System.err.println(e);
    } 
    
    return idEspecie;
}
    
    
}
