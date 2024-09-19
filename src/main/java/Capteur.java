import java.util.Observable;

public class Capteur extends Observable {
    private double Valeur;

    public Capteur(double valeur) {
        Valeur = valeur;
    }

    public void mettreAJour(double nouvelleValeur) {
        setValeur(nouvelleValeur);
        setChanged();
        notifyObservers();
    }

    public double getValeur() {
        return Valeur;
    }

    public void setValeur(double valeur) {
        Valeur = valeur;
    }
}