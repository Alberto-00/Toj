package Model.Articolo;

import java.util.List;

public class ArticoloManager implements ArticoloDAO{

    @Override
    public List<Articolo> fetchArticolo(String attribute) throws Exception {
        return null;
    }

    @Override
    public List<Articolo> fetchArticolo(int id_categoria) throws Exception {
        return null;
    }

    @Override
    public List<Articolo> fetchArticolo(String categoria, String sesso) throws Exception {
        return null;
    }

    @Override
    public List<Articolo> fetchArticolo(String taglia, String sesso, String categoria) throws Exception {
        return null;
    }

    @Override
    public Articolo fetchArticolo(String... field) throws Exception {
        return null;
    }

    @Override
    public boolean createArticolo(Articolo articolo) throws Exception {
        return false;
    }

    @Override
    public boolean updateArticolo(Articolo articolo) throws Exception {
        return false;
    }

    @Override
    public boolean deleteArticolo(Articolo articolo) throws Exception {
        return false;
    }
}
