import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Cette classe est le moteur du jeu qui crée toutes les pièces, affiche les messages,
 * et fait tourner le jeu.
 * Elle évalue et execute également les commandes que le parser retourne.
 * 
 * @Clément Sainte-Rose
 */
public class GameEngine
{ 
    private Parser aParser;
    private HashMap <String, Room> aRooms;
    private UserInterface aGui; 
    private Player aPlayer;
    
    /**
     * Constructeur par défaut qui initialise l'objet Parser, crée une HashMap pour stocker les pièces et appelle la méthode createRooms() pour créer les pièces.
     */
    public GameEngine ()
    {
        this.aParser = new Parser();
        this.aRooms = new HashMap <String, Room> ();
        this.createRooms();
    }// GameEngine ()
    
    /**
     * Cette procédure crée une interface utilisateur et appelle la méthode printWelcome() pour afficher un message de bienvenue.
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }// setGUI ()
    
    /**
     * Cette procédure affiche un message de bienvenue, le nombre de mouvements restants du joueur et quelques conseils. 
     * Elle appelle également la méthode printLocationInfo() pour afficher les informations sur la pièce actuelle.
     */
    private void printWelcome ()
    {   
        this.aGui.println("\n" + "Welcome in the game The Knight's Quest");
        this.aGui.println("You have " + this.aPlayer.getMovesRemaining() + " moves left to save the princess !");
        this.aGui.println("Find the key to be able to climb to the second floor");
        this.aGui.println("Type 'help' if you need help." + "\n");
        printLocationInfo();
    }// printWelcome ()

    /**
     * Cette procédure affiche la description de la pièce actuelle et affiche l'image correspondante s'il y en a une.
     */
    private void printLocationInfo ()
    {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        if (this.aPlayer.getCurrentRoom().getImageName() != null){
           this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName() );
        }// if ()
    }// printLocationInfo ()
    
    /**
     * Procédure qui crée les pièces du jeu, les stocke dans une Hashmap, crée les items du jeu et les réparti dans les pièces
     */
    private void createRooms ()
    {
        // Définition des salles
        Room vHall = new Room ("in the castle hall.","Images/hallroom.jpg");
        Room vLivingRoom1 =new Room("in the first livingroom. Relax you.","Images/livingroom.jpg");
        Room vLivingRoom2 =new Room("in the second livingroom. Take a tea.","Images/livingrooms.jpg");
        Room vDiningRoom =new Room("in the diningroom take a part of the cake on the table.","Images/diningroom.jpg");
        Room vCodeRoom =new Room("in my secret place.S-W-W-N-U-E-S-E.","Images/coderoom.jpg");
        Room vChessRoom =new Room("in the place where i can solve all my problems","Images/chessroom.jpg");
        Room vArmoury =new Room("Good loot Knigth !","Images/armouryroom.jpg");
        Room vBedRoom1 =new Room("in the parent's room","Images/bedroom.jpg");
        Room vBedRoom2 =new Room("in the child's room","Images/bedroom.jpg");
        Room vBathRoom =new Room("in the bathroom take a shower","Images/bathroom.jpg");
        Room vTianaRoom =new Room("Congratulations for help her.Now get out of here because the savage dragon arrive ! Have you a code to enter here ?","Images/tianaroom.jpg");
        
        // Définition des sorties pour chaque salle
        vHall.setExit("south",vLivingRoom1);
        vHall.setExit("up",vLivingRoom2);
        vLivingRoom1.setExit("north",vHall);
        vLivingRoom1.setExit("east",vDiningRoom);
        vDiningRoom.setExit("west",vLivingRoom1);
        vDiningRoom.setExit("east",vChessRoom);
        vChessRoom.setExit("east",vArmoury);
        vChessRoom.setExit("west",vDiningRoom);
        vChessRoom.setExit("Ke6",vCodeRoom);
        vCodeRoom.setExit("south",vChessRoom);
        vArmoury.setExit("west",vChessRoom);
        vLivingRoom2.setExit("down",vHall);
        vLivingRoom2.setExit("east",vBedRoom1);
        vLivingRoom2.setExit("south",vBedRoom2);
        vBedRoom1.setExit("south",vBathRoom);
        vBedRoom1.setExit("west",vLivingRoom2);
        vBedRoom2.setExit("north",vLivingRoom2);
        vBedRoom2.setExit("east",vBathRoom);
        vBathRoom.setExit("north",vBedRoom1);
        vBathRoom.setExit("west",vBedRoom2);
        vBathRoom.setExit("east",vTianaRoom);
        vTianaRoom.setExit("west",vBathRoom);
        vTianaRoom.setExit("025117",vHall);
        
        
        
        // Initialisation du jeu
        this.aPlayer = new Player ("clément", vHall, 4, 20);
        
        // Stockage des pièces
        aRooms.put("boy's bedroom", vBedRoom1);
        aRooms.put("girl's bedroom", vBedRoom2);
        aRooms.put("princess's bedroom", vTianaRoom);
        aRooms.put("bathroom", vBathRoom);
        aRooms.put("dining", vDiningRoom);
        aRooms.put("secondary living room", vLivingRoom2);
        aRooms.put("main living room", vLivingRoom1);
        aRooms.put("armoury", vArmoury);
        aRooms.put("secret room", vCodeRoom);
        aRooms.put("puzzle room", vChessRoom);
        aRooms.put("hall", vHall);
        
        // Création des items
        Beamer vBeamer = new Beamer("beamer","Conserve this item because he can teleport you where you charge it", 2);
        Item vBread = new Item("bread","This bread is potentially expired.",0.5);
        Item vMedKit = new Item("medkit","you have to keep it preciously",2);
        Item vKey = new Item("key","you will need it to go upstairs ", 0.10);
        
        // Placement des items dans les pièces
        vCodeRoom.getRoomItems().addItem("medkit", vMedKit);
        vDiningRoom.getRoomItems().addItem("bread", vBread);
        vHall.getRoomItems().addItem("beamer", vBeamer);
        vArmoury.getRoomItems().addItem("key", vKey);
    }// createRooms ()
    
    /**
     * Cette procédure transforme et analyse la commande de l'utilisateur.
     */
    public void interpretCommand (final String pStringCommand) 
    {
        this.aGui.println("\n" + "> " + pStringCommand ); 
        Command vCommand = this.aParser.getCommand(pStringCommand);
        
        if (vCommand.isUnknown()){
            this.aGui.println("I don't know what you mean...");
        }else{
            String vCommandWord = vCommand.getCommandWord();
            if (vCommandWord.equals("go")){
                this.goRoom(vCommand);
            }else if (vCommandWord.equals("look")){
                this.look(vCommand);
            }else if (vCommandWord.equals("eat")){
                this.eat(vCommand);
            }else if (vCommandWord.equals("quit")){
                if ( vCommand.hasSecondWord() ){
                    this.aGui.println( "Quit what?" );
                }else{
                    this.endGame();
                }// else
            }else if (vCommandWord.equals("help")){
                this.printHelp();
            }else if (vCommandWord.equals("back")){
                this.back(vCommand);
            }else if (vCommandWord.equals("test")){
                this.test(vCommand);
            }else if (vCommandWord.equals("take")){
                this.take(vCommand);
            }else if (vCommandWord.equals("drop")){
                this.drop(vCommand);
            }else if (vCommandWord.equals("inventory")){
                this.inventory(vCommand);
            }else if (vCommandWord.equals("charge")){
                this.charge(vCommand);
            }else if (vCommandWord.equals("fire")){
                this.fire(vCommand);
            }else{
                this.aGui.println("Unknown command!");
            }// else
        }// else
    }// interpretCommand ()
    
    /**
     * Cette méthode permet d'accéder au nom de la pièce.
     */
    public Room getRoom (final String pRoom)
    {
        return this.aRooms.get(pRoom);
    }// getRoom ()
    
    /**
     * Procédure d'aide qui affiche les mots de commande disponibles
     */
    private void printHelp ()
    {
        this.aGui.println("You have " + this.aPlayer.getMovesRemaining() + " moves left to save the princess !");
        this.aGui.println("\n" + "Your command words are: " + "\n" + this.aParser.getCommands());
    }// printHelp ()
    
    /**
     * Cette procédure permet au joueur de se déplacer d'une pièce à une autre en fonction de la commande entrée.
     */
    private void goRoom (final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("Go where ?");
            return;
        }
        if (!CommandWords.isDirection(pCommand.getSecondWord())){
            this.aGui.println("Unknown direction !");
            return; 
        }
        
        String vDirection = pCommand.getSecondWord();      
        if (this.aPlayer.getCurrentRoom().getExit(vDirection) == null){
            this.aGui.println("There is no door !");
            return; 
        }
        this.aPlayer.goRoom(vDirection);
        printLocationInfo(); 
        this.timerEnd();
    }// goRoom ()
    
    /**
     * Cette procédure permet de revenir dans la pièce précédente.
     */
    private void back (final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            this.aGui.println("You can't back !");
            return;
        }
        if (this.aPlayer.getPreviousRooms().empty()){
            this.aGui.println("There is no previous room");
            return; 
        }
        if (!this.aPlayer.back()){
            this.aGui.println("You can't back because you used the beamer !");
        }
        printLocationInfo();
        this.timerEnd(); 
    }// back ()
    
    /**
     * Cette procédure affiche les informations sur la pièce actuelle.
     */
    private void look (final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            String vWant = pCommand.getSecondWord();
            Item vActualItem = this.aPlayer.getCurrentRoom().getRoomItems().getItem(vWant);
            if (vActualItem == null){
                this.aGui.println("This item is not in this room or does not exist");
                return;
            }else{
                this.aGui.println(vActualItem.getLongItemDescription()); // Affiche la description complète de l'item
                return;
            }
        }else{
            this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());// Affiche la description de la pièce actuelle
        }
    }// look ()
    
    /**
     * Cette procédure permet au joueur de manger un objet s'il y en a un disponible.
     */
    private void eat (final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){ 
            this.aGui.println("What do you want to eat ?");
            return;
        }
        
        String vFood = pCommand.getSecondWord();
        if (!vFood.equals("bread")){ 
            this.aGui.println("I don't have this food !");
            return;
        }
        
        if (this.aPlayer.getInventoryItems().getItem("bread") == null){
            this.aGui.println("You don't have bread in you inventory !");
            return;
        }
        
        this.aPlayer.removeInventoryWeight(this.aPlayer.getInventoryItems().getItem("bread").getWeight());
        this.aPlayer.setMaxWeight(10); 
        this.aPlayer.addMovesRemaining(20); 
        this.aPlayer.getInventoryItems().removeItem("bread"); 
        this.aGui.println("You ate a magic bread and now you can stock more things !");
        this.aGui.println("Your strenght come back ! Your moves limit increased by 30");
    }// eat ()

    /**
     * Cette procédure vérifie si la commande a un deuxième mot
     */
    private void test(final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("What do you want to test ?");
            return;
        }
        
        String vFile = pCommand.getSecondWord();
        try{ 
            Scanner vScan = new Scanner (new File (""+ vFile + ".txt"));
            this.aGui.println("Testing " + vFile + " ...");
            while (vScan.hasNextLine()){
                interpretCommand(vScan.nextLine());
            }
        }catch (final FileNotFoundException pE){ 
            this.aGui.println("There is no such file of testing !");
        }
    }// test ()
    
    /**
     * Cette procédure permet au joueur de prendre un objet présent dans la pièce :
     * S'il est présent dans la liste des objets de la pièce, 
     * S'il est assez léger pour être ajouté à l'inventaire du joueur, 
     * Et s'il a été mentionné dans la commande.
     */
    private void take (final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("What do you want to take ?");
            return;
        }
        
        Item vItem = this.aPlayer.getCurrentRoom().getRoomItems().getItem(pCommand.getSecondWord());
        if (vItem == null){ 
            this.aGui.println("This item isn't here !");
            return;
        }
        
        if (!(vItem.getWeight() + this.aPlayer.getInventoryWeight() <= this.aPlayer.getMaxWeight())){ // Vérification si l'item n'est pas trop lourd pour l'inventaire
            this.aGui.println("This item is too heavy. Make place !");
            return;
        }
        
        this.aPlayer.take(vItem);
        this.aGui.println("Congats now you have " + vItem.getName() + "\n");
        this.aGui.println(vItem.getLongItemDescription());
        this.aGui.println(this.aPlayer.getInventoryItems().getItemString());
    }// take ()
    
    /**
     * Cette procédure  permet au joueur de déposer un objet de son inventaire dans la pièce :
     * S'il est présent dans la liste des objets de l'inventaire,
     * Et s'il a été mentionné dans la commande.
     */
    private void drop (final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("What do you want to drop ?");
            return;
        }
        
        Item vItem = this.aPlayer.getInventoryItems().getItem(pCommand.getSecondWord());
        if (vItem == null){
            this.aGui.println("You don't have this item in your inventory !");
            return;
        }
        
        this.aPlayer.drop(vItem);
        this.aGui.println("You have dropped the " + vItem.getName());
        this.aGui.println(this.aPlayer.getInventoryItems().getItemString());
    }// drop ()
    
    /**
     * Cette procédure affiche simplement le contenu de l'inventaire du joueur, 
     * Ainsi que son poids total et le poids maximal autorisé.
     */
    private void inventory (final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            this.aGui.println("You only have one inventory, type 'inventory' if you want to see it !");
            return;
        }
        double vInventoryRound = (double)Math.round((this.aPlayer.getInventoryWeight())*100)/100;
        this.aGui.println(this.aPlayer.getInventoryItems().getItemString());
        this.aGui.println("The total weight is " + vInventoryRound + " kg");
        this.aGui.println("The max you can carry is " + this.aPlayer.getMaxWeight() + " kg");
    }// inventory ()
    
    /**
     * Procédure permet de charger un objet "Beamer" :
     * S'il est présent dans l'inventaire du joueur,
     * S'il n'est pas déjà chargé et s'il a été mentionné dans la commande.
     */
    private void charge (final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("What do you want to charge ?");
            return;
        }
        
        String vSecondWord = pCommand.getSecondWord();
        if (!vSecondWord.equals("beamer")){
            this.aGui.println("I don't know how to charge this");
            return;
        }
        
        Item vItem = this.aPlayer.getInventoryItems().getItem(vSecondWord);
        Beamer vBeam = (Beamer)vItem;
        if (vItem == null){
            this.aGui.println("You don't have the beamer in your inventory");
            return;
        }
        
        if (vBeam.isCharged()){
            this.aGui.println("Your beamer is already charged !");
            return;
        }
        
        this.aPlayer.charge(vBeam);
        this.aGui.println("Your beamer has been charged !");
        this.timerEnd(); 
    }// charge ()
    
    /**
     * Cette procédure permet au joueur d'utiliser le "Beamer" :
     * S'il est présent dans l'inventaire, 
     * S'il est chargé et s'il a été mentionné dans la commande.
     */
    private void fire (final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("What do you want to fire ?");
            return;
        }        
        String vSecondWord = pCommand.getSecondWord();
        if (!vSecondWord.equals("beamer")){
            this.aGui.println("You can't fire");
            return;
        }        
        Item vItem = this.aPlayer.getInventoryItems().getItem(vSecondWord);
        Beamer vBeam = (Beamer)vItem;
        if (vItem == null){
            this.aGui.println("You don't have the beamer in your inventory");
            return;
        }        
        if (!vBeam.isCharged()){
            this.aGui.println("You have to charge your beamer before !");
            return;
        }
        
        this.aPlayer.fire(vBeam);
        this.aGui.println("Your beamer has been fired !");
        printLocationInfo(); 
        this.timerEnd(); 
    }// fire ()
    
    /**
     * Cette procédure est appelée à chaque fois que le timer atteint zéro. 
     * Elle vérifie si le nombre de mouvements restants du joueur est égal à zéro,
     * Ce qui signifie que le joueur n'a pas réussi à sauver la princesse avant le retour du dragon;
     */
    private void timerEnd ()
    {
        if (this.aPlayer.getMovesRemaining() == 0){
            this.aGui.println("The dragon get back here. Good game try another time...");
            this.endGame();
        }
    }// timerEnd ()
    
    /**
     * Cette procédure permet d'arrêter le jeu.
     */
    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false ); 
    }// endGame ()
}// GameEngine
