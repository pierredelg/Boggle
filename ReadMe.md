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

### Lancement du Jeu
- lancer la commande java :
> java -cp $CLASSPATH:out boggle.ui.Plateau

####Ajouter JAVA et JAVAFx
- télécharger le jdk 1.8 sur le site d'Oracle:
> https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

- Ajoutez le jar de javafx au classpath (si le jdk se trouve dans le dossier Téléchargement/ de votre répertoire personnel) : 
> export CLASSPATH=$CLASSPATH:.:~/Téléchargement/jdk1.8.0_231/jre/lib/ext/jfxrt.jar

####Installer find
> sudo apt-get install find
