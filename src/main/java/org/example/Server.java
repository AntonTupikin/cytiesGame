package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.*;


public class Server {
    private static int id = 1;
    private static Game game;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8585);) {
            out.println("Сервер запущен");// стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                ) {
                    if (id == 1) {
                        System.out.println("Клиент подключился");
                        out.println(String.format("????"));
                        out.flush();
                        System.out.println("Первое сообщение отправлено");
                        String word = in.readLine().toLowerCase();
                        game = new Game(word);
                        id++;
                        continue;
                    } else {
                        System.out.println("Клиент подключился");
                        out.println(String.format(game.getWord()));
                        out.flush();
                        System.out.println("Первое сообщение отправлено");
                        String word2 = in.readLine().toLowerCase();
                        out.write(String.format(game.check(word2)));
                        out.flush();
                        System.out.println("Сервер отправил сообщение клиенту");
                        if (game.check(word2).equals("Yes")) {
                            game.setWord(word2);
                        }
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }


    }
}
