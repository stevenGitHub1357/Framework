javac -d . *.java
mkdir .\Framework\WEB-INF\classes\
mkdir .\Framework\WEB-INF\lib\
mkdir .\Framework\pages\
mkdir .\Framework\WEB-INF\src\

robocopy .\Test-Framework\src  .\Framework\WEB-INF\classes /S /E *.class
copy .\Test-Framework\view\  .\Framework\pages\ 
copy .\Test-Framework\src\  .\Framework\WEB-INF\src\
copy .\Test-Framework\ .\Framework\WEB-INF\

jar cfe Framework.jar Main -C   .\Test-Framework\src .
jar cfe Framework(Test).war Main -C   .\Test-Framework\ .

copy .\Framework.jar  .\Framework\WEB-INF\lib 

"build framework terminer"

"Copier dans tomcat

Pause
mkdir D:\ITU\Donnees_logiciels\apache-tomcat-10.0.22\webapps\Framework
mkdir ..\..\Framework
xcopy .\Framework   ..\..\Framework /S
xcopy .\Framework   D:\ITU\Donnees_logiciels\apache-tomcat-10.0.22\webapps\Framework /S
Pause