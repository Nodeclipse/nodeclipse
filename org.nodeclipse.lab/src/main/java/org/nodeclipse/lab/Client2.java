package org.nodeclipse.lab;

import java.io.*;
import java.net.*;

public class Client2 {

    Socket socket;
    OutputStream os;
    InputStream is;

    Client2() {
        try {
            // 向本机的5859端口发出客户请求
            socket = new Socket("127.0.0.1", 5859);
            socket.setKeepAlive(true);
            // 由Socket对象得到输出流，并构造PrintWriter对象
            os = socket.getOutputStream();
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            is =socket.getInputStream();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            // 关闭Socket输出流
            os.close();
            // 关闭Socket输入流
            is.close();
            // 关闭Socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {

        // D:\Workspace\runtime-EclipseApplication\test\src
        // node --debug-brk=5859 index.js
        Client2 client = new Client2();
        System.out.println(client.send("{\"seq\":1,\"type\":\"request\",\"command\":\"version\"}\n"));
        //System.out.println(client.send("{\"seq\":1,\"type\":\"request\",\"command\":\"version\"}"));
        Thread.sleep(20000);
        client.close();
    }

    private String send(String commond) throws IOException {
        synchronized (socket) {
            os.write(commond.getBytes());
            // 将从系统标准输入读入的字符串输出到Server
            os.flush();
            // 刷新输出流，使Server马上收到该字符串
            System.out.println("Client:" + commond);
            // 在系统标准输出上打印读入的字符串
            StringBuffer sb = new StringBuffer("");
            int availableCount;
            while((availableCount=is.available()) > 0){  
                byte[] requestbuffer = new byte[availableCount];  
                is.read(requestbuffer);
                sb.append(new String(requestbuffer, "UTF-8"));  
            }   
            System.out.println("~~~~");
            String response = sb.toString();
            // 从Server读入一字符串，并打印到标准输出上
            System.out.println("Server: return end" + response);
            return response;
        }
    }
}