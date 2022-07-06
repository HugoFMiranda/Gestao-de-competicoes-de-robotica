package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Associar_equipa is a class that represents a table in a database
 */
public class Associar_equipa {
    private int id_competicao;
    private int id_equipa;
    private String data_criacao;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // A constructor that sets the date of creation of the object to the current
    // date.
    public Associar_equipa() {
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    // A constructor that sets the date of creation of the object to the current
    // date.
    public Associar_equipa(int id_competicao, int id_equipa) {
        this.id_competicao = id_competicao;
        this.id_equipa = id_equipa;
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    /**
     * This function returns the id_competicao of the object Competicao
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
     * This function returns the value of the private variable id_equipa
     * 
     * @return The id_equipa of the object.
     */
    public int getId_equipa() {
        return this.id_equipa;
    }

    /**
     * This function sets the id_equipa variable to the value of the id_equipa
     * parameter
     * 
     * @param id_equipa int
     */
    public void setId_equipa(int id_equipa) {
        this.id_equipa = id_equipa;
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
     * The function is used to print the values of the variables in the class
     * 
     * @return The id_competicao, id_equipa and data_creacao.
     */
    @Override
    public String toString() {
        return "{" +
                ", id_competicao='" + getId_competicao() + "'" +
                ", id_equipa='" + getId_equipa() + "'" +
                ", data_creacao='" + getData_criacao() + "'" +
                "}";
    }

    /**
     * If the object is the same as the one being compared, return true. If the
     * object is not an
     * instance of the same class, return false. If the object is an instance of the
     * same class,
     * compare the fields
     * 
     * @param o the object to compare to
     * @return The id_competicao, id_equipa and data_creacao.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Associar_equipa)) {
            return false;
        }
        Associar_equipa associar_equipa = (Associar_equipa) o;
        return id_competicao == associar_equipa.id_competicao
                && id_equipa == associar_equipa.id_equipa && Objects.equals(data_criacao, associar_equipa.data_criacao);
    }

}
