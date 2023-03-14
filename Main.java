package modele;

import etu1889.framework.Mapping;
import etu1889.framework.annotation.AnnotationTesterUrl;

import java.util.*;

import javax.management.ObjectName;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.Class;
import java.lang.*;

public class Main {
    public static ArrayList<Mapping> init()throws Exception{
        ArrayList<Mapping> allTrueMapping = new ArrayList<Mapping>();
        Mapping mapping = new Mapping(null, null);
        File pathOfPack = new File(".\\modele");
        File[] allClassInPack = pathOfPack.listFiles();
        if(allClassInPack == null){
            throw new Exception("Package vide ou inexistant");
        }
        for (int i = 0; i < allClassInPack.length; i++) {
            try {
                String actuelClassName="modele."+allClassInPack[i].getName().split(".class")[0];
                Class<?> trueClasse = Class.forName(actuelClassName);
                Method[] allMethodeInClass = trueClasse.getDeclaredMethods();
                for (int j = 0; j < allMethodeInClass.length; j++) {
                    if(allMethodeInClass[j].getAnnotation(AnnotationTesterUrl.class)!=null){
                        mapping.setClassName(actuelClassName);
                        mapping.setMethode(String.valueOf(allMethodeInClass[j]));
                        allTrueMapping.add(mapping);
                    }
                }
            } catch (Exception e) {
                System.out.println("error in research of mapping");
            }
        }
        return allTrueMapping;
    }

    public static void main(String[] args) {
        try {
            init();
            System.out.println("Class: "+init().get(0).getClassName());
            System.out.println("Methode: "+init().get(0).getMethode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
