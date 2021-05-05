package Model.Articolo;

import java.time.LocalDate;
import java.util.List;

public interface ArticoloDAO <E extends Exception>{

    List<Articolo> doRetrieveAll() throws E;

    List<Articolo> doRetrieveBySex(String sesso) throws E;

    List<Articolo> doRetriveBySexAndCat(String sesso, int id_categoria) throws E;

    List<Articolo> doRetrieveBySexAndPrice(String sesso, double prezzoMin, double prezzoMax) throws E;

    List<Articolo> doRetrieveBySexAndSize(String taglia, String sesso) throws E;

    List<Articolo> doRetrieveBySexAndDate(String sesso, LocalDate data) throws E;

    List<Articolo> doRetrieveBySexAndDate(String sesso, String colore) throws E;

    List<Articolo> doRetrieveByDesc(String descrizione) throws E;

    boolean doCreateArticolo(Articolo articolo) throws E;

    boolean doUpdateArticolo(Articolo articolo) throws E;

    boolean doDeleteArticolo(Articolo articolo) throws E;
}
