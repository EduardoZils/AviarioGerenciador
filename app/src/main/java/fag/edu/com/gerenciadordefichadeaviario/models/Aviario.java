package fag.edu.com.gerenciadordefichadeaviario.models;

import java.util.Date;

public class Aviario {
    int cd_aviario;
    Usuario usuario;
    Endereco endereco;
    int nm_identificador;
    int nm_cap_aves;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Aviario(int cd_aviario, Usuario usuario, Endereco endereco, int nm_identificador, int nm_cap_aves, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_aviario = cd_aviario;
        this.usuario = usuario;
        this.endereco = endereco;
        this.nm_identificador = nm_identificador;
        this.nm_cap_aves = nm_cap_aves;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_aviario() {
        return cd_aviario;
    }

    public void setCd_aviario(int cd_aviario) {
        this.cd_aviario = cd_aviario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getNm_identificador() {
        return nm_identificador;
    }

    public void setNm_identificador(int nm_identificador) {
        this.nm_identificador = nm_identificador;
    }

    public int getNm_cap_aves() {
        return nm_cap_aves;
    }

    public void setNm_cap_aves(int nm_cap_aves) {
        this.nm_cap_aves = nm_cap_aves;
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
        return "Aviario{" +
                "nm_identificador=" + nm_identificador +
                '}';
    }
}
