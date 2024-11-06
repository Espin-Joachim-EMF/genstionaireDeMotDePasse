import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;

public class GestionnaireDeMotDePasse {

    public static final char[] CHARACHTER = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*',
            '+', ',', '-', '.', '/', ':', ';', '<', '=', '>',
            '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|',
            '}', '~'
    };

    public static final int MIN_PASSWORD_LENGTH = 5;
    public static final int MAX_PASSWORD_LENGTH = 25;

    private static HashMap<String, String> motsDePasseMap = new HashMap<>();

    public static void main(String[] args) {
        boolean continuer = true;
        while (continuer) {
            String[] option = {
                    "Ajouter un mot de passe",
                    "Afficher les mots de passe",
                    "Rechercher un mot de passe",
                    "Supprimer un site et mot de passe",
            };
            int choix = JOptionPane.showOptionDialog(null, "Choissiez une option", "Gestionnaire de mot de passe",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

            switch (choix) {
                case 0 -> ajouterMotDePasse();
                case 1 -> afficherMotsDePasse();
                case 2 -> chercherMotDePasse();
                case 3 -> supprimerSite();
                default -> continuer = false;
            }
        }
    }

    private static void ajouterMotDePasse() {
        String site = JOptionPane.showInputDialog(null, "Entrez un site. exemple.com");
        if (site.isEmpty() || site == null) {
            JOptionPane.showMessageDialog(null, "le site de peut pas etre vide.");
            return;
        }

        String[] options = { "Entrez le mot de passe", "Générer le mot de passe" };
        int choixMdp = JOptionPane.showOptionDialog(null, "choissisez une option.", "Créeation de mot de passe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choixMdp) {
            case 0 -> {
                String motDePass = JOptionPane.showInputDialog(null, "Entrez le mot de passe");
                if (motDePass == null || motDePass.isEmpty() || !isPasswordLengthValid(motDePass.length())) {
                    JOptionPane.showMessageDialog(null,
                            "Mot de passe invalide (doit contenir entre 5 et 20 caractères).");
                    return;
                }
                motsDePasseMap.put(site, motDePass);
                JOptionPane.showMessageDialog(null, "Mot de passe pour " + site + " configuré avec succès.");
            }

            case 1 -> {
                String input = JOptionPane.showInputDialog(null, "Taille du mot de passe (entre 5 et 20 caractères) ?");
                try {
                    int length = Integer.parseInt(input);
                    if (!isPasswordLengthValid(length)) {
                        JOptionPane.showMessageDialog(null, "Taille de mot de passe invalide.");
                        return;
                    }
                    motsDePasseMap.put(site, genererMotDePasse(length));
                    JOptionPane.showMessageDialog(null, "Mot de passe pour " + site + " configuré avec succès.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrée invalide.");
                }
            }
            default -> JOptionPane.showMessageDialog(null, "Choix invalide");
        }
    }

    private static boolean isPasswordLengthValid(int length) {
        return length >= MIN_PASSWORD_LENGTH && length <= MAX_PASSWORD_LENGTH;
    }

    private static String genererMotDePasse(int length) {
        Random random = new Random();
        StringBuilder motDePass = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            motDePass.append(CHARACHTER[random.nextInt(CHARACHTER.length)]);
        }
        return motDePass.toString();
    }

    private static void afficherMotsDePasse() {
        if (motsDePasseMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun mot de passe enregistrée.");
            return;
        }
        StringBuilder listeMotsDePasse = new StringBuilder("Mots de passe enregistrés :\n");
        motsDePasseMap.forEach((site, mdp) -> listeMotsDePasse.append(site).append(" : ").append(mdp).append("\n"));
        JOptionPane.showMessageDialog(null, listeMotsDePasse.toString());
    }

    private static void chercherMotDePasse() {
        if (motsDePasseMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun mot de passe enregistrée.");
            return;
        }
        String site = JOptionPane.showInputDialog(null, "Entrez le nom du site");
        if (site == null || !motsDePasseMap.containsKey(site)) {
            JOptionPane.showMessageDialog(null, "Site non trouvé");
            return;
        }
        JOptionPane.showMessageDialog(null, "Mot de passe pour " + site + " : " + motsDePasseMap.get(site));
    }

    private static void supprimerSite() {
        if (motsDePasseMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun mot de passe enregistrée.");
            return;
        }
        String site = JOptionPane.showInputDialog(null, "Entrez le nom du site à supprimer");
        if (site == null || !motsDePasseMap.containsKey(site)) {
            JOptionPane.showMessageDialog(null, "Site non trouvé.");
            return;
        }
        motsDePasseMap.remove(site);
        JOptionPane.showMessageDialog(null, "Le site " + site + " à été supprimé avec succès.");
    }

}