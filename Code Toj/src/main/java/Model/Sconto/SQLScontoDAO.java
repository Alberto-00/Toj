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

    @Override
    public boolean doCreateSconto(Sconto sconto) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("cod_sconto", "s");
            queryBuilder.insert("codice","data_scadenza","sconto");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,sconto.getCodice());
                ps.setDate(2, Date.valueOf(sconto.getDataScadenza()));
                ps.setDouble(3,sconto.getSconto());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doUpdateSconto(Sconto sconto) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("cod_sconto", "s");
            queryBuilder.update("codice", "data_scadenza", "sconto").where("codice = "+sconto.getCodice());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, sconto.getCodice());
                ps.setDate(2, Date.valueOf(sconto.getDataScadenza()));
                ps.setDouble(3,sconto.getSconto());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doDeleteSconto(String sconto) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("cod_sconto", "s");
            queryBuilder.delete().where("codice = '" + sconto + "'");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                System.out.println(ps.toString());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
