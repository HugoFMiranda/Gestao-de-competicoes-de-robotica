package model;

import java.util.Objects;

/**
 * Organizador is a User
 */
public class Organizador extends User {

    private String password;
    private String email;

    // It's a constructor.
    public Organizador(String nome, String email, String password) {
        super(nome);
        this.password = password;
        this.email = email;
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
     * This function sets the email of the user
     * 
     * @param email The email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This function sets the password of the Organizador object and returns the
     * Organizador object
     * 
     * @param password the password to set
     * @return The object itself.
     */
    public Organizador password(String password) {
        setPassword(password);
        return this;
    }

    /**
     * This function sets the email of the Organizador object and returns the object
     * itself
     * 
     * @param email String
     * @return The object itself.
     */
    public Organizador email(String email) {
        setEmail(email);
        return this;
    }

    // It's a constructor.
    public Organizador() {
        super();
    }

    // It's a constructor.
    public Organizador(String nome, String password) {
        super(nome);
        this.password = password;
    }

    /**
     * This function returns the password of the user
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * This function sets the password of the user
     * 
     * @param password The password to use for the connection.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * If the object is the same object, return true. If the object is not an
     * instance of the same
     * class, return false. If the object is an instance of the same class, compare
     * the fields
     * 
     * @param o the object to compare with
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Organizador)) {
            return false;
        }
        Organizador organizador = (Organizador) o;
        return Objects.equals(getNome(), organizador.getNome()) && Objects.equals(password, organizador.password)
                && Objects.equals(getData_criacao(), organizador.getData_criacao());
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString() method returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                ", password='" + getPassword() + "'" +
                ", data_creacao='" + getData_criacao() + "'" +
                "}";
    }

}
