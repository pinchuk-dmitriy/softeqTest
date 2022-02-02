package webCrawler.validation;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;

import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;

public class WebSpiderValidator {

    @SneakyThrows
    public static void webSpiderValidation(final URL startURL, final int maxLinkDepth, final int maxLinksNumber) {

        checkArgument(maxLinkDepth > 0);
        checkArgument(maxLinksNumber > 0);
        checkArgument(Jsoup.connect(startURL.toString()).execute().statusCode() == 200);

    }
}
