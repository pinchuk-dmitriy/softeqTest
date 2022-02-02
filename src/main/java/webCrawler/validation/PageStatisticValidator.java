package webCrawler.validation;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;

import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class PageStatisticValidator {

    @SneakyThrows
    public static void pageStatisticValidation(URL url) {

        checkNotNull(url);
        checkArgument(Jsoup.connect(url.toString()).execute().statusCode() == 200);

    }
}
