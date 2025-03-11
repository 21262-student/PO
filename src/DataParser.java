import java.sql.Date;
import java.text.SimpleDateFormat;

public class DataParser {
    static public Date getDate(String string)
    {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
        try {
            date = new Date(formatter.parse(string).getTime());
        } catch (Exception e) {
            System.out.println("Date format is incorrect: " + e.getMessage());
        }

        return date;
    }
}
