package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Ronda is a class that represents a round of a competition
 */
public class Ronda {
    private int id;
    private String data_criacao;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int id_competicao;
    private String tipo;
    private String numero;

    // A constructor that sets the date of creation of the round to the current
    // date.
    public Ronda() {
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    // A constructor that sets the date of creation of the round to the current
    // date.
    public Ronda(int id_competicao, String tipo, String numero) {
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
        this.id_competicao = id_competicao;
        this.tipo = tipo;
        this.numero = numero;
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
     * This function returns the value of the private variable id_competicao
     * 
     * @return The id_competicao.
     */
    public int getId_competicao() {
        return this.id_competicao;
    }

    /**
     * This function sets the value of the variable id_competicao to the value of
     * the parameter
     * id_competicao
     * 
     * @param id_competicao int
     */
    public void setId_competicao(int id_competicao) {
        this.id_competicao = id_competicao;
    }

    /**
     * This function returns the value of the variable tipo
     * 
     * @return The tipo of the object.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * This function sets the tipo of the object
     * 
     * @param tipo The type of the object.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * This function returns the value of the variable numero
     * 
     * @return The value of the variable numero.
     */
    public String getNumero() {
        return this.numero;
    }

    /**
     * This function sets the name of the object
     * 
     * @param numero The name of the person
     */
    public void setNumero(String numero) {
        this.numero = numero;
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
     * If the object is the same object, return true. If the object is not an
     * instance of the same
     * class, return false. If the object is an instance of the same class, compare
     * the fields
     * 
     * @param o the object to compare to
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ronda)) {
            return false;
        }
        Ronda ronda = (Ronda) o;
        return Objects.equals(data_criacao, ronda.data_criacao)
                && id_competicao == ronda.id_competicao && Objects.equals(tipo, ronda.tipo)
                && Objects.equals(numero, ronda.numero);
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString() method returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                " data_creacao='" + getData_criacao() + "'" +
                ", id_competicao='" + getId_competicao() + "'" +
                ", tipo='" + getTipo() + "'" +
                ", numero='" + getNumero() + "'" +
                "}";
    }

}
