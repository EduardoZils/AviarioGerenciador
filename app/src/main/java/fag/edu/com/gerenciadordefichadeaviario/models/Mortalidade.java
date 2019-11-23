package fag.edu.com.gerenciadordefichadeaviario.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.text.SimpleDateFormat;
import java.util.Date;

@MultiUnique("cdMortalidade, cdLote")
public class Mortalidade extends SugarRecord {

    @Expose
    int cdMortalidade;
    @Expose
    int cdLote;
    @NotNull
    Lote lote;
    @Expose
    int nrAvesAbatidas;
    @Expose
    int nrAvesEliminadas;
    @Expose
    Date dtMorte;
    @Expose
    Date dtCadastro;
    @Expose
    Date dtAtualizacao;
    @Expose
    boolean blAtivo;
    boolean integrado;

    public Mortalidade() {
    }

    public Mortalidade(int cdMortalidade, int cdLote, Lote lote, int nrAvesAbatidas, int nrAvesEliminadas, Date dtMorte, Date dtCadastro, Date dtAtualizacao, boolean blAtivo, boolean integrado) {
        this.cdMortalidade = cdMortalidade;
        this.cdLote = cdLote;
        this.lote = lote;
        this.nrAvesAbatidas = nrAvesAbatidas;
        this.nrAvesEliminadas = nrAvesEliminadas;
        this.dtMorte = dtMorte;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
        this.integrado = integrado;
    }

    public int getCdMortalidade() {
        return cdMortalidade;
    }

    public void setCdMortalidade(int cdMortalidade) {
        this.cdMortalidade = cdMortalidade;
    }

    public int getCdLote() {
        return cdLote;
    }

    public void setCdLote(int cdLote) {
        this.cdLote = cdLote;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public int getNrAvesAbatidas() {
        return nrAvesAbatidas;
    }

    public void setNrAvesAbatidas(int nrAvesAbatidas) {
        this.nrAvesAbatidas = nrAvesAbatidas;
    }

    public int getNrAvesEliminadas() {
        return nrAvesEliminadas;
    }

    public void setNrAvesEliminadas(int nrAvesEliminadas) {
        this.nrAvesEliminadas = nrAvesEliminadas;
    }

    public Date getDtMorte() {
        return dtMorte;
    }

    public void setDtMorte(Date dtMorte) {
        this.dtMorte = dtMorte;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Mortes " + nrAvesAbatidas +
                " - Eliminados " + nrAvesEliminadas + " - " + sdf.format(dtMorte);
    }
}
