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
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import clases.Administrativo;
import clases.Animal;
import clases.Celda;
import clases.Especie;
import clases.Zoologico;
import clases.Cuidador;
import clases.Servicio;
import clases.Veterinario;
import clases.Custodio;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTable;

import reportes.ReporteOcupacion;
import reportes.ReporteSalario;
import reportes.ReporteSuperpoblacionEspecie;
import jdialog.CrearAdministrador;
import jdialog.CrearAnimal;
import jdialog.CrearCelda;
import jdialog.CrearCuidador;
import jdialog.CrearCustodio;
import jdialog.CrearEspecie;
import jdialog.CrearServicio;
import jdialog.CrearVeterinario;
import jdialog.EditarAdministrador;
import jdialog.EditarAnimal;
import jdialog.EditarCelda;
import jdialog.EditarCuidador;
import jdialog.EditarCustodio;
import jdialog.EditarEspecie;
import jdialog.EditarServicio;
import jdialog.EditarVeterinario;
import jdialog.ReportePercentajeCelda;

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
	private JLayeredPane  ZoologicoNacional;
	private JLabel IconZoo;
	private JTextArea resumeArea;
	private Celda[] arrayDeCeldas;
	private JTable table1;	
	private JTable tableAnimales;
	private JTable tableCuidadores;
	private Zoologico zoologico;
	private JTable table;
	
	private DefaultTableModel modeloVet;
	private JTable tablaVet;

	private DefaultTableModel modeloServicio;
	private JTable tablaServicio;

	private JTable tableCustodio;
	private DefaultTableModel modelCustodio;
	

	private JTable tablaAdmin;
	private DefaultTableModel modeloAdmin;

	private ArrayList<Celda> celdasDisponibles;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zoo frame = new Zoo( 	);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Zoo(Zoologico zoologico) {
		this.zoologico = Zoologico.getZoo();
		celdasDisponibles = zoologico.getTodasLasCeldas();

		setTitle("Zoológico");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public Zoologico getZoologico() {
		return zoologico;
	}

	public Zoo() {
		this.zoologico = Zoologico.getZoo();

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

		ZoologicoNacional = new JLayeredPane();
		MenuPrincipal.addTab("Zoologico Nacional", null, ZoologicoNacional, null);
		ZoologicoNacional.setLayout(null);
		ZoologicoNacional.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 2177, 1141);
		panel.setOpaque(false);
		ZoologicoNacional.add(panel, new Integer(1));
		panel.setLayout(null);

		resumeArea = new JTextArea();
		resumeArea.setBackground(new Color(192, 192, 192));
		resumeArea.setFont(new Font("Segoe UI Black", Font.PLAIN, 28));
		resumeArea.setBounds(1543, 0, 634, 363);
		panel.add(resumeArea);
		resumeArea.setEditable(false);

		JButton button = new JButton("OCUPACION DE CELDAS");
		button.setBounds(49, 74, 419, 47);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteOcupacion dialog = new ReporteOcupacion(Zoologico.getZoo());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});

		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));

		JButton button_1 = new JButton("PORCENTAJE DISPONIBILIDAD");
		button_1.setBounds(49, 184, 419, 47);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePercentajeCelda dialog = new ReportePercentajeCelda(Zoologico.getZoo());
				dialog.setVisible(true);
			}
		});
		button_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));

		JButton button_2 = new JButton("SALARIO TOTAL DEL ZOO");
		button_2.setBounds(568, 74, 419, 47);
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteSalario dialog = new ReporteSalario(Zoologico.getZoo());
				dialog.setVisible(true);
			}
		});
		button_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));

		JButton button_3 = new JButton("EVALUAR SUPERPOBLACION");
		button_3.setBounds(568, 184, 419, 47);
		panel.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteSuperpoblacionEspecie dialog = new ReporteSuperpoblacionEspecie (Zoologico.getZoo());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		button_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		actualizarResumen();

		JLabel LogoZoo = new JLabel("");
		LogoZoo.setBounds(112, 1015, 176, 47);
		ZoologicoNacional.add(LogoZoo, new Integer(0));


		IconZoo = new JLabel("");
		IconZoo.setBounds(227, 220, 0, 0);
		IconZoo.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/zoologico-de-26-cuba-La-Habana.jpeg")));
		ZoologicoNacional.add(IconZoo, new Integer(1));



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
						CrearCuidador dialog = new CrearCuidador(controlador, celdasDisponibles, Zoo.this);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}
				}
			}
		});
		btnAgregarCuidadores.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		btnAgregarCuidadores.setBounds(112, 959, 176, 47);
		Cuidador.add(btnAgregarCuidadores);

		String[] columnasCuidadores = {
				"CI", "Nombre", "Horas Trabajadas", "Horario", "Celda Asignada 1", "Celda Asignada 2", "Editar", "Eliminar"
		};
		ArrayList<Cuidador> cuidadores = zoologico.getCuidadores();
		Object[][] dataCuidadores = new Object[cuidadores.size()][8];

		for (int i = 0; i < cuidadores.size(); i++) {
			Cuidador c = cuidadores.get(i);

			String horario1 = c.getCeldaAsignada1() != null
				? "Celda 1: " + c.getHoraInicio1() + ":00 - " + c.getHoraFin1() + ":00"
				: "";
			String horario2 = c.getCeldaAsignada2() != null
				? " | Celda 2: " + c.getHoraInicio2() + ":00 - " + c.getHoraFin2() + ":00"
				: "";

			dataCuidadores[i][0] = c.getNumCarnet();
			dataCuidadores[i][1] = c.getNombre();
			dataCuidadores[i][2] = c.getHorasTrabajadas();
			dataCuidadores[i][3] = horario1 + horario2;
			dataCuidadores[i][4] = c.getCeldaAsignada1() != null ? c.getCeldaAsignada1().getId() : "Sin asignar";
			dataCuidadores[i][5] = c.getCeldaAsignada2() != null ? c.getCeldaAsignada2().getId() : "Sin asignar";
			dataCuidadores[i][6] = "Editar";
			dataCuidadores[i][7] = "Eliminar";
		}
		DefaultTableModel modelCuidadores = new DefaultTableModel(dataCuidadores, columnasCuidadores) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 6 || column == 7;
			}
		};

		tableCuidadores = new JTable(modelCuidadores);
		tableCuidadores.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		tableCuidadores.setForeground(Color.BLACK);
		tableCuidadores.setRowHeight(30);

		tableCuidadores.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int fila = tableCuidadores.rowAtPoint(e.getPoint());
				int columna = tableCuidadores.columnAtPoint(e.getPoint());

				Cuidador cuidador = zoologico.getCuidadores().get(fila);

				if (columna == 6) {
					editarCuidador(cuidador);
				} else if (columna == 7) {
					int confirm = JOptionPane.showConfirmDialog(Zoo.this,
							"¿Estás seguro de eliminar al cuidador con CI " + cuidador.getNumCarnet() + "?",
							"Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						zoologico.eliminarCuidador(cuidador);
						actualizarTablaCuidadores();
						actualizarTablaCeldas();
						actualizarResumen();
						JOptionPane.showMessageDialog(Zoo.this, "Cuidador eliminado correctamente.");
					}
				}
			}
		});

		tableCuidadores.getTableHeader().setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		tableCuidadores.getTableHeader().setForeground(Color.BLACK);

		JScrollPane scrollCuidadores = new JScrollPane(tableCuidadores);
		scrollCuidadores.setBounds(50, 50, 1989, 850); 
		Cuidador.add(scrollCuidadores);

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
					CrearVeterinario dialog = new CrearVeterinario(zoo, Zoo.this);
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

		String[] columnasVet = {
				"CI", "Nombre", "Especialidad", "Celdas Atendidas",
				"Añadir Celda", "Editar", "Eliminar"
		};
		modeloVet = new DefaultTableModel(columnasVet, 0) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return col >= 4;
			}
		};
		tablaVet = new JTable(modeloVet);
		tablaVet.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		tablaVet.setRowHeight(30);
		tablaVet.getTableHeader().setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		tablaVet.getTableHeader().setForeground(Color.BLACK);

		JScrollPane scrollVet = new JScrollPane(tablaVet);
		scrollVet.setBounds(50, 50, 1559, 801);
		Veterinario.add(scrollVet);

		tablaVet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaVet.rowAtPoint(e.getPoint());
				int columna = tablaVet.columnAtPoint(e.getPoint());
				if (fila < 0) return;

				String ci = (String) modeloVet.getValueAt(fila, 0);
				Veterinario vet = null;
				for (Veterinario v : zoologico.getVeterinarios()) {
					if (v.getNumCarnet().equals(ci)) {
						vet = v; break;
					}
				}
				if (vet == null) return;

				switch (columna) {
				case 3:
					if (vet.getCeldasAtendidas().isEmpty()) {
						JOptionPane.showMessageDialog(Zoo.this,
								"Este veterinario no tiene celdas asignadas.",
								"Sin celdas", JOptionPane.INFORMATION_MESSAGE);
					} else {
						StringBuilder sb = new StringBuilder("Celdas atendidas por " + vet.getNombre() + ":\n\n");
						for (Celda c : vet.getCeldasAtendidas()) {
							sb.append("- ID: ").append(c.getId()).append(" | Entorno: ")
							.append(c.getEntorno()).append("\n");
						}
						JOptionPane.showMessageDialog(Zoo.this,
								sb.toString(),
								"Celdas asignadas", JOptionPane.INFORMATION_MESSAGE);
					}
					break;

				case 4: 
					ArrayList<Celda> disponibles = new ArrayList<>();
					for (Celda c : zoologico.getTodasLasCeldas()) {
						if (c.getAlimentacion() == vet.getEspecialidad()) {
							boolean atendida = false;
							for (Veterinario o : zoologico.getVeterinarios()) {
								if (o.getCeldasAtendidas().contains(c)) {
									atendida = true; break;
								}
							}
							if (!atendida) disponibles.add(c);
						}
					}
					if (disponibles.isEmpty()) {
						JOptionPane.showMessageDialog(Zoo.this,
								"No hay celdas libres para esa especialidad.",
								"Aviso", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					JComboBox<Celda> combo = new JComboBox<>(disponibles.toArray(new Celda[0]));
					int ok = JOptionPane.showConfirmDialog(Zoo.this, combo,
							"Agregar celda a " + vet.getNombre(),
							JOptionPane.OK_CANCEL_OPTION);
					if (ok == JOptionPane.OK_OPTION) {
						Celda elegida = (Celda) combo.getSelectedItem();
						try {
							vet.agregarCeldaAtencion(elegida);
							actualizarTablaVeterinarios();
							actualizarResumen();
						} catch (IllegalArgumentException ex) {
							JOptionPane.showMessageDialog(Zoo.this,
									ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					break;

				case 5: 
					EditarVeterinario dlg = new EditarVeterinario(vet, zoologico, Zoo.this);
					dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dlg.setVisible(true);
					break;

				case 6: 
					int confirm = JOptionPane.showConfirmDialog(Zoo.this,
							"¿Eliminar veterinario " + vet.getNombre() + "?",
							"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						zoologico.eliminarVeterinario(vet);
						actualizarTablaVeterinarios();
						actualizarResumen();
						JOptionPane.showMessageDialog(Zoo.this,
								"Veterinario eliminado correctamente.");
					}
					break;
				}
			}
		});
		actualizarTablaVeterinarios();

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
					CrearAdministrador dialog = new CrearAdministrador(Zoologico.getZoo(), Zoo.this);
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

		String[] columnasAdmin = {
				"Carnet", "Nombre", "Puesto", "Oficina", "Años Exp.", "Editar", "Eliminar"
		};

		modeloAdmin = new DefaultTableModel(columnasAdmin, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 5 || column == 6;
			}
		};
		tablaAdmin = new JTable(modeloAdmin);
		tablaAdmin.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		tablaAdmin.setRowHeight(30);
		tablaAdmin.getTableHeader().setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		tablaAdmin.getTableHeader().setForeground(Color.BLACK);

		JScrollPane scrollAdmin = new JScrollPane(tablaAdmin);
		scrollAdmin.setBounds(50, 50, 1800, 850);
		Administrador.add(scrollAdmin);

		tablaAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int fila = tablaAdmin.rowAtPoint(e.getPoint());
				int col  = tablaAdmin.columnAtPoint(e.getPoint());
				if (fila < 0) return;

				String carnet = (String) modeloAdmin.getValueAt(fila, 0);
				Administrativo admin = null;
				for (Administrativo a : zoologico.getAdministrativos()) {
					if (a.getNumCarnet().equals(carnet)) {
						admin = a;
						break;
					}
				}
				if (admin == null) return;

				if (col == 5) {
					EditarAdministrador dlg = new EditarAdministrador(admin, zoologico, Zoo.this);
					dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dlg.setVisible(true);
				} else if (col == 6) {
					int confirm = JOptionPane.showConfirmDialog(
							Zoo.this,
							"¿Seguro que deseas eliminar al administrativo " + admin.getNombre() + "?",
							"Confirmar eliminación",
							JOptionPane.YES_NO_OPTION
							);
					if (confirm == JOptionPane.YES_OPTION) {
						zoologico.eliminarAdministrativo(admin);
						actualizarTablaAdministrativos();
						actualizarResumen();
						JOptionPane.showMessageDialog(
								Zoo.this, 
								"Administrativo eliminado correctamente."
								);
					}
				}
			}
		});
		actualizarTablaAdministrativos();

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
					CrearServicio dialog = new CrearServicio(Zoologico.getZoo(), Zoo.this);
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

		String[] columnasServicio = { "Nombre", "Carnet", "Zona", "Editar", "Eliminar" };
		modeloServicio = new DefaultTableModel(columnasServicio, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 3 || column == 4;
			}
		};

		tablaServicio = new JTable(modeloServicio);
		tablaServicio.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		tablaServicio.setRowHeight(30);
		tablaServicio.getTableHeader().setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		tablaServicio.getTableHeader().setForeground(Color.BLACK);

		JScrollPane scrollServicio = new JScrollPane(tablaServicio);
		scrollServicio.setBounds(50, 50, 1559, 801);
		Servicio.add(scrollServicio);

		tablaServicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaServicio.rowAtPoint(e.getPoint());
				int columna = tablaServicio.columnAtPoint(e.getPoint());
				if (fila < 0) return;

				String carnet = (String) modeloServicio.getValueAt(fila, 1);
				Servicio serv = null;
				for (Servicio s : zoologico.getServicios()) {
					if (s.getNumCarnet().equals(carnet)) {
						serv = s;
						break;
					}
				}
				if (serv == null) return;

				if (columna == 3) {
					EditarServicio dialog = new EditarServicio(serv, zoologico, Zoo.this);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} else if (columna == 4) {
					int confirm = JOptionPane.showConfirmDialog(Zoo.this,
							"¿Seguro que deseas eliminar a " + serv.getNombre() + "?",
							"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						zoologico.eliminarServicio(serv);
						actualizarTablaServicio();
						actualizarResumen();
						JOptionPane.showMessageDialog(Zoo.this,
								"Servicio eliminado correctamente.");
					}
				}
			}
		});
		actualizarTablaServicio();
		tablaVet.setModel(modeloVet);


		JLabel LogoServicio = new JLabel("");
		LogoServicio.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoServicio.setBounds(2049, 959, 128, 120);
		Servicio.add(LogoServicio);

		IconServicio = new JLabel("");
		IconServicio.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Trabajadores.jpg")));
		IconServicio.setBounds(0, 0, 0, 0);
		Servicio.add(IconServicio);

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



		Custodio = new JPanel();
		MenuTrabajadores.addTab("Gestionar Custodios", null, Custodio, null);
		Custodio.setLayout(null);

		JButton btnAgregarCustodio_1 = new JButton("AGREGAR");
		btnAgregarCustodio_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearCustodio dialog = new CrearCustodio (Zoologico.getZoo(), Zoo.this);
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

		String[] columnasCustodio = {
				"CI", "Nombre", "Edad", "Días Guardia", "Editar", "Eliminar"
		};

		ArrayList<Custodio> custodios = zoologico.getCustodios();
		Object[][] dataCustodio = new Object[custodios.size()][6];

		for (int i = 0; i < custodios.size(); i++) {
			Custodio c = custodios.get(i);
			dataCustodio[i][0] = c.getNumCarnet();
			dataCustodio[i][1] = c.getNombre();
			dataCustodio[i][2] = c.getEdad();
			dataCustodio[i][3] = formatearDiasGuardia(c.getDiasGuardia());
			dataCustodio[i][4] = "Editar";
			dataCustodio[i][5] = "Eliminar";
		}

		modelCustodio = new DefaultTableModel(dataCustodio, columnasCustodio) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 4 || column == 5;
			}
		};
		tableCustodio = new JTable(modelCustodio);
		tableCustodio.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		tableCustodio.setForeground(Color.BLACK);
		tableCustodio.setRowHeight(30);
		tableCustodio.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int fila = tableCustodio.rowAtPoint(e.getPoint());
				int columna = tableCustodio.columnAtPoint(e.getPoint());
				if (fila < 0) return;

				Custodio custodio = zoologico.getCustodios().get(fila);

				if (columna == 4) {
					editarCustodio(custodio);
				} else if (columna == 5) {
					int confirm = JOptionPane.showConfirmDialog(Zoo.this,
							"¿Estás seguro de eliminar al custodio con CI " + custodio.getNumCarnet() + "?",
							"Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						zoologico.eliminarCustodio(custodio);
						actualizarTablaCustodio();
						actualizarResumen();
						JOptionPane.showMessageDialog(Zoo.this, "Custodio eliminado correctamente.");
					}
				}
			}
		});
		actualizarTablaCustodio();

		tableCustodio.getTableHeader().setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		tableCustodio.getTableHeader().setForeground(Color.BLACK);

		JScrollPane scrollCustodio = new JScrollPane(tableCustodio);
		scrollCustodio.setBounds(50, 50, 1800, 850);
		Custodio.add(scrollCustodio);
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

		Celdas = new JPanel();
		MenuPrincipal.addTab("Gestionar Celdas", null, Celdas, null);
		Celdas.setLayout(null);

		JButton btnAgregarCelda = new JButton("AGREGAR");
		btnAgregarCelda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearCelda dialog = new CrearCelda(Zoologico.getZoo(), Zoo.this);
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

		String[] columnNames = {"ID", "Disponibilidad", "Capacidad Total", "Animales", "Cuidadores", "Editar", "Eliminar"};
		Object[][] data = new Object[zoologico.getCantidadCeldas()][7];

		ArrayList<Celda> celdas = zoologico.getTodasLasCeldas();
		for (int i = 0; i < celdas.size(); i++) {
			Celda c = celdas.get(i);
			data[i][0] = c.getId();
			data[i][1] = c.getDisponibilidad().toString();
			data[i][2] = c.getCapacidadTotal();
			data[i][3] = c.getAnimales().size();
			data[i][4] = c.getCuidadores().size();
			data[i][5] = "Editar";
			data[i][6] = "Eliminar";
		}

		DefaultTableModel mode = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 5 || column == 6;
			}
		};

		table = new JTable(mode);
		actualizarTablaCeldas();

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());

				if (columna == 5) { 
					Celda celda = zoologico.getTodasLasCeldas().get(fila);
					editarCelda(celda);
				}
				if (columna == 6) {
					Celda celda = zoologico.getTodasLasCeldas().get(fila);

					if (!celda.getAnimales().isEmpty() || !celda.getCuidadores().isEmpty()) {
						JOptionPane.showMessageDialog(Zoo.this, 
								"No se puede eliminar la celda porque tiene animales o cuidadores asignados.",
								"Eliminación no permitida", 
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					int confirm = JOptionPane.showConfirmDialog(Zoo.this, 
							"¿Estás seguro de eliminar la celda " + celda.getId() + "?", 
							"Confirmar eliminación", 
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						zoologico.eliminarCelda(celda);
						actualizarTablaCeldas();
						JOptionPane.showMessageDialog(Zoo.this, 
								"Celda eliminada correctamente.");
					}
				}
			}
		});

		table.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		table.setForeground(Color.BLACK);
		table.setRowHeight(30);

		table.getTableHeader().setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		table.getTableHeader().setForeground(Color.BLACK);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 50, 1808, 829);
		Celdas.add(scrollPane);

		JLabel LogoCelda = new JLabel("");
		LogoCelda.setIcon(new ImageIcon(Zoo.class.getResource("/iimagenes/Logo 128x128_transparent.png")));
		LogoCelda.setBounds(2049, 1014, 128, 120);
		Celdas.add(LogoCelda, new Integer(1));

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
					CrearEspecie dialog = new CrearEspecie(Zoologico.getZoo(), Zoo.this);
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

		String[] columnNames1 = {"Nombre Comun", "Nombre Cientifico", "Esperanza de Vida", "Peso Promedio", 
				"Cant de Comida", "Tamaño", "Alimentacion", "Tipo de Entorno", "Animales", "Editar", "Eliminar"};
		Object[][] data1 = new Object[zoologico.getCantidadEspecies()][11];
		ArrayList<Especie> especies = zoologico.getEspecies();

		for (int i = 0; i < especies.size(); i++) {
			Especie e = especies.get(i);
			data1[i][0] = e.getNombreComun();
			data1[i][1] = e.getNombreCientifico();
			data1[i][2] = e.getEsperanza();
			data1[i][3] = e.getPesoProm();
			data1[i][4] = e.getCantComida();
			data1[i][5] = e.getTamaño();
			data1[i][6] = e.getAlimentacion();
			data1[i][7] = e.getEntorno();
			data1[i][8] = e.getAnimales().size();
			data1[i][9] = "Editar";
			data1[i][10] = "Eliminar";
		}

		DefaultTableModel model1 = new DefaultTableModel(data1, columnNames1) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 9 || column == 10;
			}
		};

		table1 = new JTable(model1);
		JScrollPane scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(51, 60, 2090, 800);
		Especies.add(scrollPane1);
		actualizarTablaEspecie();

		table1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int fila = table1.rowAtPoint(e.getPoint());
				int columna = table1.columnAtPoint(e.getPoint());

				Especie especie = zoologico.getEspecies().get(fila);

				if (columna == 9) {
					editarEspecie(especie);
				} else if (columna == 10) {
					if (!especie.getAnimales().isEmpty()) {
						JOptionPane.showMessageDialog(Zoo.this,
								"No se puede eliminar la especie porque tiene animales asignados.",
								"Eliminación no permitida",
								JOptionPane.WARNING_MESSAGE);
						return; 
					}

					int confirm = JOptionPane.showConfirmDialog(Zoo.this,
							"¿Estás seguro de eliminar la especie " + especie.getNombreComun() + "?",
							"Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						zoologico.eliminarEspecie(especie);
						actualizarTablaEspecie();
						JOptionPane.showMessageDialog(Zoo.this, "Especie eliminada correctamente.");
					}
				}
			}
		});

		table1.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		table1.setForeground(Color.BLACK);
		table1.setRowHeight(30);

		table1.getTableHeader().setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		table1.getTableHeader().setForeground(Color.BLACK);


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
					CrearAnimal dialog = new CrearAnimal(controlador, Zoo.this);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		btnAgregar.setBounds(112, 1015, 176, 47);
		Animales.add(btnAgregar);
		btnAgregar.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));

		String[] columnasAnimales = {
				"ID", "Sexo", "Fecha de Nacimiento", "Especie", "Celda", "Editar", "Eliminar"
		};
		Object[][] dataAnimales = new Object[zoologico.getCantidadAnimales()][7];
		ArrayList<Animal> animales = zoologico.getAnimales();

		for (int i = 0; i < animales.size(); i++) {
			Animal a = animales.get(i);
			dataAnimales[i][0] = a.getId();
			dataAnimales[i][1] = a.getSexo().toString();
			dataAnimales[i][2] = a.getNacimiento().toString();
			dataAnimales[i][3] = a.getEspecie().getNombreComun();
			dataAnimales[i][4] = a.getCelda().getId();
			dataAnimales[i][5] = "Editar";
			dataAnimales[i][6] = "Eliminar";
		}

		DefaultTableModel modelAnimales = new DefaultTableModel(dataAnimales, columnasAnimales) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 5 || column == 6;
			}
		};

		tableAnimales = new JTable(modelAnimales);
		tableAnimales.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		tableAnimales.setForeground(Color.BLACK);
		tableAnimales.setRowHeight(30);

		tableAnimales.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int fila = tableAnimales.rowAtPoint(e.getPoint());
				int columna = tableAnimales.columnAtPoint(e.getPoint());

				Animal animal = zoologico.getAnimales().get(fila);

				if (columna == 5) {
					editarAnimal(animal);
				} else if (columna == 6) {
					int confirm = JOptionPane.showConfirmDialog(Zoo.this,
							"¿Estás seguro de eliminar el animal con ID " + animal.getId() + "?",
							"Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						zoologico.eliminarAnimal(animal);

						actualizarTablaAnimales();
						actualizarTablaCeldas();
						actualizarTablaEspecie();
						actualizarResumen();

						JOptionPane.showMessageDialog(Zoo.this, "Animal eliminado correctamente.");
					}
				}
			}
		});

		tableAnimales.getTableHeader().setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		tableAnimales.getTableHeader().setForeground(Color.BLACK);

		JScrollPane scrollAnimales = new JScrollPane(tableAnimales);
		scrollAnimales.setBounds(50, 50, 1800, 850);
		Animales.add(scrollAnimales);


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
	public void actualizarResumen() {
		Zoologico zoo = Zoologico.getZoo();
		String resumen = String.format(
				"CANTIDAD DE CELDAS: %d\nCANTIDAD DE ESPECIES: %d\nCANTIDAD DE ANIMALES: %d\nCANTIDAD DE TRABAJADORES: %d\n" +
						"    CANTIDAD DE CUIDADORES: %d\n    CANTIDAD DE VETERINARIOS: %d\n    CANTIDAD DE ADMINISTRADORES: %d\n" +
						"    CANTIDAD DE CUSTODIOS: %d\n    CANTIDAD DE SERVICIOS: %d",
						zoo.getCantidadCeldas(),
						zoo.getCantidadEspecies(),
						zoo.getCantidadAnimales(),
						zoo.getCantidadTrabajadores(),
						zoo.getCuidadores().size(),
						zoo.getVeterinarios().size(),
						zoo.getAdministrativos().size(),
						zoo.getCustodios().size(),
						zoo.getServicios().size()
				);
		resumeArea.setText(resumen);
	}
	public void actualizarTablaCeldas() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (Celda c : zoologico.getTodasLasCeldas()) {
			model.addRow(new Object[]{
					c.getId(),
					c.getDisponibilidad().toString(),
					c.getCapacidadTotal(),      
					c.getAnimales().size(),
					c.getCuidadores().size(),
					"Editar",
					"Eliminar"
			});
		}
	}
	public void actualizarTablaEspecie() {
		DefaultTableModel model1 = (DefaultTableModel) table1.getModel();	
		model1.setRowCount(0);

		for (Especie e : zoologico.getEspecies()) {
			model1.addRow(new Object[]{
					e.getNombreComun(),
					e.getNombreCientifico(),
					e.getEsperanza(),
					e.getPesoProm(),
					e.getCantComida(),
					e.getTamaño(),
					e.getAlimentacion(),
					e.getEntorno(),
					e.getAnimales().size(),
					"Editar",
					"Eliminar"
			});
		}
	}
	public void actualizarTablaAnimales() {
		DefaultTableModel model = (DefaultTableModel) tableAnimales.getModel();
		model.setRowCount(0);
		for (Animal a : zoologico.getAnimales()) {
			model.addRow(new Object[]{
					a.getId(),
					a.getSexo().toString(),
					a.getNacimiento().toString(),
					a.getEspecie().getNombreComun(),
					a.getCelda().getId(),
					"Editar",
					"Eliminar"
			});
		}
	}
	public void actualizarTablaCuidadores() {
	    DefaultTableModel model = (DefaultTableModel) tableCuidadores.getModel();
	    model.setRowCount(0);

	    for (Cuidador c : zoologico.getCuidadores()) {
	        String horario1 = c.getCeldaAsignada1() != null
	            ? "Celda 1: " + c.getHoraInicio1() + ":00 - " + c.getHoraFin1() + ":00"
	            : "N/A";

	        String horario2 = c.getCeldaAsignada2() != null
	            ? " | Celda 2: " + c.getHoraInicio2() + ":00 - " + c.getHoraFin2() + ":00"
	            : "";

	        model.addRow(new Object[]{
	            c.getNumCarnet(),
	            c.getNombre(),
	            c.getHorasTrabajadas(),
	            horario1 + horario2,
	            c.getCeldaAsignada1() != null ? c.getCeldaAsignada1().getId() : "Sin asignar",
	            c.getCeldaAsignada2() != null ? c.getCeldaAsignada2().getId() : "Sin asignar",
	            "Editar",
	            "Eliminar"
	        });
	    }
	    model.fireTableDataChanged();	    
	}

	public void actualizarTablaServicio() {
		modeloServicio.setRowCount(0);
		for (Servicio s : zoologico.getServicios()) {
			modeloServicio.addRow(new Object[]{
					s.getNombre(),
					s.getNumCarnet(),
					s.getZona(),
					"Editar",
					"Eliminar"
			});
		}
	}
	public void actualizarTablaAdministrativos() {
		DefaultTableModel model = (DefaultTableModel) tablaAdmin.getModel();
		model.setRowCount(0);
		for (Administrativo a : zoologico.getAdministrativos()) {
			model.addRow(new Object[]{
					a.getNumCarnet(),
					a.getNombre(),
					a.getPuestoTrabajo(),
					a.getOficina(),
					a.getAnyosExp(),
					"Editar",
					"Eliminar"
			});
		}
	}
	public void actualizarTablaVeterinarios() {
		modeloVet.setRowCount(0);

		for (Veterinario v : zoologico.getVeterinarios()) {
			JComboBox<Celda> comboCeldas = new JComboBox<>(v.getCeldasAtendidas().toArray(new Celda[0]));

			modeloVet.addRow(new Object[]{
					v.getNumCarnet(),
					v.getNombre(),
					v.getEspecialidad().toString(),
					"Ver Celdas", 
					"Añadir Celda",
					"Editar",
					"Eliminar"
			});
		}
	}
	public void actualizarTablaCustodio() {
	    DefaultTableModel model = (DefaultTableModel) tableCustodio.getModel();
	    model.setRowCount(0);
	    for (Custodio c : zoologico.getCustodios()) {
	        Object[] fila = {
	            c.getNumCarnet(),
	            c.getNombre(),
	            c.getEdad(),
	            formatearDiasGuardia(c.getDiasGuardia()),
	            "Editar",
	            "Eliminar"
	        };
	        model.addRow(fila);
	    }
	}
	private String formatearDiasGuardia(ArrayList<Integer> diasGuardia) {
	    if (diasGuardia == null || diasGuardia.isEmpty()) {
	        return "-";
	    }
	    String[] nombresDias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < diasGuardia.size(); i++) {
	        int dia = diasGuardia.get(i);
	        if (dia >= 0 && dia <= 6) {
	            sb.append(nombresDias[dia]);
	            if (i < diasGuardia.size() - 1) {
	                sb.append(", ");
	            }
	        }
	    }
	    return sb.toString();
	}
	private void editarCelda(Celda celda) {
		EditarCelda dialog = new EditarCelda(zoologico, celda, Zoo.this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	private void editarEspecie(Especie especie) {
		EditarEspecie dialog = new EditarEspecie(zoologico, Zoo.this, especie);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	private void editarAnimal(Animal animal) {
		EditarAnimal dialog = new EditarAnimal(zoologico, Zoo.this, animal);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	private void editarCustodio(Custodio c) {
	    EditarCustodio dialog = new EditarCustodio(c, zoologico, Zoo.this);
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setVisible(true);
	}
	private void editarCuidador(Cuidador cuidador) {
	    ArrayList<Celda> disponibles = new ArrayList<>();
	    for (Celda c : zoologico.getTodasLasCeldas()) {
	         if (!c.equals(cuidador.getCeldaAsignada1()) && c.getCuidadores().size() < 2) {
	            disponibles.add(c);
	        }
	    }

	    if (cuidador.getCeldaAsignada1() != null) {
	        disponibles.add(cuidador.getCeldaAsignada1());
	    }

	    if (disponibles.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "No hay celdas disponibles para editar este cuidador", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    EditarCuidador dialog = new EditarCuidador(zoologico, disponibles, Zoo.this, cuidador);
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setVisible(true);
	}



}