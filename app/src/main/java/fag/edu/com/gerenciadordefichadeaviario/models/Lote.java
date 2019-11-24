package fag.edu.com.gerenciadordefichadeaviario.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lote extends SugarRecord {
    @Unique
    @Expose
    int cdLote;
    @Expose
    int cdAviario;

    Aviario aviario;
    @Expose
    Date dtChegada;
    @Expose
    Date dtEntrega;
    @Expose
    Date dtEstimadaEntrega;
    @Expose
    int qtAves;
    @Expose
    String dsLinhagem;
    @Expose
    Date dtCadastro;
    @Expose
    Date dtAtualizacao;
    @Expose
    boolean blAtivo;
    boolean integrado;

    public Lote() {
    }

    public Lote(int cdLote, int cdAviario, Aviario aviario, Date dtChegada, Date dtEntrega, Date dtEstimadaEntrega, int qtAves, String dsLinhagem, Date dtCadastro, Date dtAtualizacao, boolean blAtivo, boolean integrado) {
        this.cdLote = cdLote;
        this.cdAviario = cdAviario;
        this.aviario = aviario;
        this.dtChegada = dtChegada;
        this.dtEntrega = dtEntrega;
        this.dtEstimadaEntrega = dtEstimadaEntrega;
        this.qtAves = qtAves;
        this.dsLinhagem = dsLinhagem;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
        this.integrado = integrado;
    }

    public int getCdLote() {
        return cdLote;
    }

    public void setCdLote(int cdLote) {
        this.cdLote = cdLote;
    }

    public int getCdAviario() {
        return cdAviario;
    }

    public void setCdAviario(int cdAviario) {
        this.cdAviario = cdAviario;
    }

    public Aviario getAviario() {
        return aviario;
    }

    public void setAviario(Aviario aviario) {
        this.aviario = aviario;
    }

    public Date getDtChegada() {
        return dtChegada;
    }

    public void setDtChegada(Date dtChegada) {
        this.dtChegada = dtChegada;
    }

    public Date getDtEntrega() {
        return dtEntrega;
    }

    public void setDtEntrega(Date dtEntrega) {
        this.dtEntrega = dtEntrega;
    }

    public Date getDtEstimadaEntrega() {
        return dtEstimadaEntrega;
    }

    public void setDtEstimadaEntrega(Date dtEstimadaEntrega) {
        this.dtEstimadaEntrega = dtEstimadaEntrega;
    }

    public int getQtAves() {
        return qtAves;
    }

    public void setQtAves(int qtAves) {
        this.qtAves = qtAves;
    }

    public String getDsLinhagem() {
        return dsLinhagem;
    }

    public void setDsLinhagem(String dsLinhagem) {
        this.dsLinhagem = dsLinhagem;
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

    @Override
    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return cdLote +
                " Chegada: " + sdf.format(dtChegada) +
                " Saida: " + sdf.format(dtEntrega) +
                " " + dsLinhagem;
    }
}
