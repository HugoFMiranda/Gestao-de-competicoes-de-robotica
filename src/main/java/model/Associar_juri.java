package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Associar_juri is a class that represents the association between a juri and a
 * competition.
 */
public class Associar_juri {
    private int id_juri;
    private int id_competicao;
    private String data_criacao;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // A constructor that sets the date of creation of the object to the current
    // date.
    public Associar_juri() {
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    // A constructor that sets the date of creation of the object to the current
    // date.
    public Associar_juri(int id_juri, int id_competicao) {
        this.id_juri = id_juri;
        this.id_competicao = id_competicao;
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    /**
     * This function returns the value of the private variable id_juri
     * 
     * @return The id_juri
     */
    public int getId_juri() {
        return this.id_juri;
    }

    /**
     * This function sets the value of the variable id_juri to the value of the
     * parameter id_juri
     * 
     * @param id_juri the id of the juri
     */
    public void setId_juri(int id_juri) {
        this.id_juri = id_juri;
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
     * If the object is the same object, return true. If the object is not an
     * instance of the same
     * class, return false. If the object is an instance of the same class, compare
     * the fields
     * 
     * @param o the object to compare to
     * @return The id_juri, id_competicao and data_creacao.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Associar_juri)) {
            return false;
        }
        Associar_juri associar_juri = (Associar_juri) o;
        return id_juri == associar_juri.id_juri && id_competicao == associar_juri.id_competicao
                && Objects.equals(data_criacao, associar_juri.data_criacao);
    }

    /**
     * The function is used to return a string representation of the object
     * 
     * @return The id_juri, id_competicao and data_creacao.
     */
    @Override
    public String toString() {
        return "{" +
                " id_juri='" + getId_juri() + "'" +
                ", id_competicao='" + getId_competicao() + "'" +
                ", data_creacao='" + getData_criacao() + "'" +
                "}";
    }

}
