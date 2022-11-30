package com.enigma.repository;

import com.enigma.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepoImpl implements CustomerRepo {
    private final Connection connection;

    public CustomerRepoImpl(Connection connection) {
        this.connection = connection;
    }

    public void addCustomer(Customer customer) throws SQLException {

        // Menyiapkan sql statement
        String sql = "INSERT INTO mst_customer(name, address, birth_date, status, phone) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        setCustomer(customer, statement);

        // Eksekusi update statement ke database
        // digunakan untuk memanipulasi database (tambah, update dan delete)
        statement.executeUpdate();

        // Menutup statement setelah melakukan eksekusi queri
        statement.close();

        System.out.println("Data " + customer.getName() + " masuk");
    }

    public Customer getById(Integer id) throws SQLException {
        Customer customer = null;

        String sql = "SELECT * FROM mst_customer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        // Eksekusi query statement
        ResultSet resultSet = statement.executeQuery();

        // Perlu menjalankan result.next method setiap kali melakukan eksekusi query
        // agar data dapat diambil dari pointernya
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

        return customer;
    }

    public List<Customer> getAll(Integer page, Integer pageSize) throws SQLException {
        List<Customer> customerList = null;

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

        return customerList;
    }

    public void updateById(Customer customer) throws SQLException {
        String sql = "UPDATE mst_customer SET name = ?, address = ?, birth_date = ?, status = ?, phone = ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        setCustomer(customer, statement);
        statement.setInt(6, customer.getId());

        statement.executeUpdate();
        statement.close();

        System.out.println("Id " + customer.getId() + " berhasil di update");
    }

    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM mst_customer WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();

        statement.close();
        System.out.println("Id " + id + " berhasil dihapus");
    }

    public void addBulkCustomer(List<Customer> customers) throws SQLException {
        String sql = "INSERT INTO mst_customer(name, address, birth_date, status, phone) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        for (Customer c: customers) {
            setCustomer(c, statement);
            // Menambah individual statement ke dalam batch
            statement.addBatch();
        }

        // Eksekusi batch, untuk memulai eksekusi dari kumpulan data secara bersamaan / 1 kali eksekusi
        statement.executeBatch();
        statement.close();

        System.out.println(customers.size() + " data telah ditambahkan");
    }

    // Untuk menentukan value pada index statement
    // simbol ? mewakili value yang belum ditentukan (lihat di method yang memanggil setCustomer method)
    // (?, ?) berarti ada 2 index: simbol ? sebelah kiri adalah index 1, sedangkan kanan adalah index 2
    private void setCustomer(Customer customer, PreparedStatement statement) throws SQLException {
        statement.setString(1, customer.getAddress());
        statement.setString(2, customer.getAddress());
        statement.setDate(3, Date.valueOf(customer.getBirthDate()));
        statement.setString(4, customer.getStatus());
        statement.setString(5, customer.getPhone());
    }
}
