package biometricgatesecurity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;


public class Communicator implements SerialPortEventListener
{
        TextArea ta;
        public static String st;
	SerialPort serialPort;
        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = 
        { 
            "/dev/tty.usbserial-A9007UX1", // Mac OS X
            "/dev/ttyACM0", // Raspberry Pi
            "/dev/ttyUSB0", // Linux
            "COM4", // Windows
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
//        private InputStream input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize(TextArea ta) 
        {
            // the next line is for Raspberry Pi and 
            // gets us into the while loop and was suggested here was suggested https://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
            //System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
            this.ta = ta;
            
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

            //First, Find an instance of serial port as set in PORT_NAMES.
            while (portEnum.hasMoreElements()) 
            {
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                for (String portName : PORT_NAMES) 
                {
                    if (currPortId.getName().equals(portName))
                    {
                        portId = currPortId;
                        break;
                    }
                }
            }
            if (portId == null) 
            {
                System.out.println("Could not find COM port.");
                return;
            }

            try 
            {
                // open serial port, and use class name for the appName.
                serialPort = (SerialPort) portId.open(this.getClass().getName(),TIME_OUT);
                // set port parameters
                serialPort.setSerialPortParams(DATA_RATE,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);

                // open the streams
                input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
//                input = serialPort.getInputStream();
                output = serialPort.getOutputStream();

                // add event listeners
                serialPort.addEventListener(this);
                
                serialPort.notifyOnDataAvailable(true);
            } 
            catch (Exception e) 
            {
                System.err.println(e.toString());
            }
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() 
        {
            if (serialPort != null) 
            {
                serialPort.removeEventListener();
                serialPort.close();
            }
	}

//        public String getText(){return test;}
                
	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
        @Override
	public synchronized void serialEvent(SerialPortEvent oEvent)
        {
            EnrollFingerprintController efc = new EnrollFingerprintController();
            if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE)
            {
                try 
                {
//                    int available = input.available();
//                    byte[] chunk = new byte[available];
//                    input.read(chunk, 0, available);
//                    st = new String(chunk);
//                    System.out.println(st);
                    
                    String inputLine=input.readLine();
//                    test = inputLine;
                    System.out.println(inputLine);
                    this.ta.appendText(inputLine+"\n");
//                    ScrollPane sp = new ScrollPane();
//                    Label label = new Label();
//                    label.setText(inputLine);
//                    sp.setContent(label);
//                    efc.setArduinoContent(inputLine);
//                    System.out.println("help");

               
                    boolean isStored = inputLine.indexOf("Stored")!=-1 ? true : false;
                    
                    if(isStored)
                    {
                        String[] arrOfStoredString = inputLine.split(" ");
//                        System.out.println("sdgsdgsdgsdgs");

                        System.out.println(arrOfStoredString[1]);
                        
                    }
//                    EnrollFingerprintController efc = new EnrollFingerprintController();
//                    efc.setaText(inputLine);
                } 
                catch (Exception e) 
                {
//                    System.err.println(e.toString());
                }
            }
		// Ignore all the other eventTypes, but you should consider the other ones.
        }
//    String test = "";
}