package tools;

import main.Book;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DownloadLinksParserTest {

    Book testBook = new Book("testBook", "http://librinostri.catholica.cz/kniha/2687");

/*
    @Test
    @DisplayName("test")
    public void test() throws IOException {
        ArrayList<Book> testBooks = new ArrayList<>();
        testBooks.add(testBook);
        DownloadLinksParser downloadLinksParser = new DownloadLinksParser();
        System.out.println(downloadLinksParser.parseDownloadLinks(testBooks).get(0).getDOWNLOAD_LINKS());
        Jsoup jsoupTest = PowerMockito.spy(new Jsoup());

        assertEquals()
    }
*/
}
