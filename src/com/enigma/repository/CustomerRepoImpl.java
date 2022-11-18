package com.enigma.repository;

import com.enigma.db.Connector;
import com.enigma.model.Customer;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepoImpl implements CustomerRepo {
    private final Connector conn;

    private Connection connection;

    public CustomerRepoImpl(Connector conn) {
        this.conn = conn;
    }

    public void addCustomer(Customer customer) {
        try {
            connection = conn.connect();
            String sql = "INSERT INTO mst_customer(name, address, birth_date, status, phone) VALUES (?, ?, ?, ?, ?)";

            // Menyiapkan query insert ke table
            PreparedStatement statement = connection.prepareStatement(sql);

            // Menentukan nilai yang akan di masukan ke kolom di table
            // parameter index dilihat berdasarkan urutan di variable sql
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setDate(3, Date.valueOf(customer.getBirthDate()));
            statement.setString(4, customer.getStatus());
            statement.setString(5, customer.getPhone());

            // Melakukan eksekusi kueri ke database / memasukan data ke database
            statement.executeUpdate();

            // Menutup statement setelah melakukan eksekusi
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Data " + customer.getName() + " masuk");
    }

    public Customer getById(Integer id) {
        Customer customer = null;
        try {
            connection = conn.connect();
            customer = null;

            String sql = "SELECT * FROM mst_customer WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer customerId = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                String customerAddress = resultSet.getString("address");
                String birthDate = resultSet.getString("birth_date");
                String status = resultSet.getString("status");
                String phone = resultSet.getString("phone");

                customer = new Customer(customerId, customerName, customerAddress, birthDate, status, phone);
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return customer;
    }

    public List<Customer> getAll(Integer page, Integer pageSize) {
        List<Customer> customerList = null;
        try {
            connection = conn.connect();

            Integer offset = page > 0 ? (page - 1) * pageSize : 0;

            customerList = new ArrayList<>();

            String sql = "SELECT * FROM mst_customer ORDER BY id LIMIT ? OFFSET ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer customerId = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                String customerAddress = resultSet.getString("address");
                String birthDate = resultSet.getString("birth_date");
                String status = resultSet.getString("status");
                String phone = resultSet.getString("phone");

                customerList.add(new Customer(customerId, customerName, customerAddress, birthDate, status, phone));
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return customerList;
    }

    public void updateById(Customer customer) {
        try {
            connection = conn.connect();

            String sql = "UPDATE mst_customer SET name = ?, address = ?, birth_date = ?, status = ?, phone = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setDate(3, Date.valueOf(customer.getBirthDate()));
            statement.setString(4, customer.getStatus());
            statement.setString(5, customer.getPhone());
            statement.setInt(6, customer.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Id " + customer.getId() + " berhasil di update");
    }

    public void deleteById(Integer id) {
        try {
            Connection connection = conn.connect();
            String sql = "DELETE FROM mst_customer WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();
            System.out.println("Id " + id + " berhasil dihapus");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
