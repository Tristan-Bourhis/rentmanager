package com.epf.rentmanager.model;

public class Vehicule {

    private int id;
    private String constructeur;
    private String modele;
    private int nbPlaces;

    public Vehicule(String constructeur, int nbPlaces){
        this.constructeur=constructeur;
        this.nbPlaces=nbPlaces;
    }

    public Vehicule(int id, String constructeur, int nbPlaces){
        this.id=id;
        this.constructeur=constructeur;
        this.nbPlaces=nbPlaces;
    }

    public Vehicule(String constructeur, String modele, int nbPlaces) {
		this.constructeur = constructeur;
		this.modele = modele;
		this.nbPlaces = nbPlaces;
	}

	public Vehicule(int id, String constructeur, String modele, int nbPlaces){
        this.id=id;
        this.constructeur=constructeur;
        this.modele=modele;
        this.nbPlaces=nbPlaces;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String toString(){
        return "Id : "+this.id+" Constructeur : "+this.constructeur+" Nombre de places : "+this.nbPlaces;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    

}