/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calc;

/**
 *
 * @author Júlia
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraComGrafico extends JFrame implements ActionListener {

    private JTextField textField;
    private JButton btnGraph;
    private JPanel graphPanel;
    private Timer timer;
    private int x = 0;

    public CalculadoraComGrafico() {
        super("Calculadora com Gráfico");

        // Configurações do JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Configurações do JTextField
        textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField, BorderLayout.NORTH);

        // Configurações do botão de gráfico
        btnGraph = new JButton("Desenhar Gráfico");
        btnGraph.addActionListener(this);
        add(btnGraph, BorderLayout.SOUTH);

        // Configurações do painel de gráfico
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraph(g);
            }
        };
        graphPanel.setPreferredSize(new Dimension(400, 300));
        add(graphPanel, BorderLayout.CENTER);

        // Configurações do Timer
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x++;
                graphPanel.repaint();
            }
        });

        // Exibe a calculadora
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraComGrafico();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGraph) {
            timer.start();
        }
    }

    private void drawGraph(Graphics g) {
        int width = graphPanel.getWidth();
        int height = graphPanel.getHeight();
        int centerY = height / 2;

        g.setColor(Color.BLACK);
        g.drawLine(0, centerY, width, centerY);
        g.drawLine(0, 0, 0, height);

        g.setColor(Color.RED);
        int previousY = centerY;
        int currentY = (int) (centerY + Math.sin(Math.toRadians(x)) * centerY);
        g.drawLine(x - 1, previousY, x, currentY);
    }
}

