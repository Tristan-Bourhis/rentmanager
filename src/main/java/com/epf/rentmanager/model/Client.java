package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate naissance;

    public Client(){}
    
    public Client(String nom, String prenom, String email){
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
    }

    public Client(String nom, String prenom, String email, LocalDate naissance){
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.naissance=naissance;
    }   

    public Client(int id, String nom, String prenom, String email, LocalDate naissance){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.naissance=naissance;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String toString(){
        return "Id : " + this.id + " Nom : " +this.nom + " Prenom : " + this.prenom + " Email : " + this.email + " Naissance : " + this.naissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    



}
