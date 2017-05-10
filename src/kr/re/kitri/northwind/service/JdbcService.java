package kr.re.kitri.northwind.service;

import kr.re.kitri.northwind.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danawacomputer on 2017-05-10.
 */
public class JdbcService {
    public static List<Customer> makeList(String url, String name, String password) {
        //연결
        //쿼리문
        //새로운 리스트에 담기

        String query = "select customerid,\n" +
                "companyname,\n" +
                "contactname,\n" +
                "address,\n" +
                "city,\n" +
                "phone\n" +
                "from customers\n" +
                "order by contactname;";

        List<Customer> list = null;
        try {
            Connection conn =
                    DriverManager.getConnection(url, name, password);

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            list = new ArrayList();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));

                list.add(customer);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
