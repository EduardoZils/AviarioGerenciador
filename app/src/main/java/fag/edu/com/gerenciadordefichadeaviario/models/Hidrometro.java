package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Hidrometro extends SugarRecord {
    @Unique
    int cdHidrometro;
    Lote lote;
    Double qtGasto;
    Date dtColeta;
    Date dtCadastro;
    Date dtAtualizacao;

    public Hidrometro() {
    }

    public Hidrometro(int cdHidrometro, Lote lote, Double qtGasto, Date dtColeta, Date dtCadastro, Date dtAtualizacao) {
        this.cdHidrometro = cdHidrometro;
        this.lote = lote;
        this.qtGasto = qtGasto;
        this.dtColeta = dtColeta;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdHidrometro() {
        return cdHidrometro;
    }

    public void setCdHidrometro(int cdHidrometro) {
        this.cdHidrometro = cdHidrometro;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Double getQtGasto() {
        return qtGasto;
    }

    public void setQtGasto(Double qtGasto) {
        this.qtGasto = qtGasto;
    }

    public Date getDtColeta() {
        return dtColeta;
    }

    public void setDtColeta(Date dtColeta) {
        this.dtColeta = dtColeta;
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

    @Override
    public String toString() {
        return "Hidrometro{" +
                "qtGasto=" + qtGasto +
                ", dtColeta=" + dtColeta +
                '}';
    }
}
