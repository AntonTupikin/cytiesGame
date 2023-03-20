package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.*;


public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8585)) { // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            out.println("Сервер запущен");
            try (Socket clientSocket = serverSocket.accept(); // ждем подключения

                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                System.out.println("Клиент подключился");


                final String name = in.readLine();
                System.out.println("Сервер отправил сообщение клиенту");
                out.write(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }}
