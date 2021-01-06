/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author sermar
 */
public class Cantantes {

    private String nombreReal;
    private String nombreArt;
    private int premios;
    private int discos;
    private String fecha_nac;
    private String fecha_falle;
    private int idArt;

    public Cantantes() {
    }

    public Cantantes(String nombreReal, String nombreArt, int premios, int discos, String fecha_nac, String fecha_falle) {
        this.nombreReal = nombreReal;
        this.nombreArt = nombreArt;
        this.premios = premios;
        this.discos = discos;
        this.fecha_nac = fecha_nac;
        this.fecha_falle = fecha_falle;
    }

    public Cantantes(int idArt) {
        this.idArt = idArt;
    }

    public Cantantes(String nombreReal, String nombreArt, int premios, int discos, String fecha_nac, String fecha_falle, int idArt) {
        this.nombreReal = nombreReal;
        this.nombreArt = nombreArt;
        this.premios = premios;
        this.discos = discos;
        this.fecha_nac = fecha_nac;
        this.fecha_falle = fecha_falle;
        this.idArt = idArt;
    }

    public int getIdArt() {
        return idArt;
    }

    public void setIdArt(int idArt) {
        this.idArt = idArt;
    }   

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getNombreArt() {
        return nombreArt;
    }

    public void setNombreArt(String nombreArt) {
        this.nombreArt = nombreArt;
    }

    public int getPremios() {
        return premios;
    }

    public void setPremios(int premios) {
        this.premios = premios;
    }

    public int getDiscos() {
        return discos;
    }

    public void setDiscos(int discos) {
        this.discos = discos;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getFecha_falle() {
        return fecha_falle;
    }

    public void setFecha_falle(String fecha_falle) {
        this.fecha_falle = fecha_falle;
    }

    @Override
    public String toString() {
        return "Cantantes{" + "nombreReal=" + nombreReal + ", nombreArt=" + nombreArt + ", premios=" + premios + ", discos=" + discos + ", fecha_nac=" + fecha_nac + ", fecha_falle=" + fecha_falle + '}';
    }
    
    
    
}
