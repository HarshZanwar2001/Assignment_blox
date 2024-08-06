package com.springboot.testapi;

import java.io.*;
import java.net.*;
public class BankAtoBankB {
    public static void main(String[] args) {
        try {
            ServerSocket server1 = new ServerSocket(5000);
            Socket socket = server1.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String message = (String) in.readUTF();
            System.out.println("Message " + message);
            server1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
