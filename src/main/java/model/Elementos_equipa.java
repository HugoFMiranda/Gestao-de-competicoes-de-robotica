package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A class that represents an element of the team.
 */
public class Elementos_equipa {

    private int id;
    private int id_equipa;
    private String nome;
    private String email;
    private String data_criacao;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // A constructor that sets the date of creation of the object.
    public Elementos_equipa() {
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    // A constructor that sets the date of creation of the object.
    public Elementos_equipa(int id_equipa, String nome, String email) {
        this.id_equipa = id_equipa;
        this.nome = nome;
        this.email = email;
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
     * This function returns the email of the user
     * 
     * @return The email address of the user.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * public void setEmail(String email) {
     * 
     * @param email The email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * If the object is the same as the one being compared, return true. If the
     * object is not an
     * instance of the same class, return false. If the object is an instance of the
     * same class,
     * compare the fields. If the fields are the same, return true. If the fields
     * are not the same,
     * return false
     * 
     * @param o the object to compare to
     * @return The hashCode() method is overridden to return the hashCode of the
     *         id_equipa field.
     */

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Elementos_equipa)) {
            return false;
        }
        Elementos_equipa elementos_equipa = (Elementos_equipa) o;
        return id_equipa == elementos_equipa.id_equipa && Objects.equals(nome, elementos_equipa.nome)
                && Objects.equals(email, elementos_equipa.email)
                && Objects.equals(data_criacao, elementos_equipa.data_criacao);
    }

    /**
     * The toString() method returns the string representation of the object
     * 
     * @return The toString() method returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "{" +
                " id_equipa='" + getId_equipa() + "'" +
                ", nome='" + getNome() + "'" +
                ", email='" + getEmail() + "'" +
                ", data_creacao='" + getData_criacao() + "'" +
                "}";
    }

}
