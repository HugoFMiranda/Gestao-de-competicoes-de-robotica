package controller;

import database.DBConnection;
import handler.LoginHandler;
import model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Update things controller
 */
public class GetController {

    private Robot robot;
    private Associar_robo associarrobo;

    public GetController() {
        robot = new Robot();
    }

    /**
     * It gets a robot from the database, given its id
     *
     * @param id int
     * @return A Robot object.
     */
    public Robot getRobot(int id) throws SQLException {
        String GET_SQL = "SELECT * FROM Robot WHERE id = ?;";
        boolean res = false;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            ps.setInt(1, id);
            rset = ps.executeQuery();
            res = rset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!res) {
            conn.close();
            return null;
        } else {
            this.robot = new Robot();
            this.robot.setId(rset.getInt("id_equipa"));
            this.robot.setId_equipa(rset.getInt("id_equipa"));
            this.robot.setMac_adr(rset.getString("mac_adress"));
            this.robot.setData_criacao(rset.getString("_data_creacao_"));
        }
        conn.close();
        return robot;
    }

    /**
     * It gets all the competitions from the database and returns them in an
     * ArrayList
     *
     * @return An ArrayList of Competicao objects.
     */
    public ArrayList<Competicao> getCompeticoesJuri() throws SQLException {
        String GET_SQL = "SELECT * FROM Competicao";
        String ASSOCIACAO_SQL = "SELECT * FROM associar_juri WHERE id_juri = ? AND id_competicao = ?;";

        boolean res = true;
        ArrayList<Competicao> listaCompeticoes = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            rset = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (res) {
            assert rset != null;
            if (rset.next()) {
                Competicao comp = new Competicao();
                comp.setId(rset.getInt("id"));
                comp.setId_organizador(rset.getInt("id_organizador"));
                comp.setNome(rset.getString("nome"));
                comp.setDesc(rset.getString("descricao"));
                comp.setNrondas(rset.getInt("nrondas"));
                comp.setMax_equipas(rset.getInt("max_equipas"));
                listaCompeticoes.add(comp);
            } else {
                res = false;
            }
        }

        for (int i = 0; i < listaCompeticoes.size(); i++) {
            ps = conn.prepareStatement(ASSOCIACAO_SQL);
            LoginHandler lh = new LoginHandler();
            ps.setInt(1, lh.getUser().getId());
            ps.setInt(2, listaCompeticoes.get(i).getId());
            rset = ps.executeQuery();
            listaCompeticoes.get(i).setJurilogadoAssociado(rset.next());
        }
        conn.close();
        System.out.println(listaCompeticoes);
        return listaCompeticoes;
    }

    public ArrayList<Competicao> getCompeticoesEquipa() throws SQLException {
        String GET_SQL = "SELECT * FROM Competicao";
        String ASSOCIACAO_SQL = "SELECT * FROM associar_equipa WHERE id_equipa = ? AND id_competicao = ?;";

        boolean res = true;
        ArrayList<Competicao> listaCompeticoes = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            rset = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (res) {
            assert rset != null;
            if (rset.next()) {
                Competicao comp = new Competicao();
                comp.setId(rset.getInt("id"));
                comp.setId_organizador(rset.getInt("id_organizador"));
                comp.setNome(rset.getString("nome"));
                comp.setDesc(rset.getString("descricao"));
                comp.setNrondas(rset.getInt("nrondas"));
                comp.setMax_equipas(rset.getInt("max_equipas"));
                listaCompeticoes.add(comp);
            } else {
                res = false;
            }
        }

        for (int i = 0; i < listaCompeticoes.size(); i++) {
            ps = conn.prepareStatement(ASSOCIACAO_SQL);
            LoginHandler lh = new LoginHandler();
            ps.setInt(1, lh.getUser().getId());
            ps.setInt(2, listaCompeticoes.get(i).getId());
            rset = ps.executeQuery();
            listaCompeticoes.get(i).setEquipalogadoAssociado(rset.next());
        }
        conn.close();
        return listaCompeticoes;
    }

    /**
     * It gets all the rounds of a competition
     *
     * @param id the id of the competition
     * @return An ArrayList of Ronda objects.
     */
    public ArrayList<Ronda> getRondasCompeticoes(int id) throws SQLException {
        String GET_SQL = "SELECT * FROM Ronda WHERE id_competicao = ?;";
        boolean res = true;
        ArrayList<Ronda> listaRondas = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement(GET_SQL);
            ps.setInt(1, id);
            rset = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (res) {
            assert rset != null;
            if (rset.next()) {
                Ronda ronda = new Ronda();
                ronda.setId(rset.getInt("id"));
                ronda.setId_competicao(rset.getInt("id_competicao"));
                ronda.setNumero(rset.getString("numero"));
                ronda.setTipo(rset.getString("tipo"));
                listaRondas.add(ronda);
            } else {
                res = false;
            }
        }
        conn.close();
        return listaRondas;
    }

    /**
     * It gets a competition from the database and returns it as a Competicao object
     *
     * @param id int
     * @return A Competicao object.
     */
    public Competicao getCompeticao(int id) {
        String GETCOMPETICAO_SQL = "SELECT * FROM Competicao WHERE id = ?;";
        String GETORGANIZADOR_SQL = "SELECT * FROM Organizador WHERE id = ?;";
        String GETASSOCIAREQUIPAS_SQL = "SELECT * FROM associar_equipa WHERE id_competicao = ?;";
        String GETASSOCIARJURIS_SQL = "SELECT * FROM associar_juri WHERE id_competicao = ?;";
        String GETEQUIPAS_SQL = "SELECT * FROM Equipa WHERE id = ?";
        String GETJURIS_SQL = "SELECT * FROM Elementos_juri WHERE id = ?";

        ArrayList<Integer> idsEquipas = new ArrayList<>();
        ArrayList<Integer> idsJuris = new ArrayList<>();
        ArrayList<String> equipas = new ArrayList<>();
        ArrayList<String> juris = new ArrayList<>();

        boolean resAssociarEquipas = true;
        boolean resAssociarJuris = true;

        Competicao comp = new Competicao();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;

        try {
            ps = conn.prepareStatement(GETCOMPETICAO_SQL);
            ps.setInt(1, id);
            rset = ps.executeQuery();
            if (rset.next()) {

                comp.setId(rset.getInt("id"));
                comp.setId_organizador(rset.getInt("id_organizador"));
                comp.setNome(rset.getString("nome"));
                comp.setEstado(rset.getString("estado"));
                comp.setNrondas(rset.getInt("nrondas"));
                Timestamp timestamp = rset.getTimestamp("_data_criacao_");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                comp.setData_criacao(timestamp.toLocalDateTime().format(dtf));
            }
            ps.close();
            rset.close();

            ps = conn.prepareStatement(GETORGANIZADOR_SQL);
            ps.setInt(1, comp.getId_organizador());
            rset = ps.executeQuery();

            if (rset.next()) {
                comp.setNome_organizador(rset.getString("nome"));
            }

            ps = conn.prepareStatement(GETASSOCIAREQUIPAS_SQL);
            ps.setInt(1, comp.getId());
            rset = ps.executeQuery();
            while (resAssociarEquipas) {
                if (rset.next()) {
                    idsEquipas.add(rset.getInt("id_equipa"));
                } else {
                    resAssociarEquipas = false;
                }
            }
            ps.close();
            rset.close();

            ps = conn.prepareStatement(GETASSOCIARJURIS_SQL);
            ps.setInt(1, comp.getId());
            rset = ps.executeQuery();
            while (resAssociarJuris) {
                if (rset.next()) {
                    idsJuris.add(rset.getInt("id_juri"));
                } else {
                    resAssociarJuris = false;
                }
            }

            ps.close();
            rset.close();

            for (Integer idsEquipa : idsEquipas) {
                ps = conn.prepareStatement(GETEQUIPAS_SQL);
                ps.setInt(1, idsEquipa);
                rset = ps.executeQuery();
                if (rset.next()) {
                    equipas.add(rset.getString("nome"));
                }
            }

            comp.setEquipasRegistadas(equipas);
            ps.close();
            rset.close();

            for (Integer integer : idsJuris) {
                ps = conn.prepareStatement(GETJURIS_SQL);
                ps.setInt(1, integer);
                rset = ps.executeQuery();
                if (rset.next()) {
                    juris.add(rset.getString("nome"));
                }
            }

            comp.setJurisRegistados(juris);

            ps.close();
            rset.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comp;
    }

    /**
     * It gets the data from the database and puts it in an ArrayList
     *
     * @param id the id of the round
     * @return An ArrayList of Associar_robo objects.
     */
    public ArrayList<Associar_robo> getDadosRonda(int id) throws SQLException {
        String GET_SQL = "SELECT * FROM associar_robot WHERE id_ronda = ?;";
        String GET_ROBOTSQL = "SELECT * FROM Robot WHERE id = ?;";

        boolean res = true;
        ArrayList<Associar_robo> listaDados = new ArrayList<>();
        ArrayList<Integer> idrobot = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;
        ps = conn.prepareStatement(GET_SQL);
        ps.setInt(1, id);
        rset = ps.executeQuery();
        // Getting the data from the database and putting it into a list.
        while (res) {
            if (rset.next()) {
                associarrobo = new Associar_robo();
                associarrobo.setId(rset.getInt("id"));
                int velocidade = rset.getInt("velocidade");
                String velocidadeformatado = velocidade + " m/s";
                associarrobo.setVelocidade(velocidadeformatado);
                int tempo = rset.getInt("tempo");
                String tempoformatado = (tempo / 1000) / 60 + "m " + ((tempo / 1000) % 60) + "s "
                        + ((tempo % 1000) % 60) + "ms ";
                associarrobo.setTempo(tempoformatado);
                associarrobo.setPontos(rset.getInt("pontos"));
                idrobot.add(rset.getInt("id_robot"));
                listaDados.add(associarrobo);
            } else {
                res = false;
            }

        }
        // Getting the name of the robot from the database.
        for (int i = 0; i < idrobot.size(); i++) {
            ps = conn.prepareStatement(GET_ROBOTSQL);
            ps.setInt(1, idrobot.get(i));
            rset = ps.executeQuery();
            if (rset.next()) {
                listaDados.get(i).setRobot(rset.getString("nome"));
            }
        }
        conn.close();
        return listaDados;
    }

    /**
     * It gets the id of the robot from the table associar_robot and then gets the
     * name of the robot
     * from the table Robot
     *
     * @param id the id of the user
     * @return The name of the robot.
     */
    public Robot getNomeRobot(int id) throws SQLException {
        String GET_SQL = "SELECT * FROM associar_robot WHERE id = ?;";
        String GETROBOT_SQL = "SELECT * FROM Robot WHERE id = ?";
        int idRobot = 0;
        Robot robot = new Robot();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;

        ps = conn.prepareStatement(GET_SQL);
        ps.setInt(1, id);
        rset = ps.executeQuery();

        if (rset.next()) {
            idRobot = rset.getInt("id_robot");
        }

        ps = conn.prepareStatement(GETROBOT_SQL);
        ps.setInt(1, idRobot);
        rset = ps.executeQuery();

        if (rset.next()) {
            robot.setNome(rset.getString("nome"));
        }
        return robot;
    }

    /**
     * It gets the rankings of a competition
     * 
     * @param id_competicao the id of the competition
     * @return An ArrayList of Ranking objects.
     */
    public ArrayList<Ranking> getRankingsCompeticao(int id_competicao) {
        String GETrondas_SQL = "SELECT * FROM Ronda WHERE id_competicao = ?;";
        String GETrobos_SQL = "SELECT * FROM associar_robot WHERE id_ronda = ?;";
        String GETpontos_SQL = "SELECT * FROM associar_robot WHERE id_ronda = ? AND id_robot = ?;";
        String GETrobo_SQL = "SELECT * FROM Robot WHERE id = ?;";
        String GETequipa_SQL = "SELECT * FROM Equipa WHERE id = ?;";

        ArrayList<Ranking> rankingArray = new ArrayList<>();

        /**
         * 1. buscar os ids das rondas da competição - sql get rondas
         * 2. buscar os ids sem repetidos dos robos - sql get robos
         * 3. fazer uma soma de pontos por robo - sql get pontos
         * 4. criar ranking
         * 5. collection sort
         * 6. ????
         */

        ArrayList<Integer> idsRondas = new ArrayList<>();
        ArrayList<Integer> idsRobot = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;
        boolean resRondas = true;

        try {
            ps = conn.prepareStatement(GETrondas_SQL);
            ps.setInt(1, id_competicao);
            rset = ps.executeQuery();
            while (resRondas) {
                if (rset.next()) {
                    idsRondas.add(rset.getInt("id"));
                } else {
                    resRondas = false;
                }
            }
            for (int i = 0; i < idsRondas.size(); i++) {
                ps = conn.prepareStatement(GETrobos_SQL);
                ps.setInt(1, idsRondas.get(i));
                rset = ps.executeQuery();
                boolean resRobot = true;
                while (resRobot) {
                    if (rset.next()) {
                        if (!idsRobot.contains(rset.getInt("id_robot"))) {
                            idsRobot.add(rset.getInt("id_robot"));
                        }
                    } else {
                        resRobot = false;
                    }
                }
            }

            for (int a = 0; a < idsRobot.size(); a++) {
                Ranking ranking = new Ranking();
                int pontos = 0;
                int velocidademaxima = 0;
                int melhortempo = 0;

                for (int b = 0; b < idsRondas.size(); b++) {
                    ps = conn.prepareStatement(GETpontos_SQL);
                    ps.setInt(1, idsRondas.get(b));
                    ps.setInt(2, idsRobot.get(a));
                    rset = ps.executeQuery();
                    while (rset.next()) {
                        if (velocidademaxima < rset.getInt("velocidade")) {
                            velocidademaxima = rset.getInt("velocidade");
                        }
                        if (melhortempo == 0 || rset.getInt("tempo") < melhortempo) {
                            melhortempo = rset.getInt("tempo");
                        }
                        pontos += rset.getInt("pontos");
                    }
                }

                ranking.setTempoMilissegundos(melhortempo);
                String melhorTempoFormatado = (melhortempo / 1000) / 60 + "m " + ((melhortempo / 1000) % 60) + "s "
                        + ((melhortempo % 1000) % 60) + "ms ";
                ranking.setBesttempo(melhorTempoFormatado);
                String velocidadeMaximaFormatada = velocidademaxima + " m/s";
                ranking.setVelmax(velocidadeMaximaFormatada);
                ranking.setPontos(pontos);
                int idEquipa = 0;

                ps = conn.prepareStatement(GETrobo_SQL);
                ps.setInt(1, idsRobot.get(a));
                rset = ps.executeQuery();
                if (rset.next()) {
                    idEquipa = rset.getInt("id_equipa");
                    ranking.setNome_robo(rset.getString("nome"));
                }

                ps = conn.prepareStatement(GETequipa_SQL);
                ps.setInt(1, idEquipa);
                rset = ps.executeQuery();
                if (rset.next()) {
                    ranking.setNome_equipa(rset.getString("nome"));
                }

                rankingArray.add(ranking);
            }
            Collections.sort(rankingArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rankingArray;
    }

    // TODO IGUAL AO DE CIMA
    public ArrayList<Ranking> getRankingsRonda(int id_ronda) {
        String GETrondas_SQL = "SELECT * FROM associar_robot WHERE id_ronda = ? ORDER BY pontos DESC;";
        String GETnomeR_SQL = "SELECT * FROM Robot WHERE id= ?;";
        String GETnomeE_SQL = "SELECT * FROM Equipa WHERE id = ?";
        return null;
    }

    /**
     * It gets a juri from the database and returns it
     * 
     * @param id_juri int
     * @return A Juri object.
     */
    public Juri getJuri(int id_juri) throws SQLException {
        String SQL = "SELECT * FROM Elementos_juri WHERE id = ?";
        Juri juri = new Juri();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;

        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id_juri);
            rset = ps.executeQuery();
            if (rset.next()) {
                juri.setNome(rset.getString("nome"));
                juri.setEmail(rset.getString("email"));
                juri.setPassword(rset.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return juri;
    }

    /**
     * It gets the name of a competition from the database
     * 
     * @param id_competicao int
     * @return A Competicao object.
     */
    public Competicao getNomeCompeticao(int id_competicao) throws SQLException {
        String SQL = "SELECT * FROM Competicao WHERE id = ?";

        Competicao comp = new Competicao();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;

        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id_competicao);
            rset = ps.executeQuery();
            if (rset.next()) {
                comp.setNome(rset.getString("nome"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return comp;
    }

    /**
     * It gets all the teams from the database, then it gets all the competitions
     * associated with each
     * team, and then it gets the name of each competition and adds it to the team
     * 
     * @return An ArrayList of Equipa objects.
     */
    public ArrayList<Equipa> getOrganizadorEquipas() throws SQLException {
        String SQL = "SELECT * FROM Equipa;";
        String GET_SQL = "SELECT * FROM associar_equipa WHERE id_equipa = ?;";
        String GETCOMPETICOES_SQL = "SELECT * FROM Competicao WHERE id = ?;";

        ArrayList<Equipa> listaEquipas = new ArrayList<>();
        ArrayList<Competicao> comps = new ArrayList<>();

        boolean resEquipas = true;

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;

        try {
            ps = conn.prepareStatement(SQL);
            rset = ps.executeQuery();
            while (resEquipas) {
                if (rset.next()) {
                    Equipa equipa = new Equipa();
                    equipa.setId(rset.getInt("id"));
                    equipa.setNome(rset.getString("nome"));
                    listaEquipas.add(equipa);
                } else {
                    resEquipas = false;
                }
            }

            for (int i = 0; i < listaEquipas.size(); i++) {
                boolean resCompeticoes = true;
                ps = conn.prepareStatement(GET_SQL);
                ps.setInt(1, listaEquipas.get(i).getId());
                rset = ps.executeQuery();
                while (resCompeticoes) {
                    if (rset.next()) {
                        Competicao comp = new Competicao();
                        comp.setIdequipaassociada(listaEquipas.get(i).getId());
                        comp.setId(rset.getInt("id_competicao"));
                        comps.add(comp);
                    } else {
                        resCompeticoes = false;
                    }
                }
            }

            for (int i = 0; i < comps.size(); i++) {
                ps = conn.prepareStatement(GETCOMPETICOES_SQL);
                ps.setInt(1, comps.get(i).getId());
                rset = ps.executeQuery();
                if (rset.next()) {
                    System.out.println(i);
                    System.out.println(comps.get(i).getIdequipaassociada());
                    comps.get(i).setNome(rset.getString("nome"));
                    for(int a=0; a<listaEquipas.size(); a++) {
                        if(listaEquipas.get(a).getId() == comps.get(i).getIdequipaassociada()) {
                            if(!listaEquipas.get(a).getCompeticoes().contains(comps.get(i))) {
                                listaEquipas.get(a).addCompeticao(comps.get(i));
                            }
                        }
                    }
                    }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        conn.close();
        return listaEquipas;
    }

    /**
     * It gets the team name and the team members from the database and returns a
     * team object
     * 
     * @param id_equipa 1
     * @return A list of teams.
     */
    public Equipa getOrganizadorEquipa(int id_equipa) throws SQLException {
        String SQL = "SELECT * FROM Equipa WHERE id = ?;";
        String ELEMENTOS_SQL = "SELECT * FROM Elementos_equipa WHERE id_equipa = ?";
        Equipa equipa = new Equipa();
        ArrayList<Elementos_equipa> elementos = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;

        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id_equipa);
            rset = ps.executeQuery();
            if (rset.next()) {
                equipa.setNome(rset.getString("nome"));
            }

            boolean resElementos = true;
            ps = conn.prepareStatement(ELEMENTOS_SQL);
            ps.setInt(1, id_equipa);
            rset = ps.executeQuery();
            while (resElementos) {
                if (rset.next()) {
                    Elementos_equipa elemento = new Elementos_equipa();
                    elemento.setNome(rset.getString("nome"));
                    elementos.add(elemento);
                } else {
                    resElementos = false;
                }
            }
            equipa.setElementos(elementos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return equipa;
    }

    /**
     * It gets all the juris from the database, then it gets all the competitions
     * that each juri is
     * associated with, and then it adds those competitions to the juri
     * 
     * @return An ArrayList of Juri objects.
     */
    public ArrayList<Juri> getOrganizadorJuris() throws SQLException {
        String SQL = "SELECT * FROM Elementos_juri;";
        String GET_SQL = "SELECT * FROM associar_juri WHERE id_juri = ?;";
        String GETCOMPETICOES_SQL = "SELECT * FROM Competicao WHERE id = ?;";

        ArrayList<Juri> listaJuris = new ArrayList<>();
        ArrayList<Competicao> comps = new ArrayList<>();

        boolean resEquipas = true;

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;

        try {
            ps = conn.prepareStatement(SQL);
            rset = ps.executeQuery();
            while (resEquipas) {
                if (rset.next()) {
                    Juri juri = new Juri();
                    juri.setId(rset.getInt("id"));
                    juri.setNome(rset.getString("nome"));
                    listaJuris.add(juri);
                } else {
                    resEquipas = false;
                }
            }

            for (int i = 0; i < listaJuris.size(); i++) {
                boolean resCompeticoes = true;
                ps = conn.prepareStatement(GET_SQL);
                ps.setInt(1, listaJuris.get(i).getId());
                rset = ps.executeQuery();
                while (resCompeticoes) {
                    if (rset.next()) {
                        Competicao comp = new Competicao();
                        comp.setIdequipaassociada(listaJuris.get(i).getId());
                        comp.setId(rset.getInt("id_competicao"));
                        comps.add(comp);
                    } else {
                        resCompeticoes = false;
                    }
                }
            }

            for (int i = 0; i < comps.size(); i++) {
                ps = conn.prepareStatement(GETCOMPETICOES_SQL);
                ps.setInt(1, comps.get(i).getId());
                rset = ps.executeQuery();
                if (rset.next()) {
                    comps.get(i).setNome(rset.getString("nome"));
                    for(int a=0; a<listaJuris.size(); a++) {
                        if(listaJuris.get(a).getId() == comps.get(i).getIdequipaassociada()) {
                            if(!listaJuris.get(a).getCompeticoes().contains(comps.get(i))) {
                                listaJuris.get(a).addCompeticao(comps.get(i));
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        conn.close();
        return listaJuris;
    }

    /**
     * It gets the name and email of the organizer of a jury
     * 
     * @param id_juri int
     * @return A Juri object.
     */
    public Juri getOrganizadorJuri(int id_juri) throws SQLException {
        String SQL = "SELECT * FROM Elementos_juri WHERE id = ?;";

        Juri juri = new Juri();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;

        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id_juri);
            rset = ps.executeQuery();
            if (rset.next()) {
                juri.setNome(rset.getString("nome"));
                juri.setEmail(rset.getString("email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return juri;
    }

    /**
     * It gets an array list of robots of the user
     * @return
     * @throws SQLException
     */
    public ArrayList<Robot> getRobos() throws SQLException {
        String SQL = "SELECT * FROM Robot WHERE id_equipa = ?";
        LoginHandler lh = new LoginHandler();
        int id = lh.getUser().getId();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;
        ArrayList<Robot> robotList = new ArrayList<>();

        Robot robot;

        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            rset = ps.executeQuery();

            while(rset.next()) {
                robot = new Robot();
                robot.setId(rset.getInt("id"));
                robot.setMac_adr(rset.getString("mac_address"));
                robot.setNome(rset.getString("nome"));
                LocalDateTime localTime = rset.getTimestamp("_data_criacao_").toLocalDateTime();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                robot.setData_criacao(dtf.format(localTime));
                robotList.add(robot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return robotList;
    }

    public Equipa getEquipa(int id_equipa) throws SQLException {
        String SQL = "SELECT * FROM Equipa WHERE id = ?;";
        String ELEMENTOS_SQL = "SELECT * FROM Elementos_equipa WHERE id_equipa = ?";
        Equipa equipa = new Equipa();
        ArrayList<Elementos_equipa> elementos = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;

        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id_equipa);
            rset = ps.executeQuery();
            if (rset.next()) {
                equipa.setNome(rset.getString("nome"));
            }

            boolean resElementos = true;
            ps = conn.prepareStatement(ELEMENTOS_SQL);
            ps.setInt(1, id_equipa);
            rset = ps.executeQuery();
            while (resElementos) {
                if (rset.next()) {
                    Elementos_equipa elemento = new Elementos_equipa();
                    elemento.setId(rset.getInt("id"));
                    elemento.setNome(rset.getString("nome"));
                    elementos.add(elemento);
                } else {
                    resElementos = false;
                }
            }
            equipa.setElementos(elementos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return equipa;
    }

    public int getidCompeticao(String nome) throws SQLException {
        String SQL = "SELECT * FROM Competicao WHERE nome = ?";
        int id_comp = 0;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rset;
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, nome);
            rset = ps.executeQuery();
            if (rset.next()) {
                id_comp = rset.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return id_comp;
    }

}
