package com.bank.GUI.Components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoSmall extends JPanel {
    private BufferedImage image;

    public LogoSmall() {

        try {
            image = ImageIO.read(new File("src\\com\\bank\\GUI\\Assets\\leumi_small.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 180, 50, null);

    }
}
