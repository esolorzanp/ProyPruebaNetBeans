package model;

import java.sql.*;

public class Examen {

    private int exaId;
    private String exaDescripcion;
    private int exaValor;
    private String exaTipo;
    private String exaEstado;
    private String exaUsuAnu;
    private Timestamp exaFechaAnu;

    public Examen(int exaId, String exaDescripcion, int exaValor, String exaTipo, String exaEstado, String exaUsuAnu, Timestamp exaFechaAnu) {
        this.exaId = exaId;
        this.exaDescripcion = exaDescripcion;
        this.exaValor = exaValor;
        this.exaTipo = exaTipo;
        this.exaEstado = exaEstado;
        this.exaUsuAnu = exaUsuAnu;
        this.exaFechaAnu = exaFechaAnu;
    }

    public Examen() {
        this.exaId = -1;
        this.exaDescripcion = "";
        this.exaValor = 0;
        this.exaTipo = "";
        this.exaEstado = "Activo";
        this.exaUsuAnu = "";
        this.exaFechaAnu = null;
    }

    public int getExaId() {
        return exaId;
    }

    public void setExaId(int exaId) {
        this.exaId = exaId;
    }

    public String getExaDescripcion() {
        return exaDescripcion;
    }

    public void setExaDescripcion(String exaDescripcion) {
        this.exaDescripcion = exaDescripcion;
    }

    public int getExaValor() {
        return exaValor;
    }

    public void setExaValor(int exaValor) {
        this.exaValor = exaValor;
    }

    public String getExaTipo() {
        return exaTipo;
    }

    public void setExaTipo(String exaTipo) {
        this.exaTipo = exaTipo;
    }

    public String getExaEstado() {
        return exaEstado;
    }

    public void setExaEstado(String exaEstado) {
        this.exaEstado = exaEstado;
    }

    public String getExaUsuAnu() {
        return exaUsuAnu;
    }

    public void setExaUsuAnu(String exaUsuAnu) {
        this.exaUsuAnu = exaUsuAnu;
    }

    public Timestamp getExaFechaAnu() {
        return exaFechaAnu;
    }

    public void setExaFechaAnu(Timestamp exaFechaAnu) {
        this.exaFechaAnu = exaFechaAnu;
    }

    @Override
    public String toString() {
        return "ExamenDao {"
                + "exaId=" + exaId
                + ", exaDescripcion='" + exaDescripcion + '\''
                + ", exaValor=" + exaValor
                + ", exaTipo='" + exaTipo + '\''
                + ", exaEstado='" + exaEstado + '\''
                + ", exaUsuAnu='" + exaUsuAnu + '\''
                + ", exaFechaAnu=" + exaFechaAnu
                + '}';
    }

}
