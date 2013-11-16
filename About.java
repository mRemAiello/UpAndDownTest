import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class About extends javax.swing.JFrame
{	
	/** Creates new form About */
	public About() {
		initComponents();
	}

	private void initComponents()
	{
		licenseArea = new javax.swing.JTextArea();

		setTitle("About...");
		setAlwaysOnTop(true);
		setResizable(true);

		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		/* EMU-Mic1 Label **/
		JLabel jLabel2 = new JLabel();
		jLabel2.setFont(new java.awt.Font("Dialog", 0, 18));
		jLabel2.setText("Up and down test");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,0); //left padding
		c.gridx = 1;
		c.gridy = 0;
		pane.add(jLabel2, c);

		/* Authors **/
		String authors = "<html><b>Mirko Raimondo Aiello</b> - mremaiello@gmail.com - http://mraiello.altervista.org/";

		JPanel JPanel1 = new JPanel();
		JPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Authors"));
		JPanel1.add( new JLabel(authors) );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(JPanel1,c);

		/* Dedicated to... **/
		String dedics = "Anybody who studies computer science";

		JPanel JPdedic = new JPanel();
		JPdedic.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dedicated to..."));
		JPdedic.add( new JLabel(dedics) );
		c.gridx = 2;
		c.gridy = 1;
		pane.add( JPdedic,c );

		/* License TextArea **/
		licenseArea.setColumns(20);
		licenseArea.setEditable(false);
		licenseArea.setRows(5);
		licenseArea.setCaretPosition(0);
		licenseArea.setAutoscrolls(false);
		JScrollPane scroolAreaTxt = new JScrollPane();
		scroolAreaTxt.setViewportView(licenseArea);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.ipady = 60;
		pane.add( scroolAreaTxt,c );

		/* Jframe and size **/
		add(pane);
		setBounds( 100,40,650,430 );
		setSize( 650, 430);
	}

	public void setLicenseTxt(String txt) {
		licenseArea.setText(txt);
	}

	private javax.swing.JTextArea licenseArea;

}
