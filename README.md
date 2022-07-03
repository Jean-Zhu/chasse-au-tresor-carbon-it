CARBON IT : La chasse aux trésors
======

## Sommaire
* [Description du projet](#description-du-projet)
* [Installation et démarrage](#installation-et-dmarrage)
* [Technologies](#technologies)
* [Architecture](#architecture)
* [Auteur](#auteur)
##  Description du projet

Ce projet a pour objectif de simuler une partie de chasse aux trésors.
Il représente la réalisation d'un exercice technique donné par Carbon IT.

## Installation et démarrage

- Pour lancer le projet, cloner ou télécharger l'archive de ce repo.
- Générer le jar en lançant la commande `mvn clean package` ou `mvn clean install`
- Aller dans le dossier target et lancer le jar :
    - Pour lancer le test avec le fichier donné dans l'exercice :
        - `java -jar treasurehunt-0.0.1-SNAPSHOT.jar`
    - Pour spécifier un fichier en entrée (chemin relatif) | exemple : pour un fichier dans ./test/test.txt :
        - `java -jar treasurehunt-0.0.1-SNAPSHOT.jar -i ./test/test.txt`
    - Pour spécifier le nom du fichier de sortie | exemple : result.txt :
        - `java -jar treasurehunt-0.0.1-SNAPSHOT.jar -o result.txt`
    - Pour spécifier un fichier en entrée et le fichier de sortie :
        - `java -jar treasurehunt-0.0.1-SNAPSHOT.jar -i ./test/test.txt -o result.txt`


## Technologies

- Java 8
- Maven
- Junit 4

## Architecture

Le code a été séparé en plusieurs dossiers. Voici les 2 principaux dossiers:
* **/src/main/[...]/file** : Dossier regroupant tout ce qui se rapporte à la lecture ou écriture de fichier
* **/src/main/[...]/map** : Dossier regroupant tout ce qui se rapporte à la représentation et la simulation de la chasse aux trésors

## Auteur

Jean ZHU