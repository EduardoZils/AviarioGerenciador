package fag.edu.com.gerenciadordefichadeaviario.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.text.SimpleDateFormat;
import java.util.Date;

@MultiUnique("cdHidrometro, cdLote")
public class Hidrometro extends SugarRecord {
    @Expose
    int cdHidrometro;
    @Expose
    int cdLote;
    @NotNull
    Lote lote;
    @Expose
    Double qtGasto;
    @Expose
    Date dtColeta;
    @Expose
    Date dtCadastro;
    @Expose
    Date dtAtualizacao;
    @Expose
    boolean blAtivo;
    boolean integrado;

    public Hidrometro() {
    }

    public Hidrometro(int cdHidrometro, int cdLote, Lote lote, Double qtGasto, Date dtColeta, Date dtCadastro, Date dtAtualizacao, boolean blAtivo, boolean integrado) {
        this.cdHidrometro = cdHidrometro;
        this.cdLote = cdLote;
        this.lote = lote;
        this.qtGasto = qtGasto;
        this.dtColeta = dtColeta;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
        this.integrado = integrado;
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
        return qtGasto + "L " +
                sdf.format(dtColeta)
                ;
    }
}
