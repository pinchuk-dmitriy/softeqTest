	WEB CRAWLER

Jsoup was used to work with urls.
To simplify writing code, I used lombok. Junit was used to write unit-tests.

	How to run it?
You need to write your necessery parameters in configuration file application.resources and then start the project.

	About the code
Runner.java - class, which read the configuration file and starts crawler.
WebSpider.java:
Main method crawler() - This method calls the crawl method, which calls the initUrls() method, which creates a single Set collection with a start page.
Crawl method is called recursively every time a link is navigated to a new depth level.
allStatReport() function writes all collected statistics to a CSV file using PrintWriter and StreamApi.
topPagesReport() function writes the top 10 pages by the maximum number of matches to a CSV file.
PageStatistic.java:
countMatches() method counted matches in current document.
