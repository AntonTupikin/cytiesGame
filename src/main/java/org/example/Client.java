package org.example;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        try (Socket clientSocket = new Socket("localhost", 8585)) // этой строкой мы запрашиваем у сервера доступ на соединение)
        {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // поток чтения из сокета
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // поток записи в сокет
            ) {
                String word = in.readLine();
                System.out.println(word);
                if (word.equals("????")) {
                    String word1 = reader.readLine();
                    out.write(word1 + "\n");
                    out.flush();
                } else {
                    String word2 = reader.readLine();
                    out.write(word2 + "\n");
                    out.flush();
                    String word3 = in.readLine();
                    System.out.println(word3);
                }

            } catch (IOException e) {
                System.err.println(e);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


