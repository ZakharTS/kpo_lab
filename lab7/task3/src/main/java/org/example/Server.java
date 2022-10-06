package org.example;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        while(true) {
            Socket s = ss.accept();
            DataInputStream socketIn = new DataInputStream(s.getInputStream());
            DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
            socketHandler(socketIn, socketOut);
        }
//        ss.close();
    }
    private static void socketHandler(DataInputStream socketIn, DataOutputStream socketOut) throws IOException {
        Scanner in = new Scanner(System.in);
        String input;
        for (;true;) {
            String out = socketIn.readUTF();
            System.out.print("From client: ");
            System.out.println(out);
            System.out.print("To client: ");
            input = in.nextLine();
            socketOut.writeUTF(input);
        }
    }
}
