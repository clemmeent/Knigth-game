
/**
 * Game instancie GameEngine et UserInterface et les associe ensemble pour permettre le fonctionnement du jeu.
 * @Clément Sainte-Rose
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;

    /**
     * Le constructeur par défaut de la classe Game crée une instance de GameEngine et une instance de UserInterface,
     * Puis associe l'instance de GameEngine à l'instance de UserInterface en appelant la méthode setGUI() de GameEngine.
     */
    public Game () 
    {
       this.aEngine = new GameEngine();
       this.aGui = new UserInterface(this.aEngine);
       this.aEngine.setGUI(this.aGui);
    }// Game ()
}// Game
