package Model.Articolo;

import java.time.LocalDate;
import java.util.List;

public interface ArticoloDAO <E extends Exception>{

    List<Articolo> fetchArticoloBySex(String sesso) throws E;

    List<Articolo> fetchArticolo(String sesso, int id_categoria) throws E;

    List<Articolo> fetchArticolo(String sesso, double prezzo) throws E;

    List<Articolo> fetchArticolo(String taglia, String sesso) throws E;

    List<Articolo> fetchArticolo(String sesso, LocalDate date) throws E;

    List<Articolo> fetchArticoloByDesc(String descrizione) throws E;

    boolean createArticolo(Articolo articolo) throws E;

    boolean updateArticolo(Articolo articolo) throws E;

    boolean deleteArticolo(Articolo articolo) throws E;
}
