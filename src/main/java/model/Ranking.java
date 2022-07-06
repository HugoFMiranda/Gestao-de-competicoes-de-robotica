package model;

import java.util.Objects;

/**
 * The class Ranking implements the Comparable interface, which means that it must implement the
 * compareTo method.
 */
public class Ranking implements Comparable<Ranking>{

    private String nome_equipa;

    private String nome_robo;

    private int tempoMilissegundos;
    private String besttempo;

    private String velmax;

    private int pontos;

    // A constructor.
    public Ranking() {
    }

    // A constructor.
    public Ranking(String nome_equipa, String nome_robo, String besttempo, String velmax, int pontos) {
        this.nome_equipa = nome_equipa;
        this.nome_robo = nome_robo;
        this.besttempo = besttempo;
        this.velmax = velmax;
        this.pontos = pontos;
    }

    /**
     * This function returns the name of the team
     * 
     * @return The name of the team.
     */
    public String getNome_equipa() {
        return nome_equipa;
    }

    /**
     * This function sets the value of the variable nome_equipa to the value of the parameter
     * nome_equipa
     * 
     * @param nome_equipa name of the team
     */
    public void setNome_equipa(String nome_equipa) {
        this.nome_equipa = nome_equipa;
    }

    /**
     * This function returns the name of the robot
     * 
     * @return The name of the robot.
     */
    public String getNome_robo() {
        return nome_robo;
    }

    /**
     * This function sets the name of the robot
     * 
     * @param nome_robo name of the robot
     */
    public void setNome_robo(String nome_robo) {
        this.nome_robo = nome_robo;
    }

    /**
     * This function returns the besttempo variable
     * 
     * @return The besttempo variable is being returned.
     */
    public String getBesttempo() {
        return besttempo;
    }

    /**
     * This function sets the besttempo variable to the value of the besttempo variable in the class
     * 
     * @param besttempo The best tempo of the song
     */
    public void setBesttempo(String besttempo) {
        this.besttempo = besttempo;
    }

    /**
     * This function returns the maximum velocity of the car
     * 
     * @return The value of the variable velmax.
     */
    public String getVelmax() {
        return velmax;
    }

    /**
     * This function sets the maximum velocity of the object
     * 
     * @param velmax The maximum velocity of the vehicle
     */
    public void setVelmax(String velmax) {
        this.velmax = velmax;
    }

    /**
     * This function returns the value of the variable pontos
     * 
     * @return The value of the variable pontos.
     */
    public int getPontos() {
        return pontos;
    }

    /**
     * This function sets the value of the variable pontos to the value of the parameter pontos
     * 
     * @param pontos points
     */
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getTempoMilissegundos() {
        return tempoMilissegundos;
    }

    public void setTempoMilissegundos(int tempoMilissegundos) {
        this.tempoMilissegundos = tempoMilissegundos;
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString method is being returned.
     */
    @Override
    public String toString() {
        return "Ranking{" +
                "nome_equipa='" + nome_equipa + '\'' +
                ", nome_robo='" + nome_robo + '\'' +
                ", besttempo=" + besttempo +
                ", velmax=" + velmax +
                ", pontos=" + pontos +
                '}';
    }

    /**
     * If the object is the same object, return true. If the object is null or not the same class,
     * return false. If the object is the same class, compare the fields
     * 
     * @param o Object
     * @return The method returns a boolean value.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranking ranking = (Ranking) o;
        return besttempo == ranking.besttempo && velmax == ranking.velmax && pontos == ranking.pontos && Objects.equals(nome_equipa, ranking.nome_equipa) && Objects.equals(nome_robo, ranking.nome_robo);
    }


    /**
     * If the points are equal, return 0. If the points are greater, return -1. If the points are less,
     * return 1
     * 
     * @param o The object to be compared.
     * @return The difference between the two objects.
     */
    @Override
    public int compareTo(Ranking o) {
        if (this.pontos == o.pontos){
            if(this.tempoMilissegundos > o.tempoMilissegundos){
                return 1;
            } else if(this.tempoMilissegundos < o.tempoMilissegundos) {
                return -1;
            }
            return 0;
        } else if (this.pontos > o.pontos) {
            return -1;
        } else {
            return 1;
        }
    }
}
