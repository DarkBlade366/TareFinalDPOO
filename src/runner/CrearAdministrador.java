package runner;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Administrativo;
import clases.Zoologico;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearAdministrador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Zoologico controlador;
	private JTextField textFieldNombre;
	private JTextField textFieldCarnet;
	private JTextField textFieldPuesto;
	private JTextField textFieldOficina;
	private JSpinner spinnerAnosExp;


	public CrearAdministrador(Zoologico controlador) {
		this.controlador = controlador;

		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textFieldOficina = new JTextField();
		textFieldOficina.setColumns(10);
		textFieldOficina.setBounds(21, 208, 186, 32);
		contentPanel.add(textFieldOficina);

		JLabel label = new JLabel("NOMBRE");
		label.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		label.setBounds(21, 42, 168, 26);
		contentPanel.add(label);

		JLabel label_2 = new JLabel("CARNET");
		label_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		label_2.setBounds(332, 42, 168, 26);
		contentPanel.add(label_2);

		JLabel lblAosDeExperiencia = new JLabel("AÑOS DE EXPERIENCIA");
		lblAosDeExperiencia.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblAosDeExperiencia.setBounds(21, 292, 304, 26);
		contentPanel.add(lblAosDeExperiencia);

		JLabel lblOficina = new JLabel("OFICINA");
		lblOficina.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblOficina.setBounds(21, 163, 168, 26);
		contentPanel.add(lblOficina);

		JLabel lblPuesto = new JLabel("PUESTO");
		lblPuesto.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblPuesto.setBounds(332, 163, 168, 26);
		contentPanel.add(lblPuesto);

		spinnerAnosExp = new JSpinner();
		spinnerAnosExp.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerAnosExp.setBounds(21, 331, 168, 32);
		contentPanel.add(spinnerAnosExp	);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(21, 81, 186, 32);
		contentPanel.add(textFieldNombre);

		textFieldCarnet = new JTextField();
		textFieldCarnet.setColumns(10);
		textFieldCarnet.setBounds(325, 81, 186, 32);
		contentPanel.add(textFieldCarnet);

		textFieldPuesto = new JTextField();
		textFieldPuesto.setColumns(10);
		textFieldPuesto.setBounds(325, 198, 186, 32);
		contentPanel.add(textFieldPuesto);

		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CREAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearAdministrador();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL\r\n");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

		}

	}
	private void crearAdministrador() {
		try {
			String nombre = textFieldNombre.getText().trim();
			String carnet = textFieldCarnet.getText().trim();
			String puesto = textFieldPuesto.getText().trim();
			String oficina = textFieldOficina.getText().trim();
			int anosExp = (int) spinnerAnosExp.getValue();

			Administrativo admin = new Administrativo(nombre, carnet, anosExp, puesto, oficina);
			controlador.agregarTrabajador(admin);

			JOptionPane.showMessageDialog(this, "Administrativo creado exitosamente.");
			dispose();

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
}
