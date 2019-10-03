package fag.edu.com.gerenciadordefichadeaviario.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

public class Mortalidade extends SugarRecord {
    @Unique
    int cd_mortalidade;
    Lote lote;
    int qt_aves_abatidas;
    int qt_aves_eliminadas;
    Date dt_morte;
    Date dt_cadastro;
    Date dt_atualizacao;
}
