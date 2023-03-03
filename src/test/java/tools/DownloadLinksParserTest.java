package tools;

import main.Book;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


public class DownloadLinksParserTest {

    Book testBook = new Book("testBook", "http://librinostri.catholica.cz/kniha/2687");

    @Test
    @DisplayName("test")
    public void test() {
        Connection connection = new HttpConnection();
        MockedStatic<Jsoup> mockJsoup = Mockito.mockStatic(Jsoup.class);
        //mockJsoup.when(Jsoup::connect).thenReturn(connection);
    }
}
