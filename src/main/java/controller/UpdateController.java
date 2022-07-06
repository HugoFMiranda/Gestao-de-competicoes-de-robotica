package controller;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * It updates the database
 */
public class UpdateController {

    // It's a constructor.
    public UpdateController() {
    }

    /**
     * It updates the time of a robot in the database
     * 
     * @param id            the id of the robot
     * @param horas         hours
     * @param minutos       minutes
     * @param segundos      seconds
     * @param milissegundos int
     * @return The number of rows affected by the update.
     */
    public int novoTempo(int id, int horas, int minutos, int segundos, int milissegundos) throws SQLException {
        String SQL = "UPDATE associar_robot SET tempo = ? WHERE id = ?";
        int novotempo = 0;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;
        try {
            ps = conn.prepareStatement(SQL);
            novotempo = horas * 3600000 + minutos * 60000 + segundos * 1000 + milissegundos;
            ps.setInt(1, novotempo);
            ps.setInt(2, id);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    /**
     * It updates the points of a robot in the database
     * 
     * @param id            the id of the robot
     * @param classificacao String
     * @return The number of rows affected by the query.
     */
    public int novaPontuacao(int id, String classificacao, int subtrairPontos) {
        String SQL = "SELECT * FROM associar_robot WHERE id = ?;";
        String UPDATE_SQL = "UPDATE associar_robot SET pontos = ?, validado = ? WHERE id = ?;";
        int res = 0;
        boolean nulo = false;

        if (classificacao.equals("naoterminou") || classificacao.equals("saiu")) {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(UPDATE_SQL);
                ps.setInt(1, 0);
                ps.setString(2, "Invalidado");
                ps.setInt(3, id);
                res = ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (classificacao.equals("0.25") || classificacao.equals("0.5") || classificacao.equals("repetiu")) {
            int pontos = 0;
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps;
            ResultSet rset = null;
            try {
                ps = conn.prepareStatement(SQL);
                ps.setInt(1, id);
                rset = ps.executeQuery();
                if (rset.next()) {
                    if (classificacao.equals("0.25") || classificacao.equals("repetiu")) {
                        pontos = rset.getInt("pontos") + 2;
                        pontos = pontos - subtrairPontos;
                    } else if(classificacao.equals("0.5")){
                        pontos = rset.getInt("pontos") + 1;
                        pontos = pontos - subtrairPontos;
                    }
                    pontos = rset.getInt("pontos");
                    pontos = pontos - subtrairPontos;
                    if(pontos < 0){
                        pontos = 0;
                    }
                }

                ps = conn.prepareStatement(UPDATE_SQL);
                ps.setInt(1, pontos);
                ps.setInt(2, id);
                res = ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return res;
    }

    /**
     * It updates the database with the new values of the juri
     * 
     * @param id_juri int
     * @param nome String
     * @param email juri@gmail.com
     * @param pass String
     * @return The number of rows affected by the update.
     */
    public int editarJuri(int id_juri, String nome, String email, String pass) throws SQLException {
        String SQL = "UPDATE Elementos_juri SET nome = ?, email = ?,password = ? WHERE id = ?";
        int res = 0;

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try{
            ps = conn.prepareStatement(SQL);
            ps.setString(1,nome);
            ps.setString(2,email);
            ps.setString(3,pass);
            ps.setInt(4,id_juri);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    /**
     * It updates the database with the new values of the competition
     * 
     * @param id_competicao int
     * @param nome String
     * @param descricao String
     * @param nrondas number of rounds
     * @param nequipas number of teams
     * @return The number of rows affected by the query.
     */
    public int editarCompeticao(int id_competicao, String nome, String descricao, int nrondas, int nequipas) throws SQLException {
        String SQL = "UPDATE Competicao SET nome = ?, descricao = ?, nrondas = ?, max_equipas = ? WHERE id = ?;";
        int res = 0;

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try{
            ps = conn.prepareStatement(SQL);
            ps.setString(1,nome);
            ps.setString(2,descricao);
            ps.setInt(3,nrondas);
            ps.setInt(4,nequipas);
            ps.setInt(5,id_competicao);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    public int editarEquipa(int id_equipa, String nome) throws SQLException {
        String SQL = "UPDATE Equipa SET nome = ? WHERE id = ?;";

        int res = 0;

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try{
            ps = conn.prepareStatement(SQL);
            ps.setString(1,nome);
            ps.setInt(2,id_equipa);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }
}
