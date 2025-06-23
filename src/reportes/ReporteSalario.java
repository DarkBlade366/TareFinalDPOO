package reportes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Zoologico;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReporteSalario extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public ReporteSalario(Zoologico controlador) {

		setTitle("Reporte Salario Total");
		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(0, 0, 580, 443);
		contentPanel.add(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 0, 580, 443);

		double totalSalario = controlador.getSalarioTotal();
        textArea.setText(String.format("Salario total a pagar por el zoológico:\n$ %.2f", totalSalario));

        getContentPane().add(scrollPane, BorderLayout.CENTER);
		
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
