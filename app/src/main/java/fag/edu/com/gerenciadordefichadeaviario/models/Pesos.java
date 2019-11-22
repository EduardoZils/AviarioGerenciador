package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Pesos extends SugarRecord {
    @Unique
    int cdPeso;
    Pesagem pesagem;
    int qtAvesUtilizadas;
    Double vlPesagem;
    Date dtCadastro;
    Date dtAtualizacao;
    boolean blAtivo;

    public Pesos() {
    }

    public Pesos(int cdPeso, Pesagem pesagem, int qtAvesUtilizadas, Double vlPesagem, Date dtCadastro, Date dtAtualizacao, boolean blAtivo) {
        this.cdPeso = cdPeso;
        this.pesagem = pesagem;
        this.qtAvesUtilizadas = qtAvesUtilizadas;
        this.vlPesagem = vlPesagem;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
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

    @Override
    public String toString() {
        return qtAvesUtilizadas + " aves - " + vlPesagem +
                " Kg";
    }
}
