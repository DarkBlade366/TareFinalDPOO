package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import clases.Especie;
import clases.Zoologico;
import enumes.Alimentacion;
import enumes.TipoEntorno;

public class EditarEspecie extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombreComun;
	private JTextField textFieldEsperanzaVida;
	private JTextField textFieldNombreCientifico;
	private JTextField textFieldPesoPromedio;
	private JTextField textFieldTamano;
	private JTextField textFieldCantComida;
    private JComboBox<Alimentacion> comboBoxAlimentacion;
    private JComboBox<TipoEntorno> comboBoxTipoEntorno;
    private Zoologico controlador;
    private Zoo ventanaPrincipal;
    private Especie especie;

	public EditarEspecie(Zoologico controlador, Zoo ventanaPrincipal, Especie especie) {
		this.controlador = controlador;
		this.ventanaPrincipal = ventanaPrincipal;
		this.especie = especie;

		setTitle("Editar Especie");
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNombreComun = new JLabel("NOMBRE COMUN");
		lblNombreComun.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblNombreComun.setBounds(21, 21, 226, 29);
		contentPanel.add(lblNombreComun);

		textFieldNombreComun = new JTextField(especie.getNombreComun());
		textFieldNombreComun.setBounds(21, 67, 186, 32);
		contentPanel.add(textFieldNombreComun);

		JLabel lblNombreCientifico = new JLabel("NOMBRE CIENTIFICO");
		lblNombreCientifico.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblNombreCientifico.setBounds(333, 21, 226, 29);
		contentPanel.add(lblNombreCientifico);

		textFieldNombreCientifico = new JTextField(especie.getNombreCientifico());
		textFieldNombreCientifico.setBounds(333, 67, 186, 32);
		contentPanel.add(textFieldNombreCientifico);

		JLabel lblEsperanzaDeVida = new JLabel("ESPERANZA DE VIDA");
		lblEsperanzaDeVida.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblEsperanzaDeVida.setBounds(21, 120, 226, 29);
		contentPanel.add(lblEsperanzaDeVida);

		textFieldEsperanzaVida = new JTextField(String.valueOf(especie.getEsperanza()));
		textFieldEsperanzaVida.setBounds(21, 170, 186, 32);
		contentPanel.add(textFieldEsperanzaVida);

		JLabel lblPesoPromedio = new JLabel("PESO PROMEDIO");
		lblPesoPromedio.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblPesoPromedio.setBounds(333, 122, 226, 29);
		contentPanel.add(lblPesoPromedio);

		textFieldPesoPromedio = new JTextField(String.valueOf(especie.getPesoProm()));
		textFieldPesoPromedio.setBounds(333, 170, 186, 32);
		contentPanel.add(textFieldPesoPromedio);

		JLabel lblTamanio = new JLabel("TAMAÑO");
		lblTamanio.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblTamanio.setBounds(21, 227, 226, 29);
		contentPanel.add(lblTamanio);

		textFieldTamano = new JTextField(String.valueOf(especie.getTamaño()));
		textFieldTamano.setBounds(21, 277, 186, 32);
		contentPanel.add(textFieldTamano);

		JLabel lblCantComida = new JLabel("CANT. DE COMIDA");
		lblCantComida.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblCantComida.setBounds(333, 227, 226, 29);
		contentPanel.add(lblCantComida);

		textFieldCantComida = new JTextField(String.valueOf(especie.getCantComida()));
		textFieldCantComida.setBounds(333, 277, 186, 32);
		contentPanel.add(textFieldCantComida);

		JLabel lblAlimentacion = new JLabel("ALIMENTACION");
		lblAlimentacion.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblAlimentacion.setBounds(21, 330, 226, 29);
		contentPanel.add(lblAlimentacion);

		comboBoxAlimentacion = new JComboBox<>();
		comboBoxAlimentacion.setModel(new DefaultComboBoxModel<>(Alimentacion.values()));
		comboBoxAlimentacion.setSelectedItem(especie.getAlimentacion());
		comboBoxAlimentacion.setBounds(21, 379, 186, 32);
		contentPanel.add(comboBoxAlimentacion);

		JLabel lblTipoEntorno = new JLabel("TIPO DE ENTORNO");
		lblTipoEntorno.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblTipoEntorno.setBounds(333, 329, 226, 29);
		contentPanel.add(lblTipoEntorno);

		comboBoxTipoEntorno = new JComboBox<>();
		comboBoxTipoEntorno.setModel(new DefaultComboBoxModel<>(TipoEntorno.values()));
		comboBoxTipoEntorno.setSelectedItem(especie.getEntorno());
		comboBoxTipoEntorno.setBounds(333, 379, 186, 32);
		contentPanel.add(comboBoxTipoEntorno);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("GUARDAR");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editarEspecie();
				}
			});
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("CANCELAR");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			buttonPane.add(cancelButton);
		}
	}

	private void editarEspecie() {
		try {
			especie.setNombreComun(textFieldNombreComun.getText().trim());
			especie.setNombreCientifico(textFieldNombreCientifico.getText().trim());
			especie.setEsperanza(Integer.parseInt(textFieldEsperanzaVida.getText().trim()));
			especie.setPesoProm(Double.parseDouble(textFieldPesoPromedio.getText().trim()));
			especie.setTamaño(Double.parseDouble(textFieldTamano.getText().trim()));
			especie.setCantComida(Double.parseDouble(textFieldCantComida.getText().trim()));
			especie.setAlimentacion((Alimentacion) comboBoxAlimentacion.getSelectedItem());
			especie.setEntorno((TipoEntorno) comboBoxTipoEntorno.getSelectedItem());

			ventanaPrincipal.actualizarTablaEspecie();
			ventanaPrincipal.actualizarResumen();
			ventanaPrincipal.actualizarTablaCeldas();
			ventanaPrincipal.actualizarTablaAnimales();
			ventanaPrincipal.actualizarTablaCuidadores();

			JOptionPane.showMessageDialog(this, "Especie actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al editar especie: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
