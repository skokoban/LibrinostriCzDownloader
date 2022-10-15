package tools;

import main.Librinostri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UpdaterTest {

/*======================================================================================================================
                                         Preparation methods
======================================================================================================================*/



/*======================================================================================================================
                                         Methods
======================================================================================================================*/

    @Test
    @DisplayName("New pdf files was found")
    void passWhenNewPDFFilesWasFound() {
        Librinostri librinostri = new Librinostri();
        Updater updaterMock = Mockito.mock(Updater.class);
        //when(updaterMock.findNewBooks(librinostri.getBOOKS_INFO())).thenReturn()
    }
}
