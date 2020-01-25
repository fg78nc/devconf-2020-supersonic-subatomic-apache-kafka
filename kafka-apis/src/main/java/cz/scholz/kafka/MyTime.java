package cz.scholz.kafka;

public class MyTime {
    String time;

    public static MyTime create(String time)   {
        MyTime myTime = new MyTime();
        myTime.setTime(time);
        return myTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MyTime{" +
                "time='" + time + '\'' +
                '}';
    }
}
