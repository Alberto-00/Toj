package Model.Articolo;

import Model.search.Condition;
import Model.storage.QueryBuilder;

import java.util.List;

final class ArticoloQuery {

    private static final String PRODUCT_TABLE = "articolo";
    private static final String PRODUCT_ALIAS = "a";

    static String search (List<Condition> conditionList, String sex){
        QueryBuilder builder = new QueryBuilder(PRODUCT_TABLE, PRODUCT_ALIAS);
        builder.select("*, s.Quantita, ta.id_nome, c.nome_colore, c.cod_esadecimale, p.pathName, c2.nome_categoria").
                innerJoin("size", "s").on("s.ID_articolo = a.ID_articolo").
                innerJoin("categoria", "c2").on("a.ID_categoria = c2.ID_categoria").
                innerJoin("taglia", "ta").on("s.id_nome = ta.id_nome").
                innerJoin("tinta", "t").on("a.ID_articolo = t.ID_articolo").
                innerJoin("colore", "c").on("c.cod_esadecimale = t.cod_esadecimale").
                innerJoin("pathimg", "p").on("a.ID_articolo = p.ID_articolo");
        if (!conditionList.isEmpty()){
            builder.where("a.Sesso = '" + sex + "' AND ").search(conditionList);
        }
        return builder.generateQuery();
    }
}
