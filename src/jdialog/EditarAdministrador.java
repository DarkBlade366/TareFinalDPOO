package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import runner.Zoo;
import clases.Administrativo;
import clases.Zoologico;

public class EditarAdministrador extends JDialog {

	private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JTextField txtCarnet;
    private JTextField txtPuesto;
    private JTextField txtOficina;
    private JSpinner spinnerAnosExp;

    private Administrativo admin;
    private Zoologico controlador;
    private Zoo ventanaPrincipal;

    public EditarAdministrador(Administrativo admin, Zoologico controlador, Zoo ventanaPrincipal) {
        this.admin = admin;
        this.controlador = controlador;
        this.ventanaPrincipal = ventanaPrincipal;
        
        setTitle("Editar Administrativo");
        setBounds(100, 100, 606, 559);
        setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNombre = new JLabel("NOMBRE");
        lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
        lblNombre.setBounds(21, 42, 168, 26);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField(admin.getNombre());
        txtNombre.setBounds(21, 81, 186, 32);
        contentPanel.add(txtNombre);

        JLabel lblCarnet = new JLabel("CARNET");
        lblCarnet.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
        lblCarnet.setBounds(332, 42, 168, 26);
        contentPanel.add(lblCarnet);

        txtCarnet = new JTextField(admin.getNumCarnet());
        txtCarnet.setEditable(false);
        txtCarnet.setBounds(325, 81, 186, 32);
        contentPanel.add(txtCarnet);

        JLabel lblOficina = new JLabel("OFICINA");
        lblOficina.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
        lblOficina.setBounds(21, 163, 168, 26);
        contentPanel.add(lblOficina);

        txtOficina = new JTextField(admin.getOficina());
        txtOficina.setBounds(21, 208, 186, 32);
        contentPanel.add(txtOficina);

        JLabel lblPuesto = new JLabel("PUESTO");
        lblPuesto.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
        lblPuesto.setBounds(332, 163, 168, 26);
        contentPanel.add(lblPuesto);

        txtPuesto = new JTextField(admin.getPuestoTrabajo());
        txtPuesto.setBounds(325, 198, 186, 32);
        contentPanel.add(txtPuesto);

        JLabel lblAnos = new JLabel("AÑOS DE EXPERIENCIA");
        lblAnos.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
        lblAnos.setBounds(21, 292, 304, 26);
        contentPanel.add(lblAnos);

        spinnerAnosExp = new JSpinner(new SpinnerNumberModel(admin.getAnyosExp(), 0, 60, 1));
        spinnerAnosExp.setBounds(21, 331, 168, 32);
        contentPanel.add(spinnerAnosExp);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("GUARDAR");
		        btnGuardar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		        btnGuardar.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                guardarCambios();
		            }
		        });
		        buttonPane.add(btnGuardar);

			}
			{
				JButton cancelButton = new JButton("Cancel");
		        JButton btnCancelar = new JButton("CANCELAR");
		        btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		        btnCancelar.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                dispose();
		            }
		        });
		        buttonPane.add(btnCancelar);
			}
		}
	}
    private void guardarCambios() {
        try {
            String nombre = txtNombre.getText().trim();
            String puesto = txtPuesto.getText().trim();
            String oficina = txtOficina.getText().trim();
            int anos = (int) spinnerAnosExp.getValue();

            if (nombre.isEmpty() || puesto.isEmpty() || oficina.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar completos.");
            }

            admin.setNombre(nombre);
            admin.setPuestoTrabajo(puesto);
            admin.setOficina(oficina);
            admin.setAnyosExp(anos);

            ventanaPrincipal.actualizarTablaAdministrativos();
            ventanaPrincipal.actualizarResumen();

            JOptionPane.showMessageDialog(this, "Administrativo actualizado correctamente.");
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
