package reportes;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Zoologico;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReporteOcupacion extends JDialog {

	private final JPanel contentPanel = new JPanel();	

	public ReporteOcupacion(Zoologico controlador) {
		
		setTitle("Reporte de Ocupación de Celdas");
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 0, 580, 443);
		contentPanel.add(scrollPane);
		ArrayList<String> reporte = controlador.getReporteOcupacionCeldas();
        for (String linea : reporte) {
            textArea.append(linea + "\n");
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
