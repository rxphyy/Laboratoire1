public class CapteurTemperature extends Capteur {
    private final double LIMITE_TEMP = 22;

    public CapteurTemperature(int temperature) {
        super(temperature);
    }

    public double getLimiteTemp() {
        return LIMITE_TEMP;
    }
}
