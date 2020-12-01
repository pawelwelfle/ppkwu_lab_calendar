package ppkwu.lab.calendar;

import org.springframework.web.bind.annotation.*;
import biweekly.ICalendar;

@RestController
public class CalendarController {

    @GetMapping("/calendar")
    @ResponseBody
    public static String getCalendarURL(@RequestParam("year") String year, @RequestParam("month") String month){
        String urlBuilder = new String();
        urlBuilder.concat("http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=");
        urlBuilder.concat(year);
        urlBuilder.concat("&miesiac=");
        urlBuilder.concat(month);
        urlBuilder.concat("&lang=01");
        ICalendar cal = new ICalendar();
        cal.setExperimentalProperty("X-WR-CALNAME", "Calendar");

     return urlBuilder;
    }

//    private List<Event> getWeeiaCalendar(String url){
//        return ;
//    }
}
