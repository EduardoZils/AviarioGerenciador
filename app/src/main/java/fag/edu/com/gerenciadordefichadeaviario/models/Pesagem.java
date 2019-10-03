package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Pesagem extends SugarRecord {
    @Unique
    int cd_pesagem;
    Lote lote;
    Double vl_peso_semana;
    Double vl_peso_medio;
    int nm_semana;
    Date dt_pesagem;
    int qt_pesagens;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Pesagem(int cd_pesagem, Lote lote, Double vl_peso_semana, Double vl_peso_medio, int nm_semana, Date dt_pesagem, int qt_pesagens, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_pesagem = cd_pesagem;
        this.lote = lote;
        this.vl_peso_semana = vl_peso_semana;
        this.vl_peso_medio = vl_peso_medio;
        this.nm_semana = nm_semana;
        this.dt_pesagem = dt_pesagem;
        this.qt_pesagens = qt_pesagens;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_pesagem() {
        return cd_pesagem;
    }

    public void setCd_pesagem(int cd_pesagem) {
        this.cd_pesagem = cd_pesagem;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Double getVl_peso_semana() {
        return vl_peso_semana;
    }

    public void setVl_peso_semana(Double vl_peso_semana) {
        this.vl_peso_semana = vl_peso_semana;
    }

    public Double getVl_peso_medio() {
        return vl_peso_medio;
    }

    public void setVl_peso_medio(Double vl_peso_medio) {
        this.vl_peso_medio = vl_peso_medio;
    }

    public int getNm_semana() {
        return nm_semana;
    }

    public void setNm_semana(int nm_semana) {
        this.nm_semana = nm_semana;
    }

    public Date getDt_pesagem() {
        return dt_pesagem;
    }

    public void setDt_pesagem(Date dt_pesagem) {
        this.dt_pesagem = dt_pesagem;
    }

    public int getQt_pesagens() {
        return qt_pesagens;
    }

    public void setQt_pesagens(int qt_pesagens) {
        this.qt_pesagens = qt_pesagens;
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
        return "Pesagem{" +
                "vl_peso_semana=" + vl_peso_semana +
                ", vl_peso_medio=" + vl_peso_medio +
                ", nm_semana=" + nm_semana +
                '}';
    }
}
