package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Pesagem extends SugarRecord {
    @Unique
    int cdPesagem;
    Lote lote;
    Double vlPesoSemana;
    Double vlPesoMedio;
    int nmSemana;
    Date dtPesagem;
    int qtPesagens;
    Date dtCadastro;
    Date dtAtualizacao;
    boolean blAtivo;

    public Pesagem(int cdPesagem) {
        this.cdPesagem = cdPesagem;
    }

    public Pesagem(int cdPesagem, Lote lote, Double vlPesoSemana, Double vlPesoMedio, int nmSemana, Date dtPesagem, int qtPesagens, Date dtCadastro, Date dtAtualizacao, boolean blAtivo) {
        this.cdPesagem = cdPesagem;
        this.lote = lote;
        this.vlPesoSemana = vlPesoSemana;
        this.vlPesoMedio = vlPesoMedio;
        this.nmSemana = nmSemana;
        this.dtPesagem = dtPesagem;
        this.qtPesagens = qtPesagens;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
    }

    public Pesagem() {
    }

    public int getCdPesagem() {
        return cdPesagem;
    }

    public void setCdPesagem(int cdPesagem) {
        this.cdPesagem = cdPesagem;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Double getVlPesoSemana() {
        return vlPesoSemana;
    }

    public void setVlPesoSemana(Double vlPesoSemana) {
        this.vlPesoSemana = vlPesoSemana;
    }

    public Double getVlPesoMedio() {
        return vlPesoMedio;
    }

    public void setVlPesoMedio(Double vlPesoMedio) {
        this.vlPesoMedio = vlPesoMedio;
    }

    public int getNmSemana() {
        return nmSemana;
    }

    public void setNmSemana(int nmSemana) {
        this.nmSemana = nmSemana;
    }

    public Date getDtPesagem() {
        return dtPesagem;
    }

    public void setDtPesagem(Date dtPesagem) {
        this.dtPesagem = dtPesagem;
    }

    public int getQtPesagens() {
        return qtPesagens;
    }

    public void setQtPesagens(int qtPesagens) {
        this.qtPesagens = qtPesagens;
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
        return "Pesagem{" +
                "qtPesagens=" + qtPesagens +
                '}';
    }
}
