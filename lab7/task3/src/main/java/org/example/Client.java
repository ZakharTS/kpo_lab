package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8080);
        DataInputStream socketIn = new DataInputStream(s.getInputStream());
        DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
        cmdHandler(socketIn, socketOut);
        socketIn.close();
        socketOut.close();
        s.close();
    }
    private static void cmdHandler(DataInputStream socketIn, DataOutputStream socketOut) throws IOException {
        Scanner in = new Scanner(System.in);
        String input;
        for (;true;) {
            System.out.print("To server: ");
            input = in.nextLine();
            socketOut.writeUTF(input);
            String out = socketIn.readUTF();
            System.out.print("From server: ");
            System.out.println(out);
        }
    }
}
