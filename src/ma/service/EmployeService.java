/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.beans.Employe;
import ma.connexion.Connexion;
import ma.dao.IDao;

/**
 *
 * @author SIMO LAAOUIBI
 */

public class EmployeService implements IDao<Employe> {

    public boolean createTable() {
        try {
            Statement st = Connexion.getConnection().createStatement();
            String req = "CREATE TABLE IF NOT EXISTS ‘employe‘ (\n"
                    + "‘id‘ int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "‘nom‘ varchar(50) NOT NULL,\n"
                    + "‘prenom‘ varchar(50) NOT NULL,\n"
                    + "PRIMARY KEY (‘id‘)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;";

            return true;

        } catch (SQLException e) {
            System.out.println("ereur sql" + e.getMessage());
            return false;
        }

    }

    @Override
    public boolean create(Employe o) {
        try {
            String req = "insert into employe (nom, prenom) values(?,?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ereur" + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Employe o) {
        try {
            String req = "update employe set nom = ? , prenom = ? where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setInt(3, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ereur" + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Employe o) {
        try {
            String req = "delete from employe where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);

            ps.setInt(1, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
                   
        }
        return false;
    }

    @Override
    public Employe findById(int id) {
        Employe employe = null;
        try {
            String req = "select * from employe where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employe = new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
                   
        }
        return employe;
    }

    @Override
    public List<Employe> findAll() {
        List<Employe> employes = new ArrayList<>();
        try {
            String req = "select * from employe ";
            PreparedStatement ps= Connexion.getConnection().prepareStatement(req);
                    
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employes.add(new Employe(rs.getInt("id"),
                        rs.getString("nom"), rs.getString("prenom")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
                   
        }
        return employes;
    }
}

