package runner;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Zoo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Zoo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane MenuPrincipal = new JTabbedPane(JTabbedPane.TOP);
		MenuPrincipal.setBackground(new Color(135, 206, 235));
		MenuPrincipal.setBorder(new LineBorder(new Color(0, 0, 0)));
		MenuPrincipal.setFont(new Font("Arial Black", Font.PLAIN, 17));
		MenuPrincipal.setBounds(0, 0, 1382, 653);
		contentPane.add(MenuPrincipal);
		
		JPanel Trabajadores = new JPanel();
		MenuPrincipal.addTab("Gestionar Trabajadores", null, Trabajadores, null);
		Trabajadores.setLayout(null);
		
		JTabbedPane MenuTrabajadores = new JTabbedPane(JTabbedPane.TOP);
		MenuTrabajadores.setFont(new Font("Arial", Font.PLAIN, 18));
		MenuTrabajadores.setBackground(new Color(224, 255, 255));
		MenuTrabajadores.setBounds(0, 0, 1375, 612);
		Trabajadores.add(MenuTrabajadores);
		
		JPanel Cuidador = new JPanel();
		MenuTrabajadores.addTab("Cuidador ", null, Cuidador, null);
		
		JPanel Administrativo = new JPanel();
		MenuTrabajadores.addTab("Administrativo", null, Administrativo, null);
		
		JPanel Veterinario = new JPanel();
		MenuTrabajadores.addTab("Veterinario", null, Veterinario, null);
		
		JPanel Servicio = new JPanel();
		MenuTrabajadores.addTab("Servicio", null, Servicio, null);
		
		JPanel Custodio = new JPanel();
		MenuTrabajadores.addTab("Custodio", null, Custodio, null);
		
		JPanel Celdas = new JPanel();
		MenuPrincipal.addTab("Gestionar Celdas", null, Celdas, null);
		
		JPanel Especies = new JPanel();
		MenuPrincipal.addTab("Gestionar Especies", null, Especies, null);
		
		JPanel Animales = new JPanel();
		MenuPrincipal.addTab("Gestionar Animales", null, Animales, null);
		
		
		
		setLocationRelativeTo(null);
	}
}
