# JDBC Fundamental

Before run the application, you must have enigmat database and mst_customer table.
You can follow instruction in below:

First, Run script below to create database and table

```
CREATE DATABASE enigmat;
CREATE TABLE mst_customer(id SERIAL, name VARCHAR, address VARCHAR, birth_date DATE, status VARCHAR, phone VARCHAR);
```

Second, you must change db user and db password be match with your db auth information.
I use postgres driver with user `postgress` and password `indonesia`.

Third, you are ready to start application.
