# ppkwu_lab_calendar

Documentation
Method **getCalendarURL** will check for us information from weeia site.

`/GET @GetMapping("/calendar")

http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month;
main url which is tested

Method **getWeeiaCalendarEvents** 
gets information about events by getting info by specified elements

### **To test it:**
### The main method to see info about Events:

`hit to the endpoint to get  info about some month events at weeia site` --->
http://localhost:8080/calendar?year=2020&month=09
