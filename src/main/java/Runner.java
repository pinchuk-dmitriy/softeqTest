import webCrawler.PageStatistic;
import webCrawler.WebSpider;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;


public class Runner {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";

    public static void main(String[] args) {
        FileInputStream fileInputStream;

        Properties properties = new Properties();

        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);

            final String startUrl = properties.getProperty("startUrl");
            final int defaultLinkDepth = Integer.parseInt(properties.getProperty("defaultLinkDepth"));
            final int maxLinksNumber = Integer.parseInt(properties.getProperty("maxLinksNumber"));
            final String[] terms = properties.getProperty("terms").split(",");

            PageStatistic.terms = terms;

            final WebSpider seedLink = new WebSpider(new URL(startUrl), defaultLinkDepth, maxLinksNumber);
            seedLink.crawler();
            seedLink.allStatReport();
            seedLink.topPagesReport();

        } catch (Exception e) {
            System.out.println("Error in program: file " + PATH_TO_PROPERTIES + " has been not found");
            e.printStackTrace();
        }
    }
}
