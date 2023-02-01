/**
 * @author Josemar Silva
 * @see https://github.com/josemarsilva/java-jagacy-ibm-3270-emulator-script
*/

package br.com.josemarsilva.java_jagacy_ibm_3270_emulator_script;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

import com.jagacy.util.JagacyException;

/**
 * App - mainClass manifest in pom.xml
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException, JagacyException, IOException, InterruptedException
    {
    	
    	// new CLI
    	CLI cli = new CLI(args);
    	
    	// new IBM 3270 Emulator
//    	TerminalEmulator terminalEmulator = new TerminalEmulator("teague-tammvs1.tamu.edu", 992, true, true, "script");
    	TerminalEmulator terminalEmulator = new TerminalEmulator("localhost", 3270, false, false);

    	// Run Script
    }
}
