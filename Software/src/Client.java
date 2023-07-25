package Software.src;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

/**
 * Client for chat system. Allows two-way chatting with server. Run after Server
 * is already running.
 * 
 * @author brownc32@wit.edu
 *
 */
public class Client {

	// Default settings
	private static String ip = "192.168.43.200";
	private static int port = 25565;
	private static String portString = "25565";
	private static String user = "Username";

	public static void ClientCall(String[] args) throws Exception {

		// Reads from keyboard
		BufferedReader kbIn = new BufferedReader(new InputStreamReader(System.in));

		// These three try/catch blocks take in settings from user using keyboard BR
		// System.out.printf("Enter server address: ");
		// try {
		// 	ip = kbIn.readLine();
		// } catch (IOException e) {
		// 	e.printStackTrace();
		// 	System.exit(1);
		// }

		// System.out.printf("Enter port: ");
		// String portString = "25565";
		// try {
		// 	portString = kbIn.readLine();
		// } catch (IOException e) {
		// 	e.printStackTrace();
		// 	System.exit(1);
		// }
		// port = Integer.valueOf(portString);

		// System.out.printf("Enter username: ");
		// try {
		// 	user = kbIn.readLine();
		// } catch (IOException e) {
		// 	e.printStackTrace();
		// 	System.exit(1);
		// }

		// Socket to connect to server
		Socket s = new Socket(ip, port);

		// PW to output to socket
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		// BR from inputstream to take in messages from socket
		InputStream in = s.getInputStream();
		BufferedReader inBR = new BufferedReader(new InputStreamReader(in));

		// Prints to show connected
		System.out.println("Connected!");

		// Threads for in and out
		new Thread(() -> in(inBR)).start();
		new Thread(() -> out(kbIn, pw)).start();

	}

	/**
	 * Reads in from the BR (connected to the socket) and prints out continuously.
	 * 
	 * @param inBR reads in from the socket
	 */
	private static void in(BufferedReader inBR) {
		// Endless loop
		while (true) {
			String msg;
			try {
				// Takes in line from inBR and sets msg to it. Prints it if not null.
				if ((msg = inBR.readLine()) != null) {
					System.out.println(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	/**
	 * Looping method for reading from keyboard and sending out message.
	 * 
	 * @param kbIn is the keyboard input.
	 * @param pw   is PW for sending out to the socket.
	 */
	private static void out(BufferedReader kbIn, PrintWriter pw) {
		String msg;
		while (true) {
				//msg = kbIn.readLine();
				//Msg is set to the temp so it will activate if statement below
				msg="sendTempColor";
				//pw.println(formatMsg(user, msg));
				//pw.println(msg);
				pw.flush();

				//Sends the tempColor RGB values
				LightEffect tempLightEffect=new LightEffect();
				Color tempColor=tempLightEffect.setTemperatureColor();
				pw.println("RGB " + tempColor.getRed() + " " + tempColor.getGreen() + " " + tempColor.getBlue());

				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//If statement that sends the weather effect
				API.APICall();
        		String forecast=API.getForecast();
				if(forecast.contains("Sunny") || forecast.contains("sunny")){
					pw.println("SUN");
				}else if(forecast.contains("Thunder") || forecast.contains("thunder")) {
					pw.println("STR");
				}else if(forecast.contains("Snow") || forecast.contains("snow")){
					pw.println("SNO");
				}else if(forecast.contains("Rain") || forecast.contains("rain")){
					pw.println("RAI");
				}else if(forecast.contains("Cloudy") || forecast.contains("cloudy")|| forecast.contains("Haze") || forecast.contains("haze")){
					pw.println("CLD");
				}

				try {
					Thread.sleep(12000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}	

	/**
	 * Formats the username and message to send. 'Username: Message'
	 * 
	 * @param u is the username.
	 * @param m is the message.
	 * @return formatted string from u and m.
	 */
	private static String formatMsg(String u, String m) {
		return String.format(u + ": " + m);
	}
}