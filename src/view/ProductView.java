package view;

import controllers.ProductController;
import models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProductView extends JPanel{
    private ProductController productController;
    private JButton consultarButton;
    private JButton ingresarButton;
    private JButton actualizarButton;
    private JButton borrarButton;
    private JTextArea productListArea;
    private JTextField nombreField;
    private JTextField precioField;
    private JTextField inventarioField;
    private JTextField idField;

    public ProductView() {
        productController = new ProductController();
        setLayout(new BorderLayout());

        // Panel izquierdo con botón de consultar y lista de productos
        JPanel leftPanel = new JPanel(new BorderLayout());
        consultarButton = new JButton("Consultar");
        productListArea = new JTextArea(10, 20);
        leftPanel.add(consultarButton, BorderLayout.SOUTH);
        leftPanel.add(new JScrollPane(productListArea), BorderLayout.CENTER);

        // Panel derecho con botón de ingresar, campos de datos y botones de actualizar y borrar
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

// Botones de ingresar, actualizar y borrar
        ingresarButton = new JButton("Ingresar");
        actualizarButton = new JButton("Actualizar");
        borrarButton = new JButton("Borrar");

// Campos de datos
        nombreField = new JTextField(20);
        precioField = new JTextField(20);
        inventarioField = new JTextField(20);
        idField = new JTextField(20);

// Paneles para organizar los componentes
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(precioField);
        inputPanel.add(new JLabel("Inventario:"));
        inputPanel.add(inventarioField);

        inputPanel.add(new JLabel("Id producto a actualizar o borrar:"));
        inputPanel.add(idField);


// Agregar los componentes al panel derecho
        rightPanel.add(inputPanel);
        rightPanel.add(ingresarButton);
        rightPanel.add(actualizarButton);
        rightPanel.add(borrarButton);


        // Dividir la ventana en dos partes con JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.5); // Porcentaje de espacio para cada panel
        add(splitPane, BorderLayout.CENTER);

        consultarButton.addActionListener(new ConsultarButtonListener());
        ingresarButton.addActionListener(new IngresarButtonListener());
        actualizarButton.addActionListener(new ActualizarButtonListener());
        borrarButton.addActionListener(new BorrarButtonListener());

    }

    private class ConsultarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productListArea.setText(""); // Limpiar el área de texto antes de mostrar los nuevos productos
            List productList = productController.listar();
            for (Object product : productList) {
                productListArea.append(product.toString() + "\n");
            }
        }
    }

    private class IngresarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
            String nombre = nombreField.getText();
                double precio = Double.parseDouble(precioField.getText());
                int inventario = Integer.parseInt(inventarioField.getText());

                nombreField.setText(""); // Limpiar el área de texto antes de mostrar los nuevos productos
                precioField.setText(""); // Limpiar el área de texto antes de mostrar los nuevos productos
                inventarioField.setText(""); // Limpiar el área de texto antes de mostrar los nuevos productos
                Product product = new Product(nombre,precio,inventario);
                productController.ingresar(product);

            } catch (NumberFormatException ex) {
                // Manejar error si no se pueden convertir los datos a números
                JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos para precio e inventario", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Manejar error si no se pueden convertir los datos a string
                JOptionPane.showMessageDialog(null, "Ingrese un texto valido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ActualizarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                // Obtener los datos ingresados por el usuario
                String nombre = nombreField.getText();
                double precio = Double.parseDouble(precioField.getText());
                int inventario = Integer.parseInt(inventarioField.getText());
                int idProducto = Integer.parseInt(idField.getText());

                // Llamar al método para actualizar el producto en la base de datos
                boolean actualizado = productController.actualizar(idProducto, nombre, precio, inventario);

                if (actualizado) {
                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Producto actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Mostrar mensaje de error si no se pudo actualizar el producto
                    JOptionPane.showMessageDialog(null, "Error al actualizar el producto", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Limpiar los campos de texto después de actualizar
                nombreField.setText("");
                precioField.setText("");
                inventarioField.setText("");
                idField.setText("");



            } catch (NumberFormatException ex) {
                // Manejar error si no se pueden convertir los datos a números
                JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos para precio e inventario", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Manejar error si no se pueden convertir los datos a string
                JOptionPane.showMessageDialog(null, "Ingrese un texto valido", "Error", JOptionPane.ERROR_MESSAGE);
            }



        }
    }

    private class BorrarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int idProducto = Integer.parseInt(idField.getText());
                idField.setText(""); // Limpiar el área de texto antes de mostrar los nuevos productos


                boolean eliminado = productController.eliminar(idProducto);

                // Verificar si se eliminó el producto correctamente
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                // Manejar error si no se pueden convertir los datos a números
                JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos para un Id", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
