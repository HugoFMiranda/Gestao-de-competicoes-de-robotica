package model;

import handler.LoginHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * It's a class that represents a competition
 */
public class Competicao {
    private int id;
    private int id_organizador;
    private String nome_organizador;
    private int nrondas;
    private int max_equipas;
    private ArrayList<String> equipasRegistadas;
    private ArrayList<String> jurisRegistados;
    private String nome;
    private String desc;
    private String data_criacao;

    public int getIdequipaassociada() {
        return idequipaassociada;
    }

    public void setIdequipaassociada(int idequipaassociada) {
        this.idequipaassociada = idequipaassociada;
    }

    private int idequipaassociada = 0;

    private boolean jurilogadoAssociado = false;
    private boolean organizadorlogadoAssociado = false;
    private boolean equipalogadoAssociado = false;
    private String data_conclusao;

    private String estado;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // A constructor that sets the date of creation to the current date.
    public Competicao() {
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
    }

    // It's a constructor that sets the date of creation to the current date.
    public Competicao(int id_organizador, String nome_organizador, String nome,boolean jurilogadoAssociado,boolean organizadorlogadoAssociado, ArrayList<String> equipasRegistadas,ArrayList<String> jurisRegistadas, String desc, int nrondas, int max_equipas) {
        this.id_organizador = id_organizador;
        this.nome = nome;
        this.desc = desc;
        this.nrondas = nrondas;
        this.max_equipas = max_equipas;
        LocalDateTime now = LocalDateTime.now();
        this.data_criacao = dtf.format(now);
        this.nome_organizador = nome_organizador;
        this.jurilogadoAssociado = jurilogadoAssociado;
        this.organizadorlogadoAssociado = organizadorlogadoAssociado;
        this.equipasRegistadas = equipasRegistadas;
        this.jurisRegistados = jurisRegistados;
    }

    public boolean isJurilogadoAssociado() {
        return jurilogadoAssociado;
    }

    public void setJurilogadoAssociado(boolean jurilogadoAssociado) {
        this.jurilogadoAssociado = jurilogadoAssociado;
    }

    /**
     * This function returns the value of the private variable id_organizador
     * 
     * @return The id_organizador
     */
    public int getId_organizador() {
        return this.id_organizador;
    }

    /**
     * This function sets the value of the id_organizador variable
     * 
     * @param id_organizador int
     */
    public void setId_organizador(int id_organizador) {
        LoginHandler lh = new LoginHandler();
        if(lh.getUser().getId() == id_organizador){
            this.organizadorlogadoAssociado = true;
        }
        this.id_organizador = id_organizador;
    }

    /**
     * This function returns the maximum number of teams that can be in a tournament
     * 
     * @return The maximum number of teams.
     */
    public int getMax_equipas() {
        return this.max_equipas;
    }

    /**
     * This function sets the maximum number of teams that can participate in a
     * tournament
     * 
     * @param max_equipas maximum number of teams
     */
    public void setMax_equipas(int max_equipas) {
        this.max_equipas = max_equipas;
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

    public boolean isEquipalogadoAssociado() {
        return equipalogadoAssociado;
    }

    public void setEquipalogadoAssociado(boolean equipalogadoAssociado) {
        this.equipalogadoAssociado = equipalogadoAssociado;
    }

    /**
     * This function returns the number of rounds that the game has
     * 
     * @return The number of rounds.
     */
    public int getNrondas() {
        return nrondas;
    }

    /**
     * This function sets the number of rounds of the game
     * 
     * @param nrondas number of rounds
     */
    public void setNrondas(int nrondas) {
        this.nrondas = nrondas;
    }

    /**
     * This function returns the description of the item
     * 
     * @return The description of the item.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This function sets the description of the object
     * 
     * @param desc The description of the item.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * This function returns the value of the data_conclusao variable
     * 
     * @return The data_conclusao is being returned.
     */
    public String getData_conclusao() {
        return data_conclusao;
    }

    /**
     * This function sets the value of the data_conclusao variable to the value of
     * the data_conclusao
     * parameter
     * 
     * @param data_conclusao date
     */
    public void setData_conclusao(String data_conclusao) {
        this.data_conclusao = data_conclusao;
    }

    /**
     * This function returns the value of the variable estado
     * 
     * @return The value of the variable estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * This function sets the value of the variable estado to the value of the
     * parameter estado
     * 
     * @param estado The state of the user.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_organizador() {
        return nome_organizador;
    }

    public void setNome_organizador(String nome_organizador) {
        this.nome_organizador = nome_organizador;
    }

    public ArrayList<String> getEquipasRegistadas() {
        return equipasRegistadas;
    }

    public boolean isOrganizadorlogadoAssociado() {
        return organizadorlogadoAssociado;
    }

    public void setOrganizadorlogadoAssociado(boolean organizadorlogadoAssociado) {
        this.organizadorlogadoAssociado = organizadorlogadoAssociado;
    }

    public void setEquipasRegistadas(ArrayList<String> equipasRegistadas) {
        this.equipasRegistadas = equipasRegistadas;
    }

    public ArrayList<String> getJurisRegistados() {
        return jurisRegistados;
    }

    public void setJurisRegistados(ArrayList<String> jurisRegistados) {
        this.jurisRegistados = jurisRegistados;
    }

    /**
     * If the object is not null, and the class is the same, then check if the
     * id_organizador, nrondas,
     * max_equipas, nome, desc, data_creacao, data_conclusao, and estado are the
     * same
     * 
     * @param o Object
     * @return The hashcode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competicao that = (Competicao) o;
        return id == that.id && id_organizador == that.id_organizador && nrondas == that.nrondas && max_equipas == that.max_equipas && Objects.equals(nome, that.nome) && Objects.equals(desc, that.desc) && Objects.equals(data_criacao, that.data_criacao) && Objects.equals(data_conclusao, that.data_conclusao) && Objects.equals(estado, that.estado);
    }



    /**
     * The function is called when the object is printed
     * 
     * @return The toString() method is returning a string representation of the
     *         object.
     */
    @Override
    public String toString() {
        return "Competicao{" +
                "id=" + id +
                ", id_organizador=" + id_organizador +
                ", nome_organizador='" + nome_organizador + '\'' +
                ", nrondas=" + nrondas +
                ", max_equipas=" + max_equipas +
                ", equipasRegistadas=" + equipasRegistadas +
                ", jurisRegistados=" + jurisRegistados +
                ", nome='" + nome + '\'' +
                ", desc='" + desc + '\'' +
                ", data_creacao='" + data_criacao + '\'' +
                ", jurilogadoAssociado=" + jurilogadoAssociado +
                ", organizadorlogadoAssociado=" + organizadorlogadoAssociado +
                ", equipalogadoAssociado=" + equipalogadoAssociado +
                ", data_conclusao='" + data_conclusao + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
