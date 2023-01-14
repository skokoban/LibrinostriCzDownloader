package tools;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionHelper {
/*======================================================================================================================
                                                    Methods
======================================================================================================================*/
    public HttpURLConnection makeConnection(String link) throws IOException {
        URL url = new URL(link);
        return (HttpURLConnection) url.openConnection();
    }
}