package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;


public class ArduinoConnector implements SerialPortEventListener {

SerialPort serialPort;
/** The port we’re normally going to use. */
private static final String PORT_NAMES[] = {"/dev/tty.usbserial-A9007UX1", // Mac OS X
"/dev/ttyUSB0", // Linux
"COM3", // Windows
};

private BufferedReader input;
private OutputStream output;
private BufferedWriter SerialWriter;
private static final int TIME_OUT = 3000;
private static final int DATA_RATE = 9600;

private String inputLine;

private String inputLine1;
private String inputLine2;

private String[] chunks;


public void initialize() {
CommPortIdentifier portId = null;
Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

//First, Find an instance of serial port as set in PORT_NAMES.
while (portEnum.hasMoreElements()) {
CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
for (String portName : PORT_NAMES) {
if (currPortId.getName().equals(portName)) {
portId = currPortId;
break;
}
}
}
if (portId == null) {
System.out.println("Could not find COM port.");
return;
}

try {
serialPort = (SerialPort) portId.open(this.getClass().getName(),
TIME_OUT);
serialPort.setSerialPortParams(DATA_RATE,
SerialPort.DATABITS_8,
SerialPort.STOPBITS_1,
SerialPort.PARITY_NONE);

// open the streams
input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
/*this.SerialWriter = new BufferedWriter(new OutputStreamWriter(this.output));*/
output = serialPort.getOutputStream();

serialPort.addEventListener(this);
serialPort.notifyOnDataAvailable(true);
} catch (Exception e) {
System.err.println(e.toString());
}
}
public synchronized void close() {
if (serialPort != null) {
serialPort.removeEventListener();
serialPort.close();
}
}

public synchronized void serialEvent(SerialPortEvent oEvent) {
if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
try {

if (input.ready()) {
inputLine = input.readLine();
//System.out.println(inputLine);

 chunks = inputLine.split("jambon");
 System.out.println(chunks[0]);
 System.out.println(chunks[1]);
 System.out.println(chunks[2]);
 //System.out.println(chunks[2]);
//System.out.println(chunks[0] + "\t" + chunks[1] + "\t" + chunks[2] + "\t");
}

} catch (Exception e) {
System.err.println(e.toString());
}
}
// Ignore all the other eventTypes, but you should consider the other ones.
}
public synchronized String gettemp(){
	return chunks[1];
}
public  String gethygro(){
	return chunks[0];
}
public  String gettempext(){
	return chunks[2];
}
public OutputStream getstream() {
	return output;
}

/*public void interpretation(String line)
{
	float temperature = 20;
	int consigne = 15;
	
	if(temperature > consigne)
	{
		try
		{
			output.write(1);
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	if(temperature < consigne)
	{
		try
		{
			output.write(0);
		}		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}*/

}

