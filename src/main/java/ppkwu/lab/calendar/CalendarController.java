package ppkwu.lab.calendar;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biweekly.ICalendar;

@RestController
public class CalendarController {

    @GetMapping("/calendar")
    @ResponseBody
    public static String getCalendarURL(@RequestParam("year") String year, @RequestParam("month") String month){
        String weeiaURL = "http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month;
        ICalendar cal = new ICalendar();
        cal.setExperimentalProperty("X-WR-CALNAME", "Calendar");
        return weeiaURL;
    }


    private List<Event> getWeeiaCalendar(String url){
        return ;
    }
}
