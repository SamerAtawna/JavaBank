package com.bank.GUI.Components;

import Classes.Transaction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExportToPDF {


    ExportToPDF() throws IOException {


    }

    public void export() throws IOException {
        ArrayList<Transaction> transactions = State.getInstance().transList;
        if ((transactions == null)) {
            JOptionPane.showMessageDialog(null, "לא נבחר משתמש", "שגיאה", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("transactions length " + transactions.size());
        transactions.forEach(el -> {
            System.out.println(el.toString());
        });
        //Creating PDF document object
        PDDocument document = new PDDocument();
        PDPage my_page = new PDPage();
        document.addPage(my_page);
        PDPage page = document.getPage(0);
        PDImageXObject pdImage = PDImageXObject.createFromFile("src\\com\\bank\\GUI\\Assets\\leumi_small.jpg", document);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setLeading(14.5f);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.COURIER_BOLD, 30);
        contentStream.newLineAtOffset(25, 700);
        contentStream.showText("Account details");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("-------------------------------");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();
        contentStream.setFont(PDType1Font.COURIER, 15);
        contentStream.showText("Action    |    Amount   |   Date      |   Location   ");
        contentStream.newLine();
        contentStream.showText("___________________________________________________");
        contentStream.newLine();
        contentStream.newLine();

        transactions.forEach(el -> {
            try {
                contentStream.showText(createRow(el.getAction(), String.valueOf(el.getAmount()), String.valueOf(el.getDate()), el.getLocation()));
                contentStream.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        contentStream.endText();
        contentStream.drawImage(pdImage, 380, 700);
        contentStream.close();
        document.save("C:/temp/my_doc.pdf");

        //Saving the document


        System.out.println("PDF created");
        File file = new File("C:/temp/my_doc.pdf");

        //first check if Desktop is supported by Platform or not
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) desktop.open(file);

        //let's try to open PDF file
        file = new File("C:/temp/my_doc.pdf");
        if (file.exists()) desktop.open(file);
        //Closing the document
        document.close();

    }

    String createRow(String col1, String col2, String col3, String col4) {
        String[] arr = {col1, col2, col3, col4};
        String newString;
        for (int i = 0; i < 4; i++) {
            int len = arr[i].length();
            String space = "";
            for (int j = 0; j < 10 - len; j++) {
                space += " ";
            }
            space += "|   ";
            arr[i] = arr[i] + space;
        }
        newString = arr[0] + arr[1] + arr[2] + arr[3];
        System.out.println("++ new String " + newString);
        return newString;
    }
}
