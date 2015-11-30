import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JSpinner;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Settings extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 307, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel clustersAmountLabel = new JLabel("Cantidad de clusters:");
		GridBagConstraints gbc_clustersAmountLabel = new GridBagConstraints();
		gbc_clustersAmountLabel.anchor = GridBagConstraints.WEST;
		gbc_clustersAmountLabel.insets = new Insets(0, 0, 5, 0);
		gbc_clustersAmountLabel.gridx = 1;
		gbc_clustersAmountLabel.gridy = 1;
		contentPane.add(clustersAmountLabel, gbc_clustersAmountLabel);
		
		JSpinner clustersSpinner = new JSpinner();
		GridBagConstraints gbc_clustersSpinner = new GridBagConstraints();
		gbc_clustersSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_clustersSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_clustersSpinner.gridx = 1;
		gbc_clustersSpinner.gridy = 2;
		contentPane.add(clustersSpinner, gbc_clustersSpinner);
		
		JCheckBox kAutomaticClustersCheckBox = new JCheckBox("k clusters automáticos");
		GridBagConstraints gbc_kAutomaticClustersCheckBox = new GridBagConstraints();
		gbc_kAutomaticClustersCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_kAutomaticClustersCheckBox.anchor = GridBagConstraints.WEST;
		gbc_kAutomaticClustersCheckBox.gridx = 1;
		gbc_kAutomaticClustersCheckBox.gridy = 3;
		contentPane.add(kAutomaticClustersCheckBox, gbc_kAutomaticClustersCheckBox);
		
		JButton saveButton = new JButton("Guardar");
		saveButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.setClusters((Integer)clustersSpinner.getValue());
			}
			
		});
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.gridx = 1;
		gbc_saveButton.gridy = 4;
		contentPane.add(saveButton, gbc_saveButton);
	}

}
