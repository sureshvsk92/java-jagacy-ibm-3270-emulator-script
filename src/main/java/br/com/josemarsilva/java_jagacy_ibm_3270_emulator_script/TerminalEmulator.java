package br.com.josemarsilva.java_jagacy_ibm_3270_emulator_script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;

public class TerminalEmulator  {
	
	// constants ...
	private String PROPERTIES_FILENAME = "java-jagacy-ibm-3270-emulator-script.properties";
	
	// properties	
	private Session3270 session3270 = null;
	private String host;
	private Integer port;
	private boolean ssl;
	private boolean window;

	/*
	 * TerminalEmulator() - Constructor
	 */
	public TerminalEmulator(String host, Integer port, boolean ssl, boolean window) throws JagacyException, IOException, InterruptedException {
		super();
		this.host = host;
		this.port = port;
		this.ssl = ssl;
		this.window = window;

		// build properties ...
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("host = ").append(this.host).append("\n");
		stringBuffer.append("port = ").append(this.port).append("\n");
		stringBuffer.append("ssl = ").append(this.ssl ? "true" : "false").append("\n");
		stringBuffer.append("show-cert = false\n");
		stringBuffer.append("window = ").append(this.window ? "true" : "false").append("\n");

		// write properties to file ...
		BufferedWriter bwr = new BufferedWriter(new FileWriter(PROPERTIES_FILENAME));
		bwr.write(stringBuffer.toString());
		bwr.flush();
		bwr.close();

		System.out.println(stringBuffer);

		// read jagacy properties ...		
		JagacyProperties props = new JagacyProperties(PROPERTIES_FILENAME);
		
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
		

		System.out.println(Arrays.toString(this.session3270.readScreen()));
//		waitForChange(10,5);
		this.session3270.writeKey(Key.ENTER);		
		
		// Close connection ...
		this.session3270.close();
		
		
	}

	private boolean waitForChange(int timeout,
								  int intermediateScreenCount) {
		if (intermediateScreenCount < 0) {
			throw new IllegalArgumentException(String.valueOf(intermediateScreenCount));
		}
// Add one for target screen:
		intermediateScreenCount++;
		boolean isSuccess = false;
		while (intermediateScreenCount > 0) {
			isSuccess = waitForChange(10,5);
			if (isSuccess) {
				break;
			}
		}
		return isSuccess;
	}

	/*
	 * 
	 */
	public void run() {
		
	}

}
