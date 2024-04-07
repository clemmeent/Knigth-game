
/**
 * Item permet de définir des objets et d'obtenir leur nom, leur description et leur poids,
 * Ainsi que des méthodes pour obtenir une description courte ou longue de l'objet.
 * @Clément Sainte-Rose
 */
public class Item
{
    private String aName; 
    private String aDescription;
    private double aWeigth;
    
    /**
     * Constructeur qui permet de créer un nouvel objet avec son nom, sa description et son poids.
     */
    public Item(final String pName, final String pDescription, final double pWeight)
    {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeigth = pWeight;
    }// Item ()

    /**
     * Cette méthode est un accesseur qui permet de récupérer le nom de l'objet.
     */
    public String getName()
    {
        return this.aName;
    }// getName ()
    
    /**
     * Cette méthode est un accesseur qui permet de récupérer la description de l'objet.
     */
    public String getDescription ()
    {
        return this.aDescription;
    }// getDescription ()
    
    /**
     * Cette méthode est un accesseur qui permet de récupérer le poids de l'objet.
     */
    public double getWeight ()
    {
        return this.aWeigth;
    }// getWeight ()
    
    /**
     * Cette méthode retourne la description courte de l'objet, qui est simplement son nom et son poids.
     */
    public String getItemDescription ()
    {
        return this.aName + "and it weighs " + this.aWeigth;
    }// getItemDescription ()
    
    /**
     * Cette méthode retourne la description longue de l'objet, qui contient son nom, son poids et sa description.
     */
    public String getLongItemDescription ()
    {
        return "The " + this.aName + " weighs " + this.aWeigth + "kg and " + this.aDescription;
     }// getLongItemDescription ()
}// Item
