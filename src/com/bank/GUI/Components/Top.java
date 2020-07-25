package com.bank.GUI.Components;

import Classes.Currency;
import Classes.User;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.bank.GUI.Components.JsonReader.readJsonFromUrl;

public class Top extends JPanel {
    User currUser;
    JPanel smallLogo = new LogoSmall();
    JPanel time = new JPanel();
    JPanel userPanel = new JPanel();
    JPanel userContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel currLabel = new JLabel("12:30");
    JLabel loggedUserLabel = new JLabel("משתמש נוכחי : ", SwingConstants.RIGHT);
    JLabel loggedUsername = new JLabel("samer ");
    JLabel timeLoginLabel = new JLabel("שעת כניסה :", SwingConstants.RIGHT);
    JLabel timeLogin = new JLabel("12:12 ");
    JLabel permissionLabel = new JLabel("הרשאה : ", SwingConstants.RIGHT);
    JLabel permission = new JLabel("משתמש מערכת ");
    GridBagConstraints gbc = new GridBagConstraints();
    ArrayList<Currency> currencies = new ArrayList<Currency>();
    ArrayList<Currency> currenciesFiltered = new ArrayList<Currency>();


    public Top(User currUser) throws IOException {
        JSONObject json = readJsonFromUrl("https://api.exchangeratesapi.io/latest?base=ILS");
        System.out.println(json.toString());
        System.out.println(json.get("rates"));
        json.keySet().forEach(keyStr ->
        {
            Object keyvalue = json.get(keyStr);
            System.out.println("key: " + keyStr + " value: " + keyvalue);

            //for nested objects iteration if required
            if (keyvalue instanceof JSONObject)
                printJsonObject((JSONObject) keyvalue);
        });
        this.currUser = currUser;
        this.loggedUsername.setText(currUser.getUserName());
        this.permission.setText(this.currUser.getPermission().toString());
        currencies.forEach(c -> {
            if (c.getCountry().equals("USD") ||
                    c.getCountry().equals("JPY") ||
                    c.getCountry().equals("GBP") ||
                    c.getCountry().equals("EUR")) {
                currenciesFiltered.add(c);
            }
        });
        System.out.println("CURRENTCIRS " + createCurrLabel(currenciesFiltered));
        currLabel.setText(createCurrLabel(currenciesFiltered));
        userPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        userPanel.add(loggedUsername, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        userPanel.add(loggedUserLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        userPanel.add(timeLogin, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        userPanel.add(timeLoginLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        userPanel.add(permission, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        userPanel.add(permissionLabel, gbc);
        userContainer.add(userPanel);
        time.add(currLabel);
        this.setLayout(new GridLayout(1, 3));
        this.add(smallLogo);
        this.add(time);
        this.add(userContainer);

        this.setPreferredSize(new Dimension(this.getWidth(), 100));

    }

    public void printJsonObject(JSONObject jsonObj) {
        jsonObj.keySet().forEach(keyStr ->
        {
            Object keyvalue = jsonObj.get(keyStr);
//            System.out.println("key: " + keyStr + " value: " + keyvalue);
            this.currencies.add(new Currency(keyStr, keyvalue.toString()));

            //for nested objects iteration if required
            //if (keyvalue instanceof JSONObject)
            //    printJsonObject((JSONObject)keyvalue);
        });
    }

    public String createCurrLabel(ArrayList<Currency> arr) {
        String[] curStr = {""};
        curStr[0]+="<html><b><center>ערך מטבע עדכני</b></center><p style='text-align:right; color:blue'>";
        arr.forEach(c -> {
            curStr[0] += c.getCurrency().substring(0,4) + " : " + c.getCountry() + " <br>";
        });
        curStr[0]+="</html>";
        return curStr[0];
    }
}
