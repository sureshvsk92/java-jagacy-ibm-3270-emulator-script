package br.com.josemarsilva.java_jagacy_ibm_3270_emulator_script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;

public class TerminalEmulator  {
	
	// constants ...
	private String PROPERTIES_FILENAME = new String("java-jagacy-ibm-3270-emulator-script.properties");
	
	// properties	
	private Session3270 session3270 = null;
	private JagacyProperties props;
	private String host = null;
	private Integer port = null;
	private boolean ssl = false;
	private boolean window = false;
	private String filename = null;
	
	/*
	 * TerminalEmulator() - Constructor
	 */
	public TerminalEmulator(String host, Integer port, boolean ssl, boolean window, String filename) throws JagacyException, IOException, InterruptedException {
		super();
		this.host = host;
		this.port = port;
		this.ssl = ssl;
		this.window = window;
		this.filename = filename;
		
		// build properties ...
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append("host=" + this.host + "\n");
		stringBuffer.append("port=" + this.port + "\n");
		stringBuffer.append("ssl=" + (this.ssl == true ? "true" : "false" ) + "\n");
		stringBuffer.append("showcert=false\n");
		stringBuffer.append("window=" + (this.window == true ? "true" : "false" ) + "\n");

		// write properties to file ...
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(PROPERTIES_FILENAME)));
		bwr.write(stringBuffer.toString());
		bwr.flush();
		bwr.close();
		
		// read jagacy properties ...		
		this.props = new JagacyProperties(PROPERTIES_FILENAME);
		
		// open connection ...
		open_connection();
		
	}
	
	
	/*
	 * open_connection() - Open connection
	 * returns void
	 */
	private void open_connection() throws JagacyException, InterruptedException {
		
		// Instance connection ...
		this.session3270 = new Session3270(PROPERTIES_FILENAME);
		
		// Open connection ...
		this.session3270.open();
		

		System.out.println(this.session3270.readScreen().toString());
		this.session3270.writeKey(Key.ENTER);		
		
		// Close connection ...
		this.session3270.close();
		
		
	}
	
	/*
	 * 
	 */
	public void run() {
		
	}

}
