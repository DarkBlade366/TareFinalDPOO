package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import clases.Animal;
import clases.Celda;
import clases.Especie;
import clases.Zoologico;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import enumes.Disponibilidad;

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

public class EditarAnimal extends JDialog {
	public EditarAnimal() {
	}

	private final JPanel contentPanel = new JPanel();
	private Zoologico controlador;
    private Animal animal;
    private JTextField textFieldId;
    private JComboBox<Sexo> comboBoxSexo;
    private JComboBox<Celda> comboBoxCelda;
    private JComboBox<Especie> comboBoxEspecie;
    private JSpinner spinnerNacimiento;
    private Zoo ventanaPrincipal;

    public EditarAnimal(final Zoologico controlador, Zoo ventanaPrincipal, final Animal animal) {
        this.controlador = controlador;
        this.ventanaPrincipal = ventanaPrincipal;
        this.animal = animal;

        setTitle("Editar Animal");
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
            textFieldId = new JTextField();
            textFieldId.setColumns(10);
            textFieldId.setBounds(52, 86, 186, 32);
            textFieldId.setEditable(false); 
            contentPanel.add(textFieldId);
        }
        {
            comboBoxSexo = new JComboBox<Sexo>();
            comboBoxSexo.setModel(new DefaultComboBoxModel<>(Sexo.values()));
            comboBoxSexo.setBounds(52, 228, 186, 32);
            contentPanel.add(comboBoxSexo);
        }
        {
            comboBoxEspecie = new JComboBox<Especie>();
            ArrayList<Especie> especies = controlador.getEspecies();
            comboBoxEspecie.setModel(new DefaultComboBoxModel<>(especies.toArray(new Especie[0])));
            comboBoxEspecie.setBounds(300, 228, 186, 32);
            contentPanel.add(comboBoxEspecie);
            
            comboBoxEspecie.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Especie especieSeleccionada = (Especie) comboBoxEspecie.getSelectedItem();
                    if (especieSeleccionada != null) {
                        ArrayList<Celda> celdasCompatibles = new ArrayList<>();
                        for (Celda celda : controlador.getTodasLasCeldas()) {
                            if ((celda.getDisponibilidad() == Disponibilidad.DISPONIBLE || celda.equals(animal.getCelda())) &&
                                celda.tieneCapacidad() &&
                                celda.esCompatibleCon(especieSeleccionada)) {
                                celdasCompatibles.add(celda);
                            }
                        }
                        if (!celdasCompatibles.contains(animal.getCelda())) {
                            celdasCompatibles.add(animal.getCelda());
                        }
                        comboBoxCelda.setModel(new DefaultComboBoxModel<>(celdasCompatibles.toArray(new Celda[0])));
                        comboBoxCelda.setSelectedItem(animal.getCelda());
                    }
                }
            });
        }
        {
            ArrayList<Celda> celdas = controlador.getTodasLasCeldas();
            ArrayList<Celda> celdasDisponibles = new ArrayList<>();

            for (Celda c : celdas) {
                if (c.getDisponibilidad() == Disponibilidad.DISPONIBLE || c.equals(animal.getCelda())) {
                    celdasDisponibles.add(c);
                }
            }

            comboBoxCelda = new JComboBox<Celda>();
            comboBoxCelda.setModel(new DefaultComboBoxModel<>(celdasDisponibles.toArray(new Celda[0])));
            comboBoxCelda.setBounds(52, 343, 434, 32);
            contentPanel.add(comboBoxCelda);
        }
        Date fechaNacimientoDate = Date.from(animal.getNacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant());
        SpinnerDateModel modeloFecha = new SpinnerDateModel(
                fechaNacimientoDate,
                null,
                new Date(),
                Calendar.DAY_OF_YEAR
        );
        spinnerNacimiento = new JSpinner(modeloFecha);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerNacimiento, "dd/MM/yyyy");
        spinnerNacimiento.setEditor(editor);
        spinnerNacimiento.setBounds(310, 86, 179, 32);
        contentPanel.add(spinnerNacimiento);

        comboBoxEspecie.setSelectedItem(animal.getEspecie());
        setLocationRelativeTo(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnGuardar = new JButton("GUARDAR");
                btnGuardar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        guardarCambios();
                    }
                });
                btnGuardar.setActionCommand("OK");
                buttonPane.add(btnGuardar);
                getRootPane().setDefaultButton(btnGuardar);
            }
            {
                JButton btnCancelar = new JButton("CANCELAR");
                btnCancelar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                btnCancelar.setActionCommand("Cancel");
                buttonPane.add(btnCancelar);
            }
        }

        cargarDatosAnimal();
    }

    private void cargarDatosAnimal() {
        textFieldId.setText(String.valueOf(animal.getId()));	 
        comboBoxSexo.setSelectedItem(animal.getSexo());
        comboBoxEspecie.setSelectedItem(animal.getEspecie());
        comboBoxCelda.setSelectedItem(animal.getCelda());

        Date fecha = Date.from(animal.getNacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant());
        spinnerNacimiento.setValue(fecha);
    }

    private void guardarCambios() {
        try {
            int nuevoId = Integer.parseInt(textFieldId.getText().trim());

            Sexo nuevoSexo = (Sexo) comboBoxSexo.getSelectedItem();
            if (nuevoSexo == null) throw new IllegalArgumentException("Debe seleccionar un sexo.");

            Especie nuevaEspecie = (Especie) comboBoxEspecie.getSelectedItem();
            if (nuevaEspecie == null) throw new IllegalArgumentException("Debe seleccionar una especie.");

            Celda nuevaCelda = (Celda) comboBoxCelda.getSelectedItem();
            if (nuevaCelda == null) throw new IllegalArgumentException("Debe seleccionar una celda.");

            Date fechaDate = (Date) spinnerNacimiento.getValue();
            LocalDate nuevaFecha = fechaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            animal.setSexo(nuevoSexo);
            animal.setNacimiento(nuevaFecha);

            if (!animal.getCelda().equals(nuevaCelda)) {
                animal.getCelda().eliminarAnimal(animal); 
                nuevaCelda.agregarAnimal(animal);         
                animal.setCelda(nuevaCelda);              
            }

            if (!animal.getEspecie().equals(nuevaEspecie)) {
                animal.getEspecie().eliminarAnimal(animal); 
                nuevaEspecie.agregarAnimal(animal);         
                animal.setEspecie(nuevaEspecie);            
            }

			ventanaPrincipal.actualizarTablaEspecie();
			ventanaPrincipal.actualizarResumen();
			ventanaPrincipal.actualizarTablaCeldas();
			ventanaPrincipal.actualizarTablaAnimales();
			ventanaPrincipal.actualizarTablaCuidadores();
			
            JOptionPane.showMessageDialog(this, "Animal editado correctamente.");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}