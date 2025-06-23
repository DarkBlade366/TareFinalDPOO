package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import runner.Zoo;
import clases.Custodio;
import clases.Zoologico;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCustodio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldCarnet;
	private JSpinner spinnerEdad;
	private JCheckBox chckbxLunes, chckbxMartes, chckbxMiercoles,
	chckbxJueves, chckbxViernes, chckbxSabado, chckbxDomingo;
	private Custodio custodio;
	private Zoologico controlador;
	private Zoo ventanaPrincipal;

	public EditarCustodio(Custodio custodio, Zoologico controlador, Zoo ventanaPrincipal) {
		this.custodio = custodio;
		this.controlador = controlador;
		this.ventanaPrincipal = ventanaPrincipal;

		setTitle("Editar Custodio");
		setBounds(100, 100, 606, 559);
		setLocationRelativeTo(null);	getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblNombre.setBounds(21, 32, 168, 26);
		contentPanel.add(lblNombre);

		textFieldNombre = new JTextField(custodio.getNombre());
		textFieldNombre.setBounds(21, 79, 186, 32);
		contentPanel.add(textFieldNombre);

		JLabel lblCarnet = new JLabel("CARNET");
		lblCarnet.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblCarnet.setBounds(21, 151, 168, 26);
		contentPanel.add(lblCarnet);

		textFieldCarnet = new JTextField(custodio.getNumCarnet());
		textFieldCarnet.setEditable(false);
		textFieldCarnet.setBounds(21, 198, 186, 32);
		contentPanel.add(textFieldCarnet);

		JLabel lblEdad = new JLabel("EDAD");
		lblEdad.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblEdad.setBounds(21, 275, 168, 26);
		contentPanel.add(lblEdad);

		spinnerEdad = new JSpinner(new SpinnerNumberModel(custodio.getEdad(), 18, 100, 1));
		spinnerEdad.setBounds(21, 322, 106, 32);
		contentPanel.add(spinnerEdad);

		JLabel lblDias = new JLabel("DÍAS DE GUARDIA");
		lblDias.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblDias.setBounds(325, 32, 234, 26);
		contentPanel.add(lblDias);

		chckbxLunes = new JCheckBox("Lunes");
		chckbxLunes.setBounds(325, 78, 179, 35);
		contentPanel.add(chckbxLunes);
		chckbxMartes = new JCheckBox("Martes");
		chckbxMartes.setBounds(325, 125, 179, 35);
		contentPanel.add(chckbxMartes);
		chckbxMiercoles = new JCheckBox("Miércoles");
		chckbxMiercoles.setBounds(325, 173, 179, 35);
		contentPanel.add(chckbxMiercoles);
		chckbxJueves = new JCheckBox("Jueves");
		chckbxJueves.setBounds(325, 221, 179, 35);
		contentPanel.add(chckbxJueves);
		chckbxViernes = new JCheckBox("Viernes");
		chckbxViernes.setBounds(325, 272, 179, 35);
		contentPanel.add(chckbxViernes);
		chckbxSabado = new JCheckBox("Sábado");
		chckbxSabado.setBounds(325, 321, 179, 35);
		contentPanel.add(chckbxSabado);
		chckbxDomingo = new JCheckBox("Domingo");
		chckbxDomingo.setBounds(325, 369, 179, 35);
		contentPanel.add(chckbxDomingo);

		for (Integer dia : custodio.getDiasGuardia()) {
			switch (dia) {
			case 0: chckbxLunes.setSelected(true); break;
			case 1: chckbxMartes.setSelected(true); break;
			case 2: chckbxMiercoles.setSelected(true); break;
			case 3: chckbxJueves.setSelected(true); break;
			case 4: chckbxViernes.setSelected(true); break;
			case 5: chckbxSabado.setSelected(true); break;
			case 6: chckbxDomingo.setSelected(true); break;
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("GUARDAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardar();
					}
				});
				okButton.setActionCommand("OK");
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
	private ArrayList<Integer> getDiasSeleccionados() {
		ArrayList<Integer> dias = new ArrayList<>();
		if (chckbxLunes.isSelected()) dias.add(0);
		if (chckbxMartes.isSelected()) dias.add(1);
		if (chckbxMiercoles.isSelected()) dias.add(2);
		if (chckbxJueves.isSelected()) dias.add(3);
		if (chckbxViernes.isSelected()) dias.add(4);
		if (chckbxSabado.isSelected()) dias.add(5);
		if (chckbxDomingo.isSelected()) dias.add(6);
		return dias;
	}
	private void guardar() {
	    try {
	        String nombre = textFieldNombre.getText().trim();
	        int edad = (int) spinnerEdad.getValue();
	        ArrayList<Integer> dias = getDiasSeleccionados();

	        if (nombre.isEmpty() || dias.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        custodio.setNombre(nombre);
	        custodio.setDiasGuardia(dias);

	        if (edad > 55) {
	            JOptionPane.showMessageDialog(this,
	                "El custodio sobrepasa 55 años y será transferido a Servicio.");

	            controlador.actualizarEdadCustodio(custodio, edad);
	            controlador.eliminarCustodio(custodio);
	            dispose();
	            CrearServicio crearServicioDialog = new CrearServicio(controlador, ventanaPrincipal);
	            crearServicioDialog.llenarCampos(nombre, custodio.getNumCarnet());
	            crearServicioDialog.setVisible(true);
	        } else {
	        	controlador.actualizarEdadCustodio(custodio, edad);
	            JOptionPane.showMessageDialog(this, "Custodio actualizado correctamente.");
	            dispose();
	        }
	        ventanaPrincipal.actualizarTablaCustodio();
	        ventanaPrincipal.actualizarTablaServicio();
	        ventanaPrincipal.actualizarResumen();

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

}
