package org.example;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void start() throws InterruptedException {
        Thread.sleep(3000);
        try (Socket clientSocket = new Socket("localhost", 8585)) // этой строкой мы запрашиваем у сервера доступ на соединение)
        {
            System.out.println("Клиент создан");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // поток чтения из сокета
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // поток записи в сокет
            ) {

                System.out.println("Вы что-то хотели сказать? Введите это здесь:");

                String word = reader.readLine();

                out.write(word + "\n");
                out.flush();
                String serverWord = in.readLine();
                System.out.println(serverWord);

            } catch (IOException e) {
                System.err.println(e);

            }

            System.out.println("Клиент был закрыт...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


