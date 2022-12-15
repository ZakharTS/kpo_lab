package org.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        while(true) {
            try {
                Scanner in = new Scanner(System.in);
                String input;
                System.out.print("To server: ");
                input = in.nextLine();
                if (input.equals("EXIT")) {
                    break;
                }
                Socket s = new Socket("localhost", 8080);
//            System.out.println("Connection established.");
                DataInputStream socketIn = new DataInputStream(s.getInputStream());
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());

                socketOut.writeUTF(input);
                System.out.print("From server: ");
                String out = socketIn.readUTF();
                System.out.println(out);

                socketIn.close();
                socketOut.close();
                s.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
