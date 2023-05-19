package org.ibrplanner;

import org.ibrplanner.db.DB;
import org.ibrplanner.domain.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection conn = DB.getConnection();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("select * from tb_product");

        while (rs.next()) {
            Product p = instantiateProduct(rs);
            System.out.println(p.toString());
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
}