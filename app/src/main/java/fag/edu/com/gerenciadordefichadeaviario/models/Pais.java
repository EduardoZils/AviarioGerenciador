package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Pais extends SugarRecord {
    @Unique
    int cdPais;
    String dsNome;
    String dsSigla;
    Date dtCadastro;
    Date dtAtualizacao;

    public Pais(int CdPais, String dsNome, String dsSigla, Date dtCadastro, Date dtAtualizacao) {
        this.cdPais = CdPais;
        this.dsNome = dsNome;
        this.dsSigla = dsSigla;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdPais() {
        return cdPais;
    }

    public void setCdPais(int cdPais) {
        cdPais = cdPais;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        dsNome = dsNome;
    }

    public String getDsSigla() {
        return dsSigla;
    }

    public void setDsSigla(String dsSigla) {
        dsSigla = dsSigla;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        dtCadastro = dtCadastro;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        dtAtualizacao = dtAtualizacao;
    }

    @Override
    public String toString() {
        return dsSigla + " - " + dsNome;
    }
}
