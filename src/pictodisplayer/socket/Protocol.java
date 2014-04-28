/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pictodisplayer.socket;

/**
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class Protocol {
    private static final int START = 0;
    private static final int WAITING = 1;
    private static final int SENDCOMMANDS = 2;
    private static final int SENDANSWER= 3;
    private static final int ANOTHER = 4;
    
    private int state = START;
    
    private String command[]={"","showImage","clearImages", "reset"};
    private String request= "";
    
    public String processInput(String theInput) {
        String theOutput = null;
        
        
        if (state == START) {
            theOutput = "Połączono. Komendy: ";
            for (String s : command) {
                theOutput += s +" ";
            }
            state = WAITING;
        }else if(state == WAITING){
            if (theInput.equalsIgnoreCase("showImage")) {
                theOutput = "showImage: OK";
                request = theInput;
            }else if(theInput.equalsIgnoreCase("clearImages")){
                theOutput = "clearImages: OK";
                request = theInput;
            }
            else if(theInput.equalsIgnoreCase("reset")){
                theOutput = "creset: OK";
                request = theInput;
            }
            else{
                theOutput = "Niepoprawna Komenda '"+theInput+"'";
                request = "";
            }
        }
        
        return theOutput;
    }
    
    public String getRequest(){
        
        String ret = request;
        request ="";
        return ret;
    }
}
