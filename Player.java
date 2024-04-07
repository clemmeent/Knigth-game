import java.util.Stack;
import java.util.HashMap;
import java.util.Set;

/**
 * Cette classe gère tout ce qui est en rapport avec le joueur.
 * Elle travaille en parallèle avec GameEngine pour les déplacements et la gestion des items dans l'inventaire.
 *
 * @Clément Sainte-Rose
 */
public class Player
{
    private String aPseudo; 
    private Room aCurrentRoom;
    private Stack <Room> aPreviousRooms;
    private ItemList aInventoryItems; 
    private double aMaxWeight; 
    private double aInventoryWeight;
    private int aMovesRemaining; 
    
    /**
     * Le constructeur crée un joueur avec un pseudonyme, une pièce de départ, un poids maximal transportable, et un nombre de déplacements maximum avant de perdre.
     */
    public Player(final String pPseudo, final Room pCurrentRoom, final double pMaxWeight, final int pMovesRemaining){
        this.aPseudo = pPseudo;
        this.aCurrentRoom = pCurrentRoom;
        this.aPreviousRooms = new Stack <Room> ();
         this.aInventoryItems = new ItemList ("your inventory");
        this.aMaxWeight = pMaxWeight;
        this.aInventoryWeight = 0;
        this.aMovesRemaining = pMovesRemaining;
    }// Player ()
    
    /**
     * Cette méthode retourne le pseudonyme du joueur.
     */
    public String getPseudo ()
    {
        return this.aPseudo;
    }// getPseudo ()
    
    /**
     * Cette méthode retourne la pièce actuelle du joueur.
    */
    public Room getCurrentRoom ()
    {
        return this.aCurrentRoom;
    }// getCurrentRoom ()
    
    /**
     * Cette méthode retourne la pile des pièces précédentes visitées par le joueur.
     */
    public Stack <Room> getPreviousRooms ()
    {
        return this.aPreviousRooms;
    }// getPrevisousRooms ()
    
    /**
     * Cette méthode retourne la liste d'items présents dans l'inventaire du joueur.
     */
    public ItemList getInventoryItems ()
    {
        return this.aInventoryItems;
    }// getInventoryItems ()
    
    /**
     * Cette méthode retourne le poids maximal que le joueur peut transporter.
     */
    public double getMaxWeight ()
    {
        return this.aMaxWeight;
    }// getMaxWeight ()
    
    /**
     * Cette procédure modifie le poids maximal transportable par le joueur.
     */
    public void setMaxWeight (final double pWeight){
        this.aMaxWeight = pWeight;
    }// setMaxWeight ()
    
    /**
     * Cette procédure modifie le poids actuel de l'inventaire du joueur en soustrayant un poids spécifié.
     */
    public void removeInventoryWeight (final double pWeight){
        this.aInventoryWeight -= pWeight;
    }// removeInventoryWeight ()
    
    /**
     * Cette méthode retourne le poids actuel de l'inventaire du joueur.
     */
    public double getInventoryWeight ()
    {
        return this.aInventoryWeight;
    }// getInventoryWeight ()
    
    /**
     * Cette méthode retourne le nombre de déplacements restants avant que le joueur ne perde.
     */
    public int getMovesRemaining ()
    {
        return this.aMovesRemaining;
    }// getMovesRemaining ()
    
    /**
     * Cette procédure modifie le nombre de déplacements restants en ajoutant un nombre spécifié.
     */
    public void addMovesRemaining (final int pAdd)
    {
        this.aMovesRemaining += pAdd;
    }// addMovesRemaining ()
    
    /**
     * Cette procédure déplace le joueur dans une pièce spécifiée.
     * Elle ajoute la pièce actuelle à la pile des pièces précédentes et modifie la pièce actuelle.
     */
    public void goRoom (final String pStringRoom)
    {
        Room vNextRoom = this.aCurrentRoom.getExit(pStringRoom);
        this.aPreviousRooms.push(this.aCurrentRoom);
        this.aCurrentRoom = vNextRoom;
        this.aMovesRemaining -= 1;
    }// goRoom ()
    
    /**
     * Cette méthode permet au joueur de revenir à la pièce précédente en utilisant la pile des pièces précédentes. 
     * Retourne vrai si le joueur peut revenir à la pièce précédente et faux dans le cas contraire.
     */
    public boolean back ()
    {
        if (this.aCurrentRoom.isExit(this.aPreviousRooms.peek())){ 
            this.aCurrentRoom = this.aPreviousRooms.pop();
            this.aMovesRemaining -= 1;
            return true;
        }
        return false;
    }// back ()
    
    /**
     * Cette procédure permet au joueur de ramasser un item présent dans la pièce où il se trouve et de le mettre dans son inventaire. 
     * Elle ajoute l'item à l'inventaire du joueur, le supprime de la liste des items de la pièce où il se trouvait, et ajoute son poids au poids total de l'inventaire du joueur.
     */
    public void take (final Item pItem)
    {
        this.aInventoryItems.addItem(pItem.getName(),pItem); 
        this.aCurrentRoom.getRoomItems().removeItem(pItem.getName());
        this.aInventoryWeight += pItem.getWeight();
    }// take ()
    
    /**
     * Cette procédure permet au joueur de déposer un item de son inventaire dans la pièce où il se trouve.
     * Elle ajoute l'item à la liste des items de la pièce où se trouve le joueur, le supprime de l'inventaire du joueur, et retire son poids du poids total de l'inventaire du joueur.
     */
    public void drop (final Item pItem)
    {
        this.aCurrentRoom.getRoomItems().addItem(pItem.getName(), pItem);
        this.aInventoryItems.removeItem(pItem.getName());
        this.aInventoryWeight -= pItem.getWeight();
    }// drop ()
    
    /**
     * Cette procédure permet au joueur de charger le "beamer".
     * Elle stocke la pièce actuelle dans le beamer et retire un point de déplacement au joueur.
     */
    public void charge (final Beamer pBeamer)
    {
        pBeamer.setChargedRoom(this.aCurrentRoom);
        this.aMovesRemaining -= 1;
    }// charge ()
    
    /**
     * Cette procédure permet au joueur d'utiliser le beamer pour se téléporter dans une pièce précédemment visitée.
     */
    public void fire (final Beamer pBeamer)
    {
        this.aPreviousRooms.push(this.aCurrentRoom);
        this.aCurrentRoom = pBeamer.getChargedRoom();
        this.aInventoryItems.removeItem(pBeamer.getName()); 
        this.aInventoryWeight -= pBeamer.getWeight(); 
        this.aMovesRemaining -= 1;
    }// fire ()
}// Player
