package com.springboot.testapi;

import java.io.*;
import java.net.*;

public class BankBtoBankA {
    public static void main(String[] args) {
        try {
            Socket server = new Socket("localhost", 5000);
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Pay 500");
            out.flush();
            out.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

