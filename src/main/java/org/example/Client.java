package org.example;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try(Socket clientSocket = new Socket("localhost", 8585)) // этой строкой мы запрашиваем у сервера доступ на соединение)
        {
            System.out.println("Клиент создан");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 // нам нужен ридер читающий с консоли, иначе как
                 // мы узнаем что хочет сказать клиент?
                 // читать соообщения с сервера
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // поток чтения из сокета
                 // писать туда же
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // поток записи в сокет
            ){

                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                // если соединение произошло и потоки успешно созданы - мы можем
                //  работать дальше и предложить клиенту что то ввести
                // если нет - вылетит исключение
                String word = reader.readLine(); // ждём пока клиент что-нибудь
                // не напишет в консоль
                out.write(word + "\n"); // отправляем сообщение на сервер
                out.flush();
                String serverWord = in.readLine(); // ждём, что скажет сервер
                System.out.println(serverWord); // получив - выводим на экран
            } catch (IOException e) {
                System.err.println(e);

            }

            System.out.println("Клиент был закрыт...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


