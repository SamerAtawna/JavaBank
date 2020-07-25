package Server.Database;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.Transaction;
import Classes.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;

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
        while (rs.next()) { //return row
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
                clients.add(
                        new Client(rs.getString("FullName"),
                                rs.getInt("AccountNumber"),
                                rs.getInt("ID"),
                                rs.getFloat("Balance"),
                                Enums.Status.INACTIVE,
                                rs.getInt("ClientID"),
                                rs.getInt("CardCode"))
                );

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

    public void createNewUser(User user) throws SQLException {
        String query = " insert into users (`UserName`, `Permission`, `Password`)"
                + " values (?, ?, ?)";


        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, user.getUserName());
        if(user.getPermission().equals(Enums.Permission.USER)){
            preparedStmt.setString(2, "User");
        }
        if(user.getPermission().equals(Enums.Permission.ADMIN)){
            preparedStmt.setString(2, "Admin");
        }

        preparedStmt.setString(3, user.getPassword());
        try {
            preparedStmt.execute();
        } catch (SQLException err) {
            System.out.println("Query failed .. " + err.getLocalizedMessage());

        }


    }

    public synchronized void disponse(Integer id, float amount, String location) throws SQLException {
        long ctm = System.currentTimeMillis();
        Date d = new Date(ctm);
        LocalDate localDate = d.toLocalDate();
        Date sqldate = Date.valueOf(localDate);
        String query = "update clients set Balance=Balance+" + amount + " where ID=" + id;
        PreparedStatement updateEXP = con.prepareStatement("update clients set Balance=Balance+? where ID=?");
        updateEXP.setFloat(1, amount);
        updateEXP.setInt(2, id);
        updateEXP.executeUpdate();
        System.out.println("Updated");
        PreparedStatement insertEXP = con.prepareStatement("INSERT INTO `transactions`(`ClientID`, `Action`, `Amount`, `Date`, `Location`) VALUES (?,?,?,?,?)");
        insertEXP.setInt(1, id);
        insertEXP.setString(2, "DISPOSE");
        insertEXP.setFloat(3, amount);
        insertEXP.setDate(4, sqldate);
        insertEXP.setString(5, location);
        insertEXP.executeUpdate();
    }

    public synchronized void withdraw(Integer id, float amount, String location) throws SQLException, InterruptedException {
        long ctm = System.currentTimeMillis();
        Date d = new Date(ctm);
        LocalDate localDate = d.toLocalDate();
        Date sqldate = Date.valueOf(localDate);
        System.out.println("id " + id + "amount: " + amount + "location " + location);
        System.out.println("$$$Withdraw Thread");
        Thread.sleep(2000);
        System.out.println("Done Sleep");
        System.out.println("WIthdraw id" + id + amount);
        PreparedStatement updateEXP = con.prepareStatement("update clients set Balance=Balance-? where ID=?");
        updateEXP.setFloat(1, amount);
        updateEXP.setInt(2, id);
        updateEXP.executeUpdate();
        PreparedStatement insertEXP = con.prepareStatement("INSERT INTO `transactions`(`ClientID`, `Action`, `Amount`, `Date`, `Location`) VALUES (?,?,?,?,?)");
        insertEXP.setInt(1, id);
        insertEXP.setString(2, "WITHDRAW");
        insertEXP.setFloat(3, amount);
        insertEXP.setDate(4, sqldate);
        insertEXP.setString(5, location);
        insertEXP.executeUpdate();
        System.out.println("Updated2");
    }

    public Client atmAuth(int code) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from clients where CardCode=" + code + "");
        if (rs.next() == false) {
            return new Client("");
        } else {

            return new Client(rs.getString("FullName"), rs.getInt("ID"), 0, rs.getFloat("Balance"), Enums.Status.ACTIVE, 0, 0);

        }
    }

    public ArrayList<Transaction> getTransactions(int id) throws SQLException {
        ArrayList trans = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Transactions where ClientID=" + id);
        while (rs.next()) {
            trans.add(new Transaction(rs.getInt("ClientID"),
                    rs.getString("Action"),
                    rs.getFloat("Amount"),
                    rs.getDate("Date"),
                    rs.getString("Location")));


        }
        return trans;
    }

}
