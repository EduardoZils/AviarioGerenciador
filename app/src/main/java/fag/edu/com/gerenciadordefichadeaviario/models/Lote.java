package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Lote extends SugarRecord {
    @Unique
    int cd_lote;
    Aviario aviario;
    Date dt_chegada;
    Date dt_entrega;
    Date dt_estimado_entrega;
    int qt_aves;
    String ds_linhagem;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Lote() {
    }

    public Lote(int cd_lote, Aviario aviario, Date dt_chegada, Date dt_entrega, Date dt_estimado_entrega , int qt_aves, String ds_linhagem, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_lote = cd_lote;
        this.aviario = aviario;
        this.dt_chegada = dt_chegada;
        this.dt_entrega = dt_entrega;
        this.dt_estimado_entrega = dt_entrega;
        this.qt_aves = qt_aves;
        this.ds_linhagem = ds_linhagem;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_lote() {
        return cd_lote;
    }

    public void setCd_lote(int cd_lote) {
        this.cd_lote = cd_lote;
    }

    public Aviario getAviario() {
        return aviario;
    }

    public void setAviario(Aviario aviario) {
        this.aviario = aviario;
    }

    public Date getDt_chegada() {
        return dt_chegada;
    }

    public void setDt_chegada(Date dt_chegada) {
        this.dt_chegada = dt_chegada;
    }

    public Date getDt_entrega() {
        return dt_entrega;
    }

    public void setDt_entrega(Date dt_entrega) {
        this.dt_entrega = dt_entrega;
    }

    public Date getDt_estimado_entrega() {
        return dt_estimado_entrega;
    }

    public void setDt_estimado_entrega(Date dt_estimado_entrega) {
        this.dt_estimado_entrega = dt_estimado_entrega;
    }

    public int getQt_aves() {
        return qt_aves;
    }

    public void setQt_aves(int qt_aves) {
        this.qt_aves = qt_aves;
    }

    public String getDs_linhagem() {
        return ds_linhagem;
    }

    public void setDs_linhagem(String ds_linhagem) {
        this.ds_linhagem = ds_linhagem;
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
        return "Lote{" +
                "cd_lote=" + cd_lote +
                '}';
    }
}
