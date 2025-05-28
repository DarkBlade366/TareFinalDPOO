package runner;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pantalla extends JFrame {

	private JPanel contentPane;
	private JTextField Usuario;
	private JPasswordField Contraseña;


	public Pantalla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 892);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel BackGraund = new JPanel();
		BackGraund.setBackground(Color.WHITE);
		contentPane.add(BackGraund, BorderLayout.CENTER);
		BackGraund.setLayout(null);
		
		JLabel INICIARSESION = new JLabel("INICIAR SESION");
		INICIARSESION.setForeground(new Color(230, 230, 250));
		INICIARSESION.setHorizontalAlignment(SwingConstants.CENTER);
		INICIARSESION.setFont(new Font("Arial Black", Font.PLAIN, 40));
		INICIARSESION.setBackground(new Color(192, 192, 192));
		INICIARSESION.setBounds(0, 501, 1152, 42);
		BackGraund.add(INICIARSESION);
		
		JLabel USUARIO = new JLabel("USUARIO");
		USUARIO.setBounds(124, 574, 912, 50);
		BackGraund.add(USUARIO);
		USUARIO.setHorizontalAlignment(SwingConstants.CENTER);
		USUARIO.setForeground(new Color(230, 230, 250));
		USUARIO.setFont(new Font("Arial Black", Font.PLAIN, 30));
		USUARIO.setBackground(Color.LIGHT_GRAY);
		
		JLabel CONTRASEÑA = new JLabel("CONTRASE\u00D1A");
		CONTRASEÑA.setHorizontalAlignment(SwingConstants.CENTER);
		CONTRASEÑA.setForeground(new Color(230, 230, 250));
		CONTRASEÑA.setFont(new Font("Arial Black", Font.PLAIN, 30));
		CONTRASEÑA.setBackground(Color.LIGHT_GRAY);
		CONTRASEÑA.setBounds(96, 658, 957, 50);
		BackGraund.add(CONTRASEÑA);
		
		Usuario = new JTextField();
		Usuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (Usuario.getText().equals("Introduzca su usuario")) {
				    Usuario.setText("");
				    Usuario.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (Usuario.getText().isEmpty()) {
				    Usuario.setText("Introduzca su usuario");
				    Usuario.setForeground(new Color(169, 169, 169));
				}
			}
		});
		
		final JButton ENTRAR = new JButton("ENTRAR");
		ENTRAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = Usuario.getText();
				String pass = String.valueOf(Contraseña.getPassword());
				if (user.equals("Xavier Ramirez") && pass.equals("vrdThqZH")) {
				    Zoo nueva = new Zoo();
				    nueva.setVisible(true);
				    dispose();
			}
				else if (user.equals("Alex Dayan") && pass.equals("1234")) {
				    Zoo nueva = new Zoo();
				    nueva.setVisible(true);
				    dispose();
				}
				else {
				    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		ENTRAR.setBackground(new Color(255, 255, 255));
		ENTRAR.setForeground(new Color(0, 0, 0));
		ENTRAR.setFont(new Font("Arial Black", Font.PLAIN, 17));
		ENTRAR.setBounds(504, 767, 131, 36);
		BackGraund.add(ENTRAR);
		
		Contraseña = new JPasswordField();
		Contraseña.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (String.valueOf(Contraseña.getPassword()).equals("Introduzca su contraseña")) {
				    Contraseña.setText("");
				    Contraseña.setForeground(Color.BLACK);
				    Contraseña.setEchoChar('●');
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(Contraseña.getPassword()).isEmpty()) {
				    Contraseña.setEchoChar((char) 0); 
				    Contraseña.setText("Introduzca su contraseña");
				    Contraseña.setForeground(new Color(169, 169, 169));
				}
			}
		});
		Contraseña.setText("Introduzca su contraseña");
		Contraseña.setEchoChar((char) 0);
		Contraseña.setForeground(new Color(169, 169, 169));
		Contraseña.setFont(new Font("Arial", Font.PLAIN, 13));
		Contraseña.setBackground(new Color(245, 245, 245));
		Contraseña.setBounds(461, 710, 225, 27);
		BackGraund.add(Contraseña);
		Contraseña.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ENTRAR.doClick();
		    }
		});
		Usuario.setText("Introduzca su usuario");
		Usuario.setForeground(new Color(169, 169, 169));
		Usuario.setFont(new Font("Arial", Font.PLAIN, 13));
		Usuario.setBackground(new Color(245, 245, 245));
		Usuario.setBounds(461, 623, 225, 27);
		BackGraund.add(Usuario);
		Usuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Contraseña.requestFocusInWindow();
		    }
		});
		Usuario.setColumns(10);
		
		JLabel ZooInicio = new JLabel("");
		ZooInicio.setIcon(new ImageIcon(Pantalla.class.getResource("/iimagenes/zoologico-de-26-cuba-La-Habana.jpeg")));
		ZooInicio.setBounds(0, -69, 1152, 543);
		BackGraund.add(ZooInicio);
		
		JLabel Raya_1 = new JLabel("");
		Raya_1.setIcon(new ImageIcon(Pantalla.class.getResource("/iimagenes/Raya.png")));
		Raya_1.setBounds(-77, 472, 843, 16);
		BackGraund.add(Raya_1);
		
		JLabel Raya_2 = new JLabel("");
		Raya_2.setIcon(new ImageIcon(Pantalla.class.getResource("/iimagenes/Raya.png")));
		Raya_2.setBounds(414, 472, 843, 16);
		BackGraund.add(Raya_2);
		
		JLabel FotoInicio = new JLabel("");
		FotoInicio.setIcon(new ImageIcon(Pantalla.class.getResource("/iimagenes/IniciarSesion.png")));
		FotoInicio.setBounds(0, 487, 1152, 348);
		BackGraund.add(FotoInicio);
		
		setLocationRelativeTo(null);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        ENTRAR.requestFocusInWindow();
		    }
		});
	}
}
