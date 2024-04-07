import java.util.StringTokenizer;
/**
 * Parser fournit une méthode simple pour analyser les entrées utilisateur et renvoyer des objets Command correspondants.
 */
public class Parser
{
    private CommandWords aValidCommands;
    /**
     * Le constructeur de la classe Parser initialise un objet CommandWords, qui contient une liste de commandes valides.
     */
    public Parser(){
       this.aValidCommands = new CommandWords();
    }

    /**
     * Cette méthode prend une ligne d'entrée en tant que paramètre et la divise en deux parties.
     * La première partie est la commande et la deuxième partie est l'argument de la commande. 
     * La méthode utilise la classe StringTokenizer pour séparer la chaîne d'entrée en différents jetons, qui sont ensuite utilisés pour créer un objet Command. 
     * Si la commande est valide, un objet Command contenant les deux parties est renvoyé. 
     * Sinon, un objet Command avec la première partie de la commande définie à null est renvoyé.
     */
    public Command getCommand(final String pInputLine) 
    {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if (tokenizer.hasMoreTokens()){
            vWord1 = tokenizer.nextToken();
        }else{
            vWord1 = null;
        }
        if (tokenizer.hasMoreTokens()){
            vWord2 = tokenizer.nextToken();
        }else{
            vWord2 = null;
        }

        if (this.aValidCommands.isCommand(vWord1)){
            return new Command( vWord1, vWord2 );
        }else{
            return new Command( null, vWord2 );
        }
    }

    /**
     *  Cette méthode renvoie la liste de commandes valides stockée dans l'objet CommandWords initialisé dans le constructeur.
     */
    public String getCommands ()
    {
        return this.aValidCommands.getCommandList();
    }
}
