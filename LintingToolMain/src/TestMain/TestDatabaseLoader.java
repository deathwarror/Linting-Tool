package TestMain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deathwarror
 */
import java.util.ArrayList;
public class TestDatabaseLoader {
    DatabaseLoader db;
    ArrayList<ErrorDatabase> ed;
    TestDatabaseLoader()
    {
        db = new DatabaseLoader();
    }
    
    public void run()
    {
        ed = db.load();
        
    }
}
