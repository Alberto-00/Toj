package Model.search;

public class Paginator {

    private final int limit;
    private int lastId, firstId;

    public Paginator(int page, int itemsPerPage){
        this.limit = itemsPerPage;
        this.firstId = 18*(page-1);
        this.lastId = 18*page;
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
