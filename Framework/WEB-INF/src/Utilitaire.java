package modele;


import java.util.*;

import javax.management.ObjectName;

import etu1889.framework.Mapping;
import etu1889.framework.annotation.AnnotationUrl;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


public class Utilitaire {
    public Utilitaire(){}

    public ArrayList<File> getAllPack(String current,ArrayList<File> allPackIn){
        // String path = ".\\"+current;
        String path =  this.getClass().getClassLoader().getResource("").getPath().toString();
        // System.out.println(current);
        File folder = new File(current);
        File[] listOfFolders = folder.listFiles(File::isDirectory);
        if(listOfFolders != null){
            for (int i = 0; i < listOfFolders.length; i++) {  
                allPackIn.add(listOfFolders[i]);
                String newPath = current+""+listOfFolders[i].getName()+"/";
                this.getAllPack(newPath,allPackIn);       
            }
        }
        
        return allPackIn;
    }

    public String traitementPath(String folderPath){
        String[] folderParts = folderPath.split("\\\\");
        String folderString = String.join(".", folderParts);
        return folderString;
    }

    public ArrayList<File> getAllClass(String pathAbsoluClass){
        ArrayList<File> allClass = new ArrayList<>();
        ArrayList<File> allPack = new ArrayList<>();
        this.getAllPack(pathAbsoluClass,allPack);
        for (File file : allPack) {
            File[] allClassInPack = file.listFiles((dir, name) -> name.endsWith(".class"));
            for (File fileAjout : allClassInPack) {
                // System.out.println("Name : "+fileAjout.getPath());
                allClass.add(fileAjout);
            }
        }
        return allClass;
    }
    

    public String getPackageOfFile(File file,String pathAbsolut){
        String pack = "";

        String[] tablePathFile = file.getAbsolutePath().split("\\\\");
        String[] tablePathAbsol = pathAbsolut.substring(1).split("/");

        // System.out.println("Table File  : "+tablePathFile[1]);
        // System.out.println("Table File absol : "+tablePathAbsol[1]);

        for (int i = tablePathAbsol.length; i < tablePathFile.length; i++) {
            if(i==tablePathFile.length-1){
                pack = pack+tablePathFile[i];
            }else{
                pack = pack+tablePathFile[i]+".";
            }    
        }
        pack = pack.replace(".class", "");
        // System.out.println("package avec class : "+pack);
        
        return pack;
    }

    public Class<?> getClassFrom(File file,String absolut) throws MalformedURLException, ClassNotFoundException {
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
        Thread.currentThread().getContextClassLoader();
        System.out.println("class Loader : "+classLoader);
        String className = file.getName().replace(".class", "");
        String pack = getPackageOfFile(file, absolut);
        System.out.println("File class : "+pack);
        Class<?> clas = classLoader.loadClass(pack); 
        System.out.println("La class : "+clas.getName());
        return clas;    
    }

    public ArrayList<Mapping> initMapping(String pathPrincip)throws Exception{
        ArrayList<Mapping> allTrueMapping = new ArrayList<Mapping>();
        Mapping mapping = new Mapping(null, null);
        ArrayList<File> pack = new ArrayList<>();
        ArrayList<File> allClass = getAllClass(pathPrincip);
        
        for (File file : allClass) {
            try {
                // System.out.println("path principe"+pathPrincip);
                Class<?> trueClasse = getClassFrom(file,pathPrincip);
                // System.out.println(trueClasse.getName());
                Method[] allMethodeInClass = trueClasse.getDeclaredMethods();
                for (int j = 0; j < allMethodeInClass.length; j++) {
                    if(allMethodeInClass[j].getAnnotation(AnnotationUrl.class)!=null){
                        // System.out.println("all methode : "+allMethodeInClass[j].getName());
                        mapping.setClassName(file.getName().replace(".class", ""));
                        mapping.setMethode(allMethodeInClass[j].getName());
                        mapping.setClassC(trueClasse);
                        allTrueMapping.add(mapping);
                        // System.out.println(mapping.getClassName()+" / "+mapping.getMethode());
                    }
                }
                if(allTrueMapping == null){
                    throw new Exception("Annotation inutiliser");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allTrueMapping;
    }
    
    public HashMap<String,Mapping> initHashMapUrl(String pathPrincip) throws Exception{
        HashMap<String,Mapping> urlMapping = new HashMap<>();
        Mapping mapping = new Mapping(null, null);
        ArrayList<File> pack = new ArrayList<>();
        ArrayList<Mapping> mappings = this.initMapping(pathPrincip);
        ArrayList<File> allClass = getAllClass(pathPrincip);
        for (Mapping map : mappings) {
            try {
                Class<?> trueClasse = map.getClassC();
                // System.out.println(trueClasse.getName());
                Method methodeInClass = trueClasse.getMethod(map.getMethode());
                    if(methodeInClass.getAnnotation(AnnotationUrl.class)!=null){
                        // System.out.println("all methode : "+methodeInClass.getName());
                        String url = methodeInClass.getAnnotation(AnnotationUrl.class).url();
                        // System.out.println("url : "+url);
                        urlMapping.put(url,map);
                    }    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return urlMapping;
    }

    public String getView(String url,String pathPrincip){
        ArrayList<Mapping> allMapping ;
        HashMap<String,Mapping> mappingUrls;
        String view = new String();
        try {
            mappingUrls = new Utilitaire().initHashMapUrl(pathPrincip);

            System.out.println("path principe : "+pathPrincip);

            Collection<Mapping> values = mappingUrls.values();
            // System.out.println("Url: "+ url);
            Mapping mapping = mappingUrls.get(url);
            // System.out.println("Class: "+mappingUrls.get(url));
            Class<?> currentClass = mapping.getClassC();
            System.out.println(currentClass.getName());
            Method fonction = currentClass.getDeclaredMethod(mapping.getMethode(),(Class[]) null);
            ModelView mView =  (ModelView) fonction.invoke(currentClass.newInstance());
            view = mView.getView();
        
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
       return view ;
    }

}
