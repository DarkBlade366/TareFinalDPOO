package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import runner.Zoo;

import java.awt.Font;

import clases.Celda;
import clases.Zoologico;
import enumes.Alimentacion;
import enumes.Disponibilidad;
import enumes.TipoEntorno;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCelda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField textFieldCapTotal;
	private JComboBox<TipoEntorno> comboBoxTipoEntorno;
	private JComboBox<Disponibilidad> comboBoxDisponibilidad;

	private Celda celda;
	private Zoologico zoologico;
	private Zoo ventanaPrincipal;

	public EditarCelda(Zoologico zoologico, Celda celda, Zoo ventanaPrincipal) {
		this.zoologico = zoologico;
		this.celda = celda;
		this.ventanaPrincipal = ventanaPrincipal;

		setTitle("Editar Celda");
		setLocationRelativeTo(null);
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
		
        txtId = new JTextField(celda.getId());
        txtId.setBounds(46, 117, 186, 32);
        contentPanel.add(txtId);
        txtId.setColumns(10);        

        textFieldCapTotal = new JTextField(String.valueOf(celda.getCapacidadTotal()));
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
        comboBoxDisponibilidad.setSelectedItem(celda.getDisponibilidad());
        contentPanel.add(comboBoxDisponibilidad);
        
        comboBoxTipoEntorno = new JComboBox<TipoEntorno>();
        comboBoxTipoEntorno.setModel(new DefaultComboBoxModel(TipoEntorno.values()));
        comboBoxTipoEntorno.setBounds(364, 309, 186, 32);
        comboBoxTipoEntorno.setSelectedItem(celda.getEntorno());
        contentPanel.add(comboBoxTipoEntorno);

        setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("GUARDAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 editarCelda();
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
	private void editarCelda() {
        try {
            String id = txtId.getText().trim();
            String capTotalStr = textFieldCapTotal.getText().trim();

            if (id.isEmpty() || capTotalStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int capTotal = Integer.parseInt(capTotalStr);
            Disponibilidad disponibilidad = (Disponibilidad) comboBoxDisponibilidad.getSelectedItem();
            TipoEntorno tipoEntorno = (TipoEntorno) comboBoxTipoEntorno.getSelectedItem();


            celda.setId(id);
            celda.setCapacidadTotal(capTotal);
            celda.setDisponibilidad(disponibilidad);
            celda.setEntorno(tipoEntorno);

			ventanaPrincipal.actualizarTablaEspecie();
			ventanaPrincipal.actualizarResumen();
			ventanaPrincipal.actualizarTablaCeldas();
			ventanaPrincipal.actualizarTablaAnimales();
			ventanaPrincipal.actualizarTablaCuidadores();
			ventanaPrincipal.actualizarTablaVeterinarios();

            JOptionPane.showMessageDialog(this, "Celda actualizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Capacidad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }
}


