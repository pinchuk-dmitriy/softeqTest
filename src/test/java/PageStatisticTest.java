import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import samples.PageStatisticSamples;
import webCrawler.PageStatistic;

import java.net.URL;
import java.net.UnknownHostException;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class PageStatisticTest {

    PageStatisticSamples pageStatisticSamples = new PageStatisticSamples();

    @Test
    void createValidPageStatistic() {

        URL url = pageStatisticSamples.createValidUrl();

        PageStatistic pageStatistic = pageStatisticSamples.createValidPageStatistic();

        checkNotNull(PageStatistic.terms);
        checkNotNull(pageStatistic.getUrl());
    }

    @Test
    void createPageStatisticWithInvalidUrl() {

        URL url = pageStatisticSamples.createInvalidUrl();
        PageStatistic.terms = new String[] {"Elon","Mask","Tesla"};

        assertThrows(UnknownHostException.class, () -> new PageStatistic(url));

    }

    @Test
    void validCountMatches() {

        URL url = pageStatisticSamples.createValidUrl();
        Document document = pageStatisticSamples.getValidDocument(url);

        PageStatistic.terms = new String[] {"Elon","Mask","Tesla"};
        PageStatistic pageStatistic = new PageStatistic(url);

        assertDoesNotThrow(() -> pageStatistic.countMatches(document));

    }

    @Test
    void countMatchesWithInvalidDocument() {

        URL url = pageStatisticSamples.createValidUrl();
        Document document = null;

        PageStatistic.terms = new String[] {"Elon","Mask","Tesla"};
        PageStatistic pageStatistic = new PageStatistic(url);

        assertThrows(NullPointerException.class, () -> pageStatistic.countMatches(document));

    }
}
