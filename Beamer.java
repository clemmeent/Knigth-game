
/**
 * Cette classe gère uniquement le Beamer qui est un téléporteur
 * Le beamer est un type d'Item
 *
 * @Clément Sainte-Rose
 */
public class Beamer extends Item
{
    private Room aChargedRoom;
    
    /**
     *  Il s'agit du constructeur de la classe Beamer. 
     *  Il prend trois paramètres : pName pour le nom du beamer, pDescription pour la description du beamer et pWeight pour le poids du beamer. 
     *  Il appelle le constructeur de la classe mère en utilisant le mot clé super.
     */
    public Beamer(final String pName, final String pDescription, final double pWeight)
    {
        super(pName, pDescription, pWeight);
    }// Bearmer ()
    
    /**
     * Cette méthode est un accesseur pour la pièce stockée dans le beamer. 
     * Elle retourne la pièce chargée dans le beamer.
     */
    public Room getChargedRoom ()
    {
        return this.aChargedRoom;
    }// getChargedRoom ()
    
    /**
     * Cette méthode est un modificateur pour la pièce stockée dans le beamer. 
     * Elle prend une pièce en tant que paramètre et charge le beamer avec cette pièce.
     */
    public void setChargedRoom (final Room pRoom)
    {
         this.aChargedRoom = pRoom;
    }// setChargedRoom ()
    
    /**
     * Cette méthode est une fonction booléenne qui retourne true si une pièce est stockée dans le beamer et false dans le cas contraire. 
     * Elle vérifie si la pièce chargée dans le beamer est nulle ou non.
     */
    public boolean isCharged ()
    {
        return this.aChargedRoom != null;
    }// isCharged ()
}// Beamer
