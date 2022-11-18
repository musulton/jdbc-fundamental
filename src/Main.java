import com.enigma.db.Connector;
import com.enigma.model.Customer;
import com.enigma.repository.CustomerRepoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connect = null;

        try {
            // Membuat connector, untuk mengkoneksikan aplikasi kita ke ke database
            Connector conn = new Connector();
            connect = conn.connect();
            CustomerRepoImpl customerRepoImpl = new CustomerRepoImpl(connect);

            /*
            Insert customer

            Customer mhmdSulton = new Customer(
                     "Muhammad Sulton", "Sukabumi", "1995-09-03", "Co Trainer", "08765432142"
            );
            Customer wiwinWirda = new Customer(
                    "Wiwin Wirdayanti", "Sukabumi", "1972-01-12", "IRT", "082414423512"
            );

            customerRepoImpl.addCustomer(mhmdSulton);
            customerRepoImpl.addCustomer(wiwinWirda);
             */

            Customer mhmdSulton = new Customer(
                    "Maryam", "Sukabumi", "1945-09-03", "IRT", "08765432142"
            );
            Customer wiwinWirda = new Customer(
                    "Otang", "Sukabumi", "1939-01-12", "Wiraswasta", "082414423512"
            );

            List<Customer> customers = Arrays.asList(mhmdSulton, wiwinWirda);
            customerRepoImpl.addBulkCustomer(customers);

           /*
            Update data

            Customer zenJae = new Customer(13, "Zen Jae", "Qatar", "1968-04-25", "Wiraswasta", "08987654321");
            customerRepoImpl.updateById(zenJae);
            */

            /*
            Get All Data

            List<Customer> customers = customerRepoImpl.getAll(1, 3);
            System.out.println(customers);
             */

            /*
            Get Data By Id

            Customer customer = customerRepoImpl.getById(14);
            System.out.println(customer);
             */

            /*
            Delete Data by Id

            customerRepoImpl.deleteById(13);
             */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}