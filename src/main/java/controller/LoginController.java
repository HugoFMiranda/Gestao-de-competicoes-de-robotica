package controller;

import database.DBConnection;
import model.Equipa;
import model.Juri;
import model.Organizador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*

  @author Hugo
 */
/**
 * It's a controller class that verifies if the user is a team, a jury or an
 * organizer
 */
public class LoginController {

    private Equipa equipa;
    private Juri juri;
    private Organizador organizador;

    public LoginController() {
    }

    /**
     * VerfLoginEquipa(nome, password) returns true if the user is a team, a jury or
     * an organizer
     * 
     * @param nome     username
     * @param password String
     * @return The return value is the number of the user type.
     */
    public int verfLogin(String nome, String password) throws SQLException {

        if (verfLoginEquipa(nome, password)) {
            return 1;
        } else if (verfLoginJuri(nome, password)) {
            return 2;
        } else if (verfLoginOrganizador(nome, password)) {
            return 3;
        }
        return 0;
    }

    /**
     * It checks if the username and password are correct and if they are, it sets
     * the user's id,
     * username and password
     * 
     * @param nome     String
     * @param password "12345"
     * @return The method is returning a boolean value.
     */
    public boolean verfLoginEquipa(String nome, String password) throws SQLException {
        String GET_SQL = "SELECT * FROM Equipa WHERE nome = ? AND password = ?;";
        boolean res = false;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            ps.setString(1, nome);
            ps.setString(2, password);
            rset = ps.executeQuery();
            res = rset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res) {
            this.equipa = new Equipa();
            this.equipa.setId(rset.getInt("id"));
            this.equipa.setNome(rset.getString("nome"));
            this.equipa.setPassword(rset.getString("password"));
        }
        conn.close();
        return res;
    }

    /**
     * It checks if the user exists in the database and if it does, it creates a new
     * Juri object with
     * the data from the database
     * 
     * @param nome     name
     * @param password "123"
     * @return The method is returning a boolean value.
     */
    public boolean verfLoginJuri(String nome, String password)
            throws SQLException {
        String GET_SQL = "SELECT * FROM Elementos_juri WHERE nome = ? AND password = ?;";
        boolean res = false;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            ps.setString(1, nome);
            ps.setString(2, password);
            rset = ps.executeQuery();
            res = rset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res) {
            this.juri = new Juri();
            this.juri.setId(rset.getInt("id"));
            this.juri.setNome(rset.getString("nome"));
            this.juri.setPassword(rset.getString("password"));
        }
        conn.close();
        return res;
    }

    /**
     * It checks if the user exists in the database and if it does, it creates a new
     * Organizador object
     * with the data from the database
     * 
     * @param nome     String
     * @param password 123
     * @return The method is returning a boolean value.
     */
    public boolean verfLoginOrganizador(String nome, String password)
            throws SQLException {
        String GET_SQL = "SELECT * FROM Organizador WHERE nome = ? AND password = ?;";
        boolean res = false;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            ps.setString(1, nome);
            ps.setString(2, password);
            rset = ps.executeQuery();
            res = rset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res) {
            this.organizador = new Organizador();
            this.organizador.setId(rset.getInt("id"));
            this.organizador.setNome(rset.getString("nome"));
            this.organizador.setEmail(rset.getString("email"));
            this.organizador.setPassword(rset.getString("password"));
        }
        conn.close();
        return res;
    }

    /**
     * This function returns the team
     * 
     * @return The method is returning the value of the variable equipa.
     */
    public Equipa getEquipa() {
        return this.equipa;
    }

    /**
     * This function sets the value of the variable equipa to the value of the
     * parameter equipa
     * 
     * @param equipa the team
     */
    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
    }

    /**
     * This function returns the juri
     * 
     * @return The Juri object.
     */
    public Juri getJuri() {
        return this.juri;
    }

    /**
     * This function sets the juri of the current object to the juri passed in as a
     * parameter
     * 
     * @param juri The juri object that is being edited.
     */
    public void setJuri(Juri juri) {
        this.juri = juri;
    }

    /**
     * This function returns the organizer of the event
     * 
     * @return The Organizador object.
     */
    public Organizador getOrganizador() {
        return this.organizador;
    }

    /**
     * This function sets the value of the variable organizador to the value of the
     * parameter
     * organizador
     * 
     * @param organizador Organizador
     */
    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

}
