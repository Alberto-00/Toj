package Model.Carta_Elettronica;

import Model.Utente.Utente;
import Model.Utente.UtenteExtractor;
import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLCartaElettronicaDAO implements CartaElettronicaDAO<SQLException> {

    public SQLCartaElettronicaDAO(){
        super();
    }

    @Override
    public List<CartaElettronica> fetchCartaElettronica(CartaElettronica payCard) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("carta_elettronica", "card");
            String query = queryBuilder.select().innerJoin("account_user", "ac")
                    .on("card.Email = ac.Email").where("ac.Email=?").generateQuery();
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, payCard.getUser().getEmail());
                ResultSet rs = ps.executeQuery();
                CartaElettronicaExtractor cartaElettronicaExtractor = new CartaElettronicaExtractor();
                List<CartaElettronica> payCards = new ArrayList<>();
                if(rs.next()){
                    UtenteExtractor utenteExtractor = new UtenteExtractor();
                    payCards.get(0).setUser(new Utente());;
                    payCards.get(0).setUser(utenteExtractor.extract(rs));
                }
                while (rs.next()){
                    payCards.add(cartaElettronicaExtractor.extract(rs));
                }
                return payCards;
            }
        }
    }

    @Override
    public boolean createCartaElettronica(CartaElettronica payCard) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("carta_elettronica", "card");
            queryBuilder.insert("Codice_carta", "Descrizione", "Email");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, payCard.getIDcarta());
                ps.setString(2, payCard.getDescrizione());
                ps.setString(3, payCard.getUser().getEmail());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateCartaElettronica(CartaElettronica payCard) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("carta_elettronica", "card");
            queryBuilder.update("Codice_carta", "Descrizione").where("card.Email=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, payCard.getIDcarta());
                ps.setString(2, payCard.getDescrizione());
                ps.setString(3, payCard.getUser().getEmail());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean deleteCartaElettronica(CartaElettronica payCard) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("carta_elettronica", "card");
            queryBuilder.delete().where("card.Email=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, payCard.getUser().getEmail());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
