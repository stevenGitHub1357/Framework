/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import etu1889.framework.annotation.AnnotationUrl;
import etu1889.framework.*;
import etu1889.framework.ModelView;

import java.io.*;
import java.util.*;

public class Emp {
    int idEmploye;
    String nom;
    String prenom;
    int age;
    FileUpload photo;

    // Ce nom est fixé par convention pour tout les classes
    HashMap<String, Object> session = new HashMap<String, Object>();

    public Emp() {
    }

    public Emp(int idEmploye, String nom, String prenom, int age) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Emp[] listEmp() {
        Emp[] ans = new Emp[2];
        ans[0] = new Emp(1, "RAKOTO", "Nirina", 20);
        ans[1] = new Emp(2, "RAHARY", "Soa", 19);

        return ans;
    }
    
    @AnnotationUrl(url = "emp-all")
    public ModelView findAll() {

        ModelView view = new ModelView("page.jsp");
        //view.invalidateSession();
        view.addItem("lst", this.listEmp());
        view.addItem("test", 12);

        System.out.println("etu1889.framework.Employe.findAll()");
        
        return view;
    }


    @AnnotationUrl(url = "emp-add")
    public ModelView add() {
        ModelView view = new ModelView("add.jsp");
        try {
            System.out.println("--------  Emp-Add ---------");
            System.out.println("Nom: " + this.nom);
            System.out.println("Prenom: " + this.prenom);
            System.out.println("Age: " + this.age);
            System.out.println("PhotoFileName: " + this.photo.getFileName());
        } catch(Exception ex) {
            // ex.printStackTrace();
        }
        
        return view;
    }

    @AnnotationUrl(url = "up-add")
    public ModelView upload() {
        ModelView view = new ModelView("add.jsp");
        try {
            System.out.println("--------  up-Add ---------");
            System.out.println("PhotoFileName: " + this.photo.getFileName());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return view;
    }

    @AnnotationUrl(url = "emp-findById")
    public ModelView findById(int id) {
        System.out.println("--------  Emp-FindById ---------");
        System.out.println("*Id: " + id);

        ModelView view = new ModelView("info.jsp");
        view.addItem("emp", this.listEmp()[id]);
        
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

    public FileUpload getPhoto() {
        return this.photo;
    }

    public void setPhoto(FileUpload fu) {
        this.photo = fu;
    }

    public void setSession(HashMap<String, Object> s) {
        this.session = s;
    }
}
