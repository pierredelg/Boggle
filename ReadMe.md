# Boogle

------------------

## Installation

###Pré-requis : 
- Assurez vous d'avoir java 1.8 d'installé sur votre machine
- Assurez vous d'avoir javafx d'installé sur votre machine
- Assurer vous d'avoir la commande find

### Compilation des fichiers sources
- ouvrez un terminal et placez vous dans le dossier contenant l'archive téléchargée
- décompréssez le fichier tar.gz : 
> tar xvf archivedossier.tar
- placez vous dans le dossier décompréssé
- éxécutez la commande suivante afin de répertorier les sources dans un fichier : 
> find -name "*.java" > source.txt
- éxécutez la commande suivante afin de compiler les sources : 
> javac -d out @source.txt

####Ajouter JAVA 8 et JAVAFx
- télécharger le jdk 1.8 sur le site d'Oracle:
> https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

- Ajoutez le jar de javafx au classpath (si le jdk se trouve dans le dossier Téléchargement/ de votre répertoire personnel) : 
> export CLASSPATH=$CLASSPATH:.:~/Téléchargement/jdk1.8.0_231/jre/lib/ext/jfxrt.jar

####Installer find
> sudo apt-get install find


####Ajouter JAVA 11 et JAVAFx
- télécharger le jdk 11.0.5 sur le site d'Oracle:
> https://www.oracle.com/technetwork/java/javase/downloads/index.html

- télécharger le jar de javafx sur le site:
> https://gluonhq.com/products/javafx/ 
- prendre la version JavaFX Linux SDK pour la version Long Term Support 

- Décompressez le jar de JavaFx dans un répertoire

- Pour lancer avec intelliJ :
direction edit configuration/VM option et ajoutez la ligne suivante:
--module-path "chemin vers votre repertoire"/openjfx-11.0.2_linux-x64_bin-sdk/javafx-sdk-11.0.2/lib --add-modules=javafx.controls

- Ajouter tout les .jar de lib (vue ci dessus de javaFx) dans la librairie du projet.

- Suivez les instructions pour créez le boggle.jar:
> http://info.clg.qc.ca/java/divers/creer-jar-intellij-idea

Une fois crééé, ajouter dans edit configurations du boogle.jar la ligne suivante dans VM options puis lancer le boggle.jar:
--module-path "chemin vers votre repertoire"/openjfx-11.0.2_linux-x64_bin-sdk/javafx-sdk-11.0.2/lib --add-modules=javafx.controls


- Pour lancer le jar boggle.jar en ligne de commande lancer cette commande:
java -cp "chemin/vers/repertoire/du/jdk11"/jdk-11.0.5/bin/java --module-path "chemin/vers/repertoire/openjfx"/openjfx-11.0.2_linux-x64_bin-sdk/javafx-sdk-11.0.2/lib --add-modules javafx.controls -Dfile.encoding=UTF-8 -jar "chemin/vers/boggle.jar"/boggle/out/artifacts/boggle_jar/boggle.jar

### Lancement du Jeu
- lancer la commande java :
> java -cp $CLASSPATH:out boggle.ui.Plateau