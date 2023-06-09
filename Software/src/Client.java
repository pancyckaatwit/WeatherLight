package Software.src;

import java.awt.Color;
import java.io.*;
import java.net.*;

/**
 * Client for chat system. Allows two-way chatting with server. Run after Server
 * is already running.
 * 
 * @author brownc32@wit.edu
 *
 */
public class Client {

	// Default settings
	private static String ip = "192.168.8.127";
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
				//If statement that sends the tempColor RGB values
				if(msg.equals("sendTempColor")) {
					LightEffect lightEffect=new LightEffect();
					Color tempColor=lightEffect.setTemperatureColor();
					pw.println(tempColor.getRed() + " " + tempColor.getGreen() + " " + tempColor.getBlue());
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