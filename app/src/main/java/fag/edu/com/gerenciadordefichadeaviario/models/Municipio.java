package fag.edu.com.gerenciadordefichadeaviario.models;

import java.util.Date;

public class Municipio {
    int cd_municipio;
    Estado estado;
    String ds_nome;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Municipio(int cd_municipio, Estado estado, String ds_nome, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_municipio = cd_municipio;
        this.estado = estado;
        this.ds_nome = ds_nome;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_municipio() {
        return cd_municipio;
    }

    public void setCd_municipio(int cd_municipio) {
        this.cd_municipio = cd_municipio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDs_nome() {
        return ds_nome;
    }

    public void setDs_nome(String ds_nome) {
        this.ds_nome = ds_nome;
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
        return "Municipio{" +
                "ds_nome='" + ds_nome + '\'' +
                '}';
    }
}
