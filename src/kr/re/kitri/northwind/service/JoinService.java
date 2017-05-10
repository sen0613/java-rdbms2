package kr.re.kitri.northwind.service;

import kr.re.kitri.northwind.model.Join;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danawacomputer on 2017-05-10.
 */
public class JoinService {
    public static List<Join> makeList(String url, String name, String password) {
    String QUERY =
            "select a.contactname, a.city, a.country, b.orderdate, b.shipcountry\n" +
                    "from customers a, orders b\n" +
                    "where a.customerid = b.customerid";

    List<Join> list = null;
     try {
        Connection conn =
                DriverManager.getConnection(url, name, password);

        PreparedStatement ps = conn.prepareStatement(QUERY);
        ResultSet rs = ps.executeQuery();

        list = new ArrayList();

        while (rs.next()) {
            Join join = new Join(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getString(5));


            list.add(join);
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

        return list;
}

}

