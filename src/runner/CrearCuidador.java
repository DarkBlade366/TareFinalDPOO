package runner;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Celda;
import clases.Cuidador;
import clases.Especie;
import clases.Zoologico;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import enumes.TipoEntorno;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearCuidador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Zoologico controlador;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JComboBox<Celda> comboBoxCelda;
    private JSpinner spinnerInicio;
    private JSpinner spinnerFin;
    private ArrayList<Celda> celdasDisponibles;
	
	public CrearCuidador(Zoologico controlador, ArrayList<Celda> celdasDisponibles) {
		this.controlador = controlador;
		this.celdasDisponibles = celdasDisponibles;
		
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("NOMBRE");
			lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblNombre.setBounds(21, 63, 168, 26);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblHoraInicio = new JLabel("HORA EN QUE INICIO");
			lblHoraInicio.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblHoraInicio.setBounds(21, 177, 234, 26);
			contentPanel.add(lblHoraInicio);
		}
		{
			JLabel lblCarnet = new JLabel("CARNET");
			lblCarnet.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblCarnet.setBounds(305, 63, 168, 26);
			contentPanel.add(lblCarnet);
		}
		{
			JLabel lblCeldasAtendidas = new JLabel("CELDAS ATENDIDAS");
			lblCeldasAtendidas.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblCeldasAtendidas.setBounds(305, 304, 216, 26);
			contentPanel.add(lblCeldasAtendidas);
		}
		{
			JLabel lblHoraTermina = new JLabel("HORA EN QUE TERMINA");
			lblHoraTermina.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblHoraTermina.setBounds(305, 177, 254, 26);
			contentPanel.add(lblHoraTermina);
		}
		{
			JLabel lblHorasTrabajadas = new JLabel("HORAS TRABAJADAS");
			lblHorasTrabajadas.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblHorasTrabajadas.setBounds(21, 304, 234, 26);
			contentPanel.add(lblHorasTrabajadas);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(21, 104, 186, 32);
			contentPanel.add(textField);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(305, 104, 186, 32);
			contentPanel.add(textField_1);
		}
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(21, 356, 186, 32);
			contentPanel.add(textField_4);
		}
		{

		}
		{
			spinnerInicio = new JSpinner();
			spinnerInicio.setModel(new SpinnerNumberModel(1, 1, 24, 1));
			spinnerInicio.setBounds(31, 233, 168, 32);
			contentPanel.add(spinnerInicio);
		}
		{
			spinnerFin = new JSpinner();
			spinnerFin.setModel(new SpinnerNumberModel(24, 1, 24, 1));
			spinnerFin.setBounds(305, 233, 168, 32);
			contentPanel.add(spinnerFin);
		}

		if (celdasDisponibles.isEmpty()) {
		    JOptionPane.showMessageDialog(this, "No hay celdas disponibles para agregar cuidadores", "Atención", JOptionPane.WARNING_MESSAGE);
		    dispose();
		} else {
		    comboBoxCelda = new JComboBox<>(celdasDisponibles.toArray(new Celda[0]));
		    comboBoxCelda.setBounds(305, 356, 256, 32);
		    contentPanel.add(comboBoxCelda);
		}
		
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CREAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearCuidador();
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
						dispose();					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void crearCuidador() {
	    try {
	        String nombre = textField.getText().trim();
	        String carnet = textField_1.getText().trim();
	        int horasTrabajadas = Integer.parseInt(textField_4.getText().trim());

	        int inicio = (int) spinnerInicio.getValue();
	        int fin = (int) spinnerFin.getValue();
	        Celda celda = (Celda) comboBoxCelda.getSelectedItem();
	        
	        if (!celda.puedeAgregarCuidador(inicio, fin)) {
	            throw new IllegalArgumentException("Ya hay cuidadores con horarios solapados en esta celda.");
	        }
	        
	        if (inicio >= fin) {
	            throw new IllegalArgumentException("La hora de inicio debe ser menor que la hora de fin.");
	        }


	        Cuidador nuevo = new Cuidador(nombre, carnet, horasTrabajadas, inicio, fin);
	        controlador.agregarTrabajador(nuevo);
	        celda.agregarCuidador(nuevo);;

	        JOptionPane.showMessageDialog(this, "Cuidador creado y asignado exitosamente.");
	        dispose();

	    } catch (IllegalArgumentException ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	

}
