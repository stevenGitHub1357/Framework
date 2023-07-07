package modele;

import etu1889.framework.Mapping;
import etu1889.framework.servlet.FrontServlet;
import etu1889.framework.*;
import modele.Utilitaire;

import java.util.*;

import javax.management.ObjectName;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.Class;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
        
        try {
            ArrayList<File> pack = new ArrayList<>();
            Utilitaire utiles = new Utilitaire();
            String current = FrontServlet.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            current = "/D:/ITU/Donnees_logiciels/apache-tomcat-10.0.22/webapps/Framework/WEB-INF/classes/";
            System.out.println("current : "+current);
            // ArrayList<File> allPack = utiles.getAllPack(current,pack);
            // ArrayList<File> allClass = utiles.getAllClass(current);
            // ArrayList<Mapping> allMapping = utiles.initMapping(current);
            // HashMap<String,Mapping> map = utiles.initHashMapUrl(current);
            // for(int i=0; i<allPack.size(); i++){
            //     System.out.println(allPack.get(i));
            // }
            System.out.println("rep "+new Utilitaire().getView("emp-add",current));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
