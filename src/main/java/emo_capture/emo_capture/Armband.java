package emo_capture.emo_capture;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Armband {

	private SerialPort band;
	public Armband() {
		band = new SerialPort("flora");
		if(!band.isOpened()) {
			try {
				band.openPort();
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void send(int i) {
		//TODO:Implement signal
		try {
			band.writeInt(i);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("send failed");
		}
	}
}
