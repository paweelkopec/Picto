package pictodisplayer.socket;

/**
 * Socket protocol
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class Protocol {

    private static final int START = 0;
    private static final int WAITING = 1;
    private static final int SENDCOMMANDS = 2;
    private static final int SENDANSWER = 3;
    private static final int ANOTHER = 4;
    private int state = START;

    private String command[] = {"", "show", "reset", "pause", "start", "end"};
    private String request = "";

    /**
     * Socket Process
     *
     * @param theInput
     * @return String
     */
    public String processInput(String theInput) {
        String theOutput = null;
        if (state == START) {
            theOutput = "Połączono. Komendy: ";
            for (String s : command) {
                theOutput += s + " ";
            }
            state = WAITING;
        } else if (state == WAITING) {
            if (theInput.equalsIgnoreCase("show")) {
                theOutput = "show: OK";
                request = theInput;
            } else if (theInput.equalsIgnoreCase("reset")) {
                theOutput = "reset: OK";
                request = theInput;
            } else if (theInput.equalsIgnoreCase("pause")) {
                theOutput = "pause: OK";
                request = theInput;
            } else if (theInput.equalsIgnoreCase("start")) {
                theOutput = "start: OK";
                request = theInput;
            }else if (theInput.equalsIgnoreCase("end")) {
                theOutput = "end: close connection";
                request = theInput;
            } else {
                theOutput = "Niepoprawna Komenda '" + theInput + "'";
                request = "";
            }
        }
        return theOutput;
    }

    /**
     * Get current request
     *
     * @return
     */
    public String getRequest() {
        String ret = request;
        request = "";
        return ret;
    }
}
