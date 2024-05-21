package model;

public class Especie {

    private int espId;
    private String espDescripcion;
    private String espEstado;

    public Especie(int espId, String espDescripcion, String espEstado) {
        this.espId = espId;
        this.espDescripcion = espDescripcion;
        this.espEstado = espEstado;
    }

    public Especie(String espDescripcion, String espEstado) {
        this.espId = -1;
        this.espDescripcion = espDescripcion;
        this.espEstado = espEstado;
    }

    public Especie() {
        this.espId = -1;
        this.espDescripcion = "";
        this.espEstado = "";
    }

    public int getEspId() {
        return espId;
    }

    public void setEspId(int espId) {
        this.espId = espId;
    }

    public String getEspDescripcion() {
        return espDescripcion;
    }

    public void setEspDescripcion(String espDescripcion) {
        this.espDescripcion = espDescripcion;
    }

    public String getEspEstado() {
        return espEstado;
    }

    public void setEspEstado(String espEstado) {
        this.espEstado = espEstado;
    }

    @Override
    public String toString() {
        return "Especie {"
                + "espId=" + espId
                + ", espDescripcion='" + espDescripcion + '\''
                + ", espEstado='" + espEstado + '\''
                + '}';
    }

}
