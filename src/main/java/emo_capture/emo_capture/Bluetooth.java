package emo_capture.emo_capture;

import javax.bluetooth.*;

public class Bluetooth {

	private static Object lock = new Object();
	public Bluetooth() {

	try {
            // 1
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            // 2
            DiscoveryAgent agent = localDevice.getDiscoveryAgent();       
            // 3
            //agent.startInquiry(DiscoveryAgent.GIAC, new MyDiscoveryListener());
            /*try {
                synchronized(lock){
                    lock.wait();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println("Device Inquiry Completed. ");
            System.out.println(localDevice.getFriendlyName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

	}
	public static void main(String[] args) {
		Bluetooth blue = new Bluetooth();
	}
}
