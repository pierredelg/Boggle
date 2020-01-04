# Boogle

------------------

## Installation

###Pré-requis : 
- Assurez vous d'avoir java 11 d'installé sur votre machine
- Assurez vous d'avoir javafx d'installé sur votre machine

####Ajouter JAVA 11
- télécharger le jdk 11.0.5 sur le site d'Oracle:
> https://www.oracle.com/technetwork/java/javase/downloads/index.html
- Décompressez le jar du jdk

####Ajouter JAVAFx
- télécharger javafx sur le site:
> https://gluonhq.com/products/javafx/ 
- prendre la version JavaFX Linux SDK pour la version Long Term Support 
- Décompressez le jar de JavaFx


####Lancement du jeu via le boggle.jar

- Placez vous dans le repertoire boggle_jar est lancer la commande suivante:

java -cp "chemin/vers/repertoire/du/jdk11 --module-path "chemin/vers/repertoire/openjfx/lib" --add-modules=javafx.controls -Dfile.encoding=UTF-8 -jar "boggle.jar"

exemple:

java -cp /home/ludo/javaJDK/jdk-11.0.5/bin/java --module-path /home/ludo/javaJDK/openjfx-11.0.2_linux-x64_bin-sdk/javafx-sdk-11.0.2/lib --add-modules=javafx.controls -Dfile.encoding=UTF-8 -jar /home/ludo/LPDA2I/boggle/out/artifacts/boggle_jar/boggle.jar





####Installer find
> sudo apt-get install find
