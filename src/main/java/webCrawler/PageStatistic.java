package webCrawler;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;
import webCrawler.validation.PageStatisticValidator;

import java.net.URL;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@Setter
public class PageStatistic {

    private final URL url;

    public static String[] terms;
    private int[] matches;

    private int totalMatches;

    public PageStatistic(URL url) {
        PageStatisticValidator.pageStatisticValidation(url);
        this.url = url;
        this.matches = new int[terms.length];
    }

    public void countMatches(Document document) {
        checkNotNull(document);

        String thisDocument = document.text();
        int lastIndex;
        int countOfMatches;

        for (int i = 0; i < terms.length; i++) {

            lastIndex = 0;
            countOfMatches = 0;
            String currentTerm = terms[i];

            while(lastIndex != -1) {
                lastIndex = thisDocument.indexOf(currentTerm, lastIndex);

                if (lastIndex != -1) {
                    countOfMatches++;
                    lastIndex++;
                } else {
                    matches[i] = countOfMatches;
                    totalMatches += countOfMatches;
                }
            }
        }
    }

    @Override
    public String toString() {
        String str = url.toString() + " ";
        for( int i = 0; i < matches.length; i++) {
            str = str + "," + matches[i];
        }
        str = str + "," + totalMatches;
        return str;
    }
}