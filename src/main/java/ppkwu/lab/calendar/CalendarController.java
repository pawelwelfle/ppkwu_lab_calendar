package ppkwu.lab.calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;
import biweekly.ICalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

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


    private List<Event> getWeeiaCalendarEvents(String url) throws IOException {
        List<Event> weeiaCalendarEvents = new ArrayList<>();
        URL urlConn = new URL(url);
        URLConnection conn = urlConn.openConnection();
        BufferedReader b = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder s = new StringBuilder();
        String loadedLines;
        while ((loadedLines = b.readLine()) != null) {
            s.append(loadedLines);
        }
        //From StringBuilder to string
        String lines = s.toString();
        Document document = Jsoup.parse(lines);
        Elements activeElements = document.select("td.active");
        Elements events = activeElements.select("div.InnerBox");
        Elements days = activeElements.select("a.active");
        for (int i = 0; i < events.size(); i++) {
            weeiaCalendarEvents.add(new Event(Integer.parseInt(days.get(i).text()), events.get(i).text()));
        }

        return weeiaCalendarEvents ;
    }
}

