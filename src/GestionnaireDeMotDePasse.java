import java.util.HashMap;
import javax.swing.JOptionPane;

public class GestionnaireDeMotDePasse {

    private static HashMap<String, String> motsDePasseMap = new HashMap<>();

    public static void main(String[] args) {
        boolean continuer = true;
        while (continuer) {
            String[] option = { "Ajouter un mot de passe", "Afficher les mots de passe", "Rechercher un mot de passe","Supprimer un mots de passe",
                    "Quitter"};
            int choix = JOptionPane.showOptionDialog(null, "Choissiez une options", "Gestionnaire de mot de passe",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

            switch (choix) {
                case 0 -> ajouterMotDePasse();
                case 1 -> afficherMotsDePasse();
                case 2 -> cherchezMdp();
                case 3 -> suprimerMdp();
                case 4  -> {
                    continuer = false;
                }

                default -> JOptionPane.showMessageDialog(null, "Choix Invalide", "Choix invalide",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private static void ajouterMotDePasse() {
        String site = JOptionPane.showInputDialog(null, "Entrez le site");
        if (site.isEmpty() || site == null) {
            JOptionPane.showMessageDialog(null, "le site de peut pas etre vide.");
            return;
        }
        String motDePass = JOptionPane.showInputDialog(null, "Entrez le mot de passe");
        if (motDePass == null || motDePass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le mot de passe ne peut pas etre vide");
            return;
        }
        motsDePasseMap.put(site, motDePass);
        JOptionPane.showMessageDialog(null, "Le mot de passe pour " + site + " a bien été configurée.");
    }

    public static void afficherMotsDePasse() {
        if (motsDePasseMap.isEmpty() || motsDePasseMap == null) {
            JOptionPane.showMessageDialog(null, "Aucun mot de passe engregistrée.", "Affichage des mots de passe",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder listeMotsDePasse = new StringBuilder("Mot de passe ennregistrée :\n");
        for (String site : motsDePasseMap.keySet()) {
            listeMotsDePasse.append(site).append(" : ").append(motsDePasseMap.get(site)).append("\n");

        }
        JOptionPane.showMessageDialog(null, listeMotsDePasse.toString());
        return;
    }

    private static void cherchezMdp() {
        if (motsDePasseMap == null || motsDePasseMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le nom du site ne peut pas être vide.");
            return;
        }
        String site = JOptionPane.showInputDialog(null, "Entrez le nom du site");

        String motDePasse = motsDePasseMap.get(site);
        if (motDePasse != null) {
            JOptionPane.showMessageDialog(null, "Mot de passe pour " + site + " : " + motDePasse);
        } else {
            JOptionPane.showMessageDialog(null, "Auncun mot de pass trouvé pour " + site);
        }
    }

    private static void suprimerMdp() {
        String site = JOptionPane.showInputDialog(null,
                "Entrez le nom du site au quelle vous voulez suprimmer le mot de passe");
        if (motsDePasseMap.containsKey(site)) {
            motsDePasseMap.remove(site);
            JOptionPane.showMessageDialog(null, "Le mot de passe de " + site + " a bien été suprimer.");
        } else {
            JOptionPane.showMessageDialog(null, "Le site n'a pas été trouvée");
            return;
        }
    }
}