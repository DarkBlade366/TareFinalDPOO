package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Animal;
import clases.Celda;
import clases.Especie;
import clases.Zoologico;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import enumes.Disponibilidad;
import enumes.TipoEntorno;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import enumes.Sexo;

import javax.swing.DefaultComboBoxModel;

import runner.Zoo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearAnimal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	
	private Zoologico controlador;
	private JTextField textField;
    private JComboBox<Sexo> comboBoxSexo;
    private JComboBox<Celda> comboBoxCelda;
    private JComboBox<Especie> comboBoxEspecie;
    private JSpinner spinner;
    private Zoo ventanaPrincipal;


	public CrearAnimal(Zoologico controlador, Zoo ventanaPrincipal) {
		this.controlador = controlador;
	    this.ventanaPrincipal = ventanaPrincipal;
		
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("IDENTIFICADOR");
			label.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			label.setBounds(52, 41, 168, 26);
			contentPanel.add(label);
		}
		{
			JLabel lblSexo = new JLabel("SEXO");
			lblSexo.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblSexo.setBounds(52, 181, 168, 26);
			contentPanel.add(lblSexo);
		}
		{
			JLabel lblFechaDeNacimiento = new JLabel("FECHA DE NACIMIENTO");
			lblFechaDeNacimiento.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblFechaDeNacimiento.setBounds(300, 41, 259, 26);
			contentPanel.add(lblFechaDeNacimiento);
		}
		{
			JLabel lblEspecie = new JLabel("ESPECIE");
			lblEspecie.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblEspecie.setBounds(302, 181, 168, 26);
			contentPanel.add(lblEspecie);
		}
		{
			JLabel lblCelda = new JLabel("CELDA");
			lblCelda.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblCelda.setBounds(52, 296, 168, 26);
			contentPanel.add(lblCelda);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(52, 86, 186, 32);
			contentPanel.add(textField);
		}
		{
			comboBoxSexo = new JComboBox<Sexo>();
			comboBoxSexo.setModel(new DefaultComboBoxModel(Sexo.values()));
			comboBoxSexo.setBounds(52, 228, 186, 32);
			contentPanel.add(comboBoxSexo);
		}
		{
			comboBoxEspecie = new JComboBox<Especie>();
			ArrayList<Especie> especies = controlador.getEspecies();
			comboBoxEspecie.setModel(new DefaultComboBoxModel<>(especies.toArray(new Especie[0])));	
			comboBoxEspecie.setBounds(300, 228, 186, 32);
			contentPanel.add(comboBoxEspecie);
		}
		{
			ArrayList<Celda> celdas = controlador.getTodasLasCeldas();
			ArrayList<Celda> celdasDisponibles = new ArrayList<>();

			for (Celda c : celdas) {
			    if (c.getDisponibilidad() == Disponibilidad.DISPONIBLE) {
			        celdasDisponibles.add(c);
			    }
			}
			
			comboBoxCelda = new JComboBox<Celda>();
			comboBoxCelda.setModel(new DefaultComboBoxModel<>(celdasDisponibles.toArray(new Celda[0])));	
			comboBoxCelda.setBounds(52, 343, 434, 32);
			contentPanel.add(comboBoxCelda);
		}
		
		Date fechaHoy = new Date();

		SpinnerDateModel modeloFecha = new SpinnerDateModel(
			    fechaHoy, 
			    null,                     
			    fechaHoy, 
			    Calendar.DAY_OF_YEAR      
			);
		spinner = new JSpinner(modeloFecha);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
		spinner.setEditor(editor);
		spinner.setBounds(310, 86, 179, 32);
		contentPanel.add(spinner);
		
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CREAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearAnimal();						
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
	private void crearAnimal() {
	    try {
	        int id = Integer.parseInt(textField.getText().trim());

	        Sexo sexo = (Sexo) comboBoxSexo.getSelectedItem();
	        if (sexo == null) throw new IllegalArgumentException("Debe seleccionar un sexo.");

	        Especie especie = (Especie) comboBoxEspecie.getSelectedItem();
	        if (especie == null) throw new IllegalArgumentException("Debe seleccionar una especie.");

	        Celda celda = (Celda) comboBoxCelda.getSelectedItem();
	        if (celda == null) throw new IllegalArgumentException("Debe seleccionar una celda.");

	        Date fechaDate = (Date) spinner.getValue();
	        LocalDate nacimiento = fechaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        
	        Animal nuevoAnimal = controlador.agregarAnimal(id, nacimiento, sexo, especie, celda);
	        if (nuevoAnimal == null) throw new IllegalStateException("No se pudo crear el animal");

	        celda.agregarAnimal(nuevoAnimal);
	        nuevoAnimal.setCelda(celda);
	        
			ventanaPrincipal.actualizarTablaEspecie();
			ventanaPrincipal.actualizarResumen();
			ventanaPrincipal.actualizarTablaCeldas();
			ventanaPrincipal.actualizarTablaAnimales();
			ventanaPrincipal.actualizarTablaCuidadores();

	        
	        JOptionPane.showMessageDialog(this, "Animal creado exitosamente.");
	        dispose();
	        
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (IllegalArgumentException | IllegalStateException ex) {
	        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
