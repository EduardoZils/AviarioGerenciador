package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Vacina extends SugarRecord {
    @Unique
    int cd_vacina;
    Lote lote;
    String ds_nome;
    String ds_forma_uso;
    Date dt_uso;
    String ds_prescricao;
    Double qt_usada;
    Date dt_registro;
    Date dt_atualizacao;

    public Vacina(int cd_vacina, Lote lote, String ds_nome, String ds_forma_uso, Date dt_uso, String ds_prescricao, Double qt_usada, Date dt_registro, Date dt_atualizacao) {
        this.cd_vacina = cd_vacina;
        this.lote = lote;
        this.ds_nome = ds_nome;
        this.ds_forma_uso = ds_forma_uso;
        this.dt_uso = dt_uso;
        this.ds_prescricao = ds_prescricao;
        this.qt_usada = qt_usada;
        this.dt_registro = dt_registro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_vacina() {
        return cd_vacina;
    }

    public void setCd_vacina(int cd_vacina) {
        this.cd_vacina = cd_vacina;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public String getDs_nome() {
        return ds_nome;
    }

    public void setDs_nome(String ds_nome) {
        this.ds_nome = ds_nome;
    }

    public String getDs_forma_uso() {
        return ds_forma_uso;
    }

    public void setDs_forma_uso(String ds_forma_uso) {
        this.ds_forma_uso = ds_forma_uso;
    }

    public Date getDt_uso() {
        return dt_uso;
    }

    public void setDt_uso(Date dt_uso) {
        this.dt_uso = dt_uso;
    }

    public String getDs_prescricao() {
        return ds_prescricao;
    }

    public void setDs_prescricao(String ds_prescricao) {
        this.ds_prescricao = ds_prescricao;
    }

    public Double getQt_usada() {
        return qt_usada;
    }

    public void setQt_usada(Double qt_usada) {
        this.qt_usada = qt_usada;
    }

    public Date getDt_registro() {
        return dt_registro;
    }

    public void setDt_registro(Date dt_registro) {
        this.dt_registro = dt_registro;
    }

    public Date getDt_atualizacao() {
        return dt_atualizacao;
    }

    public void setDt_atualizacao(Date dt_atualizacao) {
        this.dt_atualizacao = dt_atualizacao;
    }

    @Override
    public String toString() {
        return "Vacina{" +
                "ds_nome='" + ds_nome + '\'' +
                ", ds_forma_uso='" + ds_forma_uso + '\'' +
                '}';
    }
}
