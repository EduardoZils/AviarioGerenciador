package fag.edu.com.gerenciadordefichadeaviario.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.util.Date;

@MultiUnique("cdPesagem, cdLote")
public class Pesagem extends SugarRecord {
    @Expose
    int cdPesagem;
    @Expose
    int cdLote;
    @NotNull
    Lote lote;
    @Expose
    Double vlPesoSemana;
    @Expose
    Double vlPesoMedio;
    @Expose
    int nmSemana;
    @Expose
    Date dtPesagem;
    @Expose
    int qtPesagens;
    @Expose
    Date dtCadastro;
    @Expose
    Date dtAtualizacao;
    @Expose
    boolean blAtivo;
    boolean integrado;

    public Pesagem(int cdPesagem) {
        this.cdPesagem = cdPesagem;
    }

    public Pesagem(int cdPesagem, Lote lote, Double vlPesoSemana, Double vlPesoMedio, int nmSemana, Date dtPesagem, int qtPesagens, Date dtCadastro, Date dtAtualizacao, boolean blAtivo, boolean integrado) {
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
        this.integrado = integrado;
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

    public int getCdLote() {
        return cdLote;
    }

    public void setCdLote(int cdLote) {
        this.cdLote = cdLote;
    }

    public boolean isIntegrado() {
        return integrado;
    }

    public void setIntegrado(boolean integrado) {
        this.integrado = integrado;
    }

    @Override
    public String toString() {
        return "Pesagem{" +
                "qtPesagens=" + qtPesagens +
                '}';
    }
}
