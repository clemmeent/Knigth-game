import java.util.HashMap;
import java.util.Set;

/**
 * Room fournit des méthodes pour décrire une pièce, ses sorties et les objets qu'elle contient.
 * Ainsi que pour définir des sorties et vérifier si une pièce est une sortie de la pièce actuelle.
 * @Clément Sainte-Rose
 */
public class Room
{
    private String aDescription;
    private HashMap <String, Room> aExits;
    private String aImageName;
    private ItemList aRoomItems;
    
    /**
     * Constructeur naturel de la classe Room initialise la description de la pièce, 
     * Le nom de l'image associée,
     * Crée une HashMap vide pour stocker les sorties de la pièce, 
     * Et enfin une ItemList vide pour stocker les objets présents dans la pièce.
     */
    public Room (final String pDescription, final String pImage)
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap <String, Room> ();
        this.aImageName = pImage;
        this.aRoomItems = new ItemList ("the room");
    }// Room ()
    
    /**
     * Cette méthode renvoie la description de la pièce.
     */
    public String getDescription ()
    {
        return this.aDescription;
    }// getDescription ()
    
    /**
     * Cette méthode renvoie une description plus détaillée de la pièce, 
     * Elle ajoute la liste des sorties et des objets présents dans la pièce.
    */
    public String getLongDescription ()
    {
        return "You are "+ this.aDescription + "\n" + this.getExitString() +  
        "\n" + this.aRoomItems.getItemString();
    }// getLongDescription ()
    
    /**
     * Cette méthode prend une direction en entrée et renvoie la pièce voisine dans cette direction, 
     * S'il n'y a pas de sortie dans cette direction elle renvoie null.
     */
    public Room getExit (final String pDirection)
    {
        return this.aExits.get(pDirection);
    }// getExit ()
    
    /**
     * Cette méthode renvoie une chaîne de caractères qui décrit les sorties de la pièce.
     */
    public String getExitString ()
    {
        StringBuilder vChaine = new StringBuilder ("Exits :");
        Set <String> vKeys = this.aExits.keySet();
        for (String vExit : vKeys){
            vChaine.append(" " + vExit);
        }
        return vChaine.toString();
    }// getExitString ()
    
    /**
     * Cette méthode permet de définir une sortie pour la pièce dans une direction donnée.
     */
    public void setExit (final String pDirection, final Room pNeighbor)
    {
        this.aExits.put(pDirection, pNeighbor);
    }// setExit ()
    
    /**
     * Cette méthode vérifie si une pièce donnée est une sortie de la pièce actuelle.
     */
    public boolean isExit (final Room pRoom)
    {
        if (this.aExits.containsValue(pRoom)){
            return true;
        }
        return false;
    }// isExit ()
       
    /**
     * Cette méthode renvoie le nom de l'image associée à la pièce.
     */
    public String getImageName()
    {
        return this.aImageName;
    }// getImageName ()
    
    /**
     * Cette méthode renvoie la liste d'objets présents dans la pièce.
     */
    public ItemList getRoomItems ()
    {
        return this.aRoomItems;
    }// getRoomItems ()
} // Room
