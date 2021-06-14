package com.example.editionfactureandroid;

public class model {
    String firstname,lastname,adresse,ville,codePostal,genre, objet,todoNom,todoAdresse,designation,quantite,puht,tva,numaffaire, date,total;
    int id;
    public model(int id, String firstname, String lastname, String adresse, String ville, String codePostal, String genre, String objet, String todoNom, String todoAdresse, String designation, String quantite, String puht, String tva, String numaffaire, String date , String total) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.genre = genre;
        this.objet = objet;
        this.todoNom = todoNom;
        this.todoAdresse = todoAdresse;
        this.designation = designation;
        this.quantite = quantite;
        this.puht = puht;
        this.tva = tva;
        this.numaffaire = numaffaire;
        this.date=date;
        this.total=total;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getTodoNom() {
        return todoNom;
    }

    public void setTodoNom(String todoNom) {
        this.todoNom = todoNom;
    }

    public String getTodoAdresse() {
        return todoAdresse;
    }

    public void setTodoAdresse(String todoAdresse) {
        this.todoAdresse = todoAdresse;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getPuht() {
        return puht;
    }

    public void setPuht(String puht) {
        this.puht = puht;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getNumaffaire() {
        return numaffaire;
    }

    public void setNumaffaire(String numaffaire) {
        this.numaffaire = numaffaire;
    }
}
