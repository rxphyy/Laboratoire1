import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controlleur implements Observer {
    private ArrayList<Capteur> Capteurs;

    private boolean Chauffage;
    private boolean Climatisation;
    private boolean Ventilation;

    public Controlleur() {
        this.Capteurs = new ArrayList<>();

        this.Chauffage = false;
        this.Ventilation = false;
        this.Climatisation = false;
    }

    public void mettreAJourCapteur(Capteur capteur, double valeur) {
        if (capteur instanceof CapteurTemperature)
            envoyerMessage("Nouvelle valeur reçue: Mesure de température = " + valeur + "°C");
        else
            envoyerMessage("Nouvelle valeur reçue: Mesure de CO2 = " + valeur + "ppm");

        capteur.mettreAJour(valeur);
    }

    private void envoyerMessage(String message) {
        System.out.println("--> Contrôleur : " + message);
    }

    public void ajouterCapteur(Capteur capteur) {
        this.Capteurs.add(capteur);
        capteur.addObserver(this);
    }

    private void demarrerChauffage() {
        if (!Chauffage) {
            this.Chauffage = true;
            envoyerMessage("Chauffage démarré.");
        }
    }

    private void arreterChauffage() {
        if (Chauffage) {
            this.Chauffage = false;
            envoyerMessage("Chauffage arrêté.");
        }
    }

    private void arreterVentilation() {
        if (Ventilation) {
            this.Ventilation = false;
            envoyerMessage("Ventilation arrêté.");
        }
    }

    private void demarrerVentilation() {
        if (!Ventilation) {
            this.Ventilation = true;
            envoyerMessage("Ventilation démarrée.");
        }
    }

    private void demarrerClimatisation() {
        if (!Climatisation) {
            this.Climatisation = true;
            envoyerMessage("Climatisation démarrée.");
        }
    }

    private void arreterClimatisation() {
        if (Climatisation) {
            this.Climatisation = false;
            envoyerMessage("Climatisation arrêtée.");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof CapteurTemperature) {
            CapteurTemperature c = (CapteurTemperature) o;
            if (c.getValeur() > c.getLimiteTemp()) {
                demarrerClimatisation();
                arreterChauffage();
            } else if (c.getValeur() < c.getLimiteTemp()) {
                arreterClimatisation();
                demarrerChauffage();
            }
        } else if (o instanceof CapteurCO2) {
            CapteurCO2 c = (CapteurCO2) o;
            if (c.getValeur() > c.getLimiteCO2()) {
                demarrerVentilation();
            } else if (c.getValeur() < c.getLimiteCO2() && Ventilation) {
                arreterVentilation();
            }
        }
    }
}