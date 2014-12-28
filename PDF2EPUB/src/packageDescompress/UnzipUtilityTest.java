package packageDescompress;

public class UnzipUtilityTest {
    public static void main(String[] args) {
        String zipFilePath = "C:\\Documents and Settings\\Alberto\\Escritorio\\Hiroaki Samura - La Espada del Inmortal 01 (Spanish, CRG) por Umbriel.zip";
        String destDirectory = "C:\\Documents and Settings\\Alberto\\Escritorio\\Hiroaki Samura - La Espada del Inmortal 01 (Spanish, CRG) por Umbriel";
        UnzipUtility unzipper = new UnzipUtility();
        try {
            unzipper.unzip(zipFilePath, destDirectory);
        } catch (Exception ex) {
            // some errors occurred
            ex.printStackTrace();
        }
    }
}