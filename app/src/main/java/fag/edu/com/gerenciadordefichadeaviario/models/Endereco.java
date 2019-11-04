package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Endereco extends SugarRecord {
    @Unique
    int cdEndereco;
    Municipio municipio;
    String dsCep;
    String dsAdjetivo;
    Date dtCadastro;
    Date dtAtualizacao;

    public Endereco() {
    }


    public Endereco(int cdEndereco, Municipio municipio, String dsCep, String dsAdjetivo, Date dtCadastro, Date dtAtualizacao) {
        this.cdEndereco = cdEndereco;
        this.municipio = municipio;
        this.dsCep = dsCep;
        this.dsAdjetivo = dsAdjetivo;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdEndereco() {
        return cdEndereco;
    }

    public void setCdEndereco(int cdEndereco) {
        this.cdEndereco = cdEndereco;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getDsCep() {
        return dsCep;
    }

    public void setDsCep(String dsCep) {
        this.dsCep = dsCep;
    }

    public String getDsAdjetivo() {
        return dsAdjetivo;
    }

    public void setDsAdjetivo(String dsAdjetivo) {
        this.dsAdjetivo = dsAdjetivo;
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
        return "Endereco{" +
                "municipio=" + municipio +
                '}';
    }
}
