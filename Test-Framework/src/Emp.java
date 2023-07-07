/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import etu1889.framework.annotation.AnnotationUrl;
import etu1889.framework.ModelView;

public class Emp {
    int idEmploye;
    String nom;
    String prenom;
    int age;

    public Emp() {
    }

    public Emp(int idEmploye, String nom, String prenom, int age) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    @AnnotationUrl(url = "emp-view")
    public ModelView view() {
        ModelView view = new ModelView("view.html");
        return view;
    }

    public int getIdEmp() {
        return this.idEmploye;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String snom) {
        this.nom = snom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String sprenom) {
        this.prenom = sprenom;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int sage) {
        this.age = sage;
    }
}
