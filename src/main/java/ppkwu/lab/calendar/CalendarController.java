package ppkwu.lab.calendar;

import org.springframework.web.bind.annotation.*;

@RestController
public class CalendarController {

    @GetMapping("/download")
    @ResponseBody
    public static void getCalendar() {
    }
}
