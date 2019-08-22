package fag.edu.com.gerenciadordefichadeaviario.models;

import java.util.Date;

public class Pais {
    int cd_pais;
    String ds_nome;
    String ds_sigla;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Pais(int cd_pais, String ds_nome, String ds_sigla, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_pais = cd_pais;
        this.ds_nome = ds_nome;
        this.ds_sigla = ds_sigla;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_pais() {
        return cd_pais;
    }

    public void setCd_pais(int cd_pais) {
        this.cd_pais = cd_pais;
    }

    public String getDs_nome() {
        return ds_nome;
    }

    public void setDs_nome(String ds_nome) {
        this.ds_nome = ds_nome;
    }

    public String getDs_sigla() {
        return ds_sigla;
    }

    public void setDs_sigla(String ds_sigla) {
        this.ds_sigla = ds_sigla;
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
        return "Pais{" +
                "ds_nome='" + ds_nome + '\'' +
                ", ds_sigla='" + ds_sigla + '\'' +
                '}';
    }
}
