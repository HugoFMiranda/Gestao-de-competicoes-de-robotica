package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * This class is an abstract class that represents a user
 */
public abstract class User {
    private int id;
    private String nome;
    private String data_criacao;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // A constructor that sets the date of creation of the user.
    public User() {
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    // A constructor that sets the date of creation of the user.
    public User(String nome) {
        this.nome = nome;
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
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
     * This function returns the value of the variable nome
     * 
     * @return The value of the variable nome.
     */
    public String getNome() {
        return this.nome;
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
     * This function sets the name of the object
     * 
     * @param nome The name of the person
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * instance of the same class, return false. If the object is an instance of the
     * same class,
     * compare the fields and return true if they are equal
     * 
     * @param o The object to compare with.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(nome, user.nome) && Objects.equals(data_criacao, user.data_criacao);
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString() method returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "{" + "id = " + getId() +
                " nome='" + getNome() + "'" +
                ", data_creacao='" + getData_criacao() + "'" +
                "}";
    }

}
