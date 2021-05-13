package Model.Taglia;



public interface TagliaDao <E extends Exception>{

    boolean doCreateTaglia(Taglia taglia) throws E;

    boolean doUpdateTaglia(Taglia taglia) throws E;

    boolean doDeleteTaglia(Taglia taglia) throws E;
}
