package Model.Articolo;

import Controller.http.SearchBuilder;
import Model.search.Condition;
import Model.search.Operator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ArticoloSearch implements SearchBuilder {

    @Override
    public List<Condition> buildSearch(HttpServletRequest request) {
        List<Condition> conditions = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String param = parameterNames.nextElement();
            String value = request.getParameter(param);
            if (value != null && !value.isBlank()){
                switch (param){
                    case "price":
                        conditions.add(new Condition("Prezzo", Operator.LE, value));
                        break;
                    case "catgoryId":
                        conditions.add(new Condition("nome_categoria", Operator.EQ, value));
                        break;
                    case "tagliaId":
                        conditions.add(new Condition("id_nome", Operator.EQ, value));
                        break;
                    case "coloreId":
                        conditions.add(new Condition("nome_colore", Operator.EQ, value));
                        break;
                    default:
                        break;
                }
            }
        }
        return conditions;
    }
}
