package com.qrReader;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static com.qrReader.QrCode.createQR;
import static com.qrReader.QrCode.readQR;

public class MainWindow extends JFrame implements ActionListener {

    Vector<JButton> buttons = new Vector<>();
    String[] strings = new String[]{"Leggi QR", "Crea QR"};
    JFileChooser choose = new JFileChooser();

    public MainWindow(){

        super("QR Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        for (int i = 0; i < strings.length; i++){
            buttons.add(new JButton(strings[i]));
            buttons.get(i).addActionListener(this);
            buttons.get(i).setActionCommand(strings[i]);
            add(buttons.get(i));
        }

        setLayout(new FlowLayout());
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Leggi QR" -> {
                try {
                    int result = choose.showOpenDialog(this);
                    if(result == JFileChooser.APPROVE_OPTION){
                        Desktop.getDesktop().browse(new URI(readQR(choose.getSelectedFile().toString(), "UTF-8")));
                    }
                } catch (IOException | NotFoundException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
            case "Crea QR" -> {
                try {
                    String data = JOptionPane.showInputDialog(null);
                    int result = choose.showSaveDialog(this);
                    if(result == JFileChooser.APPROVE_OPTION){
                        createQR(data, choose.getSelectedFile().toString(), "UTF-8", 200, 200);
                    }
                } catch (WriterException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

