import java.util.HashMap;
import java.util.Set;

/**
 * Cette classe gère la liste des objets dans une pièce ou l'inventaire du joueur.
 * Elle contient des méthodes telles que getItem(), qui renvoie un objet en fonction de son nom, ou addItem(), qui ajoute un objet à la liste.
 * @Clément Sainte-Rose
 */
public class ItemList
{
    private HashMap <String, Item> aItems;
    private String aLocation;
    /**
     * Constructeur de la classe il prend en entrée la localisation de la liste d'items, 
     * Qui peut être soit l'inventaire du joueur, soit une pièce dans le jeu.
     * 
     */
    public ItemList (final String pLocation)
    {
        this.aItems = new HashMap <String, Item> ();
        this.aLocation = pLocation;
    }// ItemList ()
    
    /**
     * Cette méthode récupère un item de la liste en utilisant son nom (clé) en entrée, et retourne l'objet Item correspondant.
     */
    public Item getItem (final String pItem)
    {
        return this.aItems.get(pItem);
    }// getItem ()
    
    /**
     * Cette méthode renvoie une HashMap complète contenant tous les items de la liste.
     */
    public HashMap <String, Item> getItems ()
    {
        return aItems;
    }// getItems ()
    
    /**
     * Cette méthode renvoie une chaîne de caractères contenant tous les noms des items de la liste.
     */
    public String getItemString ()
    {
        if (this.aItems.isEmpty()){
            return "There is no items in " + this.aLocation;
        }
        StringBuilder vChaine = new StringBuilder ("Items in " + this.aLocation + " :");
        Set <String> vItemsNames = this.aItems.keySet();
        for (String vName : vItemsNames){
            vChaine.append(" " + vName);
        }
        return vChaine.toString();
    }// getItemString ()
    
    /**
     * Cette procédure permet d'ajouter un nouvel item dans la liste en spécifiant son nom (clé) et l'objet Item correspondant.
     */
    public void addItem(final String pName, final Item pItem){
        this.aItems.put(pName, pItem);
    }// addItem ()
    
    /**
     * Cette procédure permet de supprimer un item de la liste en utilisant son nom (clé) en entrée.
     */
    public void removeItem (final String pName){
        this.aItems.remove(pName);
    }// removeItem ()
}//  ItemList
