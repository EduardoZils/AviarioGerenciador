package fag.edu.com.gerenciadordefichadeaviario.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.util.Date;

public class Pesos extends SugarRecord {
    @Unique
    @Expose
    int cdPeso;
    @Expose
    int cdPesagem;
    @Expose
    int cdLote;
    @NotNull
    Pesagem pesagem;
    @Expose
    int qtAvesUtilizadas;
    @Expose
    Double vlPesagem;
    @Expose
    Date dtCadastro;
    @Expose
    Date dtAtualizacao;
    @Expose
    boolean blAtivo;
    boolean integrado;

    public Pesos() {
    }

    public Pesos(int cdPeso, int cdPesagem, int cdLote, Pesagem pesagem, int qtAvesUtilizadas, Double vlPesagem, Date dtCadastro, Date dtAtualizacao, boolean blAtivo, boolean integrado) {
        this.cdPeso = cdPeso;
        this.cdPesagem = cdPesagem;
        this.cdLote = cdLote;
        this.pesagem = pesagem;
        this.qtAvesUtilizadas = qtAvesUtilizadas;
        this.vlPesagem = vlPesagem;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
        this.integrado = integrado;
    }

    public int getCdPeso() {
        return cdPeso;
    }

    public void setCdPeso(int cdPeso) {
        this.cdPeso = cdPeso;
    }

    public Pesagem getPesagem() {
        return pesagem;
    }

    public void setPesagem(Pesagem pesagem) {
        this.pesagem = pesagem;
    }

    public int getCdLote() {
        return cdLote;
    }

    public void setCdLote(int cdLote) {
        this.cdLote = cdLote;
    }

    public int getQtAvesUtilizadas() {
        return qtAvesUtilizadas;
    }

    public void setQtAvesUtilizadas(int qtAvesUtilizadas) {
        this.qtAvesUtilizadas = qtAvesUtilizadas;
    }

    public Double getVlPesagem() {
        return vlPesagem;
    }

    public void setVlPesagem(Double vlPesagem) {
        this.vlPesagem = vlPesagem;
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

    public boolean isIntegrado() {
        return integrado;
    }

    public void setIntegrado(boolean integrado) {
        this.integrado = integrado;
    }

    public int getCdPesagem() {
        return cdPesagem;
    }

    public void setCdPesagem(int cdPesagem) {
        this.cdPesagem = cdPesagem;
    }

    @Override
    public String toString() {
        return qtAvesUtilizadas + " aves - " + vlPesagem +
                " Kg";
    }
}
