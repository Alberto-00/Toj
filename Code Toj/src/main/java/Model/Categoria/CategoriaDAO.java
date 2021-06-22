package Model.Categoria;

import java.util.List;

public interface CategoriaDAO <E extends Exception>{

    List<Categoria> doRetrieveAll() throws E;

    List<Categoria> doRetrieveBySex(String sex) throws E;

    boolean doCreateCategoria(Categoria categoria) throws E;

    boolean doUpdateCategoria(Categoria categoria) throws E;

    boolean doDeleteCategoria(Categoria categoria) throws E;
}
