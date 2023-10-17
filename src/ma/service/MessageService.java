/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.service;

/**
 *
 * @author SIMO LAAOUIBI
 */

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.beans.Message;
import ma.connexion.Connexion;
import ma.dao.IDao;

/**
 *
 * @author PC
 */
public class MessageService implements IDao<Message> {

    private EmployeService es;

    public MessageService() {
        es = new EmployeService();
    }

    @Override
    public boolean create(Message o) {
        try {
            String req = "insert into message (objet, sujet, date, idE, idR) values(?,?,?,?,?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getObject());
            ps.setString(2, o.getSujet());
            ps.setDate(3, new Date(o.getDate().getTime()));
            ps.setInt(4, o.getEmpEmetteur().getId());
            ps.setInt(5, o.getEmpRecepteur().getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Message o) {
        return false;
    }

    @Override
    public boolean delete(Message o) {
        return false;
    }

    @Override
    public Message findById(int id) {
        Message employe = null;
        try {
            String req = "select * from message where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employe = new Message(rs.getString("objet"), rs.getString("sujet"), rs.getDate("date"), es.findById(rs.getInt("idE")), es.findById(rs.getInt("idR")));
                       
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employe;
    }

    @Override
    public List<Message> findAll() {
        List<Message> employes = new ArrayList<>();
        try {
            String req = "select * from message ";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employes.add(new Message(rs.getInt("id"), rs.getString("objet"), rs.getString("sujet"), rs.getDate("date"), es.findById(rs.getInt("idE")), es.findById(rs.getInt("idR"))));
                       
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employes;
    
    }

    @Override
    public boolean createTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

