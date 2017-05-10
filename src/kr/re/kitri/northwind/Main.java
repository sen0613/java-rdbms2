package kr.re.kitri.northwind;

import kr.re.kitri.northwind.model.Customer;
import kr.re.kitri.northwind.model.Join;
import kr.re.kitri.northwind.service.JdbcService;
import kr.re.kitri.northwind.service.JoinService;
import java.util.List;

/**
 * Created by danawacomputer on 2017-05-10.
 */
public class Main {


    public static final String DRIVER_POSTGRES = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/northwind";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "swsw1225";

    public static void main(String[] args) {

        loadDriver(DRIVER_POSTGRES);

        JdbcService service = new JdbcService();
        List<Customer> list = service.makeList(DB_URL, USERNAME, PASSWORD);

        for (Customer e : list) {

            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n",
                    e.getCustomerid(), e.getCompanyname(), e.getContactname(),
                    e.getAddress(), e.getCity(), e.getPhone());
        }

        System.out.println("***********************************\n");

        JoinService service2 = new JoinService();
        List<Join> list2 = service2.makeList(DB_URL, USERNAME, PASSWORD);

        for (Join e : list2) {
            System.out.printf("%s\t%s\t%s\t%s\t%s\n",
                    e.getContactname(), e.getCity(), e.getCountry(),
                    e.getOrderdate(), e.getShipcountry());
        }
    }

    private static void loadDriver(String driverPostgres) {
        try {
            Class.forName(DRIVER_POSTGRES);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}