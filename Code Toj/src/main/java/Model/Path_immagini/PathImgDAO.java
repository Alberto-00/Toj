package Model.Path_immagini;

import Model.Articolo.Articolo;

public interface PathImgDAO <E extends Exception>{

    boolean createPathImg (Articolo articolo) throws E;

}
