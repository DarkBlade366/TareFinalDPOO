package runner;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Custodio;
import clases.Servicio;
import clases.Zoologico;
import runner.CrearServicio;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearCustodio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Zoologico controlador;
	private JTextField textFieldNombre;
	private JTextField textFieldCarnet;
	private JSpinner spinnerEdad;
	private JCheckBox chckbxLunes;
    private JCheckBox chckbxMartes;
    private JCheckBox chckbxMiercoles;
    private JCheckBox chckbxJueves;
    private JCheckBox chckbxViernes;
    private JCheckBox chckbxSabado;
    private JCheckBox chckbxDomingo;
	
	public CrearCustodio(Zoologico controlador) {
		this.controlador = controlador;
		
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("NOMBRE");
		label.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		label.setBounds(21, 32, 168, 26);
		contentPanel.add(label);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(21, 79, 186, 32);
		contentPanel.add(textFieldNombre);
		
		textFieldCarnet = new JTextField();
		textFieldCarnet.setColumns(10);
		textFieldCarnet.setBounds(21, 198, 186, 32);
		contentPanel.add(textFieldCarnet);
		
		JLabel label_1 = new JLabel("CARNET");
		label_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		label_1.setBounds(21, 151, 168, 26);
		contentPanel.add(label_1);
		
		JLabel lblEdad = new JLabel("EDAD");
		lblEdad.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblEdad.setBounds(21, 275, 168, 26);
		contentPanel.add(lblEdad);
		
		spinnerEdad = new JSpinner();
		spinnerEdad.setModel(new SpinnerNumberModel(18, 18, 100, 1));
		spinnerEdad.setBounds(21, 322, 106, 32);
		contentPanel.add(spinnerEdad);
		
		JLabel lblDiasDeGuardia = new JLabel("DIAS DE GUARDIA");
		lblDiasDeGuardia.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblDiasDeGuardia.setBounds(325, 32, 234, 26);
		contentPanel.add(lblDiasDeGuardia);
		
		chckbxLunes = new JCheckBox("Lunes");
		chckbxLunes.setBounds(325, 78, 179, 35);
		contentPanel.add(chckbxLunes);
		
		chckbxMartes = new JCheckBox("Martes");
		chckbxMartes.setBounds(325, 125, 179, 35);
		contentPanel.add(chckbxMartes);
		
		chckbxMiercoles = new JCheckBox("Miercoles");
		chckbxMiercoles.setBounds(325, 173, 179, 35);
		contentPanel.add(chckbxMiercoles);
		
		chckbxJueves = new JCheckBox("Jueves");
		chckbxJueves.setBounds(325, 221, 179, 35);
		contentPanel.add(chckbxJueves);
		
		chckbxViernes = new JCheckBox("Viernes");
		chckbxViernes.setBounds(325, 272, 179, 35);
		contentPanel.add(chckbxViernes);
		
		chckbxSabado = new JCheckBox("Sabado");
		chckbxSabado.setBounds(325, 321, 179, 35);
		contentPanel.add(chckbxSabado);
		
		chckbxDomingo = new JCheckBox("Domingo");
		chckbxDomingo.setBounds(325, 369, 179, 35);
		contentPanel.add(chckbxDomingo);
		
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CREAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearCustodio();
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
	private ArrayList<Integer> obtenerDiasSeleccionados() {
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
	private void crearCustodio() {
	    try {
	        String nombre = textFieldNombre.getText().trim();
	        String carnet = textFieldCarnet.getText().trim();
	        int edad = (Integer) spinnerEdad.getValue();

	        ArrayList<Integer> diasGuardia = obtenerDiasSeleccionados();
	        if (diasGuardia.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un día de guardia.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        Custodio c = new Custodio(nombre, carnet, edad);
	        c.setDiasGuardia(diasGuardia);

	        if (edad > 55) {
	            JOptionPane.showMessageDialog(this, "El custodio será asignado a labores de servicio porque supera los 55 años.");
	            dispose(); 
	            
	            CrearServicio crearServicioDialog = new CrearServicio(controlador, nombre, carnet);
	            crearServicioDialog.setVisible(true);
	            return;
	        }
	        JOptionPane.showMessageDialog(this, "Custodio creado correctamente.");
	        controlador.agregarTrabajador(c);
	        dispose();

	    } catch (IllegalArgumentException ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
