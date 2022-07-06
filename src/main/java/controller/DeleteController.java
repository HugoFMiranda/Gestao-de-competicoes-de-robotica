package controller;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * It's a class that deletes  a row from a table in a database
 */
public class DeleteController {
    // It's a constructor.
    public DeleteController(){
    }

    /**
     * It deletes a row from the table associar_juri in a database
     *  
     * @param id_competicao 1
     * @param id_juri 1 
     * @return The numbe r of rows affected by the query.
     */
    public int deleteJuri(int id_competicao, int id_juri) throws SQLException {
        String SQL = "DELETE FROM associar_juri WHERE id_juri = ? AND id_competicao = ?";
        int res = 0;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try{
            ps = conn.prepareStatement(SQL);
            ps.setInt(1,id_juri);
            ps.setInt(2,id_competicao);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
 
        conn.close();
        return res; 
    } 

    /**
     * It deletes a row from the table associar_equipa in a database
     * 
     * @param id_competicao 1
     * @param id_equipa 1
     * @return The number of rows affected by the query.
     */
    public int deleteEquipa(int id_competicao, int id_equipa) throws SQLException {
        String SQL = "DELETE FROM associar_equipa WHERE id_equipa = ? AND id_competicao = ?";
        int res = 0;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try{
            ps = conn.prepareStatement(SQL);
            ps.setInt(1,id_equipa);
            ps.setInt(2,id_competicao);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conn.close();
        return res;
    }

    public int deleteRobot(int id_robot) throws SQLException {
        String SQL = "DELETE FROM Robot WHERE id = ?";
        int res = 0;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try{
            ps = conn.prepareStatement(SQL);
            ps.setInt(1,id_robot);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conn.close();
        return res;
    }

    public int deleteCompeticao(int id_competicao) throws SQLException {
        String SQL = "DELETE FROM Competicao WHERE id = ?";
        int res = 0;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try{
            ps = conn.prepareStatement(SQL);
            ps.setInt(1,id_competicao);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conn.close();
        return res;
    }

    public int deleteMembro(int id_membro) throws SQLException {
        String SQL = "DELETE FROM Elementos_equipa WHERE id = ?";
        int res = 0;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try{
            ps = conn.prepareStatement(SQL);
            ps.setInt(1,id_membro);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conn.close();
        return res;
    }
}
