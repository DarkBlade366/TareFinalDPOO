package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import clases.Celda;
import clases.Cuidador;
import clases.Zoologico;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import runner.Zoo;

public class EditarCuidador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCarnet;
	private JTextField txtHorasTrabajadas;
	private JComboBox<Celda> comboBoxCelda;
	private JSpinner spinnerInicio;
	private JSpinner spinnerFin;
	private Cuidador cuidador;
	private Zoologico controlador;
	private Zoo ventanaPrincipal;
	private ArrayList<Celda> celdasDisponibles;
	private JComboBox<Celda> comboBoxCelda2;

	public EditarCuidador(Cuidador cuidador, Zoologico controlador, ArrayList<Celda> celdasDisponibles, Zoo ventanaPrincipal) {
		this.cuidador = cuidador;
		this.controlador = controlador;
		if (celdasDisponibles != null) {
		    this.celdasDisponibles = celdasDisponibles;
		} else {
		    this.celdasDisponibles = new ArrayList<Celda>();
		}this.ventanaPrincipal = ventanaPrincipal;

		setTitle("Editar Cuidador");
		setBounds(100, 100, 606, 559);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblNombre.setBounds(21, 63, 168, 26);
		contentPanel.add(lblNombre);

		JLabel lblCarnet = new JLabel("CARNET");
		lblCarnet.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblCarnet.setBounds(305, 63, 168, 26);
		contentPanel.add(lblCarnet);

		JLabel lblHoraInicio = new JLabel("HORA EN QUE INICIO");
		lblHoraInicio.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblHoraInicio.setBounds(21, 177, 234, 26);
		contentPanel.add(lblHoraInicio);

		JLabel lblHoraTermina = new JLabel("HORA EN QUE TERMINA");
		lblHoraTermina.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblHoraTermina.setBounds(305, 177, 254, 26);
		contentPanel.add(lblHoraTermina);

		JLabel lblHorasTrabajadas = new JLabel("HORAS TRABAJADAS");
		lblHorasTrabajadas.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblHorasTrabajadas.setBounds(21, 304, 234, 26);
		contentPanel.add(lblHorasTrabajadas);

		JLabel lblCeldasAtendidas = new JLabel("CELDAS ATENDIDAS");
		lblCeldasAtendidas.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblCeldasAtendidas.setBounds(305, 304, 216, 26);
		contentPanel.add(lblCeldasAtendidas);

		txtNombre = new JTextField(cuidador.getNombre());
		txtNombre.setBounds(21, 104, 186, 32);
		contentPanel.add(txtNombre);

		txtCarnet = new JTextField(cuidador.getNumCarnet());
		txtCarnet.setEditable(false);
		txtCarnet.setBounds(305, 104, 186, 32);
		contentPanel.add(txtCarnet);

		txtHorasTrabajadas = new JTextField(String.valueOf(cuidador.getHorasTrabajadas()));
		txtHorasTrabajadas.setBounds(21, 356, 186, 32);
		contentPanel.add(txtHorasTrabajadas);

		spinnerInicio = new JSpinner(new SpinnerNumberModel(cuidador.getHoraInicio(), 1, 24, 1));
		spinnerInicio.setBounds(31, 233, 168, 32);
		contentPanel.add(spinnerInicio);

		spinnerFin = new JSpinner(new SpinnerNumberModel(cuidador.getHoraFin(), 1, 24, 1));
		spinnerFin.setBounds(305, 233, 168, 32);
		contentPanel.add(spinnerFin);

		comboBoxCelda = new JComboBox<>();
		comboBoxCelda.setModel(new DefaultComboBoxModel<>(celdasDisponibles.toArray(new Celda[0])));
		if (cuidador.getCeldaAsignada1() != null) {
			comboBoxCelda.setSelectedItem(cuidador.getCeldaAsignada1());
		}
		comboBoxCelda.setBounds(305, 356, 256, 32);
		contentPanel.add(comboBoxCelda);

		comboBoxCelda2 = new JComboBox<>();
		ArrayList<Celda> opcionesCelda2 = new ArrayList<>();
		for (Celda c : celdasDisponibles) {
			if (!c.equals(cuidador.getCeldaAsignada1()) && c.getCuidadores().size() < 2) {
				opcionesCelda2.add(c);
			}
		}
		comboBoxCelda2.setModel(new DefaultComboBoxModel<>(opcionesCelda2.toArray(new Celda[0])));
		if (cuidador.getCeldaAsignada2() != null) {
			comboBoxCelda2.setSelectedItem(cuidador.getCeldaAsignada2());
		}
		comboBoxCelda2.setBounds(305, 400, 256, 32);
		contentPanel.add(comboBoxCelda2);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("GUARDAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarCambios();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
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
	private void guardarCambios() {
		try {
			String nombre = txtNombre.getText().trim();
			double horas = Double.parseDouble(txtHorasTrabajadas.getText().trim());
			int inicio = (int) spinnerInicio.getValue();
			int fin = (int) spinnerFin.getValue();
			Celda nuevaCelda1 = (Celda) comboBoxCelda.getSelectedItem();
			Celda nuevaCelda2 = (Celda) comboBoxCelda2.getSelectedItem();

			if (inicio >= fin) {
				throw new IllegalArgumentException("La hora de inicio debe ser menor que la de fin.");
			}

			if (nuevaCelda2 != null && nuevaCelda1.equals(nuevaCelda2)) {
				throw new IllegalArgumentException("No puede asignar la misma celda como primaria y secundaria.");
			}

			cuidador.setNombre(nombre);
			cuidador.setHorasTrabajadas(horas);
			cuidador.setHoraInicio(inicio);
			cuidador.setHoraFin(fin);

			if (cuidador.getCeldaAsignada1() != null && !cuidador.getCeldaAsignada1().equals(nuevaCelda1)) {
				cuidador.getCeldaAsignada1().removerCuidador(cuidador);
			}
			if (cuidador.getCeldaAsignada2() != null && nuevaCelda2 != null && !cuidador.getCeldaAsignada2().equals(nuevaCelda2)) {
				cuidador.getCeldaAsignada2().removerCuidador(cuidador);
			}
			if (!nuevaCelda1.getCuidadores().contains(cuidador)) {
				nuevaCelda1.agregarCuidador(cuidador);
			}
			if (nuevaCelda2 != null && !nuevaCelda2.getCuidadores().contains(cuidador)) {
				nuevaCelda2.agregarCuidador(cuidador);
			}
			cuidador.setCeldaAsignada1(nuevaCelda1);
			cuidador.setCeldaAsignada2(nuevaCelda2);

			ventanaPrincipal.actualizarTablaCuidadores();
			ventanaPrincipal.actualizarTablaCeldas();
			ventanaPrincipal.actualizarResumen();

			JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente.");
			dispose();

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


}
