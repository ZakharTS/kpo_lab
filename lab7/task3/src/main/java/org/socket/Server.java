package org.socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
            while(true) {
                Socket s = ss.accept();
                System.out.println("Connection established.");
                DataInputStream socketIn = new DataInputStream(s.getInputStream());
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                socketHandler(socketIn, socketOut);
                s.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

    }
    private static void socketHandler(DataInputStream socketIn, DataOutputStream socketOut) {
        try {
            Scanner in = new Scanner(System.in);
            String input;
            for (; true; ) {
                System.out.print("From client: ");
                String out = socketIn.readUTF();
                System.out.println(out);
                Database.cmdHandler(out, socketOut);
//                System.out.print("To client: ");
//                input = in.nextLine();
//                socketOut.writeUTF(input);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
