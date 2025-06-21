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
import enumes.Alimentacion;

import javax.swing.DefaultComboBoxModel;

import clases.Especie;
import clases.Zoologico;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearEspecie extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
    private JComboBox<Alimentacion> comboBoxAlimentacion;
    private JComboBox<TipoEntorno> comboBoxTipoEntorno;
    private Zoologico controlador;
	

	public CrearEspecie(Zoologico controlador) {
		this.controlador = controlador;
		
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		setLocationRelativeTo(null);
		{
			JLabel lblEsperanzaDeVida = new JLabel("ESPERANZA DE VIDA");
			lblEsperanzaDeVida.setBounds(21, 120, 226, 29);
			lblEsperanzaDeVida.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			contentPanel.add(lblEsperanzaDeVida);
		}
		
		JLabel lblNombreComun = new JLabel("NOMBRE COMUN");
		lblNombreComun.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblNombreComun.setBounds(21, 21, 226, 29);
		contentPanel.add(lblNombreComun);
		
		JLabel lblNombreCientifico = new JLabel("NOMBRE CIENTIFICO");
		lblNombreCientifico.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblNombreCientifico.setBounds(333, 21, 226, 29);
		contentPanel.add(lblNombreCientifico);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(21, 67, 186, 32);
		contentPanel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(21, 170, 186, 32);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(333, 67, 186, 32);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(333, 170, 186, 32);
		contentPanel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(21, 277, 186, 32);
		contentPanel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(333, 277, 186, 32);
		contentPanel.add(textField_5);
		
		comboBoxAlimentacion = new JComboBox<Alimentacion>();
		comboBoxAlimentacion.setModel(new DefaultComboBoxModel(Alimentacion.values()));
		comboBoxAlimentacion.setBounds(21, 379, 186, 32);
		contentPanel.add(comboBoxAlimentacion);
		
		comboBoxTipoEntorno = new JComboBox<TipoEntorno>();
		comboBoxTipoEntorno.setModel(new DefaultComboBoxModel(TipoEntorno.values()));
		comboBoxTipoEntorno.setBounds(333, 379, 186, 32);
		contentPanel.add(comboBoxTipoEntorno);
		
		JLabel lblPesoPromedio = new JLabel("PESO PROMEDIO");
		lblPesoPromedio.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblPesoPromedio.setBounds(333, 122, 226, 29);
		contentPanel.add(lblPesoPromedio);
		
		JLabel lblTamao = new JLabel("TAMAÑO");
		lblTamao.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblTamao.setBounds(21, 227, 226, 29);
		contentPanel.add(lblTamao);
		
		JLabel lblCantidadDeComida = new JLabel("CANT. DE COMIDA");
		lblCantidadDeComida.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblCantidadDeComida.setBounds(333, 227, 226, 29);
		contentPanel.add(lblCantidadDeComida);
		
		JLabel lblAlimentacion = new JLabel("ALIMENTACION");
		lblAlimentacion.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblAlimentacion.setBounds(21, 330, 226, 29);
		contentPanel.add(lblAlimentacion);
		
		JLabel lblTipoDeEntorno = new JLabel("TIPO DE ENTORNO");
		lblTipoDeEntorno.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblTipoDeEntorno.setBounds(333, 329, 226, 29);
		contentPanel.add(lblTipoDeEntorno);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CREAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearEspecie();
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

	private void crearEspecie() {
	    try {
	        String nombreComun = textField.getText().trim();
	        String nombreCientifico = textField_2.getText().trim();
	        int esperanzaVida = Integer.parseInt(textField_1.getText().trim());
	        double pesoPromedio = Double.parseDouble(textField_3.getText().trim());
	        double tamano = Double.parseDouble(textField_4.getText().trim());
	        double cantidadComida = Double.parseDouble(textField_5.getText().trim());
	        Alimentacion alimentacion = (Alimentacion) comboBoxAlimentacion.getSelectedItem();
	        TipoEntorno tipoEntorno = (TipoEntorno) comboBoxTipoEntorno.getSelectedItem();

	        controlador.agregarEspecie(nombreComun, nombreCientifico, esperanzaVida, pesoPromedio, tamano, cantidadComida, alimentacion, tipoEntorno);
	        JOptionPane.showMessageDialog(this, "Especie creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        dispose();
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Verifica que los valores numéricos estén correctamente escritos.", "Error de formato", JOptionPane.ERROR_MESSAGE);

	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

}
