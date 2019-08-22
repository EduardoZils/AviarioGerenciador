package fag.edu.com.gerenciadordefichadeaviario.models;

import java.util.Date;

public class Hidrometro {
    int cd_hidrometro;
    Lote lote;
    Double qt_gasto;
    Date dt_coleta;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Hidrometro(int cd_hidrometro, Lote lote, Double qt_gasto, Date dt_coleta, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_hidrometro = cd_hidrometro;
        this.lote = lote;
        this.qt_gasto = qt_gasto;
        this.dt_coleta = dt_coleta;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_hidrometro() {
        return cd_hidrometro;
    }

    public void setCd_hidrometro(int cd_hidrometro) {
        this.cd_hidrometro = cd_hidrometro;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Double getQt_gasto() {
        return qt_gasto;
    }

    public void setQt_gasto(Double qt_gasto) {
        this.qt_gasto = qt_gasto;
    }

    public Date getDt_coleta() {
        return dt_coleta;
    }

    public void setDt_coleta(Date dt_coleta) {
        this.dt_coleta = dt_coleta;
    }

    public Date getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(Date dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public Date getDt_atualizacao() {
        return dt_atualizacao;
    }

    public void setDt_atualizacao(Date dt_atualizacao) {
        this.dt_atualizacao = dt_atualizacao;
    }

    @Override
    public String toString() {
        return "Hidrometro{" +
                "cd_hidrometro=" + cd_hidrometro +
                '}';
    }
}
