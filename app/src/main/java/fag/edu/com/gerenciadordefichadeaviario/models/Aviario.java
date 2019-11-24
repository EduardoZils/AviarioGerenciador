package fag.edu.com.gerenciadordefichadeaviario.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.util.Date;

public class Aviario extends SugarRecord {
    @Unique
    @Expose
    int cdAviario;
    @NotNull
    Usuario usuario;
    @Expose
    int cdUsuario;
    @NotNull
    Endereco endereco;
    @Expose
    int cdEndereco;
    @Expose
    @NotNull
    int nrIdentificador;
    @Expose
    @NotNull
    int nrCapAves;
    @Expose
    @NotNull
    Date dtCadastro;
    @Expose
    @NotNull
    Date dtAtualizacao;
    @Expose
    @NotNull
    Boolean blAtivo;
    boolean integrado;

    public Aviario() {
    }

    public Aviario(int cdAviario, Usuario usuario, int cdUsuario, Endereco endereco, int cdEndereco, int nrIdentificador, int nrCapAves, Date dtCadastro, Date dtAtualizacao, Boolean blAtivo, boolean integrado) {
        this.cdAviario = cdAviario;
        this.usuario = usuario;
        this.cdUsuario = cdUsuario;
        this.endereco = endereco;
        this.cdEndereco = cdEndereco;
        this.nrIdentificador = nrIdentificador;
        this.nrCapAves = nrCapAves;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
        this.integrado = integrado;
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

    public Boolean getBlAtivo() {
        return blAtivo;
    }

    public void setBlAtivo(Boolean blAtivo) {
        this.blAtivo = blAtivo;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public int getCdEndereco() {
        return cdEndereco;
    }

    public void setCdEndereco(int cdEndereco) {
        this.cdEndereco = cdEndereco;
    }

    public boolean isIntegrado() {
        return integrado;
    }

    public void setIntegrado(boolean integrado) {
        this.integrado = integrado;
    }

    @Override
    public String toString() {
        return  "->"+ nrIdentificador;
    }
}
