package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Aviario extends SugarRecord {
    @Unique
    int cdAviario;
    Usuario usuario;
    Endereco endereco;
    int nrIdentificador;
    int nrCapAves;
    Date dtCadastro;
    Date dtAtualizacao;

    public Aviario() {
    }

    public Aviario(int cdAviario, Usuario usuario, Endereco endereco, int nrIdentificador, int nrCapAves, Date dtCadastro, Date dtAtualizacao) {
        this.cdAviario = cdAviario;
        this.usuario = usuario;
        this.endereco = endereco;
        this.nrIdentificador = nrIdentificador;
        this.nrCapAves = nrCapAves;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdAviario() {
        return cdAviario;
    }

    public void setCdAviario(int cdAviario) {
        this.cdAviario = cdAviario;
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

    public int getNrIdentificador() {
        return nrIdentificador;
    }

    public void setNrIdentificador(int nrIdentificador) {
        this.nrIdentificador = nrIdentificador;
    }

    public int getNrCapAves() {
        return nrCapAves;
    }

    public void setNrCapAves(int nrCapAves) {
        this.nrCapAves = nrCapAves;
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
        return "Aviario{" +
                "cdAviario=" + cdAviario +
                '}';
    }
}
