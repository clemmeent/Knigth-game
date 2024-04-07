
/**
 * Cette classe définit un objet Command qui représente une commande dans le jeu. 
 * Cette commande peut être composée de plusieurs mots, et les mots sont stockés sous forme de chaînes de caractères.
 * @Clément Sainte-Rose
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;

    /**
     * Constructeur qui initialise les deux attributs de la commande avec les valeurs passées en paramètre.
     */
    public Command (final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }// Command ()
    
    /**
     * Cette méthode permet d'accéder au premier mot de la commande 
     */
    public String getCommandWord ()
    {
        return this.aCommandWord;
    }// getCommandWord ()
    
    /**
     * Cette méthode permet d'accéder au deuxième mot de la commande 
     */
    public String getSecondWord ()
    {
        return this.aSecondWord;
    }// getSecondWord ()
    
    /**
     * Cette méthode permet de savoir si la commande contient un deuxième mot ou non.
     */
    public boolean hasSecondWord ()
    {
        return this.getSecondWord() != null;
    }// hasSecondWord ()
    
    /**
     * Cette méthode indique si le mot de commande est inconnu
     */
    public boolean isUnknown ()
    {
        return this.getCommandWord() == null;
    }// isUnknown ()
}// Command
