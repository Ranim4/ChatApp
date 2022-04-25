package formationUdemy;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Frame extends JFrame {
    static JTextArea textArea;
    JTextField textField;
    JScrollPane scrollPane;
    String message;

    public Frame(String title, Writer out) {
        super (title);

        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            if ("Nimbus".equals(look.getName())){
                try {
                    UIManager.setLookAndFeel(look.getClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(this);
            }
            System.out.println(look.getName());
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLayout(new BorderLayout());

                textArea = new JTextArea();
                textArea.setEditable(false);
                scrollPane = new JScrollPane(textArea);
                add(scrollPane, BorderLayout.CENTER);

                textField = new JTextField();

                textField.addActionListener(e -> {
                    message = textField.getText();

                    try {
                        out.write(message+"\n");
                        out.flush();
                    } catch (IOException ex) {
                        textArea.append("One of the parties had to disconnect\n");
                    }
                    textField.setText("");
                    textArea.append("Me : " + message +"\n");
                    Toolkit.getDefaultToolkit().beep();
                });
                add(textField, BorderLayout.SOUTH);

                setLocationRelativeTo(null);
                setPreferredSize(new Dimension(400, 400));
                setMaximumSize(new Dimension(400,400));
                setMinimumSize(new Dimension(400,400));
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
            }
        });
    }

    public static void appendMessage(String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                textArea.append("Him : " + message + "\n");
              //  Toolkit.getDefaultToolkit().beep();
            }
        });
    }
}
