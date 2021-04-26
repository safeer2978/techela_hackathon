import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PdfTesting {
    public static void main(String[] args) throws IOException {

        String s = "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <title>Hindi Font का उपयोग</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1>We Can Use Hindi Font</h1>\n" +
                "    <p>इस Page में हम हिन्दी भाषा का उपयोग करेंगे</p>\n" +
                "    <h1>We Can also use Hindi Hex Code</h1>\n" +
                "    <p>&#x0907;&#x0938;&nbsp;Page&nbsp;&#x092E;&#x0947;&#x0902;&nbsp;&#x0939;&#x092E;&nbsp;&#x0939;&#x093F;&#x0928;&#x094D;&#x0926;&#x0940;&nbsp;&#x092D;&#x093E;&#x0937;&#x093E;&nbsp;&#x0909;&#x092A;&#x092F;&#x094B;&#x0917;&nbsp;&#x0915;&#x0930;&#x0947;&#x0902;&#x0917;&#x0947;</p>\n" +
                "    \n" +
                "    <a href=\"https://www.anirdesh.com/gujarati/hindi-unicode.php\"><h1>Hindi Code Entity</h1></a>\n" +
                "  </body>\n" +
                "</html>";

        File f = new File("text.html");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
        bufferedWriter.write(s);
        bufferedWriter.close();

        Desktop.getDesktop().browse(f.toURI());
    }

    private static void generatePDFFromHTML(String filename) {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream("src/output/html.pdf"));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(filename));
        document.close();
    }

}
