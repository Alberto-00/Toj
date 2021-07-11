package Model.Path_immagini;

import Model.Articolo.Articolo;

import java.util.List;

public interface PathImgDAO <E extends Exception>{

    boolean createPathImg (Articolo articolo) throws E;

    boolean findPath (String pathName) throws E;

    int countByID(Articolo articolo) throws E;

    List<PathImg> doRetrieveByID(Articolo articolo) throws E;

    boolean deletePath(String pathName) throws E;
}
