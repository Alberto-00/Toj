package Model.Sconto;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLScontoDAO implements ScontoDAO<SQLException>{

    @Override
    public List<Sconto> doRetrieveAll() throws SQLException {
        try(Connection con = ConPool.getConnection() ){
            QueryBuilder queryBuilder = new QueryBuilder("cod_sconto", "s");
            queryBuilder.select("*");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())){
                ResultSet rs = ps.executeQuery();
                List<Sconto> sconti = new ArrayList<>();
                ScontoExtractor scontoExtractor = new ScontoExtractor();

                while (rs.next()){
                    sconti.add(scontoExtractor.extract(rs));
                }
                return sconti;
            }
        }
    }

    @Override
    public boolean findSconto(String id) throws SQLException {
        try(Connection con = ConPool.getConnection() ){
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as count " +
                    "FROM applicato WHERE codice='" + id + "';")){
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                    return rs.getInt("count") > 0;
                    return false;
            }
        }
    }

    @Override
    public Sconto doRetrieveByName(String id) throws SQLException {
        try(Connection con = ConPool.getConnection() ){
            QueryBuilder queryBuilder = new QueryBuilder("cod_sconto", "s");
            queryBuilder.select("*").where("s.codice = '" + id + "'");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())){
                ResultSet rs = ps.executeQuery();
                ScontoExtractor scontoExtractor = new ScontoExtractor();
                if(rs.next()){
                   return scontoExtractor.extract(rs);
                }
            }
        }
        return null;
    }
}
