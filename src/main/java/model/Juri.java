package model;

import handler.LoginHandler;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Juri is a class that extends User and has the attributes email and password
 */
public class Juri extends User {
    private String email;
    private String password;

    private boolean organizadorLogadoAssociado = false;

    private ArrayList<String> competicoes = new ArrayList<>();

    // Calling the constructor of the superclass.
    public Juri() {
        super();
    }

    // A constructor that receives the parameters' nome, email and password and calls
    // the constructor of
    // the superclass.
    public Juri(String nome, String email, String password) {
        super(nome);
        this.email = email;
        this.password = password;
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

    public ArrayList<String> getCompeticoes() {
        return competicoes;
    }

    public void setCompeticoes(ArrayList<String> competicoes) {
        this.competicoes = competicoes;
    }

    public boolean isOrganizadorLogadoAssociado() {
        return organizadorLogadoAssociado;
    }

    public void setOrganizadorLogadoAssociado(boolean organizadorLogadoAssociado) {
        this.organizadorLogadoAssociado = organizadorLogadoAssociado;
    }
    public void addCompeticao(Competicao comp){
        LoginHandler lh = new LoginHandler();
        if(lh.getUser().getId() == comp.getId_organizador()){
            organizadorLogadoAssociado = true;
        }
        this.competicoes.add(comp.getNome());
    }

    /**
     * If the object is the same as the one being compared, return true. If the
     * object is not an
     * instance of the same class, return false. If the object is an instance of the
     * same class,
     * compare the fields and return true if they are all equal
     * 
     * @param o the object to compare with
     * @return The method returns a boolean value.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Juri)) {
            return false;
        }
        Juri elementos_juri = (Juri) o;
        return Objects.equals(getNome(), elementos_juri.getNome()) && Objects.equals(email, elementos_juri.email)
                && Objects.equals(password, elementos_juri.password);
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
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", data_creacao='" + getData_criacao() + "'" +
                "}";
    }

}
