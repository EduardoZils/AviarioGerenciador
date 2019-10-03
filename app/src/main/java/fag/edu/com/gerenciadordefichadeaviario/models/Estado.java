package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Estado extends SugarRecord {
    @Unique
    int cd_estado;
    Pais pais;
    String ds_nome;
    String ds_sigla;
    Date dt_cadastro;
    Date dt_atualizacao;
}
