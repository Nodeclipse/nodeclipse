package com.test;

import java.io.*;
import java.net.*;

public class Client {
    // D:\Workspace\runtime-EclipseApplication\test\src
    // node --debug-brk=5859 index.js
    public static void main(String args[]) {
        try {
            Socket socket = new Socket("127.0.0.1", 5859);
            // 向本机的5859端口发出客户请求
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            // 由系统标准输入设备构造BufferedReader对象
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            // 由Socket对象得到输出流，并构造PrintWriter对象
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            String readline;
            readline = sin.readLine(); // 从系统标准输入读入一字符串
            while (!readline.equals("bye")) {
                // 若从标准输入读入的字符串为 "bye"则停止循环
                os.println(readline);
                // 将从系统标准输入读入的字符串输出到Server
                os.flush();
                // 刷新输出流，使Server马上收到该字符串
                System.out.println("Client:" + readline);
                // 在系统标准输出上打印读入的字符串
                char[] buf = new char[64];
                StringBuffer sb = new StringBuffer("");
                String line = null;
                while ((line = is.readLine()) != null) {
                    sb.append(line + "\n");
                    System.out.print(line + "\n");
                }
                String response = sb.toString();
                System.out.println("Server:" + response);
                // 从Server读入一字符串，并打印到标准输出上
                // 从系统标准输入读入一字符串
                readline = sin.readLine();
            } // 继续循环
            os.close(); // 关闭Socket输出流
            is.close(); // 关闭Socket输入流
            socket.close(); // 关闭Socket
        } catch (Exception e) {

            System.out.println("Error" + e); // 出错，则打印出错信息

        }

    }
}