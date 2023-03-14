package modele;
import etu1889.framework.annotation.*;

public class Emp{
    public Emp(){

    }
    
    @AnnotationTesterUrl(url="emp-add")
    public boolean add(){
        String rep="methode eu emp-add";
        System.out.println(rep);
        return true;
    }
}