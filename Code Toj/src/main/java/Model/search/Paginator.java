package Model.search;

import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Paginator {

    private int limit, firstId, offset;
    private int lastId, count;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Paginator(int page, int itemsPerPage){
        this.limit = itemsPerPage;
        this.offset = (page == 1) ? 0 : (page - 1) * itemsPerPage + 1;
    }

    public Paginator(int page, int itemsPerPage, boolean latest) throws SQLException {
        this.limit = itemsPerPage;
        this.count = 0;
        SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
        List<Integer> ids = new ArrayList<>();

        for (Articolo articolo: sqlArticoloDAO.getArticoli())
            ids.add(articolo.getIDarticolo());

        this.firstId = ids.get(this.limit*(page-1));
        for(int i=(this.limit*(page-1)); i<(page*this.limit) && i<ids.size(); i++) {
            this.lastId = ids.get(i);
            count++;
        }
    }

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
