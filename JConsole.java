import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class JConsole extends JTextArea {

	private PrintStream printStream;

	public JConsole() {
		printStream = new PrintStream(new ConsolePrintStream());
	}

	public PrintStream getPrintStream() {
		return printStream;
	}

	//L' output stream definito da noi
	private class ConsolePrintStream extends ByteArrayOutputStream {
		public synchronized void write(byte[] b, int off, int len) {
			setCaretPosition(getDocument().getLength());
			String str = new String(b);
			append(str.substring(off, len));
		}
	}

}
