package model;

import handler.LoginHandler;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Equipa is a class that extends User and has a password
 */
public class Equipa extends User {
    private String password;
    private ArrayList<String> competicoes = new ArrayList<>();

    private ArrayList<Elementos_equipa> elementos = new ArrayList<>();

    private boolean organizadorLogadoAssociado = false;

    // Calling the constructor of the superclass.
    public Equipa() {
        super();
    }

    // Calling the constructor of the superclass.
    public Equipa(String nome, String password) {
        super(nome);
        this.password = password;
    }

    public ArrayList<String> getCompeticoes() {
        return competicoes;
    }

    public void setCompeticoes(ArrayList<String> competicoes) {
        this.competicoes = competicoes;
    }

    public void addCompeticao(Competicao comp){
        LoginHandler lh = new LoginHandler();
        if(lh.getUser().getId() == comp.getId_organizador()){
            organizadorLogadoAssociado = true;
        }
        this.competicoes.add(comp.getNome());
    }

    public boolean isOrganizadorLogadoAssociado() {
        return organizadorLogadoAssociado;
    }

    public void setOrganizadorLogadoAssociado(boolean organizadorLogadoAssociado) {
        this.organizadorLogadoAssociado = organizadorLogadoAssociado;
    }

    public ArrayList<Elementos_equipa> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<Elementos_equipa> elementos) {
        this.elementos = elementos;
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
     * If the object is the same as the one being compared, return true. If the
     * object is not an
     * instance of the class, return false. If the object is an instance of the
     * class, compare the
     * password of the object to the password of the class
     * 
     * @param o the object to compare with
     * @return The password of the team.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Equipa)) {
            return false;
        }
        Equipa equipa = (Equipa) o;
        return Objects.equals(password, equipa.password);
    }

    /**
     * The hashCode() method returns a hash code value for the object
     * 
     * @return The hashcode of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.getNome(), password, super.getData_criacao());
    }

    /**
     * The function returns a string that contains the name, password and date of
     * creation of the user
     * 
     * @return The toString method is being overridden to return the name, password,
     *         and data_creacao
     *         of the user.
     */
    @Override
    public String toString() {
        return "{" +
                " name='" + super.getNome() + "'" +
                ", password='" + getPassword() + "'" +
                ", data_creacao='" + getData_criacao() + "'" +
                "}";
    }

}
