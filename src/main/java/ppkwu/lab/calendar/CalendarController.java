package ppkwu.lab.calendar;

import org.springframework.web.bind.annotation.*;

@RestController
public class CalendarController {

    @RequestMapping(method = RequestMethod.GET)
    public static String getCalendarURL(@RequestParam("year") String year, @RequestParam("month") String month) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=");
        urlBuilder.append(year);
        urlBuilder.append("&miesiac=");
        urlBuilder.append(month);
        urlBuilder.append("&lang=01");

        return urlBuilder.toString();
    }
}
