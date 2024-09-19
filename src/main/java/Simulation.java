import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) {
        Controlleur controlleur = new Controlleur();
        CapteurTemperature capteurTemperature = new CapteurTemperature(20);
        CapteurCO2 capteurCO2 = new CapteurCO2(700);

        controlleur.ajouterCapteur(capteurTemperature);
        controlleur.ajouterCapteur(capteurCO2);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Entrez une nouvelle température : ");
            double nouvelleTemperature = sc.nextDouble();
            controlleur.mettreAJourCapteur(capteurTemperature, nouvelleTemperature);

            System.out.print("Entrez une nouvelle concentration de CO2 : ");
            double nouvelleConcentration = sc.nextDouble();
            controlleur.mettreAJourCapteur(capteurCO2, nouvelleConcentration);
        }
    }
}