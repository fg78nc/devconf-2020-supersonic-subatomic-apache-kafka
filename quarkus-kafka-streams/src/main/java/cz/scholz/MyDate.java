package cz.scholz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    String date;

    public static MyDate create(String date)   {
        MyDate myDate = new MyDate();
        myDate.setDate(date);
        return myDate;
    }

    public static MyDate fromMyTime(MyTime time)   {
        MyDate myDate = new MyDate();

        try {
            Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(time.getTime());
            myDate.setDate(new SimpleDateFormat("yyyy/MM/dd").format(date));
        } catch (ParseException e) {
            myDate.setDate("Invalid time");
        }

        return myDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "date='" + date + '\'' +
                '}';
    }
}
