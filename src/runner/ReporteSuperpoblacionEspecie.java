package runner;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Zoologico;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ReporteSuperpoblacionEspecie extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;


	public ReporteSuperpoblacionEspecie(Zoologico controlador) {

		setTitle("Especies en Riesgo de Superpoblación");
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(0, 0, 580, 443);
		contentPanel.add(textArea);

		JScrollPane scrollPane = new JScrollPane(textArea);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBounds(0, 0, 580, 2);


		ArrayList<String> reporteList = controlador.getReporteSuperpoblacionEspecies();
		StringBuilder builder = new StringBuilder();
		for (String linea : reporteList) {
		    builder.append(linea).append("\n");
		}
		textArea.setText(builder.toString());

	        
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
