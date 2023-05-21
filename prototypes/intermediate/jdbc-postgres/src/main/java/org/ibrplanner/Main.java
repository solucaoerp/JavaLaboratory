package org.ibrplanner;

import org.ibrplanner.db.DB;
import org.ibrplanner.domain.Order;
import org.ibrplanner.domain.OrderStatus;
import org.ibrplanner.domain.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection conn = DB.getConnection();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("select * from tb_order");

        while (rs.next()) {
            Order order = instantiateOrder(rs);
            System.out.println(order.toString());
        }
    }

    private static Product instantiateProduct(ResultSet rs) throws SQLException {
        Product p = new Product();

        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        p.setDescription(rs.getString("description"));
        p.setImageUri(rs.getString("image_uri"));

        return p;
    }

    private static Order instantiateOrder(ResultSet rs) throws SQLException {
        Order order = new Order();

        order.setId(rs.getLong("id"));
        order.setLatitude(rs.getDouble("latitude"));
        order.setLongitude(rs.getDouble("longitude"));
        order.setMoment(rs.getTimestamp("moment").toInstant());
        order.setStatus(OrderStatus.values()[rs.getInt("status")]);

        return order;
    }
}