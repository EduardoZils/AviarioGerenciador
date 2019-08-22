package fag.edu.com.gerenciadordefichadeaviario.models;

import java.util.Date;

public class Pesos {
    int cd_peso;
    Pesagem pesagem;
    int qt_aves_utilizadas;
    Double vl_pesagem;
    Date dt_cadastro;
    Date dt_atualizacao;

    public Pesos(int cd_peso, Pesagem pesagem, int qt_aves_utilizadas, Double vl_pesagem, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_peso = cd_peso;
        this.pesagem = pesagem;
        this.qt_aves_utilizadas = qt_aves_utilizadas;
        this.vl_pesagem = vl_pesagem;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_peso() {
        return cd_peso;
    }

    public void setCd_peso(int cd_peso) {
        this.cd_peso = cd_peso;
    }

    public Pesagem getPesagem() {
        return pesagem;
    }

    public void setPesagem(Pesagem pesagem) {
        this.pesagem = pesagem;
    }

    public int getQt_aves_utilizadas() {
        return qt_aves_utilizadas;
    }

    public void setQt_aves_utilizadas(int qt_aves_utilizadas) {
        this.qt_aves_utilizadas = qt_aves_utilizadas;
    }

    public Double getVl_pesagem() {
        return vl_pesagem;
    }

    public void setVl_pesagem(Double vl_pesagem) {
        this.vl_pesagem = vl_pesagem;
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
        return "Pesos{" +
                "vl_pesagem=" + vl_pesagem +
                '}';
    }
}
