package samples;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import webCrawler.PageStatistic;

import java.net.URL;

public class PageStatisticSamples {

    @SneakyThrows
    public PageStatistic createValidPageStatistic() {
        PageStatistic.terms = new String[] {"Elon","Mask","Tesla"};
        return new PageStatistic(new URL("https://en.wikipedia.org/wiki/Elon_Musk"));
    }

    @SneakyThrows
    public URL createValidUrl() {
        return new URL("https://en.wikipedia.org/wiki/Elon_Musk");
    }

    @SneakyThrows
    public URL createInvalidUrl() {
        return new URL("http://url");
    }

    @SneakyThrows
    public Document getValidDocument(URL url) {
        return Jsoup.connect(url.toString()).get();

    }

}
