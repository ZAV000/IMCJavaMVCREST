package mx.tecmilenio.imc.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "historial")
public class HistorialResponse {
    private List<CalculoIMC> calculos = new ArrayList<>();

    public HistorialResponse() {
    }

    public HistorialResponse(List<CalculoIMC> calculos) {
        this.calculos = calculos;
    }

    @XmlElement(name = "calculo")
    public List<CalculoIMC> getCalculos() {
        return calculos;
    }

    public void setCalculos(List<CalculoIMC> calculos) {
        this.calculos = calculos;
    }
}
