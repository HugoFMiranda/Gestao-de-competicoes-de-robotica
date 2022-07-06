package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Associar_robo is a class that represents a row in the associar_robo table in the database
 */
public class Associar_robo {

    private int id;
    private int id_robot;
    private String robot;
    private String velocidade;
    private String tempo;
    private int pontos;
    private String data_criacao;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // A constructor.
    public Associar_robo() {
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    // A constructor.
    public Associar_robo(int id, String robot, String velocidade, String tempo, int pontos) {
        this.id = id;
        this.robot = robot;
        this.velocidade = velocidade;
        this.tempo = tempo;
        this.pontos = pontos;
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    /**
     * This function returns the id of the object
     * 
     * @return The id of the object.
     */
    public int getId() {
        return id;
    }

    /**
     * This function sets the id of the object to the id passed in as a parameter
     * 
     * @param id The id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function returns the value of the variable pontos
     * 
     * @return The value of the variable pontos.
     */
    public int getPontos() {
        return pontos;
    }

    /**
     * This function sets the value of the variable pontos to the value of the parameter pontos
     * 
     * @param pontos points
     */
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    /**
     * This function returns the robot's name
     * 
     * @return The robot variable is being returned.
     */
    public String getRobot() {
        return robot;
    }

    /**
     * This function sets the robot variable to the robot parameter
     * 
     * @param robot The name of the robot.
     */
    public void setRobot(String robot) {
        this.robot = robot;
    }

    /**
     * This function returns the value of the variable velocidade
     * 
     * @return The value of the variable velocidade.
     */
    public String getVelocidade() {
        return velocidade;
    }

    /**
     * This function sets the value of the variable velocidade to the value of the parameter velocidade
     * 
     * @param velocidade speed
     */
    public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
    }

    /**
     * This function returns the tempo of the song
     * 
     * @return The tempo of the song.
     */
    public String getTempo() {
        return tempo;
    }

    /**
     * This function sets the tempo of the song
     * 
     * @param tempo The tempo of the song in BPM.
     */
    public void setTempo(String tempo) {
        this.tempo = tempo;
    }


    public String getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(String data_criacao) {
        this.data_criacao = data_criacao;
    }

    public int getId_robot() {
        return id_robot;
    }

    public void setId_robot(int id_robot) {
        this.id_robot = id_robot;
    }

    /**
     * If the object is the same, return true. If the object is not the same, return false
     * 
     * @param o Object
     * @return The method returns a boolean value.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Associar_robo)) return false;
        Associar_robo associarrobo = (Associar_robo) o;
        return velocidade == associarrobo.velocidade && tempo == associarrobo.tempo && pontos == associarrobo.pontos && Objects.equals(robot, associarrobo.robot);
    }

    /**
     * The function is called when the object is printed
     * 
     * @return The robot, speed, time, and points.
     */
    @Override
    public String toString() {
        return "Associar_robo{" +
                "robot=" + robot +
                ", velocidade=" + velocidade +
                ", tempo=" + tempo +
                ", pontos=" + pontos +
                '}';
    }
}
