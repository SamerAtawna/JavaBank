package Server.Database;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.User;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Leumi", "root", "");

    public DB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }


    public ArrayList<User> getUsers() throws SQLException {
        ArrayList users = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users");
        while (rs.next()) {
            if (rs.getString("Permission").equals("Admin")) {
                System.out.println("tru1");
                users.add(new User(rs.getString("UserName"), rs.getString("Password"), "0", Enums.Permission.ADMIN));
            }
            if (rs.getString("Permission").equals("User")) {
                System.out.println("tru2");
                users.add(new User(rs.getString("UserName"), rs.getString("Password"), "0", Enums.Permission.USER));
            }

        }
        return users;
    }


    public User authenticate(String userName, String password) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users where UserName='" + userName + "' and password='" + password + "'");
        if (rs.next() == false) {
            return new User("");
        } else {
            if (rs.getString("Permission").equals("Admin")) {
                System.out.println("tru1");
                return new User(rs.getString("UserName"), rs.getString("Password"), "0", Enums.Permission.ADMIN);
            }
            if (rs.getString("Permission").equals("User")) {
                System.out.println("tru2");
                return new User(rs.getString("UserName"), rs.getString("Password"), "0", Enums.Permission.USER);
            }
        }
        return new User("");
    }

    public ArrayList<Client> getClients() throws SQLException {
        ArrayList clients = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from clients");
        while (rs.next()) {
            if (rs.getString("Status").equals("ACTIVE")) {
                clients.add(new Client(rs.getString("FullName"),
                        rs.getInt("AccountNumber"),
                        rs.getInt("ID"),
                        rs.getFloat("Balance"),
                        Enums.Status.ACTIVE,
                        rs.getInt("ClientID"),
                        rs.getInt("CardCode")));

            }
            if (rs.getString("Status").equals("INACTIVE")) {
                clients.add(new Client(rs.getString("FullName"),
                        rs.getInt("AccountNumber"),
                        rs.getInt("ID"),
                        rs.getFloat("Balance"),
                        Enums.Status.INACTIVE,
                        rs.getInt("ClientID"),
                        rs.getInt("CardCode")));

            }
        }
        return clients;
    }

    public void createNewAccount(Client client) throws SQLException {
        String query = " insert into clients (`FullName`, `AccountNumber`, `Balance`, `Status`, `ClientID`, `CardCode`)"
                + " values (?, ?, ?, ?, ?, ?)";


        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, client.getFullName());
        preparedStmt.setInt(2, client.getAccountNumber());
        preparedStmt.setFloat(3, client.getBalance());
        preparedStmt.setString(4, "ACTIVE");
        preparedStmt.setInt(5, client.getClientID());
        preparedStmt.setInt(6, client.getCardCode());
        preparedStmt.execute();

    }

    public void disponse(Integer id, float amount) throws SQLException {
        String query = "update clients set Balance=Balance+" + amount + " where ID=" + id;
        PreparedStatement updateEXP = con.prepareStatement("update clients set Balance=Balance+? where ID=?");
        updateEXP.setFloat(1, amount);
        updateEXP.setInt(2, id);
        updateEXP.executeUpdate();
        System.out.println("Updated");
    }

    public void withdraw(Integer id, float amount) throws SQLException {
        PreparedStatement updateEXP = con.prepareStatement("update clients set Balance=Balance-? where ID=?");
        updateEXP.setFloat(1, amount);
        updateEXP.setInt(2, id);
        updateEXP.executeUpdate();
        System.out.println("Updated2");
    }

}
