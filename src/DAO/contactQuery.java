package DAO;

import Model.Contact;
import Model.User;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class contactQuery {
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        String sql = "SELECT *" +
                "FROM client_schedule.contacts;";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String contactEmail = rs.getString("Email");
            Contact contact = new Contact(contactId, contactName, contactEmail);
            allContacts.add(contact);
        }
        return allContacts;
    }

    public static Contact getContactById(int contactId) throws SQLException {
        String sql = "Select * FROM client_schedule.contacts WHERE Contact_ID=" + contactId + ";";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String contactName = rs.getString("Contact_Name");
        String contactEmail = rs.getString("Email");
        Contact contact = new Contact(contactId, contactName, contactEmail);
        return contact;
    }
}
