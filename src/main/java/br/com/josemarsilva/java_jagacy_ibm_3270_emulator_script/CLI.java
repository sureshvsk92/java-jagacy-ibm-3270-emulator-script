/**
 * @author Josemar Silva
 * @see https://github.com/josemarsilva/java-simplecalc-cli
*/

package br.com.josemarsilva.java_jagacy_ibm_3270_emulator_script;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CLI {
	
	/*
	 * private final message constants
	 */
	private static final String JAVAJAGACYIBM3270EMULATORSCRIPT= new String("java-jagacy-ibm-3270-emulator-script [options]\n" 
			+ "Java Jagacy IBM 3270 Emulator Script - v.2019.06.26\n"
			+ "https://github.com/josemarsilva/java-jagacy-ibm-3270-emulator-script\n"
			+ "Options:");
	
	private static final String JAVAJAGACYIBM3270EMULATORSCRIPT_SINTAX = new String( "" + "\n"  
			+ "Script Sintax Example:" + "\n"  
			+ "{" + "\n"
				+ "\"script\": {" + "\n"
					+ "\"command\": [" + "\n"
					+ "]" + "\n"
				+ "}"
			+ "}"
	);
	
	
	/*
	 * private properties
	 */
	private CommandLine cmd;

	/*
	 * CLI - Constructor
	 * @return void
	 */
	public CLI(String[] args) throws ParseException {
		super();
		parse(args);
		
	}
	
	
	/*
	 * getCmd - Getter
	 * @return cmd
	 */
	public CommandLine getCmd() {
		return cmd;
	}


	/*
	 * setCmd - Setter
	 * @param cmd Command Line to be set
	 * @return void
	 */
	public void setCmd(CommandLine cmd) {
		this.cmd = cmd;
	}


	/*
	 * Show complete formatted usage help
	 * @return void
	 */
	public void show_help_usage(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( JAVAJAGACYIBM3270EMULATORSCRIPT, options );
	}
	
	/*
	 * Show help sintax script
	 * @return void
	 */
	public void show_help_sintax_script(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( JAVAJAGACYIBM3270EMULATORSCRIPT_SINTAX, options );
	}
	
	/*
	 * Parse command line
	 * @return void
	 */
	public void parse(String[] args) throws ParseException {
		// create Options object
		Options options = new Options();
		
		// add option "-f"
		options.addOption("f", false, "Filename of script to be executed");
		// add option "-h"
		options.addOption("h", true, "Hostname mainframe to connect (required)");
		// add option "-p"
		options.addOption("p", true, "Port mainframe to connect (required)");
		// add option "-s"
		options.addOption("s", false, "SSL protocol. List of values [0=false (default);1=true]");
		// add option "-w"
		options.addOption("w", false, "Window enabled. List of values [0=false (default);1=true]");
		// add option "-?"
		options.addOption("?", false, "Show help usage information");
		// add option "-x"
		options.addOption("x", false, "Show help script commands sintax");
		
		// create a parser 
		CommandLineParser parser = new DefaultParser();
		
		// store parse command line 
		setCmd(parser.parse( options, args));
		
		// show help usage information
		if ( getCmd().hasOption("?") || args.length == 0) {
			show_help_usage(options);
		}
		
		// show help script command
		if ( getCmd().hasOption("x") ) {
			show_help_sintax_script(options);
		}
		
	}


	/*
	 * getCmd - Getter
	 * @return String Returns string arguments for expression
	 */
	public String getExpression() {
		String expression = new String("");
		List<String> argList = getCmd().getArgList(); 
		for (int i=0;i<argList.size();i++) {
			// Options ?
			if (!argList.get(i).equals("-h")) {
				if (expression.equals("")) 
					expression = argList.get(i);
				else
					expression = expression + " " + argList.get(i);
			}
		}
		return expression ;
	}


}
