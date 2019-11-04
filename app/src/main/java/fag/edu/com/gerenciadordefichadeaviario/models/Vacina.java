package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Vacina extends SugarRecord {
    @Unique
    int cdVacina;
    Lote lote;
    String dsNome;
    String dsFormaUso;
    Date dtUso;
    String dsPrescricao;
    Double qtUsada;
    Date dtCadastro;
    Date dtAtualizacao;
    boolean blAtivo;

    public Vacina() {
    }

    public Vacina(int cdVacina, Lote lote, String dsNome, String dsFormaUso, Date dtUso, String dsPrescricao, Double qtUsada, Date dtCadastro, Date dtAtualizacao, boolean blAtivo) {
        this.cdVacina = cdVacina;
        this.lote = lote;
        this.dsNome = dsNome;
        this.dsFormaUso = dsFormaUso;
        this.dtUso = dtUso;
        this.dsPrescricao = dsPrescricao;
        this.qtUsada = qtUsada;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
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

    @Override
    public String toString() {
        return "Vacina{" +
                "ds_nome='" + dsNome + '\'' +
                ", ds_forma_uso='" + dsFormaUso + '\'' +
                '}';
    }
}
