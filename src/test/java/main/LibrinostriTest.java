package main;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LibrinostriTest {

/*======================================================================================================================
                                         Attributes
======================================================================================================================*/

    private File rssFile = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "testRSS.php");
    private Document rssDocument;
    private ArrayList<String> pdfFileLinks = new ArrayList<>();

/*======================================================================================================================
                                             Methods
======================================================================================================================*/
    void init() {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            rssDocument = saxBuilder.build(rssFile);
        } catch (JDOMException | IOException e) {
            throw new RuntimeException(e);
        }
        pdfFileLinks.add("test1");
        pdfFileLinks.add("test2");
    }
    @Test
    void passWhenAddBooksWorks() throws IOException, JDOMException {
        SAXBuilder saxBuilderMock = Mockito.mock(SAXBuilder.class);
        when(saxBuilderMock.build("http://librinostri.catholica.cz/rss.php")).thenReturn(rssDocument);
        Librinostri librinostriMock = Mockito.mock(Librinostri.class);
        when(librinostriMock.parseDownloadLinks("http://librinostri.catholica.cz/kniha/2690-pod-sluncem-satanovym"))
                .thenReturn(pdfFileLinks);
        assertEquals(2, librinostriMock.getBOOKS_INFO().size());
    }
}
