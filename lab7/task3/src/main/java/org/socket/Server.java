package org.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
    private static boolean openServer = true;
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
            while (openServer) {
                Socket s = ss.accept();
                System.out.println("Connection established.");
                DataInputStream socketIn = new DataInputStream(s.getInputStream());
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                socketHandler(socketIn, socketOut);
                s.close();
            }
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }

    private static void socketHandler(DataInputStream socketIn, DataOutputStream socketOut) {
        try {
            Scanner in = new Scanner(System.in);
            String input;
            String out = socketIn.readUTF();
            System.out.print("From client: ");
            System.out.println(out);
            if (out.equals("CLOSE")) {
                openServer = false;
                socketOut.writeUTF("Server closed.\n");
                return;
            }
            socketOut.writeUTF(Database.cmdHandler(out));
//                System.out.print("To client: ");
//                input = in.nextLine();
//                socketOut.writeUTF(input);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
