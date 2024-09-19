public class CapteurCO2 extends Capteur {
    private final double LIMITE_CO2 = 1000;

    public CapteurCO2(int ConcentrationCO2) {
        super(ConcentrationCO2);
    }

    public double getLimiteCO2() {
        return LIMITE_CO2;
    }
}
