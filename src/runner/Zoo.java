package runner;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.Font;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import clases.Celda;
import clases.Zoologico;
import runner.CrearCustodio;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Zoo extends JFrame {

	private JPanel contentPane;
	private JLabel IconCuidador;
	private JPanel Cuidador;
	private JLabel IconVeterinario;
	private JPanel Veterinario;
	private JLabel IconCustodio;
	private JPanel Custodio;
	private JLabel IconServicio;
	private JPanel Servicio;
	private JLabel IconAdministrador;
	private JPanel Administrador;
	private JLabel IconAnimal;
	private JPanel Animales;
	private JLabel IconCelda;
	private JPanel Celdas;
	private JLabel IconEspecie;
	private JPanel Especies;
	private JPanel ZoologicoNacional;
	private JLabel IconZoo;

	private Zoologico zoologico;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zoo frame = new Zoo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Zoo(Zoologico zoologico) {
		this.zoologico = zoologico;
		setTitle("Zoológico");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public Zoologico getZoologico() {
		return zoologico;
	}

	public Zoo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 2210, 1260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane MenuPrincipal = new JTabbedPane(JTabbedPane.TOP);
		MenuPrincipal.setBackground(Color.CYAN);
		MenuPrincipal.setBorder(new LineBorder(new Color(0, 0, 0)));
		MenuPrincipal.setFont(new Font("Arial Black", Font.PLAIN, 30));
		MenuPrincipal.setBounds(0, 0, 2184, 1200);
		contentPane.add(MenuPrincipal);
		
		ZoologicoNacional = new JPanel();
		MenuPrincipal.addTab("Zoologico Nacional", null, ZoologicoNacional, null);
		ZoologicoNacional.setLayout(null);
		
		JButton button = new JButton("OCUPACION DE CELDAS");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteOcupacion dialog = new ReporteOcupacion(Zoologico.getZoo());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		button.setBounds(1197, 79, 419, 47);
		ZoologicoNacional.add(button);
		
		JButton button_1 = new JButton("PORCENTAJE DISPONIBILIDAD");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePercentajeCelda dialog = new ReportePercentajeCelda(Zoologico.getZoo());
				dialog.setVisible(true);
			}
		});
		button_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		button_1.setBounds(1197, 173, 419, 47);
		ZoologicoNacional.add(button_1);
		
		JButton button_2 = new JButton("SALARIO TOTAL DEL ZOO");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteSalario dialog = new ReporteSalario(Zoologico.getZoo());
			    dialog.setVisible(true);
			}
		});
		button_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		button_2.setBounds(1737, 79, 419, 47);
		ZoologicoNacional.add(button_2);
		
		JButton button_3 = new JButton("EVALUAR SUPERPOBLACION");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteSuperpoblacionEspecie dialog = new ReporteSuperpoblacionEspecie (Zoologico.getZoo());
		        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        dialog.setVisible(true);
			}
		});
		button_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		button_3.setBounds(1737, 173, 419, 47);
		ZoologicoNacional.add(button_3);
		
		JLabel LogoZoo = new JLabel("");
		LogoZoo.setBounds(112, 1015, 176, 47);
		ZoologicoNacional.add(LogoZoo);
		
		IconZoo = new JLabel("");
		IconZoo.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/zoologico-de-26-cuba-La-Habana.jpeg")));
		IconZoo.setBounds(227, 220, 0, 0);
		ZoologicoNacional.add(IconZoo);
		
		ZoologicoNacional.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int width = ZoologicoNacional.getWidth();
				int height = ZoologicoNacional.getHeight();
				if (width > 0 && height > 0) {
					ImageIcon originalIcon = new ImageIcon(Zoo.class.getResource("/iimagenes/zoologico-de-26-cuba-La-Habana.jpeg"));
					Image image = originalIcon.getImage();
					Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					IconZoo.setIcon(new ImageIcon(scaledImage));
					IconZoo.setBounds(0, 0, width, height);
				}
			}
		});

		JPanel Trabajadores = new JPanel();
		MenuPrincipal.addTab("Gestionar Trabajadores", null, Trabajadores, null);
		Trabajadores.setLayout(null);

		JTabbedPane MenuTrabajadores = new JTabbedPane(JTabbedPane.TOP);
		MenuTrabajadores.setFont(new Font("Arial Black", Font.PLAIN, 24));
		MenuTrabajadores.setBorder(new LineBorder(new Color(0, 0, 0)));
		MenuTrabajadores.setBackground(new Color(135, 206, 235));
		MenuTrabajadores.setBounds(0, 0, 2184, 1130);
		Trabajadores.add(MenuTrabajadores);

		Cuidador = new JPanel();
		MenuTrabajadores.addTab("Gestionar Cuidadores", null, Cuidador, null);
		Cuidador.setLayout(null);

		JButton btnAgregarCuidadores = new JButton("AGREGAR");
		btnAgregarCuidadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Zoologico controlador = Zoologico.getZoo();
				if (controlador.getTodasLasCeldas().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay celdas creadas", "Atención", JOptionPane.WARNING_MESSAGE);
				} else {
					ArrayList<Celda> celdasDisponibles = new ArrayList<>();
					for (Celda c : controlador.getTodasLasCeldas()) {
						if (c.getCuidadores().size() < 2) {  
							celdasDisponibles.add(c);
						}
					}

					if (celdasDisponibles.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay celdas disponibles para agregar cuidadores", "Atención", JOptionPane.WARNING_MESSAGE);
					} else {
						CrearCuidador dialog = new CrearCuidador(controlador, celdasDisponibles);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}
				}
			}
		});
		btnAgregarCuidadores.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		btnAgregarCuidadores.setBounds(112, 959, 176, 47);
		Cuidador.add(btnAgregarCuidadores);

		JLabel LogoCuidador = new JLabel("");
		LogoCuidador.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoCuidador.setBounds(2049, 959, 128, 120);
		Cuidador.add(LogoCuidador);

		IconCuidador = new JLabel();
		IconCuidador.setBounds(0, 0, 0, 0);
		Cuidador.add(IconCuidador);

		Cuidador.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int width = Cuidador.getWidth();
				int height = Cuidador.getHeight();
				if (width > 0 && height > 0) {
					ImageIcon originalIcon = new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg"));
					Image image = originalIcon.getImage();
					Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					IconCuidador.setIcon(new ImageIcon(scaledImage));
					IconCuidador.setBounds(0, 0, width, height);
				}
			}
		});

		Veterinario = new JPanel();
		MenuTrabajadores.addTab("Gestionar Veterinarios", null, Veterinario, null);
		Veterinario.setLayout(null);

		JButton btnAgregarVeterinario = new JButton("AGREGAR");
		btnAgregarVeterinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Zoologico zoo = Zoologico.getZoo();

					boolean hayCeldasConAnimales = false;
					for (Celda c : zoo.getTodasLasCeldas()) {
						if (c.tieneAnimales()) { 
							hayCeldasConAnimales = true;
							break;
						}
					}

					if (!hayCeldasConAnimales) {
						JOptionPane.showMessageDialog(null,
								"No hay celdas con animales. No se puede crear un veterinario.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return; 
					}
					CrearVeterinario dialog = new CrearVeterinario(zoo);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnAgregarVeterinario.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		btnAgregarVeterinario.setBounds(112, 959, 176, 47);
		Veterinario.add(btnAgregarVeterinario);

		JLabel LogoVeterinario = new JLabel("");
		LogoVeterinario.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoVeterinario.setBounds(2049, 959, 128, 120);
		Veterinario.add(LogoVeterinario);

		IconVeterinario = new JLabel("");
		IconVeterinario.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg")));
		IconVeterinario.setBounds(0, 0, 0, 0);
		Veterinario.add(IconVeterinario);

		Veterinario.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int width = Veterinario.getWidth();
				int height = Veterinario.getHeight();
				if (width > 0 && height > 0) {
					ImageIcon originalIcon = new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg"));
					Image image = originalIcon.getImage();
					Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					IconVeterinario.setIcon(new ImageIcon(scaledImage));
					IconVeterinario.setBounds(0, 0, width, height);
				}
			}
		});

		Administrador = new JPanel();
		MenuTrabajadores.addTab("Gestionar Administradores", null, Administrador, null);
		Administrador.setLayout(null);

		JButton btnAgregarAdministrativos = new JButton("AGREGAR");
		btnAgregarAdministrativos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearAdministrador dialog = new CrearAdministrador(Zoologico.getZoo());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAgregarAdministrativos.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		btnAgregarAdministrativos.setBounds(112, 959, 176, 47);
		Administrador.add(btnAgregarAdministrativos);

		JLabel LogoAdministrativo = new JLabel("");
		LogoAdministrativo.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoAdministrativo.setBounds(2049, 959, 128, 120);
		Administrador.add(LogoAdministrativo);

		IconAdministrador = new JLabel("");
		IconAdministrador.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg")));
		IconAdministrador.setBounds(0, 0, 0, 0);
		Administrador.add(IconAdministrador);

		Administrador.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int width = Administrador.getWidth();
				int height = Administrador.getHeight();
				if (width > 0 && height > 0) {
					ImageIcon originalIcon = new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg"));
					Image image = originalIcon.getImage();
					Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					IconAdministrador.setIcon(new ImageIcon(scaledImage));
					IconAdministrador.setBounds(0, 0, width, height);
				}
			}
		});

		Servicio = new JPanel();
		MenuTrabajadores.addTab("Gestionar Servicio", null, Servicio, null);
		Servicio.setLayout(null);

		JButton btnAgregarTra = new JButton("AGREGAR");
		btnAgregarTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearServicio dialog = new CrearServicio(Zoologico.getZoo());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAgregarTra.setBounds(112, 959, 176, 47);
		Servicio.add(btnAgregarTra);
		btnAgregarTra.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));

		JLabel LogoServicio = new JLabel("");
		LogoServicio.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoServicio.setBounds(2049, 959, 128, 120);
		Servicio.add(LogoServicio);

		IconServicio = new JLabel("");
		IconServicio.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg")));
		IconServicio.setBounds(0, 0, 0, 0);
		Servicio.add(IconServicio);

		Custodio = new JPanel();
		MenuTrabajadores.addTab("Gestionar Custodios", null, Custodio, null);
		Custodio.setLayout(null);

		JButton btnAgregarCustodio_1 = new JButton("AGREGAR");
		btnAgregarCustodio_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearCustodio dialog = new CrearCustodio (Zoologico.getZoo());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnAgregarCustodio_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		btnAgregarCustodio_1.setBounds(112, 959, 176, 47);
		Custodio.add(btnAgregarCustodio_1);

		JLabel LogoCustodio = new JLabel("");
		LogoCustodio.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoCustodio.setBounds(2049, 959, 128, 120);
		Custodio.add(LogoCustodio);

		IconCustodio = new JLabel("");
		IconCustodio.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg")));
		IconCustodio.setBounds(0, 0, 0, 0);
		Custodio.add(IconCustodio);

		Custodio.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int width = Custodio.getWidth();
				int height = Custodio.getHeight();
				if (width > 0 && height > 0) {
					ImageIcon originalIcon = new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg"));
					Image image = originalIcon.getImage();
					Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					IconCustodio.setIcon(new ImageIcon(scaledImage));
					IconCustodio.setBounds(0, 0, width, height);
				}
			}
		});

		Servicio.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int width = Servicio.getWidth();
				int height = Servicio.getHeight();
				if (width > 0 && height > 0) {
					ImageIcon originalIcon = new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg"));
					Image image = originalIcon.getImage();
					Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					IconServicio.setIcon(new ImageIcon(scaledImage));
					IconServicio.setBounds(0, 0, width, height);
				}
			}
		});

		Celdas = new JPanel();
		MenuPrincipal.addTab("Gestionar Celdas", null, Celdas, null);
		Celdas.setLayout(null);

		JButton btnAgregarCelda = new JButton("AGREGAR");
		btnAgregarCelda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearCelda dialog = new CrearCelda(Zoologico.getZoo());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAgregarCelda.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		btnAgregarCelda.setBounds(112, 1015, 176, 47);
		Celdas.add(btnAgregarCelda);

		JLabel LogoCelda = new JLabel("");
		LogoCelda.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoCelda.setBounds(2049, 1014, 128, 120);
		Celdas.add(LogoCelda);

		IconCelda = new JLabel("");
		IconCelda.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Celda2.jpg")));
		IconCelda.setBounds(0, 0, 0, 0);
		Celdas.add(IconCelda);

		Celdas.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int width = Celdas.getWidth();
				int height = Celdas.getHeight();
				if (width > 0 && height > 0) {
					ImageIcon originalIcon = new ImageIcon(Zoo.class.getResource("/iimagenes/Celda2.jpg"));
					Image image = originalIcon.getImage();
					Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					IconCelda.setIcon(new ImageIcon(scaledImage));
					IconCelda.setBounds(0, 0, width, height);
				}
			}
		});

		Especies = new JPanel();
		MenuPrincipal.addTab("Gestionar Especies", null, Especies, null);
		Especies.setLayout(null);

		JButton btnAgregarEspecie = new JButton("AGREGAR");
		btnAgregarEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearEspecie dialog = new CrearEspecie(Zoologico.getZoo());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAgregarEspecie.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		btnAgregarEspecie.setBounds(112, 1015, 176, 47);
		Especies.add(btnAgregarEspecie);

		JLabel LogoEspecie = new JLabel("");
		LogoEspecie.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoEspecie.setBounds(2049, 1014, 128, 120);
		Especies.add(LogoEspecie);

		IconEspecie = new JLabel("");
		IconEspecie.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Especies.jpg")));
		IconEspecie.setBounds(0, 0, 0, 0);
		Especies.add(IconEspecie);

		Especies.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int width = Especies.getWidth();
				int height = Especies.getHeight();
				if (width > 0 && height > 0) {
					ImageIcon originalIcon = new ImageIcon(Zoo.class.getResource("/iimagenes/Especies.jpg"));
					Image image = originalIcon.getImage();
					Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					IconEspecie.setIcon(new ImageIcon(scaledImage));
					IconEspecie.setBounds(0, 0, width, height);
				}
			}
		});

		Animales = new JPanel();
		MenuPrincipal.addTab("Gestionar Animales", null, Animales, null);
		Animales.setLayout(null);

		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Zoologico controlador = Zoologico.getZoo();
				if (controlador.getEspecies().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay especies creadas", "Atención", JOptionPane.WARNING_MESSAGE);
				} else if (controlador.getTodasLasCeldas().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay celdas creadas", "Atención", JOptionPane.WARNING_MESSAGE);
				} else {
					CrearAnimal dialog = new CrearAnimal(controlador);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		btnAgregar.setBounds(112, 1015, 176, 47);
		Animales.add(btnAgregar);
		btnAgregar.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));

		JLabel LogoAnimal = new JLabel("");
		LogoAnimal.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoAnimal.setBounds(2049, 1014, 128, 120);
		Animales.add(LogoAnimal);

		IconAnimal = new JLabel("");
		IconAnimal.setBounds(0, 0, 0, 0);
		IconAnimal.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Animales.jpg")));
		Animales.add(IconAnimal);

		Animales.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int width = Animales.getWidth();
				int height = Animales.getHeight();
				if (width > 0 && height > 0) {
					ImageIcon originalIcon = new ImageIcon(Zoo.class.getResource("/iimagenes/Animales.jpg"));
					Image image = originalIcon.getImage();
					Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					IconAnimal.setIcon(new ImageIcon(scaledImage));
					IconAnimal.setBounds(0, 0, width, height);
				}
			}
		});


		setLocationRelativeTo(null);
	}
}