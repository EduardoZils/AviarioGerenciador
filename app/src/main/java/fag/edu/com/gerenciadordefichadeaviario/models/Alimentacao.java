package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Alimentacao extends SugarRecord {
    @Unique
    int cd_alimentacao;
    Lote lote;
    Racao racao;
    Date dt_recebimento;
    Double qt_recebida;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Alimentacao(int cd_alimentacao, Lote lote, Racao racao, Date dt_recebimento, Double qt_recebida, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_alimentacao = cd_alimentacao;
        this.lote = lote;
        this.racao = racao;
        this.dt_recebimento = dt_recebimento;
        this.qt_recebida = qt_recebida;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_alimentacao() {
        return cd_alimentacao;
    }

    public void setCd_alimentacao(int cd_alimentacao) {
        this.cd_alimentacao = cd_alimentacao;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Racao getRacao() {
        return racao;
    }

    public void setRacao(Racao racao) {
        this.racao = racao;
    }

    public Date getDt_recebimento() {
        return dt_recebimento;
    }

    public void setDt_recebimento(Date dt_recebimento) {
        this.dt_recebimento = dt_recebimento;
    }

    public Double getQt_recebida() {
        return qt_recebida;
    }

    public void setQt_recebida(Double qt_recebida) {
        this.qt_recebida = qt_recebida;
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
        return "Alimentacao{" +
                "cd_alimentacao=" + cd_alimentacao +
                '}';
    }
}
