package Software.src;

import java.awt.Color;
import java.io.*;
import java.net.*;

public class Client {

	// Default settings
	private static String ip = "192.168.43.200";
	private static int port = 25565;

	public static void ClientCall(String[] args) throws Exception {

		ip = receiveStringOverUDP(port);

		// Socket to connect to server
		Socket s = new Socket(ip, port);

		// PW to output to socket
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		// Prints to show connected
		System.out.println("Connected!");

		new Thread(() -> out(pw)).start();
	}

	private static void out(PrintWriter pw) {
		while (true) {
			pw.flush();

			// Sends the tempColor RGB values
			LightEffect tempLightEffect = new LightEffect();
			Color tempColor = tempLightEffect.setTemperatureColor();
			pw.println("RGB " + tempColor.getRed() + " " + tempColor.getGreen() + " " + tempColor.getBlue());

			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// If statement that sends the weather effect
			API.APICall();
			String forecast = API.getForecast();
			if (forecast.contains("Sunny") || forecast.contains("sunny")) {
				pw.println("SUN");
			} else if (forecast.contains("Thunder") || forecast.contains("thunder")) {
				pw.println("STR");
			} else if (forecast.contains("Snow") || forecast.contains("snow")) {
				pw.println("SNO");
			} else if (forecast.contains("Rain") || forecast.contains("rain")) {
				pw.println("RAI");
			} else if (forecast.contains("Cloudy") || forecast.contains("cloudy") || forecast.contains("Haze")
					|| forecast.contains("haze")) {
				pw.println("CLD");
			}

			try {
				Thread.sleep(12000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static String receiveStringOverUDP(int port) {
		byte[] buffer = new byte[1024];

		try (DatagramSocket socket = new DatagramSocket(port)) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			// Receive the UDP packet
			socket.receive(packet);

			// Extract the received data and convert it to a string
			String receivedString = new String(packet.getData(), 0, packet.getLength());

			return receivedString;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}