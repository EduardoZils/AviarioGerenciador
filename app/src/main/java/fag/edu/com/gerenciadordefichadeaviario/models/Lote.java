package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Lote extends SugarRecord {
    @Unique
    int cdLote;
    Aviario aviario;
    Date dtChegada;
    Date dtEntrega;
    Date dtEstimadaEntrega;
    int qtAves;
    String dsLinhagem;
    Date dtCadastro;
    Date dtAtualizacao;
    boolean blAtivo;

    public Lote() {
    }

    public Lote(int cdLote, Aviario aviario, Date dtChegada, Date dtEntrega, Date dtEstimadaEntrega, int qtAves, String dsLinhagem, Date dtCadastro, Date dtAtualizacao, boolean blAtivo) {
        this.cdLote = cdLote;
        this.aviario = aviario;
        this.dtChegada = dtChegada;
        this.dtEntrega = dtEntrega;
        this.dtEstimadaEntrega = dtEstimadaEntrega;
        this.qtAves = qtAves;
        this.dsLinhagem = dsLinhagem;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
    }

    public int getCdLote() {
        return cdLote;
    }

    public void setCdLote(int cdLote) {
        this.cdLote = cdLote;
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

    @Override
    public String toString() {
        return "Lote{" +
                "cdLote=" + cdLote +
                ", aviario=" + aviario +
                ", dtChegada=" + dtChegada +
                ", dtEntrega=" + dtEntrega +
                ", dtEestimadaEntrega=" + dtEstimadaEntrega +
                ", qtAves=" + qtAves +
                ", dsLinhagem='" + dsLinhagem + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                ", blAtivo=" + blAtivo +
                '}';
    }
}
