package emo_capture.emo_capture;

import jssc.*;

public class Armband {

	private SerialPort band;
	public Armband() {
		String portName = getUSBPort();
		System.out.println(portName);
		band = new SerialPort(portName);
		System.out.println("made it");
		try {
			band.openPort();
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   

		    try {
		    	//set baudrate and params 
		    	band.setParams(SerialPort.BAUDRATE_19200,
                         SerialPort.DATABITS_8,
                         SerialPort.STOPBITS_1,
                         SerialPort.PARITY_NONE);
		    	
		    	//set control flow. copies this form the nternet so learn something if its not working
		    	band.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
				                              SerialPort.FLOWCONTROL_RTSCTS_OUT);
			
		    } catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void send(int i) {
		//TODO:Implement signal
		try {
			Integer signal = new Integer(i);
			band.writeString(signal.toString());
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("send failed");
		}
	}
	public static String getUSBPort() {
		String[] portNames = SerialPortList.getPortNames();       
		String portName = null;
		if (portNames.length == 0) {
		    System.out.println("There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
		}

		for (int i = 0; i < portNames.length; i++){
		    if(portNames[i].contains("usb"));
		    portName= portNames[i];
		}
		return portName;
	}
	public static void main(String[] args) {
		Armband arm = new Armband();
		arm.send(2);
	}
}
