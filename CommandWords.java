 
/**
 * CommandWords permet de vérifier si une chaîne de caractères correspond à une commande ou une direction valide pour un jeu vidéo textuel.
 * @Clément Sainte-Rose
 */
public class CommandWords
{
    private final String[] aValidCommands = {"go","quit","help","look","eat","back","test","take","drop","inventory","charge","fire"};
    private static final String[] aValidDirection = {"north","south","east","west","up","down", "Ke6", "025117"};
    
    /**
     * Constructeur par défaut vide.
     */
    public CommandWords()
    {
    }// CommandWords ()
    
    /**
     * Cette méthode prend en paramètre une chaîne de caractères.
     * Elle vérifie si cette chaîne est une commande valide pour le jeu en comparant la chaîne avec chaque élément de la liste aValidCommands. 
     * Si la chaîne correspond à une commande valide, la méthode retourne true, sinon elle retourne false.
    */
    public boolean isCommand (final String pString)
    {
        for (int i=0; i<aValidCommands.length; i++) {
            if (aValidCommands[i].equals(pString)){
                return true;
            }
        }
        return false;
    }// isCommand ()
    /**
     * Cette méthode vérifie si la chaîne passée en paramètre correspond à une direction valide.
     */
    public static boolean isDirection (final String pString)
    {
        for (int i=0; i<aValidDirection.length; i++){
            if (aValidDirection[i].equals(pString)){
                return true;
            }
        }
        return false;
    }// isDirection ()
    
    /**
     * Cette méthode retourne une chaîne de caractères contenant la liste de toutes les commandes valides pour le jeu, séparées par des espaces.
     */
    public String getCommandList ()
    {
        StringBuilder vChaine = new StringBuilder();
        for (String vCommand : aValidCommands){
            vChaine.append(vCommand + " ");
        }
        return vChaine.toString();
    }// getCommandList ()
}// CommandWords
