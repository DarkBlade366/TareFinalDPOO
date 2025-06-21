package runner;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import enumes.Disponibilidad;
import enumes.TipoEntorno;

import javax.swing.DefaultComboBoxModel;

import clases.Celda;
import clases.Zoologico;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearCelda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID;
	private JTextField textFieldCapTotal;
    private JComboBox<Disponibilidad> comboBoxDisponibilidad;
    private JComboBox<TipoEntorno> comboBoxTipoEntorno;
    private Zoologico controlador;
    
    
    
	public CrearCelda(Zoologico controlador) {
		this.controlador = controlador;
		
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblIdentificador = new JLabel("IDENTIFICADOR");
		lblIdentificador.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblIdentificador.setBounds(49, 80, 168, 26);
		contentPanel.add(lblIdentificador);
		
		JLabel lblCapacidadTotal = new JLabel("CAPACIDAD TOTAL");
		lblCapacidadTotal.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblCapacidadTotal.setBounds(348, 80, 212, 26);
		contentPanel.add(lblCapacidadTotal);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(46, 117, 186, 32);
		contentPanel.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldCapTotal = new JTextField();
		textFieldCapTotal.setBounds(364, 117, 186, 32);
		contentPanel.add(textFieldCapTotal);
		textFieldCapTotal.setColumns(10);
		
		JLabel lblDisponibilidad = new JLabel("DISPONIBILIDAD");
		lblDisponibilidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblDisponibilidad.setBounds(46, 262, 186, 26);
		contentPanel.add(lblDisponibilidad);
		
		JLabel lblTipoDeEntorno = new JLabel("TIPO DE ENTORNO");
		lblTipoDeEntorno.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblTipoDeEntorno.setBounds(358, 262, 202, 26);
		contentPanel.add(lblTipoDeEntorno);
		
		comboBoxDisponibilidad = new JComboBox<Disponibilidad>();
		comboBoxDisponibilidad.setModel(new DefaultComboBoxModel(Disponibilidad.values()));
		comboBoxDisponibilidad.setBounds(46, 309, 186, 32);
		contentPanel.add(comboBoxDisponibilidad);
		
		comboBoxTipoEntorno = new JComboBox<TipoEntorno>();
		comboBoxTipoEntorno.setModel(new DefaultComboBoxModel(TipoEntorno.values()));
		comboBoxTipoEntorno.setBounds(364, 309, 186, 32);
		contentPanel.add(comboBoxTipoEntorno);
		
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton CREAR = new JButton("CREAR");
				CREAR.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				        crearCelda();
					}

				});
				CREAR.setActionCommand("OK");
				buttonPane.add(CREAR);
				getRootPane().setDefaultButton(CREAR);
			}
			{
				JButton CANCEL = new JButton("CANCEL");
				CANCEL.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				CANCEL.setActionCommand("Cancel");
				buttonPane.add(CANCEL);
			}
		}

	}
	private void crearCelda() {
	        try {
	            String id = textFieldID.getText().trim();
	            String capTotalStr = textFieldCapTotal.getText().trim();
	            
	            if (id.isEmpty() || capTotalStr.isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            int capTotal = Integer.parseInt(capTotalStr);
	            Disponibilidad disponibilidad = (Disponibilidad) comboBoxDisponibilidad.getSelectedItem();
	            TipoEntorno tipoEntorno = (TipoEntorno) comboBoxTipoEntorno.getSelectedItem();
	            
	            controlador.agregarCelda(id, disponibilidad, capTotal, tipoEntorno);
	            
	            JOptionPane.showMessageDialog(this, "Celda creada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            dispose();
	            
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "Capacidad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (IllegalArgumentException e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	
}