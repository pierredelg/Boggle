# Boogle

------------------

## Installation

###Pré-requis : 
- Assurez vous d'avoir java 1.8 d'installé sur votre machine (jdk 8)
- Assurer vous d'avoir la commande find

### Compilation des fichiers sources
- ouvrez un terminal et placez vous dans le dossier contenant l'archive téléchargée
- décompréssez le fichier tar.gz : 
> tar xvf archivedossier.tar
- placez vous à la racine du dossier décompréssé 
- éxécutez la commande suivante afin de répertorier les sources dans un fichier : 
> find -name "*.java" > source.txt
- éxécutez la commande suivante afin de compiler les sources : 
> javac -d out @source.txt

####Ajouter JAVA 8
- télécharger le jdk 1.8 sur le site d'Oracle et non openjdk:
> https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

####Installer find
> sudo apt-get install find

### Lancement du Jeu
- lancer la commande java :
> java -classpath out boggle.ui.Plateau