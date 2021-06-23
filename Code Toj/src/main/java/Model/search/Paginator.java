package Model.search;

import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Paginator {

    private final int limit, firstId;
    private int lastId, count;

    public Paginator(int page, int itemsPerPage, List<Condition> conditions, boolean latest) throws SQLException {
        this.limit = itemsPerPage;
        this.count = 0;
        SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
        List<Integer> ids = new ArrayList<>();

        for (Articolo articolo: sqlArticoloDAO.search(conditions, latest))
            ids.add(articolo.getIDarticolo());

        this.firstId = ids.get(this.limit*(page-1));
        for(int i=(this.limit*(page-1)); i<(page*this.limit) && i<ids.size(); i++) {
            this.lastId = ids.get(i);
            count++;
        }
    }

    public int getCount() {
        return count;
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
