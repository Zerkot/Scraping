Introduction
Ce projet a été conçu pour collecter et traiter des données à partir de pages web en utilisant la librairie JSOUP. Le résultat est exporté dans un fichier CSV (players.csv), qui peut ensuite être utilisé pour des analyses approfondies dans le logiciel de votre choix, tel que Power BI ou Excel.

Prérequis
Avant de lancer le projet, assurez-vous d'avoir :

Un IDE compatible avec Java, comme Eclipse : Télécharger Eclipse.
La librairie JSOUP installée : Télécharger JSOUP.
Java installé sur votre système (version 8 ou ultérieure recommandée).
Étapes pour lancer le projet
Cloner le repository
Clonez ce repository sur votre machine en exécutant la commande suivante dans votre terminal :

bash
Copier
Modifier
git clone <URL_DU_REPOSITORY>
Importer le projet dans Eclipse

Ouvrez Eclipse.
Cliquez sur File > Import > Existing Projects into Workspace.
Sélectionnez le dossier contenant les fichiers clonés.
Configurer le projet

Ajoutez la librairie JSOUP au projet :
Faites un clic droit sur le projet dans l'onglet Project Explorer.
Allez dans Build Path > Configure Build Path > Libraries > Add External JARs.
Sélectionnez le fichier JAR de JSOUP téléchargé précédemment.
Modifier le code

Adaptez le script de scraping selon les données que vous souhaitez extraire. Vous pouvez changer l'URL cible, les sélecteurs CSS, etc.
Exécuter le programme

Lancez le script en appuyant sur Run dans Eclipse.
Récupérer le fichier CSV

Une fois le script exécuté, le fichier players.csv sera généré à la racine du projet.
Ce fichier contient les données extraites, prêtes à être utilisées pour des analyses dans des outils comme Power BI, Excel ou tout autre logiciel d'analyse de données.
Exemple d'application
Dans notre projet, nous avons utilisé ce script pour scraper les statistiques des joueurs sur le site officiel de FC25. Les données récoltées incluent des informations telles que :

Nationalité
Club
Nom
Position
Statistiques détaillées (vitesse, tir, passe, etc.)
Le fichier CSV généré a ensuite été intégré dans Power BI pour créer un tableau de bord interactif permettant d'explorer les données par club, position, ou autres critères.

Remarques
Assurez-vous de respecter les règles d'éthique et de légalité du scraping (par exemple, vérifier le fichier robots.txt du site).
Si vous rencontrez des problèmes d'encodage (caractères spéciaux), assurez-vous que le fichier CSV est traité en UTF-8.
Liens utiles
Site officiel de JSOUP : https://jsoup.org/
Documentation Eclipse : https://www.eclipse.org/documentation/
Guide du fichier robots.txt : https://www.robotstxt.org/
