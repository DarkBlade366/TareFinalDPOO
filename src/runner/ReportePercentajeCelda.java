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

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ReportePercentajeCelda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public ReportePercentajeCelda(Zoologico controlador) {
		
		setTitle("Reporte Porcentaje de Celdas");
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		 contentPanel.setLayout(new BorderLayout());
		{
			textArea = new JTextArea();
			textArea.setEditable(false);
	        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		}
		String reporte = controlador.getReportePorcentajeCeldas();
		textArea.setText(reporte);
        {
        	scrollPane = new JScrollPane(textArea);
        	contentPanel.add(scrollPane, BorderLayout.CENTER);
        }

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
