package Model.Taglia;


import Model.Categoria.Categoria;

import java.util.List;

public interface TagliaDao <E extends Exception>{

    List<Taglia> doRetrieveBySex(String sex) throws E;

    boolean doCreateTaglia(Taglia taglia) throws E;

    boolean doUpdateTaglia(Taglia taglia) throws E;

    boolean doDeleteTaglia(Taglia taglia) throws E;
}
