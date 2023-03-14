package etu1889.framework;

    public class Mapping{
        String className;
        String methode;
        public Mapping(String className, String methode){
            this.className=className;
            this.methode=methode;
        }

        public String getClassName() {
            return className;
        }
        public String getMethode() {
            return methode;
        }

        public void setClassName(String className) {
            this.className = className;
        }
        public void setMethode(String methode) {
            this.methode = methode;
        }
        
    }