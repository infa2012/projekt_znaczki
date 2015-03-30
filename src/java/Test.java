
import db.DbUser;
import helpers.ConsoleHelper;
import java.util.HashMap;
import java.util.LinkedList;
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
        DbUser dbUser = new DbUser();
        System.out.println(dbUser.checkIfEmailOccupied("gohanzoo@o2.pl"));
  
    }
}
