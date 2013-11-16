
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main
{
	static PrintStream output = null;
	
	public static void main(String [] args) throws IOException, ClassNotFoundException, InstantiationException,
												IllegalAccessException, UnsupportedLookAndFeelException
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			GUI view = new GUI();
			view.setVisible(true);
		} catch (RuntimeException e)
		{
			output = new PrintStream(new File("crashDump.txt"));
			e.printStackTrace();
			e.printStackTrace(output);
			output.close();
		}
	}
}
