import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.GridLayout;
import java.awt.Color;

/**
 * Cette classe permet de créer une interface utilisateur pour un jeu et permet aux joueurs d'interagir avec le jeu en utilisant des boutons et des champs de texte.
 * @Clément Sainte-Rose
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame aMyFrame;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JLabel aImage;
    private JPanel aPanelEast;
    private JPanel aPanelWest;
    
    private JButton aButtonBack;
    private JButton aButtonNorth;
    private JButton aButtonUp;
    private JButton aButtonWest;
    private JButton aButtonEast;
    private JButton aButtonSouth;
    private JButton aButtonDown;
    private JButton aButtonInventory;
    private JButton aButtonHelp;
    private JButton aButtonQuit;
    
    
    /**
     * 
     */
    public UserInterface (final GameEngine pGameEngine){
        this.aEngine = pGameEngine;
        this.createGUI();
    }// UserInterface ()

    /**
     * Cette méthode ajoute du texte à la zone de texte aLog.
     */
    public void print (final String pText)
    {
        this.aLog.append(pText);
        this.aLog.setCaretPosition(this.aLog.getDocument().getLength());
    }// print ()

    /**
     * Cette méthode ajoute du texte avec un saut de ligne à la fin.
     */
    public void println (final String pText)
    {
        this.print(pText + "\n");
    }// println ()

    /**
     * Cette méthode charge une image à partir d'un fichier.
     * Et elle l'affiche dans le label aImage dans la fenêtre.
     */
    public void showImage (final String pImageName)
    {
        String vImagePath = "" + pImageName;
        URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
        if (vImageURL == null){
            System.out.println( "Image not found : " + vImagePath );
        }else{
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }// else
    }// showImage ()

    /**
     * Cette méthode active ou désactive certains composants d'interface utilisateur en fonction de l'état du jeu (actif ou inactif).
     */
    public void enable (final boolean pOnOff)
    {
        this.aEntryField.setEditable( pOnOff ); 
        if (!pOnOff){
            this.aEntryField.getCaret().setBlinkRate( 0 );
            this.aEntryField.removeActionListener( this );
        }
        
        this.aButtonBack.setEnabled(pOnOff);
        this.aButtonNorth.setEnabled(pOnOff);
        this.aButtonUp.setEnabled(pOnOff);
        this.aButtonWest.setEnabled(pOnOff);
        this.aButtonHelp.setEnabled(pOnOff);
        this.aButtonEast.setEnabled(pOnOff);
        this.aButtonQuit.setEnabled(pOnOff);
        this.aButtonSouth.setEnabled(pOnOff);
        this.aButtonDown.setEnabled(pOnOff);
        this.aButtonInventory.setEnabled(pOnOff);
    }// enable ()
    
    /**
     * Cette procédure crée la fenêtre et tous les composants d'interface utilisateur nécessaires. 
     * Elle définit également la disposition des composants sur la fenêtre.
     */
    private void createGUI ()
    {
        this.aMyFrame = new JFrame( "Knight's Quest" ); // Change le nom de la fenêtre
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();
        
        vPanel.setLayout( new BorderLayout() );
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        
        // Mise en place du pannel de bouton
        this.aPanelEast = new JPanel();
        this.aPanelEast.setLayout(new GridLayout(3, 3));
        
        this.aPanelWest = new JPanel();
        this.aPanelWest.setLayout(new GridLayout(3, 1));
             
        
        this.aButtonNorth = new JButton ("North");
        this.aButtonNorth.addActionListener(this);
        this.aButtonNorth.setBackground(Color.yellow);
        this.aPanelEast.add(this.aButtonNorth);
        this.aButtonUp = new JButton ("Up");
        this.aButtonUp.addActionListener(this);
        this.aButtonUp.setBackground(Color.blue);
        this.aPanelEast.add(this.aButtonUp);
        this.aButtonWest = new JButton ("West");
        this.aButtonWest.addActionListener(this);
        this.aButtonWest.setBackground(Color.yellow);
        this.aPanelEast.add(this.aButtonWest);
        this.aButtonBack = new JButton ("Back");
        this.aButtonBack.addActionListener(this);
        this.aButtonBack.setBackground(Color.blue);
        this.aPanelEast.add(this.aButtonBack);
        this.aButtonEast = new JButton ("East");
        this.aButtonEast.addActionListener(this);
        this.aButtonEast.setBackground(Color.yellow);
        this.aPanelEast.add(this.aButtonEast);
        this.aButtonSouth = new JButton ("South");
        this.aButtonSouth.addActionListener(this);
        this.aButtonSouth.setBackground(Color.blue);
        this.aPanelEast.add(this.aButtonSouth);
        this.aButtonDown = new JButton ("Down");
        this.aButtonDown.addActionListener(this);
        this.aButtonDown.setBackground(Color.yellow);
        this.aPanelEast.add(this.aButtonDown);
        this.aButtonInventory = new JButton ("Inventory");
        this.aButtonInventory.addActionListener(this);
        this.aButtonInventory.setBackground(Color.white);
        this.aPanelWest.add(this.aButtonInventory);
        this.aButtonHelp = new JButton ("Help");
        this.aButtonHelp.addActionListener(this);
        this.aButtonHelp.setBackground(Color.green);
        this.aPanelWest.add(this.aButtonHelp);
        this.aButtonQuit = new JButton ("Quit");
        this.aButtonQuit.addActionListener(this);
        this.aButtonQuit.setBackground(Color.red);
        this.aPanelWest.add(this.aButtonQuit);
        
        
        vPanel.add(this.aPanelEast, BorderLayout.EAST);
        vPanel.add(this.aPanelWest, BorderLayout.WEST);
        
        this.aEntryField.addActionListener(this);
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );
        
        
        this.aMyFrame.addWindowListener( new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);} });

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    }// createGUI ()
    
    /**
     * Cette méthode est un gestionnaire d'événements qui est appelé chaque fois qu'un des boutons dans l'interface utilisateur est cliqué.
     */
    public void actionPerformed (final ActionEvent pE) 
    {
        if (pE.getSource() == this.aButtonNorth){
            this.aEngine.interpretCommand("go north");
        }else if (pE.getSource() == this.aButtonSouth){
            this.aEngine.interpretCommand("go south");
        }else if (pE.getSource() == this.aButtonEast){
            this.aEngine.interpretCommand("go east");
        }else if (pE.getSource() == this.aButtonWest){
            this.aEngine.interpretCommand("go west");
        }else if (pE.getSource() == this.aButtonUp){
            this.aEngine.interpretCommand("go up");
        }else if (pE.getSource() == this.aButtonDown){
            this.aEngine.interpretCommand("go down");
        }else if (pE.getSource() == this.aButtonQuit){
            this.aEngine.interpretCommand("quit");
        }else if (pE.getSource() == this.aButtonHelp){
            this.aEngine.interpretCommand("help");
        }else if (pE.getSource() == this.aButtonBack){
            this.aEngine.interpretCommand("back");
        }else if (pE.getSource() == this.aButtonInventory){
            this.aEngine.interpretCommand("inventory");
        }else{
            this.processCommand();
        }
    }// actionPerformed ()

    /**
     * Cette procédure est appelée lorsque l'utilisateur appuie sur la touche "Entrée" après avoir entré une commande dans la zone de texte de l'interface utilisateur.
     */
    private void processCommand ()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    }// processCommand ()
} // UserInterface 
