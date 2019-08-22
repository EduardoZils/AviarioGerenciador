package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;

import java.util.Date;

public class Usuario extends SugarRecord {
    int cd_usuario;
    String ds_nome;
    String ds_email;
    String ds_senha;
    String ds_cpdf;
    String ds_rg;
    Date dt_nascimento;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Usuario(int cd_usuario, String ds_nome, String ds_email, String ds_senha, String ds_cpdf, String ds_rg, Date dt_nascimento, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_usuario = cd_usuario;
        this.ds_nome = ds_nome;
        this.ds_email = ds_email;
        this.ds_senha = ds_senha;
        this.ds_cpdf = ds_cpdf;
        this.ds_rg = ds_rg;
        this.dt_nascimento = dt_nascimento;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public String getDs_nome() {
        return ds_nome;
    }

    public void setDs_nome(String ds_nome) {
        this.ds_nome = ds_nome;
    }

    public String getDs_email() {
        return ds_email;
    }

    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
    }

    public String getDs_senha() {
        return ds_senha;
    }

    public void setDs_senha(String ds_senha) {
        this.ds_senha = ds_senha;
    }

    public String getDs_cpdf() {
        return ds_cpdf;
    }

    public void setDs_cpdf(String ds_cpdf) {
        this.ds_cpdf = ds_cpdf;
    }

    public String getDs_rg() {
        return ds_rg;
    }

    public void setDs_rg(String ds_rg) {
        this.ds_rg = ds_rg;
    }

    public Date getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(Date dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
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
        return "Usuario{" +
                "ds_nome='" + ds_nome + '\'' +
                '}';
    }
}
