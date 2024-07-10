package org.example.test3yashpc2;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public TableView<order> ordertable;
    public TableColumn<order, Integer> orderId;
    public TableColumn<order, String> cName;
    public TableColumn<order, String> mobNo;
    public TableColumn<order, String> size;
    public TableColumn<order, String> topping;
    public TableColumn<order, Double> total;
    public TextField cNam;
    public TextField cmob;
    public TextField csize;
    public TextField ctop;
    public TextField cid;
    public TextField ctotal;


    ObservableList<order> orderList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        cName.setCellValueFactory(new PropertyValueFactory<>("cName"));
        mobNo.setCellValueFactory(new PropertyValueFactory<>("mobNo"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        topping.setCellValueFactory(new PropertyValueFactory<>("topping"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        ordertable.setItems(orderList);
    }

    static double calculateTotal(String size, String topping) {
        double basePrice = 0.0;
        double toppingPrice = 1.50;
        int toppingCount = Integer.parseInt(topping);

        if (size.equals("XL")) {
            basePrice = 15.00;
        } else if (size.equals("L")) {
            basePrice = 12.00;
        } else if (size.equals("M")) {
            basePrice = 10.00;
        } else if (size.equals("S")) {
            basePrice = 8.00;
        }

        double total = basePrice + (toppingCount * toppingPrice);
        total = total + (total * 0.15); // Adding 15% HST
        return total;
    }

    public void onHelloButtonClick() {
        orderList.clear();
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test3yash";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM `order`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                String cName = resultSet.getString("cName");
                String mobNo = resultSet.getString("mobNo");
                String size = resultSet.getString("size");
                String topping = resultSet.getString("topping");
                double total = resultSet.getDouble("total");
                orderList.add(new order(orderId, cName, mobNo, size, topping, total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insert(ActionEvent actionEvent) {
        String cName = cNam.getText();
        String mobNo = cmob.getText();
        String size = csize.getText();
        String topping = ctop.getText();
        double total = calculateTotal(size, topping);

        ctotal.setText(String.valueOf(total));

        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test3yash";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `order`(`cName`, `mobNo`, `size`, `topping`, `total`) VALUES ('" + cName + "','" + mobNo + "','" + size + "','" + topping + "','" + total + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ActionEvent actionEvent) {
        int orderId = Integer.parseInt(cid.getText());
        String cName = cNam.getText();
        String mobNo = cmob.getText();
        String size = csize.getText();
        String topping = ctop.getText();
        double total = calculateTotal(size, topping);

        ctotal.setText(String.valueOf(total));

        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test3yash";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "UPDATE `order` SET `cName`='" + cName + "',`mobNo`='" + mobNo + "',`size`='" + size + "',`total`='" + total + "' WHERE orderId='" + orderId + "' ";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete(ActionEvent actionEvent) {
        int orderId = Integer.parseInt(cid.getText());
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test3yash";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "DELETE FROM `order` WHERE orderId='" + orderId + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loaddata(ActionEvent actionEvent) {
        int orderId = Integer.parseInt(cid.getText());
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test3yash";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM `order` WHERE orderId='" + orderId + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cName = resultSet.getString("cName");
                String mobNo = resultSet.getString("mobNo");
                String size = resultSet.getString("size");
                String topping = resultSet.getString("topping");
                double total = resultSet.getDouble("total");
                cNam.setText(cName);
                cmob.setText(mobNo);
                csize.setText(size);
                ctop.setText(topping);
                ctotal.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    }
