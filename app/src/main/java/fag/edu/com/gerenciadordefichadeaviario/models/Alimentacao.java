package fag.edu.com.gerenciadordefichadeaviario.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import com.orm.dsl.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

@MultiUnique("cdAlimentacao, cdLote")

public class Alimentacao extends SugarRecord  {
    @Expose
    int cdAlimentacao;
    @Expose
    int cdLote;
    @NotNull
    Lote lote;
    @Expose
    int cdRacao;

    Racao racao;
    @Expose
    Date dtRecebimento;
    @Expose
    Double qtRecebida;
    @Expose
    Date dtCadastro;
    @Expose
    Date dtAtualizacao;
    @Expose
    boolean blAtivo;
    boolean integrado;

    public Alimentacao() {
    }

    public Alimentacao(int cdAlimentacao, int cdLote, Lote lote, int cdRacao, Racao racao, Date dtRecebimento, Double qtRecebida, Date dtCadastro, Date dtAtualizacao, boolean blAtivo, boolean integrado) {
        this.cdAlimentacao = cdAlimentacao;
        this.cdLote = cdLote;
        this.lote = lote;
        this.cdRacao = cdRacao;
        this.racao = racao;
        this.dtRecebimento = dtRecebimento;
        this.qtRecebida = qtRecebida;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
        this.integrado = integrado;
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

    public boolean isIntegrado() {
        return integrado;
    }

    public void setIntegrado(boolean integrado) {
        this.integrado = integrado;
    }

    public int getCdLote() {
        return cdLote;
    }

    public void setCdLote(int cdLote) {
        this.cdLote = cdLote;
    }

    public int getCdRacao() {
        return cdRacao;
    }

    public void setCdRacao(int cdRacao) {
        this.cdRacao = cdRacao;
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return cdAlimentacao +
                " " + qtRecebida +
                "T " + sdf.format(dtRecebimento);
    }

    public String toStringRelatorio() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return cdAlimentacao + " " +
                racao.getDsNome() +
                " " + qtRecebida +
                "T " + sdf.format(dtRecebimento);
    }
}
