package fag.edu.com.gerenciadordefichadeaviario.models;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.util.Date;

public class Endereco extends SugarRecord {
    @Unique
    @Expose
    int cdEndereco;
    @NotNull
    Municipio municipio;
    @Expose
    int cdMunicipio;
    @Expose
    @NotNull
    String dsCep;
    @Expose
    @NotNull
    String dsAdjetivo;
    @Expose
    @NotNull
    String dsLogradouro;
    @Expose
    @NotNull
    String dsEstrada;
    @Expose
    @NotNull
    Date dtCadastro;
    @Expose
    @NotNull
    Date dtAtualizacao;
    boolean integrado;

    public Endereco() {
    }

    public Endereco(int cdEndereco, Municipio municipio, int cdMunicipio, String dsCep, String dsAdjetivo, String dsLogradouro, String dsEstrada, Date dtCadastro, Date dtAtualizacao, boolean integrado) {
        this.cdEndereco = cdEndereco;
        this.municipio = municipio;
        this.cdMunicipio = cdMunicipio;
        this.dsCep = dsCep;
        this.dsAdjetivo = dsAdjetivo;
        this.dsLogradouro = dsLogradouro;
        this.dsEstrada = dsEstrada;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.integrado = integrado;
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

    public String getDsLogradouro() {
        return dsLogradouro;
    }

    public void setDsLogradouro(String dsLogradouro) {
        this.dsLogradouro = dsLogradouro;
    }

    public String getDsEstrada() {
        return dsEstrada;
    }

    public void setDsEstrada(String dsEstrada) {
        this.dsEstrada = dsEstrada;
    }

    public int getCdMunicipio() {
        return cdMunicipio;
    }

    public void setCdMunicipio(int cdMunicipio) {
        this.cdMunicipio = cdMunicipio;
    }

    public boolean isIntegrado() {
        return integrado;
    }

    public void setIntegrado(boolean integrado) {
        this.integrado = integrado;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "municipio=" + municipio +
                '}';
    }
}
