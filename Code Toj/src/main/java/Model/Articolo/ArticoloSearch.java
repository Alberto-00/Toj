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
                    case "Sesso":
                        conditions.add(new Condition("Sesso", Operator.EQ, value));
                        break;
                    case "Prezzo":
                        conditions.add(new Condition("Prezzo", Operator.LE, value));
                        break;
                    case "nome_categoria":
                        conditions.add(new Condition("nome_categoria", Operator.EQ, value));
                        break;
                    case "id_nome":
                        conditions.add(new Condition("id_nome", Operator.EQ, value));
                        break;
                    case "nome_colore":
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
