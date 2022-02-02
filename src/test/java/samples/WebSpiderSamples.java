package samples;

import lombok.SneakyThrows;
import webCrawler.WebSpider;

import java.net.MalformedURLException;
import java.net.URL;

public class WebSpiderSamples {

    @SneakyThrows
    public WebSpider createValidWebSpider() {
        return new WebSpider(new URL("https://en.wikipedia.org/wiki/Elon_Musk"), 8, 10);
    }

    @SneakyThrows
    public WebSpider createWebSpiderWithInvalidUrl() {
        return new WebSpider(new URL("url"), 8, 100);
    }

    @SneakyThrows
    public WebSpider createWebSpiderWithInvalidDepth() {
        return new WebSpider(new URL("https://en.wikipedia.org/wiki/Elon_Musk"), 0, 100);
    }

    @SneakyThrows
    public WebSpider createWebSpiderWithInvalidLinksNumber() {
        return new WebSpider(new URL("https://en.wikipedia.org/wiki/Elon_Musk"), 8, 0);
    }

}
