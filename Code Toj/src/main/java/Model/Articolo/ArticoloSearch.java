package Model.Articolo;

import Controller.http.SearchBuilder;
import Model.search.Condition;
import Model.search.Operator;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ArticoloSearch implements SearchBuilder {

    @Override
    public List<Condition> buildSearch(HttpServletRequest request) {
        List<Condition> conditions = new ArrayList<>();
        String sex = request.getParameter("Sesso");
        String price = request.getParameter("Prezzo");
        String[] category = request.getParameterValues("nome_categoria");
        String[] colori = request.getParameterValues("nome_colore");
        String[] taglia = request.getParameterValues("id_nome");
        String nomeArticolo = request.getParameter("nome_articolo");

        if (sex != null)
            conditions.add(new Condition("Sesso", Operator.EQ, sex));

        if (price != null)
            conditions.add(new Condition("Prezzo", Operator.LE, price));

        if (nomeArticolo != null)
            conditions.add(new Condition("nome_articolo", Operator.MATCH, nomeArticolo));

        if (category != null){
            for (String s : category) {
                conditions.add(new Condition("nome_categoria", Operator.EQ, s));
            }
        } if (colori != null){
            for (String s : colori) {
                conditions.add(new Condition("nome_colore", Operator.EQ, s));
            }
        } if (taglia != null){
            for (String s : taglia) {
                conditions.add(new Condition("id_nome", Operator.EQ, s));
            }
        }
        return conditions;
    }
}
