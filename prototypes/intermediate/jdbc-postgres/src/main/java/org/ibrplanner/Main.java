package org.ibrplanner;

import org.ibrplanner.db.DB;

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
            System.out.println("Id: " + rs.getLong("Id") + ", " + "Name: " +
                    rs.getString("Name") + ", " + "Price: " + rs.getDouble("Price") + ", " +
                    "Image Url: " + rs.getString("Image_uri"));
        }
    }
}