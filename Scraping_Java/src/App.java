import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
    	// Créer une liste pour stocker les données des joueurs
        List<String[]> playerDataList = new ArrayList<>();

        // Ajouter l'en-tête du fichier CSV
        playerDataList.add(new String[]{"Nationalité", "Club", "Nom", "Position", "Note",  "VIT", "TIR", "PAS", "DRI", "DEF", "PHY"});
        try {
            // URL de base (à ajuster si besoin)
            String baseUrl = "https://www.ea.com/fr/games/ea-sports-fc/ratings?team=21&team=22&team=23&team=25&team=32&team=36&team=38&team=160&team=169&team=175&team=576&team=1824&team=1831&team=10029&team=100409&team=110329&team=111235&team=112172&team=1&team=2&team=5&team=7&team=9&team=10&team=11&team=13&team=14&team=17&team=18&team=19&team=94&team=110&team=95&team=144&team=1799&team=1808&team=1925&team=1943&team=240&team=241&team=448&team=243&team=449&team=450&team=452&team=453&team=457&team=461&team=462&team=463&team=472&team=479&team=480&team=481&team=483&team=1860&team=100888&team=110062&team=57&team=64&team=65&team=66&team=69&team=70&team=71&team=72&team=73&team=74&team=76&team=219&team=378&team=379&team=1530&team=1738&team=1809&team=1819&team=45&team=48&team=50&team=52&team=54&team=55&team=189&team=205&team=206&team=347&team=1745&team=1746&team=1842&team=110374&team=110556&team=111811&team=115841&team=115845&team=131681&team=131682&page=";
            
         // Boucle pour parcourir les 26 pages
            for (int page = 1; page <= 26; page++) {
                String url = baseUrl + page;
            
            // Connexion et récupération du contenu HTML
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(10000)
                    .get();

            // Sélecteur des lignes du tableau
            Elements playerRows = doc.select("tr.Table_row__eoyUr");

            if (playerRows.isEmpty()) {
                System.out.println("Aucun joueur trouvé. Vérifiez le sélecteur CSS.");
                return;
            }
         // Parcours de chaque ligne de joueur
            for (Element playerRow : playerRows) {
                try {
                    // Exemple d'extraction des données depuis les cellules
                    String playerName = playerRow.selectFirst("div.Table_profileContent__0t2_u").text(); // Colonne 1 : Nom du joueur
                    String position = playerRow.selectFirst("td.Table_cell__qBFwB").text();             // Colonne 2 : Position
                    String rating = playerRow.selectFirst("div.Table_statCellValueContent__eSIUF").text(); // Colonne 3 : Note

                 // Sélectionner tous les éléments <img> dans playerRow
                    Elements imgElements = playerRow.select("img.Picture_image__L8suG");
                    String club = null;
                    String nationalité = null;
                    // Vérifier qu'il y a au moins 4 éléments
                    if (imgElements.size() >= 4) {
                        // Récupérer le 3ᵉ élément (index 2) pour la nationalité
                        Element nationalityElement = imgElements.get(2);
                        nationalité = nationalityElement.attr("alt");

                        // Vérifier que l'attribut "alt" pour la nationalité n'est pas vide
                        if (!nationalité.isEmpty()) {
                            System.out.println("Nationalité : " + nationalité);
                        } else {
                            System.out.println("Le 3ᵉ 'alt' pour la nationalité est vide.");
                        }

                        // Récupérer le 4ᵉ élément (index 3) pour le club
                        Element clubElement = imgElements.get(3);
                        club = clubElement.attr("alt");

                        // Vérifier que l'attribut "alt" pour le club n'est pas vide
                        if (!club.isEmpty()) {
                            System.out.println("Nom du club : " + club);
                        } else {
                            System.out.println("Le 4ᵉ 'alt' pour le club est vide.");
                        }
                    } else {
                        System.out.println("Moins de 4 images trouvées dans la ligne.");
                    }

                    String poste = playerRow.selectFirst("span.Table_tag__vKZKn").text();               // Colonne 5 : Poste
                    String VIT = playerRow.selectFirst("div[data-label='VIT'] span.Table_statCellValue__zn5Cx").ownText(); // Colonne 6 : VIT
                    String TIR = playerRow.selectFirst("div[data-label='TIR'] span.Table_statCellValue__zn5Cx").ownText(); // Colonne 7 : TIR
                    String PAS = playerRow.selectFirst("div[data-label='PAS'] span.Table_statCellValue__zn5Cx").ownText(); // Colonne 8 : PAS
                    String DRI = playerRow.selectFirst("div[data-label='DRI'] span.Table_statCellValue__zn5Cx").ownText(); // Colonne 9 : DRI
                    String DEF = playerRow.selectFirst("div[data-label='DÉF'] span.Table_statCellValue__zn5Cx").ownText(); // Colonne 10 : DEF
                    String PHY = playerRow.selectFirst("div[data-label='PHY'] span.Table_statCellValue__zn5Cx").ownText(); // Colonne 11 : PHY

                 // Ajouter les données du joueur dans la liste
                    playerDataList.add(new String[]{nationalité, club, playerName, position, rating,  VIT, TIR, PAS, DRI, DEF, PHY});
                    
                    // Affichage des données
                    System.out.println("Nom : " + playerName);
                    System.out.println("Position : " + position);
                    System.out.println("Note : " + rating);
                    //System.out.println("Club : " + clubName);
                    System.out.println("Poste : " + poste);
                    System.out.println("VIT : " + VIT);
                    System.out.println("TIR : " + TIR);
                    System.out.println("PAS : " + PAS);
                    System.out.println("DRI : " + DRI);
                    System.out.println("DEF : " + DEF);
                    System.out.println("PHY : " + PHY);
                    System.out.println("-----------------------");
                } catch (Exception e) {
                    System.err.println("Erreur lors de l'extraction des données d'un joueur : " + e.getMessage());
                }
            }
            }

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        
     // Écriture dans le fichier CSV
        try (BufferedWriter csvWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("players.csv"), StandardCharsets.UTF_8))) {

            for (String[] rowData : playerDataList) {
                csvWriter.write(String.join(",", rowData)); // Écrit les données en UTF-8
                csvWriter.newLine(); // Ajoute une nouvelle ligne
            }
            System.out.println("Données écrites dans players.csv avec succès en UTF-8.");
            System.out.println("Répertoire de travail : " + System.getProperty("user.dir"));
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier CSV : " + e.getMessage());
        }
    }
}
