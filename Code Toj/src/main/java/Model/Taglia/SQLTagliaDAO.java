package Model.Taglia;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SQLTagliaDAO implements TagliaDao{
    @Override
    public boolean doCreateTaglia(Taglia taglia) throws Exception {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("taglia", "t");
            queryBuilder.insert("id_nome");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,taglia.getId_nome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doUpdateTaglia(Taglia taglia) throws Exception {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("taglia", "t");
            queryBuilder.update("id_nome").where("t.id_nome = " + taglia.getId_nome());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,taglia.getId_nome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doDeleteTaglia(Taglia taglia) throws Exception {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("taglia", "t");
            queryBuilder.delete().where("t.id_nome = " + taglia.getId_nome());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,taglia.getId_nome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
