package Model.Path_immagini;

import Model.Articolo.Articolo;

public interface PathImgDAO <E extends Exception>{

    boolean createPathImg (Articolo articolo) throws E;

    boolean findPath (String pathName) throws E;

    String writePath(Articolo articolo) throws E;
}
