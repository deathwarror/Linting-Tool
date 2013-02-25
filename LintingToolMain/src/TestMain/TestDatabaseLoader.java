package TestMain;

/**
 * @Author: Kenneth Hassey
 * @Date:2/22/2013
 * @Version: 1.000
 * Function:
 *     	This Class is responsible for testing the database loader
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
