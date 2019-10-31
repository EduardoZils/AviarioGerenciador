package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Municipio extends SugarRecord {
    @Unique
    int cdMunicipio;
    Estado estado;
    String dsNome;
    Date dtCadastro;
    Date dtAtualizacao;

    public Municipio() {
    }

    public Municipio(int cdMunicipio, Estado estado, String dsNome, Date dtCadastro, Date dtAtualizacao) {
        this.cdMunicipio = cdMunicipio;
        this.estado = estado;
        this.dsNome = dsNome;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdMunicipio() {
        return cdMunicipio;
    }

    public void setCdMunicipio(int cdMunicipio) {
        this.cdMunicipio = cdMunicipio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
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
        return dsNome;
    }
}
