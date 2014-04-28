/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package socket;
import pictodisplayer.socket.Server;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Server {
    
    ServerSocket server;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    
    public Server(Integer port) {
        try {
            server = new ServerSocket(port);
            socket = server.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Whoops! It didn't work!\n" + ex.getMessage());
        } finally {
            this.close();
        }
    }
    public Server() {
        
    }
    
    public String read() throws IOException {
        if (!in.ready()) {
            return null;
        }
        System.out.print("\n Reading: " + in.readLine());
        return in.readLine();
    }
    
    public void write(String data){
        System.out.print("\n Writing: " + data);
        out.print(data);
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
      String data = "Toobie ornaught toobie";
      try {
         ServerSocket srvr = new ServerSocket(1237);
         Socket skt = srvr.accept();
         System.out.print("Server has connected!\n");
         PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
         System.out.print("Sending string: '" + data + "'\n");
         out.print(data);
         out.close();
         skt.close();
         srvr.close();
      }
      catch(Exception e) {
         System.out.print("Whoops! It didn't work!\n"+e.getMessage());
      }
   }
}
