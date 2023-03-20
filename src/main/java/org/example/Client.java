package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try (Socket clientSocket = new Socket("localhost", 8085)) {

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                ) {


                    String word = reader.readLine();//вводим ответ

                    out.write(word + "\n");
                    out.flush();//отправляем и чистим буфер
                    System.out.println(in.readLine());

                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
