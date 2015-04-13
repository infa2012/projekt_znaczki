
import db.DbMessage;
import db.DbUser;
import helpers.ConsoleHelper;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.commons.codec.digest.DigestUtils;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;
import org.fusesource.jansi.AnsiConsole;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gohzno
 */
public class Test
{

    public static void main(String[] args)
    {
        DbMessage dbMessage = new DbMessage();                   
        LinkedList receivedMessages = dbMessage.getReceivedMessages(2);
        System.out.println(receivedMessages);
  
    }
}
