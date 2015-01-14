package entity;

import java.sql.Date;


public class StudentDate extends Date {

    public StudentDate(long date) {
        super(date);
    }

    @Override
    @SuppressWarnings("deprecation")
    public String toString() {

        int year = super.getYear() + 1900;
        int month = super.getMonth() + 1;
        int day = super.getDate();

        char buf[] = "00.00.2000".toCharArray();
        buf[0] = Character.forDigit(day / 10, 10);
        buf[1] = Character.forDigit(day % 10, 10);
        buf[3] = Character.forDigit(month / 10, 10);
        buf[4] = Character.forDigit(month % 10, 10);
        buf[6] = Character.forDigit(year / 1000, 10);
        buf[7] = Character.forDigit((year / 100) % 10, 10);
        buf[8] = Character.forDigit((year / 10) % 10, 10);
        buf[9] = Character.forDigit(year % 10, 10);


        return new String(buf);
    }
}
