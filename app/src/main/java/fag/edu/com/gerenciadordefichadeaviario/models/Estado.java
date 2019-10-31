package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Estado extends SugarRecord {
    @Unique
    int cdEstado;
    Pais pais;
    String dsNome;
    String dsSigla;
    Date dtCadastro;
    Date dtAtualizacao;

    public Estado() {
    }

    public Estado(int cdEstado, Pais pais, String dsNome, String dsSigla, Date dtCadastro, Date dtAtualizacao) {
        this.cdEstado = cdEstado;
        this.pais = pais;
        this.dsNome = dsNome;
        this.dsSigla = dsSigla;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdEstado() {
        return cdEstado;
    }

    public void setCdEstado(int cdEstado) {
        this.cdEstado = cdEstado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsSigla() {
        return dsSigla;
    }

    public void setDsSigla(String dsSigla) {
        this.dsSigla = dsSigla;
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
        return dsSigla + " - " + dsNome;
    }
}
