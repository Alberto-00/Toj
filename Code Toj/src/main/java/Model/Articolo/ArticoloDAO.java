package Model.Articolo;

import java.util.List;

public interface ArticoloDAO <E extends Exception>{

    List<Articolo> fetchArticolo(String attribute) throws E;

    List<Articolo> fetchArticolo(int id_categoria) throws E;

    List<Articolo> fetchArticolo(String categoria, String sesso) throws E;

    List<Articolo> fetchArticolo(String taglia, String sesso, String categoria) throws E;

    Articolo fetchArticolo(String ... field) throws E;

    boolean createArticolo(Articolo articolo) throws E;

    boolean updateArticolo(Articolo articolo) throws E;

    boolean deleteArticolo(Articolo articolo) throws E;
}
