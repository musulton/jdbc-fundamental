package com.enigma.repository;

import com.enigma.model.Customer;

import java.util.List;

public interface CustomerRepo {
    public void addCustomer(Customer customer);

    public Customer getById(Integer id);

    public List<Customer> getAll(Integer page, Integer pageSize);

    public void updateById(Customer customer);

    public void deleteById(Integer id);
}
