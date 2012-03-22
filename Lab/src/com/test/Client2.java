package com.test;

import java.io.*;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Client2 {

    Socket socket;
    OutputStream os;
    //InputStream is;
    BufferedReader is;
    Client2() {
        try {
            // 向本机的5859端口发出客户请求
            socket = new Socket("127.0.0.1", 5858);
            socket.setKeepAlive(true);
            // 由Socket对象得到输出流，并构造PrintWriter对象
            os = socket.getOutputStream();
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            //is = socket.getInputStream();
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

        String content = "{\"seq\":1,\"type\":\"request\",\"command\":\"version\"}";
        String message = "Content-Length:" + content.length() + "\r\n" + "\r\n" + content;
        System.out.println(client.send(message));
        // System.out.println(client.send("{\"seq\":1,\"type\":\"request\",\"command\":\"version\"}"));
        Thread.sleep(20000);
        //System.out.println(client.send(message));
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
//            StringBuffer sb = new StringBuffer("");
//            sb.append(is.readLine());
//            sb.append(is.readLine());
//            sb.append(is.readLine());
//            String response = sb.toString();
            Map<String, String> headers = new LinkedHashMap<String, String>();

            String contentLengthValue = null;

            while (true) { // read headers
              String line = is.readLine();
              System.out.println(line);
              if (line == null) {
                //LOGGER.fine("End of stream");
                return null;
              }
              if (line.length() == 0) {
                break; // end of headers
              }
              int semiColonPos = line.indexOf(':');
              if (semiColonPos == -1) {
                //LOGGER.log(Level.SEVERE, "Bad header line: {0}", line);
                return null;
              }
              String name = line.substring(0, semiColonPos);
              String value = line.substring(semiColonPos + 1);
              String trimmedValue = value.trim();
              if ("Content-Length".equals(name)) {
                contentLengthValue = trimmedValue;
              } else {
                headers.put(name, trimmedValue);
              }
            }

            // Read payload if applicable
            int contentLength = Integer.valueOf(contentLengthValue.trim());
            char[] contentBytes = new char[contentLength];
            int totalRead = 0;
            //LOGGER.log(Level.FINER, "Reading payload: {0} bytes", contentLength);
            while (totalRead < contentLength) {
              int readBytes = is.read(contentBytes, totalRead, contentLength - totalRead);
              if (readBytes == -1) {
                // End-of-stream (browser closed?)
                //LOGGER.fine("End of stream while reading content");
                return null;
              }
              totalRead += readBytes;
            }

            // Construct response message
            String contentString = new String(contentBytes);
            //return new Message(headers, contentString);
            // 从Server读入一字符串，并打印到标准输出上
            return contentString;
        }
    }
}