/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pictodisplayer.socket;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server{

    ServerSocket server;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    String command[] = {"showImage", "clearImages", "reset"};
    String currentCommand;
    String inputLine, outputLine;
    Protocol kkp;
    int port;
    public Server(int port){

        this.port = port;
        try {        
            server = new ServerSocket(3568);
        
            socket = server.accept();
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                   String inputLine, outputLine;

            // Initiate conversation with client
            kkp = new Protocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);
            System.out.println(outputLine);
            } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
//        server = new ServerSocket(port);
//        socket = server.accept();
//        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        String msg = "\"Dzień Dobry, Dostępne Komendy: \n";
//        for (String s : command) {
//            msg += s +" ";
//        }
//        System.out.print("\n writiaaaaaaaaaaaaang: " + msg);
////        this.write(msg);
    }

    public Server() {

    }

    
    public void run() {
        
        try {

            if(!in.ready())
                return;
            if((inputLine = in.readLine()) != null){
               
                System.out.println(outputLine);
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye.")) {
                    //die
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
    
    public String getCommand(){
        return this.kkp.getRequest();
    }

    public void close() {
        try {
            System.out.print("finally");
            out.close();
            socket.close();
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
//        String data = "Toobie ornaught toobie";
        int portNumber = 3568;
        Server server = new Server(24234);
        server.run();
//        try {
//            new Server(24234);
//        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return;

//      try (
//            ServerSocket serverSocket = new ServerSocket(portNumber);
//            Socket clientSocket = serverSocket.accept();
//            PrintWriter out =
//                new PrintWriter(clientSocket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(
//                new InputStreamReader(clientSocket.getInputStream()));
//              
//              )
//     {
//         
//            String inputLine, outputLine;
//             
//            // Initiate conversation with client
//            Protocol kkp = new Protocol();
//            outputLine = kkp.processInput(null);
//            out.println(outputLine);
// System.out.println(outputLine);
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println(outputLine);
//                outputLine = kkp.processInput(inputLine);
//                out.println(outputLine);
//                if (outputLine.equals("Bye."))
//                    break;
//            }
//        } catch (IOException e) {
//            System.out.println("Exception caught when trying to listen on port "
//                + portNumber + " or listening for a connection");
//            System.out.println(e.getMessage());
//        }
    }
}
