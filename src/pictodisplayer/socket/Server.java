/**
 * Socket Server
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
package pictodisplayer.socket;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket server;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    String currentCommand;
    String inputLine, outputLine;
    Protocol kkp;
    int port;

    /**
     * Init Server object
     *
     * @param port
     */
    public Server(int port) throws IOException {
            this.port = port;
            server = new ServerSocket(port);
            server.setSoTimeout(10000); //10 s timeout
            socket = server.accept();
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Initiate conversation with client
            kkp = new Protocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);
            System.out.println(outputLine);
    }

    /**
     * Reading and writing on the socket
     */
    public void run() {
        try {
            if (!in.ready()) {
                return;
            }
            if ((inputLine = in.readLine()) != null) {
                System.out.println(outputLine);
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (inputLine.equalsIgnoreCase("end")) {
                    in.close();
                    out.close();
                    socket.close();
                    server.close();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get current command
     *
     * @return
     */
    public String getCommand() {
        return this.kkp.getRequest();
    }

    /**
     * Close server
     */
    public void close() {
        try {
            out.close();
            socket.close();
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
