package mx.tecmilenio.imc.service;

public class IMCService {
    public double calcular(double peso, double estatura) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0.");
        }
        if (estatura < 1.0 || estatura > 2.5) {
            throw new IllegalArgumentException("La estatura debe estar entre 1.00 m y 2.50 m.");
        }
        double imc = peso / (estatura * estatura);
        return Math.round(imc * 100.0) / 100.0;
    }

    public String clasificar(double imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        }
        return "Obesidad";
    }
}
