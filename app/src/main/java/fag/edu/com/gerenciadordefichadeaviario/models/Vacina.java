package fag.edu.com.gerenciadordefichadeaviario.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.text.SimpleDateFormat;
import java.util.Date;

@MultiUnique("cdVacina, cdLote")
public class Vacina extends SugarRecord {

    @Expose
    int cdVacina;
    @Expose
    int cdLote;
    @NotNull
    Lote lote;
    @Expose
    String dsNome;
    @Expose
    String dsFormaUso;
    @Expose
    Date dtUso;
    @Expose
    String dsPrescricao;
    @Expose
    Double qtUsada;
    @Expose
    Date dtCadastro;
    @Expose
    Date dtAtualizacao;
    @Expose
    boolean blAtivo;
    boolean integrado;

    public Vacina() {
    }

    public Vacina(int cdVacina, int cdLote, Lote lote, String dsNome, String dsFormaUso, Date dtUso, String dsPrescricao, Double qtUsada, Date dtCadastro, Date dtAtualizacao, boolean blAtivo, boolean integrado) {
        this.cdVacina = cdVacina;
        this.cdLote = cdLote;
        this.lote = lote;
        this.dsNome = dsNome;
        this.dsFormaUso = dsFormaUso;
        this.dtUso = dtUso;
        this.dsPrescricao = dsPrescricao;
        this.qtUsada = qtUsada;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
        this.integrado = integrado;
    }

    public int getCdVacina() {
        return cdVacina;
    }

    public void setCdVacina(int cdVacina) {
        this.cdVacina = cdVacina;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsFormaUso() {
        return dsFormaUso;
    }

    public void setDsFormaUso(String dsFormaUso) {
        this.dsFormaUso = dsFormaUso;
    }

    public Date getDtUso() {
        return dtUso;
    }

    public void setDtUso(Date dtUso) {
        this.dtUso = dtUso;
    }

    public String getDsPrescricao() {
        return dsPrescricao;
    }

    public void setDsPrescricao(String dsPrescricao) {
        this.dsPrescricao = dsPrescricao;
    }

    public Double getQtUsada() {
        return qtUsada;
    }

    public void setQtUsada(Double qtUsada) {
        this.qtUsada = qtUsada;
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return  dsNome + " - " +
                qtUsada + "ml - " +
                sdf.format(dtUso);
    }
}
