package Model.search;

public class Paginator {

    private final int limit;
    private final int lastId, firstId;

    public Paginator(int page, int itemsPerPage){
        this.limit = itemsPerPage;
        this.firstId = itemsPerPage * (page - 1);
        this.lastId = itemsPerPage * page;
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
