package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Mortalidade extends SugarRecord {
    @Unique
    int cdMortalidade;
    Lote lote;
    int NrAvesAbatidas;
    int NrAvesEliminadas;
    Date DtMorte;
    Date DtCadastro;
    Date DtAtualizacao;
    boolean BlAtivo;

    public Mortalidade() {
    }

    public Mortalidade(int cdMortalidade, Lote lote, int nrAvesAbatidas, int nrAvesEliminadas, Date dtMorte, Date dtCadastro, Date dtAtualizacao, boolean blAtivo) {
        this.cdMortalidade = cdMortalidade;
        this.lote = lote;
        NrAvesAbatidas = nrAvesAbatidas;
        NrAvesEliminadas = nrAvesEliminadas;
        DtMorte = dtMorte;
        DtCadastro = dtCadastro;
        DtAtualizacao = dtAtualizacao;
        BlAtivo = blAtivo;
    }

    public int getCdMortalidade() {
        return cdMortalidade;
    }

    public void setCdMortalidade(int cdMortalidade) {
        this.cdMortalidade = cdMortalidade;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public int getNrAvesAbatidas() {
        return NrAvesAbatidas;
    }

    public void setNrAvesAbatidas(int nrAvesAbatidas) {
        NrAvesAbatidas = nrAvesAbatidas;
    }

    public int getNrAvesEliminadas() {
        return NrAvesEliminadas;
    }

    public void setNrAvesEliminadas(int nrAvesEliminadas) {
        NrAvesEliminadas = nrAvesEliminadas;
    }

    public Date getDtMorte() {
        return DtMorte;
    }

    public void setDtMorte(Date dtMorte) {
        DtMorte = dtMorte;
    }

    public Date getDtCadastro() {
        return DtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        DtCadastro = dtCadastro;
    }

    public Date getDtAtualizacao() {
        return DtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        DtAtualizacao = dtAtualizacao;
    }

    public boolean isBlAtivo() {
        return BlAtivo;
    }

    public void setBlAtivo(boolean blAtivo) {
        BlAtivo = blAtivo;
    }

    @Override
    public String toString() {
        return  "M " + NrAvesAbatidas +
                " - E " + NrAvesEliminadas;
    }
}
