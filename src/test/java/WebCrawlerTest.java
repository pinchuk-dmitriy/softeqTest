import org.junit.jupiter.api.Test;
import samples.WebSpiderSamples;
import webCrawler.PageStatistic;
import webCrawler.WebSpider;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class WebCrawlerTest {

    WebSpiderSamples webSpiderSamples = new WebSpiderSamples();

    @Test
    void createValidWebSpider() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        WebSpider webSpider = webSpiderSamples.createValidWebSpider();

        assertTrue(!webSpider.getStartLink().toString().isEmpty());
        assertTrue(webSpider.getMaxLinkDepth() > 0);
        assertTrue(webSpider.getMaxLinksNumber() > 0);
    }

    @Test
    void createWebSpiderWithInvalidDepth() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        assertThrows(IllegalArgumentException.class, () -> webSpiderSamples.createWebSpiderWithInvalidDepth());
    }

    @Test
    void createWebSpiderWithInvalidLinksNumber() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        assertThrows(IllegalArgumentException.class, () -> webSpiderSamples.createWebSpiderWithInvalidLinksNumber());
    }

    @Test
    void createWebSpiderWithInvalidUrl() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        assertThrows(MalformedURLException.class, () -> webSpiderSamples.createWebSpiderWithInvalidUrl());
    }

    @Test
    void validCrawler() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        WebSpider webSpider = webSpiderSamples.createValidWebSpider();
        assertNotNull(webSpider.getStartLink());

        PageStatistic.terms = new String[] {"Elon","Mask","Tesla"};

        webSpider.crawler();
        assertFalse(webSpider.getAllStatistics().isEmpty());
    }

    @Test
    void InvalidCrawlerWithoutTerms() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        WebSpider webSpider = webSpiderSamples.createValidWebSpider();
        assertNotNull(webSpider.getStartLink());

        webSpider.crawler();
        assertTrue(webSpider.getAllStatistics().isEmpty());
    }

    @Test
    void allReportWithEmptyTerms() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        WebSpider webSpider = webSpiderSamples.createValidWebSpider();
        assertNotNull(webSpider.getStartLink());

        assertThrows(NullPointerException.class, () -> webSpider.allStatReport());
    }

    @Test
    void allReportWithEmptyStatistics() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        WebSpider webSpider = webSpiderSamples.createValidWebSpider();
        assertNotNull(webSpider.getStartLink());

        PageStatistic.terms = new String[] {"Elon","Mask","Tesla"};

        assertDoesNotThrow(() -> webSpider.allStatReport());
    }

    @Test
    void topPagesReportWithEmptyTerms() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        WebSpider webSpider = webSpiderSamples.createValidWebSpider();
        assertNotNull(webSpider.getStartLink());

        assertThrows(NullPointerException.class, () -> webSpider.topPagesReport());
    }

    @Test
    void topPagesWithEmptyStatistics() {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";

        WebSpider webSpider = webSpiderSamples.createValidWebSpider();
        assertNotNull(webSpider.getStartLink());

        PageStatistic.terms = new String[] {"Elon","Mask","Tesla"};

        assertDoesNotThrow(() -> webSpider.topPagesReport());
    }


}
