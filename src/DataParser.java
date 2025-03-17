import java.text.SimpleDateFormat;
import java.util.Date;

public class DataParser {
    static public Date getDate(String string)
    {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date = new Date(formatter.parse(string).getTime());
        } catch (Exception e) {
            System.out.println("Date format is incorrect: " + e.getMessage());
        }

        return date;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }
}
