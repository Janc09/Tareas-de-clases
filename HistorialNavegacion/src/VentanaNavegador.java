import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNavegador extends JFrame {

    private HistorialNavegacion navegador;
    private JTextField campoURL;
    private JTextArea areaHistorial;
    private JButton btnVisitar, btnAtras, btnAdelante, btnFavorito, btnVerFavoritos, btnGuardarHistorial;
    private JLabel lblURL, lblPaginaActual;

    public VentanaNavegador() {
        System.out.println("Iniciando VentanaNavegador...");
        
        // Configuración de la ventana
        setTitle("Lab3");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        navegador = new HistorialNavegacion();
        System.out.println("HistorialNavegacion creado correctamente");

        // Crear componentes de la interfaz
        crearComponentes();
        configurarEventos();
        actualizarHistorial();
        
        System.out.println("Ventana configurada correctamente");
    }
    
    private void crearComponentes() {
        // Etiqueta para URL
        lblURL = new JLabel("Sitio:");
        lblURL.setBounds(20, 20, 40, 25);
        add(lblURL);
        
        // Campo de texto para URL
        campoURL = new JTextField();
        campoURL.setBounds(60, 20, 400, 30);
        campoURL.setFont(new Font("Arial", Font.PLAIN, 12));
        add(campoURL);

        // Botón visitar
        btnVisitar = new JButton("Visitar");
        btnVisitar.setBounds(470, 20, 100, 30);
        btnVisitar.setBackground(new Color(0, 123, 255));
        btnVisitar.setForeground(Color.WHITE);
        add(btnVisitar);

        // Botones de navegación
        btnAtras = new JButton("← Atrás");
        btnAtras.setBounds(20, 60, 100, 35);
        btnAtras.setBackground(new Color(108, 117, 125));
        btnAtras.setForeground(Color.WHITE);
        add(btnAtras);

        btnAdelante = new JButton("Adelante →");
        btnAdelante.setBounds(130, 60, 120, 35);
        btnAdelante.setBackground(new Color(108, 117, 125));
        btnAdelante.setForeground(Color.WHITE);
        add(btnAdelante);

        // Botón favoritos
        btnFavorito = new JButton("★ Agregar a Favoritos");
        btnFavorito.setBounds(260, 60, 180, 35);
        btnFavorito.setBackground(new Color(255, 193, 7));
        btnFavorito.setForeground(Color.BLACK);
        add(btnFavorito);

        btnVerFavoritos = new JButton("Ver Favoritos");
        btnVerFavoritos.setBounds(450, 60, 130, 35);
        btnVerFavoritos.setBackground(new Color(40, 167, 69));
        btnVerFavoritos.setForeground(Color.WHITE);
        add(btnVerFavoritos);

        // Etiqueta de página actual
        lblPaginaActual = new JLabel("Página actual: Inicio");
        lblPaginaActual.setBounds(20, 105, 400, 25);
        lblPaginaActual.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblPaginaActual);

        // Área de texto para mostrar historial
        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaHistorial.setBackground(new Color(248, 249, 250));
        JScrollPane scrollPane = new JScrollPane(areaHistorial);
        scrollPane.setBounds(20, 135, 640, 320);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Historial de Navegación"));
        add(scrollPane);

        // Botón guardar historial
        btnGuardarHistorial = new JButton("📄 Guardar Historial en Archivo");
        btnGuardarHistorial.setBounds(220, 470, 250, 35);
        btnGuardarHistorial.setBackground(new Color(220, 53, 69));
        btnGuardarHistorial.setForeground(Color.WHITE);
        add(btnGuardarHistorial);
    }
    
    private void configurarEventos() {
        // Evento para visitar página
        btnVisitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = campoURL.getText().trim();
                if (!url.isEmpty()) {
                    navegador.visitarPagina(url);
                    actualizarHistorial();
                    campoURL.setText("");
                    campoURL.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(VentanaNavegador.this, 
                        "Por favor, ingrese una URL válida", 
                        "URL Vacía", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Evento para retroceder
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navegador.retroceder();
                actualizarHistorial();
            }
        });

        // Evento para avanzar
        btnAdelante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navegador.avanzar();
                actualizarHistorial();
            }
        });

        // Evento para agregar a favoritos
        btnFavorito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navegador.agregarAFavoritos();
                JOptionPane.showMessageDialog(VentanaNavegador.this, 
                    "Página agregada a favoritos: " + navegador.getPaginaActual(),
                    "Favorito Agregado",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Evento para ver favoritos
        btnVerFavoritos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                areaHistorial.setText(navegador.getFavoritosComoTexto());
            }
        });

        // Evento para guardar historial
        btnGuardarHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navegador.imprimirHistorialEnArchivo();
                JOptionPane.showMessageDialog(VentanaNavegador.this, 
                    "Historial guardado exitosamente en 'historial.txt'",
                    "Archivo Guardado",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // Permitir presionar Enter en el campo URL
        campoURL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnVisitar.doClick();
            }
        });
    }

    private void actualizarHistorial() {
        areaHistorial.setText(navegador.getHistorialComoTexto());
        lblPaginaActual.setText("Página actual: " + navegador.getPaginaActual());
    }

    public static void main(String[] args) {
        System.out.println("Iniciando aplicación...");
        
        // SIN UIManager - Funciona en cualquier versión de Java
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaNavegador ventana = new VentanaNavegador();
                    ventana.setVisible(true);
                    System.out.println("Ventana mostrada correctamente");
                } catch (Exception e) {
                    System.out.println("Error al crear/mostrar ventana: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}