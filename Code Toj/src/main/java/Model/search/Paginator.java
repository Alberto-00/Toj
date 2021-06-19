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
        this.lastId=0;
        SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();

        List<Integer> ids = sqlArticoloDAO.getIds(sex);

        for(int i=(18*(page-1)); i<(page*18); i++) {

            this.lastId = ids.get(i);
            System.out.println(lastId);

            if(ids.get(i+1)==null){
                break;
            }
        }

        this.firstId = ids.get(18*(page-1));
        System.out.println(lastId);
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
