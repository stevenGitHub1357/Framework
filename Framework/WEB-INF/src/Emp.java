package modele;
import modele.ModelView;
// package etu1889.framework;
import etu1889.framework.annotation.*;

public class Emp{
    ModelView modele = new ModelView("Page.jsp");
    public Emp(){

    }
    @AnnotationUrl(url="emp-add")
    public ModelView add(){
        return modele;
    }
}