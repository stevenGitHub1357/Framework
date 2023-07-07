package etu1889.framework;

    public class Mapping{
        String className;
        Class classC;
        String methode;
        public Mapping(String className, String methode){
            this.className=className;
            this.methode=methode;
        }
        public Mapping(String className, String methode, Class<?> classC){
            this.className=className;
            this.methode=methode;
            this.classC = classC;
        }

        public Mapping(){}

        public String getClassName() {
            return className;
        }
        public String getMethode() {
            return methode;
        }
        public Class getClassC() {
            return classC;
        }

        public void setClassName(String className) {
            this.className = className;
        }
        public void setMethode(String methode) {
            this.methode = methode;
        }
        public void setClassC(Class classC) {
            this.classC = classC;
        }
        
    }