package controller;

import database.DBConnection;
import model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistoController {

    private Equipa equipa;
    private Juri juri;
    private Organizador organizador;
    private Robot robot;
    private Competicao competicao;
    private Elementos_equipa elementosEquipa;

    public RegistoController() {
    }

    /**
     * This function creates a new team
     */
    public void novaEquipa() {
        this.equipa = new Equipa();
    }

    /**
     * This function creates a new juri object and assigns it to the juri variable.
     */
    public void novoJuri() {
        this.juri = new Juri();
    }

    /**
     * This function is used to create a new organizer
     */
    public void novoOrganizador() {
        this.organizador = new Organizador();
    }

    /**
     * This function creates a new Robot object and assigns it to the robot variable
     */
    public void novoRobot() {
        this.robot = new Robot();
    }

    /**
     * This function creates a new competition
     */
    public void novaCompeticao() {
        this.competicao = new Competicao();
    }

    /**
     * This function creates a new instance of the class Elementos_equipa and
     * assigns it to the
     * variable elementosEquipa.
     */
    public void novoElemento() {
        this.elementosEquipa = new Elementos_equipa();
    }

    /**
     * It inserts a new team into the database
     * 
     * @param nome     String
     * @param password "12345"
     * @return The number of rows affected by the query.
     */
    public int registarEquipa(String nome, String password) throws SQLException {
        novaEquipa();
        this.equipa.setNome(nome);
        this.equipa.setPassword(password);
        String INSERT_SQL = "INSERT INTO Equipa (nome, password, _data_criacao_) VALUES (?,?,?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, equipa.getNome());
            ps.setString(2, equipa.getPassword());
            Timestamp timestamp = Timestamp.valueOf(equipa.getData_criacao());
            ps.setTimestamp(3, timestamp);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    /**
     * It inserts a new juri into the database
     * 
     * @param nome     String
     * @param email    juri@gmail.com
     * @param password 12345
     * @return The number of rows affected by the query.
     */
    public int registarJuri(String nome, String email, String password) throws SQLException {
        novoJuri();
        this.juri.setNome(nome);
        this.juri.setEmail(email);
        this.juri.setPassword(password);
        String INSERT_SQL = "INSERT INTO Elementos_juri(nome, email, password, _data_criacao_) VALUES (?,?,?,?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, juri.getNome());
            ps.setString(2, juri.getEmail());
            ps.setString(3, juri.getPassword());
            Timestamp timestamp = Timestamp.valueOf(juri.getData_criacao());
            ps.setTimestamp(4, timestamp);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    /**
     * It inserts a new row into the database with the values of the object
     * "organizador"
     * 
     * @param nome     String
     * @param email    "teste@teste.com"
     * @param password 12345
     * @return The number of rows affected by the query.
     */
    public int registarOrganizador(String nome, String email, String password)
            throws SQLException {
        novoOrganizador();
        this.organizador.setNome(nome);
        this.organizador.setEmail(email);
        this.organizador.setPassword(password);
        String INSERT_SQL = "INSERT INTO Organizador (nome, email, password, _data_criacao_) VALUES (?,?,?,?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, organizador.getNome());
            ps.setString(2, organizador.getEmail());
            ps.setString(3, organizador.getPassword());
            Timestamp timestamp = Timestamp.valueOf(organizador.getData_criacao());
            ps.setTimestamp(4, timestamp);
            System.out.println(ps);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    /**
     * It inserts a new robot into the database
     * 
     * @param id_equipa int
     * @param nome      String
     * @param mac_adr   "00:00:00:00:00:00"
     * @return The number of rows affected by the query.
     */
    public int registarRobot(int id_equipa, String nome, String mac_adr) throws SQLException {
        novoRobot();
        this.robot.setId_equipa(id_equipa);
        this.robot.setNome(nome);
        this.robot.setMac_adr(mac_adr);
        String INSERT_SQL = "INSERT INTO Robot (id_equipa, nome, mac_address , _data_criacao_) VALUES (?,?,?,?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setInt(1, robot.getId_equipa());
            ps.setString(2, robot.getNome());
            ps.setString(3, robot.getMac_adr());
            Timestamp timestamp = Timestamp.valueOf(robot.getData_criacao());
            ps.setTimestamp(4, timestamp);
            System.out.println(ps);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    /**
     * It inserts a new competition into the database
     * 
     * @param id_organizador int
     * @param nome           String
     * @param desc           String
     * @param nrondas        number of rounds
     * @param max_equipas    maximum number of teams
     * @return The number of rows affected by the query.
     */
    public int registarCompeticao(int id_organizador, String nome, String desc, int nrondas, int max_equipas)
            throws SQLException {
        novaCompeticao();
        this.competicao.setId_organizador(id_organizador);
        this.competicao.setNome(nome);
        this.competicao.setDesc(desc);
        this.competicao.setNrondas(nrondas);
        this.competicao.setMax_equipas(max_equipas);
        this.competicao.setEstado("Em andamento");
        String INSERT_SQL = "INSERT INTO Competicao (id_organizador, nome, descricao, nrondas, max_equipas, _data_criacao_, estado) VALUES (?,?,?,?,?,?,?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setInt(1, competicao.getId_organizador());
            ps.setString(2, competicao.getNome());
            ps.setString(3, competicao.getDesc());
            ps.setInt(4, competicao.getNrondas());
            ps.setInt(5, competicao.getMax_equipas());
            Timestamp timestamp = Timestamp.valueOf(competicao.getData_criacao());
            ps.setTimestamp(6, timestamp);
            ps.setString(7, competicao.getEstado());
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    /**
     * It takes 3 parameters, and returns an int
     * 
     * @param id_equipa int
     * @param nome      name
     * @param email     varchar(255)
     * @return The number of rows affected by the query.
     */
    public int registarElemento(int id_equipa, String nome, String email)
            throws SQLException {
        novoElemento();
        this.elementosEquipa.setId_equipa(id_equipa);
        this.elementosEquipa.setNome(nome);
        this.elementosEquipa.setEmail(email);
        String INSERT_SQL = "INSERT INTO Elemento_equipa (id_equipa, nome, email, _data_creacao_) VALUES (?,?,?,?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;
        try {
            ps = conn.prepareStatement(INSERT_SQL);
            ps.setInt(1, elementosEquipa.getId_equipa());
            ps.setString(2, elementosEquipa.getNome());
            ps.setString(3, elementosEquipa.getEmail());
            Timestamp timestamp = Timestamp.valueOf(elementosEquipa.getData_criacao());
            ps.setTimestamp(4, timestamp);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    /**
     * It inserts a new row in the table associar_juri, with the id_juri,
     * id_competicao and the current
     * timestamp
     * 
     * @param id_competicao 1
     * @param id_juri       1
     * @return The number of rows affected by the query.
     */
    public int inscreverJuri(int id_competicao, int id_juri) throws SQLException {
        String INSCREVER_SQL = "INSERT INTO associar_juri (id_juri, id_competicao, _data_criacao_) VALUES (?,?,?)";

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;

        try {
            ps = conn.prepareStatement(INSCREVER_SQL);
            ps.setInt(1, id_juri);
            ps.setInt(2, id_competicao);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dtf.format(now));
            ps.setTimestamp(3, timestamp);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    public int inscreverEquipa(int id_competicao, int id_equipa) throws SQLException {
        String INSCREVER_SQL = "INSERT INTO associar_equipa (id_equipa, id_competicao, _data_criacao_) VALUES (?,?,?)";

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;

        try {
            ps = conn.prepareStatement(INSCREVER_SQL);
            ps.setInt(1, id_equipa);
            ps.setInt(2, id_competicao);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dtf.format(now));
            ps.setTimestamp(3, timestamp);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return res;
    }

    /**
     * It creates a number of rounds in a competition
     * 
     * @param nrondas number of rounds
     * @return The number of rows affected by the query.
     */
    public int criarRondas(int nrondas) throws SQLException {
        String GETCompeticao_SQL = "SELECT ID FROM Competicao WHERE ID = (SELECT MAX(ID) FROM Competicao);";
        String CREATErondas_SQL = "INSERT INTO Ronda (id_competicao, tipo, numero, _data_criacao_) VALUES (?,?,?,?);";
        int idComp = 0;
        Ronda ronda = new Ronda();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;
        ps = conn.prepareStatement(GETCompeticao_SQL);
        rset = ps.executeQuery();
        if (rset.next()) {
            idComp = rset.getInt("ID");
        }
        int res = 0;
        ps = conn.prepareStatement(CREATErondas_SQL);
        for (int i = 1; i < nrondas + 2; i++) {
            ronda = new Ronda();
            if (i != nrondas + 1){
                ps.setInt(1, idComp);
                ps.setString(2, "Ronda oficial");
                ps.setString(3, String.valueOf(i));
                Timestamp timestamp = Timestamp.valueOf(ronda.getData_criacao());
                ps.setTimestamp(4, timestamp);
                res = ps.executeUpdate();
            }else{
                ps.setInt(1, idComp);
                ps.setString(2, "Ronda teste");
                ps.setString(3, "teste");
                Timestamp timestamp = Timestamp.valueOf(ronda.getData_criacao());
                ps.setTimestamp(4, timestamp);
                res = ps.executeUpdate();
            }
        }
        conn.close();
        return res;
    }

    public int adicionarMembro(int id_equipa, String nome, String email) throws SQLException {
        String SQL = "INSERT INTO Elementos_equipa(id_equipa, nome, email, _data_criacao_) VALUES (?,?,?,?);";

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        int res = 0;

        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id_equipa);
            ps.setString(2, nome);
            ps.setString(3, email);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dtf.format(now));
            ps.setTimestamp(4, timestamp);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        conn.close();
        return res;
    }
}
