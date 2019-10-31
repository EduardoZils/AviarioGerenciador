package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Endereco extends SugarRecord {
    @Unique
    int cd_endereco;
    Municipio municipio;
    String ds_cep;
    String ds_adjetivo;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Endereco() {
    }


    public Endereco(int cd_endereco, Municipio municipio, String ds_cep, String ds_adjetivo, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_endereco = cd_endereco;
        this.municipio = municipio;
        this.ds_cep = ds_cep;
        this.ds_adjetivo = ds_adjetivo;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_endereco() {
        return cd_endereco;
    }

    public void setCd_endereco(int cd_endereco) {
        this.cd_endereco = cd_endereco;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getDs_cep() {
        return ds_cep;
    }

    public void setDs_cep(String ds_cep) {
        this.ds_cep = ds_cep;
    }

    public String getDs_adjetivo() {
        return ds_adjetivo;
    }

    public void setDs_adjetivo(String ds_adjetivo) {
        this.ds_adjetivo = ds_adjetivo;
    }

    public Date getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(Date dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public Date getDt_atualizacao() {
        return dt_atualizacao;
    }

    public void setDt_atualizacao(Date dt_atualizacao) {
        this.dt_atualizacao = dt_atualizacao;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "municipio=" + municipio +
                '}';
    }
}
