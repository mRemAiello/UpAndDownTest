import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class GUI extends JFrame
{
	private JMenuBar menuBar	= new JMenuBar();
	private JMenu fileMenu		= new JMenu("File");
	private JMenuItem cancella	= new JMenuItem("Pulisci");
	private JMenuItem esci		= new JMenuItem("Esci");
	private JMenuItem help		= new JMenuItem("Informazioni");

	private JTextField sRandomTextField = new JTextField() {
		public Dimension getPreferredSize() {
			return new Dimension(150, 20);
		};
	};

	private JButton buttonGo = new JButton("Genera!") {
		public Dimension getPreferredSize() {
			return new Dimension(100, 20);
		};
	};
	
	private JConsole sOutputTextArea = new JConsole();

	public GUI()
	{
		//... Initialize components
		JMenuBar menuBar = makeMenu();
		setJMenuBar(menuBar);

		sOutputTextArea.setEditable(false);
		sOutputTextArea.setBorder(new TitledBorder("Output"));
		sOutputTextArea.setRows(14);

		//Collegamento del System.out alla JConsole
		System.setOut(sOutputTextArea.getPrintStream());
		System.setErr(sOutputTextArea.getPrintStream());
		
		JScrollPane scroll = new JScrollPane(sOutputTextArea);

		cancella.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sOutputTextArea.setText("");
				sRandomTextField.setText("");
			}
		});

		help.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new About().setVisible(true);
			}
		});

		esci.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		buttonGo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(sRandomTextField.getText().equals("") || !isInteger(sRandomTextField.getText())) {
					showError("Inserire un valore numerico.");
					return;
				}

				UpAndDownTest uad = new UpAndDownTest(Integer.parseInt(sRandomTextField.getText()));
				uad.makeTest();
			}
		});

		//... Add panels and components
		JPanel holdingPanel = new JPanel(new BorderLayout(5,10));
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(450,20));

		holdingPanel.setBorder(BorderFactory.createEmptyBorder(
				20, //top
				30, //left
				10, //bottom
				30));

		holdingPanel.add(sRandomTextField, BorderLayout.LINE_START);
		holdingPanel.add(buttonGo, BorderLayout.CENTER);
		holdingPanel.add(rightPanel, BorderLayout.LINE_END);
		holdingPanel.add(scroll, BorderLayout.PAGE_END);

		add(holdingPanel);

		//... finalize layout
		setTitle("Up and Down Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(750, 390);
		setLocation(150, 150);
	}

	private JMenuBar makeMenu()
	{
		fileMenu.add(cancella);
		fileMenu.addSeparator();
		fileMenu.add(help);
		fileMenu.add(esci);
		menuBar.add(fileMenu);
		return menuBar;
	}

	public void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}

	public boolean isInteger( String input )  
	{  
		try  
		{  
			Integer.parseInt( input );  
			return true;  
		}  
		catch( Exception e )  
		{  
			return false;  
		}  
	} 

}
