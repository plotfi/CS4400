import java.util.List;

import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Question;


public class Main {

    public static void main(String args[]) {
        DataBackend DB = new DataBackend();
        DB.start_session();


		Object[] test = DB.getSession().createQuery("from Question i fetch all properties where i.Qtext like '%" + "Life" +"%' order by i.Qtext desc").list().toArray();

        for(int i=0;i<test.length;i++) {
                System.out.println(((Question)test[i]).getQtext());
        }

        DB.close_session();
}

}
