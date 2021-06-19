package Model.search;

import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;

import java.sql.SQLException;
import java.util.List;

public class Paginator {

    private final int limit;
    private  int lastId, firstId;

    public Paginator(int page, int itemsPerPage, String sex) throws SQLException {
        this.limit = itemsPerPage;
        SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();

        if(sex.compareTo("M")==0){

            //QUESTA è la funzione che stavo iniziando a impostare, bisogna cercare di capire meglio come funziona Limit
            //this.firstId = sqlArticoloDAO.getFirstId("M", limit);

            this.firstId = itemsPerPage * (page - 1);
            this.lastId = itemsPerPage * page;
        } else{

            //Allora se cambi la funzione getFirstId e la lasci solo con il sex, con il codice che abbiamo visto IERI funziona, ma ovviamnete non ti va bene

            //this.firstId = itemsPerPage * (page - 1) + sqlArticoloDAO.getFirstId("F");
            //this.lastId = itemsPerPage * page + sqlArticoloDAO.getFirstId("F");
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
