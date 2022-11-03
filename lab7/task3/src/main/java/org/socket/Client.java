package org.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 8080);
            System.out.println("Connection established.");
            DataInputStream socketIn = new DataInputStream(s.getInputStream());
            DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
            cmdHandler(socketIn, socketOut);
            socketIn.close();
            socketOut.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private static void cmdHandler(DataInputStream socketIn, DataOutputStream socketOut) {
        try {
            Scanner in = new Scanner(System.in);
            String input;
            for (; true; ) {
                System.out.print("To server: ");
                input = in.nextLine();
                socketOut.writeUTF(input);
                System.out.print("From server: ");
                String out = socketIn.readUTF();
                System.out.println(out);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
