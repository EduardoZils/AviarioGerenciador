package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Alimentacao extends SugarRecord {
    @Unique
    int cdAlimentacao;
    Lote lote;
    Racao racao;
    Date dtRecebimento;
    Double qtRecebida;
    Date dtCadastro;
    Date dtAtualizacao;
    boolean blAtivo;

    public Alimentacao() {
    }

    public Alimentacao(int cdAlimentacao, Lote lote, Racao racao, Date dtRecebimento, Double qtRecebida, Date dtCadastro, Date dtAtualizacao, boolean blAtivo) {
        this.cdAlimentacao = cdAlimentacao;
        this.lote = lote;
        this.racao = racao;
        this.dtRecebimento = dtRecebimento;
        this.qtRecebida = qtRecebida;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
    }

    public int getCdAlimentacao() {
        return cdAlimentacao;
    }

    public void setCdAlimentacao(int cdAlimentacao) {
        this.cdAlimentacao = cdAlimentacao;
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

    public Date getDtRecebimento() {
        return dtRecebimento;
    }

    public void setDtRecebimento(Date dtRecebimento) {
        this.dtRecebimento = dtRecebimento;
    }

    public Double getQtRecebida() {
        return qtRecebida;
    }

    public void setQtRecebida(Double qtRecebida) {
        this.qtRecebida = qtRecebida;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public boolean isBlAtivo() {
        return blAtivo;
    }

    public void setBlAtivo(boolean blAtivo) {
        this.blAtivo = blAtivo;
    }

    @Override
    public String toString() {
        return "Alimentacao{" +
                "cdAlimentacao=" + cdAlimentacao +
                ", lote=" + lote +
                ", racao=" + racao +
                ", dtRecebimento=" + dtRecebimento +
                ", qtRecebida=" + qtRecebida +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                ", blAtivo=" + blAtivo +
                '}';
    }
}
