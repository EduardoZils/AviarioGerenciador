package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Racao extends SugarRecord {
    @Unique
    int cd_racao;
    String ds_nome;
    String ds_tipo;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Racao(int cd_racao, String ds_nome, String ds_tipo, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_racao = cd_racao;
        this.ds_nome = ds_nome;
        this.ds_tipo = ds_tipo;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_racao() {
        return cd_racao;
    }

    public void setCd_racao(int cd_racao) {
        this.cd_racao = cd_racao;
    }

    public String getDs_nome() {
        return ds_nome;
    }

    public void setDs_nome(String ds_nome) {
        this.ds_nome = ds_nome;
    }

    public String getDs_tipo() {
        return ds_tipo;
    }

    public void setDs_tipo(String ds_tipo) {
        this.ds_tipo = ds_tipo;
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
        return "Racao{" +
                "ds_nome='" + ds_nome + '\'' +
                ", ds_tipo='" + ds_tipo + '\'' +
                '}';
    }
}
