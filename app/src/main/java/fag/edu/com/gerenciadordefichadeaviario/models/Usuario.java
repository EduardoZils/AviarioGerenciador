package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Usuario extends SugarRecord {
    @Unique
    int cdUsuario;
    String dsNome;
    String dsEmail;
    String dsSenha;
    String dsCpf;
    String dsRg;
    Date dtNascimento;
    Date dtCadastro;
    Date dtAtualizacao;
    boolean blAtivo;

    public Usuario(int cdUsuario, String dsNome, String dsEmail, String dsSenha, String dsCpf, String dsRg, Date dtNascimento, Date dtCadastro, Date dtAtualizacao, boolean blAtivo) {
        this.cdUsuario = cdUsuario;
        this.dsNome = dsNome;
        this.dsEmail = dsEmail;
        this.dsSenha = dsSenha;
        this.dsCpf = dsCpf;
        this.dsRg = dsRg;
        this.dtNascimento = dtNascimento;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.blAtivo = blAtivo;
    }

    public Usuario() {
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public String getDsCpf() {
        return dsCpf;
    }

    public void setDsCpf(String dsCpf) {
        this.dsCpf = dsCpf;
    }

    public String getDsRg() {
        return dsRg;
    }

    public void setDsRg(String dsRg) {
        this.dsRg = dsRg;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
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
        return "Usuario{" +
                "dsNome='" + dsNome + '\'' +
                '}';
    }
}
