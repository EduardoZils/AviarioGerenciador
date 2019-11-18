package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Racao extends SugarRecord {
    @Unique
    int cdRacao;
    String dsNome;
    String dsTipo;
    Date dtCadastro;
    Date dtAtualizacao;
    boolean blAtivo;

    public Racao() {
    }

    public Racao(int cdRacao, String dsNome, String dsTipo, Date dtCadastro, Date dtAtualizacao, boolean blAtivo) {
        this.cdRacao = cdRacao;
        this.dsNome = dsNome;
        this.dsTipo = dsTipo;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
    }

    public int getCdRacao() {
        return cdRacao;
    }

    public void setCdRacao(int cdRacao) {
        this.cdRacao = cdRacao;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsTipo() {
        return dsTipo;
    }

    public void setDsTipo(String dsTipo) {
        this.dsTipo = dsTipo;
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

    public boolean isBlAtivo() {
        return blAtivo;
    }

    public void setBlAtivo(boolean blAtivo) {
        this.blAtivo = blAtivo;
    }

    @Override
    public String toString() {
        return dsNome + " - " + dsTipo;
    }
}
