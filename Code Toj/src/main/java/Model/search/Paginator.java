package Model.search;

import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;
import com.mysql.cj.conf.PropertyDefinitions;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Paginator {

    private final int limit;
    private  int lastId, firstId;

    public Paginator(int page, int itemsPerPage, String sex) throws SQLException {
        this.limit = itemsPerPage;
        SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();

        if(sex.compareTo("M")==0){

            List<Integer> ids = sqlArticoloDAO.getIds("M");
            ArrayList<Integer> trial = new ArrayList<>();

            for(int i=(18*(page-1)); i<(page*18) && ids.get(i)!=null; i++) {
                trial.add(ids.get(i));
            }

            this.firstId = trial.get(0);
            this.lastId = trial.get(17);

        } else{

            List<Integer> ids = sqlArticoloDAO.getIds("F");
            ArrayList<Integer> trial = new ArrayList<>();

            for(int i=(18*(page-1)); i<(page*18); i++) {
                trial.add(ids.get(i));
            }

            this.firstId = trial.get(0);
            this.lastId = trial.get(17);
        }

    }

    public int getLastId() {
        return lastId;
    }

    public int getFirstId() {
        return firstId;
    }

    public int getPages(int size) {
        int additionalPage = (size%limit == 0) ? 0:1;
        return (size/limit) + additionalPage;
    }
}
