import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TestClass {
    public static void main(String[] args) {
        String groupName = "ФИб-3301-51-00";
        Document doc;
        try {
            Connection connection = Jsoup.connect("https://www.vyatsu.ru/studentu-1/spravochnaya-informatsiya/raspisanie-zanyatiy-dlya-studentov.html");
            connection.timeout(15000);
            connection.ignoreHttpErrors(true);
            connection.maxBodySize(Integer.MAX_VALUE);
            doc = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Не удалось получить данные с сайта");
            return;
        }
        Elements elements = doc.select("div.grpPeriod");
        Element myElement = null;

        for(Element element:elements){
            if(element.text().equals(groupName)){
                myElement = element;
                break;
            }
        }
        String idGroup;
        if(myElement != null)idGroup = myElement.attr("data-grp_period_id");
        else{
            System.out.println("Не удалось найти такую группу");
            return;
        }

        elements = doc.select("div.listPeriod[id=listPeriod_" + idGroup + "]");
        elements = elements.get(0).select("a");

        for(Element element:elements){
            System.out.println(element.text());
            System.out.println("https://www.vyatsu.ru" + element.attr("href"));
        }

    }
}
