package ppkwu.lab.calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    int day;
    String name;

    public Event(int day, String name) {
        this.day = day;
        this.name = name;
    }
}
