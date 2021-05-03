package Model;

import javax.sql.DataSource;

public abstract class Manager {

    protected final DataSource source;

    public Manager(DataSource source){
        this.source = source;
    }
}
