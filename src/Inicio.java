
import controllers.ProductController;
import models.Product;
import view.ProductView;

import javax.swing.*;
import java.util.List;

public class Inicio {

    public static void main(String[] args) {

        // Crear y mostrar el panel (vista)

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Interfaz de productos");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ProductView());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

    }
}