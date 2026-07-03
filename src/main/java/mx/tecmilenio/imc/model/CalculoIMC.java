package mx.tecmilenio.imc.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "calculo")
public class CalculoIMC implements Serializable {
    private int id;
    private int usuarioId;
    private double peso;
    private double imc;
    private String categoria;
    private String fecha;

    public CalculoIMC() {
    }

    public CalculoIMC(int id, int usuarioId, double peso, double imc, String categoria, String fecha) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.peso = peso;
        this.imc = imc;
        this.categoria = categoria;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getImc() { return imc; }
    public void setImc(double imc) { this.imc = imc; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
