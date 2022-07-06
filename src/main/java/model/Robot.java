package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Robot is a class that represents a robot
 */
public class Robot {
    private int id;
    private int id_equipa;
    private String nome;
    private String mac_adr;
    private String data_criacao;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // A constructor that sets the date of creation of the robot to the current
    // date.
    public Robot() {
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    // A constructor that sets the date of creation of the robot to the current
    // date.
    public Robot(int id_equipa, String nome, String mac_adr) {
        this.id_equipa = id_equipa;
        this.nome = nome;
        this.mac_adr = mac_adr;
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    /**
     * This function returns the value of the private variable id_equipa
     * 
     * @return The id_equipa of the object.
     */
    public int getId_equipa() {
        return this.id_equipa;
    }

    /**
     * This function returns the id of the current object
     * 
     * @return The id of the object.
     */
    public int getId() {
        return this.id;
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
     * This function sets the value of the id_equipa variable to the value of the
     * id_equipa parameter
     * 
     * @param id_equipa int
     */
    public void setId_equipa(int id_equipa) {
        this.id_equipa = id_equipa;
    }

    /**
     * This function returns the value of the variable nome
     * 
     * @return The value of the variable nome.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * This function sets the name of the object
     * 
     * @param nome The name of the person
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * This function returns the mac_adr of the device
     * 
     * @return The mac_adr variable is being returned.
     */
    public String getMac_adr() {
        return this.mac_adr;
    }

    /**
     * This function sets the mac_adr variable to the value of the mac_adr parameter
     * 
     * @param mac_adr The MAC address of the device
     */
    public void setMac_adr(String mac_adr) {
        this.mac_adr = mac_adr;
    }

    /**
     * This function returns the value of the data_creacao field
     * 
     * @return The data_creacao variable is being returned.
     */
    public String getData_criacao() {
        return this.data_criacao;
    }

    /**
     * This function sets the value of the data_creacao variable to the value of the
     * data_creacao
     * parameter
     * 
     * @param data_criacao String
     */
    public void setData_criacao(String data_criacao) {
        this.data_criacao = data_criacao;
    }

    /**
     * If the object is the same as the one being compared, return true. If the
     * object is not an
     * instance of the class, return false. If the object is an instance of the
     * class, compare the
     * fields
     * 
     * @param o the object to compare to
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Robot)) {
            return false;
        }
        Robot robot = (Robot) o;
        return id_equipa == robot.id_equipa && Objects.equals(nome, robot.nome)
                && Objects.equals(mac_adr, robot.mac_adr) && Objects.equals(data_criacao, robot.data_criacao);
    }

    /**
     * The toString() method returns the string representation of the object
     * 
     * @return The toString() method returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "{" + "id=" + id +
                " id_equipa='" + getId_equipa() + "'" +
                ", nome='" + getNome() + "'" +
                ", mac_adr='" + getMac_adr() + "'" +
                ", data_creacao='" + getData_criacao() + "'" +
                "}";
    }

}
