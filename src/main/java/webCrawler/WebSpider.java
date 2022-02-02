package webCrawler;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import webCrawler.validation.WebSpiderValidator;

import java.io.*;
import java.net.URL;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@Setter
public class WebSpider {

    public Map<String, PageStatistic> allStatistics = new HashMap<>();

    private final Set<URL> links;
    private final URL startLink;
    private int counterOfLinkDepth;
    private int counterOfLinksNumber;
    private final int maxLinksNumber;
    private final int maxLinkDepth;

    public WebSpider(final URL startURL, final int maxLinkDepth, final int maxLinksNumber) {
        WebSpiderValidator.webSpiderValidation(startURL, maxLinkDepth, maxLinksNumber);
        this.links = new HashSet<>();
        this.startLink = startURL;
        this.maxLinkDepth = maxLinkDepth;
        this.maxLinksNumber = maxLinksNumber;
    }

    public void crawler() {
        crawl(initURLS(this.startLink));
    }

    private void crawl(final Set<URL> urls) {
        urls.removeAll(this.links);

        this.counterOfLinkDepth++;

        if (this.counterOfLinkDepth <= this.maxLinkDepth) {
            final Set<URL> newURLS = new HashSet<>();
            try {

                this.links.addAll(urls);
                for (final URL url : urls) {

                    if(Jsoup.connect(url.toString()).execute().statusCode() != 200) {
                        continue;
                    }

                    final Document document = Jsoup.connect(url.toString()).get();

                    PageStatistic pageStatistic = new PageStatistic(url);
                    pageStatistic.countMatches(document);
                    allStatistics.put(url.toString(), pageStatistic);

                    System.out.println(pageStatistic);

                    if(this.counterOfLinksNumber < this.maxLinksNumber - 1) {
                        final Elements linksOnPage = document.select("a[href]");
                        for (final Element element : linksOnPage) {
                            if(this.counterOfLinksNumber < this.maxLinksNumber - 1) {
                                final String urlText = element.attr("abs:href");
                                final URL discoveredURL = new URL(urlText);

                                if(newURLS.add(discoveredURL)){
                                    this.counterOfLinksNumber++;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if(!newURLS.isEmpty()){
                crawl(newURLS);
            }
        }
    }

    private Set<URL> initURLS(final URL startURL) {
        return Collections.singleton(startURL);
    }

    public void allStatReport() {

        checkNotNull(PageStatistic.terms);

        try (PrintWriter writer = new PrintWriter(new File("allStat.csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append("url");
            sb.append(',');
            for(int i = 0; i < PageStatistic.terms.length; i++) {
                sb.append(PageStatistic.terms[i]);
                sb.append(',');
            }
            sb.append("total");
            sb.append('\n');

            writer.write(sb.toString());

            allStatistics.entrySet().stream()
                    .map(x -> x.getValue())
                    .forEach(s ->  writer.write(s.toString() + '\n'));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void topPagesReport() {

        checkNotNull(PageStatistic.terms);

        try (PrintWriter writer = new PrintWriter(new File("topPages.csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append("url");
            sb.append(',');
            for(int i = 0; i < PageStatistic.terms.length; i++) {
                sb.append(PageStatistic.terms[i]);
                sb.append(',');
            }
            sb.append('\n');

            writer.write(sb.toString());

            allStatistics.entrySet().stream()
                    .sorted(HashMap.Entry.<String, PageStatistic>comparingByValue(new Comparator<>() {
                        @Override
                        public int compare(PageStatistic o1, PageStatistic o2) {
                            return Integer.compare(o1.getTotalMatches(), o2.getTotalMatches());
                        }
                    }).reversed())
                    .limit(10)
                    .map(x -> x.getValue())
                    .forEach(s -> writer.write(s.toString() + '\n'));


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

}