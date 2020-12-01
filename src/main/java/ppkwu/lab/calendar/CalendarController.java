package ppkwu.lab.calendar;

import biweekly.Biweekly;
import biweekly.component.VEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biweekly.ICalendar;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CalendarController {

    @GetMapping("/calendar")
    @ResponseBody
    public ResponseEntity getCalendarURL(@RequestParam("year") String year, @RequestParam("month") String month) throws IOException, ParseException {
        String weeiaURL = "http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month;
        ICalendar cal = new ICalendar();
        cal.setExperimentalProperty("X-WR-CALNAME", "Calendar");

        List<Event> events = getWeeiaCalendarEvents(weeiaURL);

        for (Event event : events) {
            VEvent vEvent = new VEvent();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + event.day);
            vEvent.setDateStart(date);
            vEvent.setDateEnd(date);
            vEvent.setSummary(event.name);
            cal.addEvent(vEvent);
        }
        File file = new File("WEEIA" + "_" + year + "_" + month + ".ics");
        Biweekly.write(cal).go(file);
        Path path = Paths.get("WEEIA" + "_" + year + "_" + month + ".ics");
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .body(resource);
    }


    private static List<Event> getWeeiaCalendarEvents(String url) throws IOException {
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
        return weeiaCalendarEvents;
    }
}

