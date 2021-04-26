
import com.aspose.pdf.HtmlLoadOptions;
import com.openhtmltopdf.mathmlsupport.MathMLDrawer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.w3c.dom.Document;


import java.awt.*;
import java.io.*;
import java.nio.file.FileSystems;

public class PDF {
    public static void main(String[] args) throws Exception {
/*
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
*/

//        Desktop.getDesktop().browse(f.toURI());

        //generatePDFFromHTML(f);

        //HtmlLoadOptions htmloptions = new HtmlLoadOptions();
       // Document doc = new Document(new FileInputStream(f),htmloptions);
        //doc.save("123.pdf");
        //renderPDF(s, new FileOutputStream("template-guide.pdf"),  f);


/*        try {
            // Source HTML file
            String inputHTML = "C:\\Users\\safee\\Desktop\\techela\\techela_hackathon\\untitled1\\text.html";
            // Generated PDF file name
            String outputPdf = "C:\\Users\\safee\\Desktop\\techela\\techela_hackathon\\untitled1\\Output.pdf";
            htmlToPdf(inputHTML, outputPdf);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        Process p;
        try {
            p = Runtime.getRuntime().exec("chrome --headless --disable-gpu --print-to-pdf=C:\\Users\\safee\\Desktop\\techela\\techela_hackathon\\untitled1\\output1234.pdf C:\\Users\\safee\\Desktop\\techela\\techela_hackathon\\untitled1\\text.html");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    private static void renderPDF(String html, OutputStream outputStream, File f) throws Exception {
        try {
            PdfRendererBuilder builder = new PdfRendererBuilder();
           // builder.useSVGDrawer(new BatikSVGDrawer());
           // builder.useMathMLDrawer(new MathMLDrawer());
           // builder.useFont(new File("C:\\Users\\safee\\Desktop\\techela\\techela_hackathon\\untitled1\\devnagri.ttf"),Font.MONOSPACED);

            builder.withHtmlContent(html, PDF.class.getResource("/").toString());
            builder.toStream(outputStream);
            builder.run();
        } finally {
            outputStream.close();
        }
    }

    private static Document html5ParseDocument(String inputHTML) throws IOException{
        org.jsoup.nodes.Document doc;
        System.out.println("parsing ...");
        doc = Jsoup.parse(new File(inputHTML), "UTF-8");
        System.out.println("parsing done ..." + doc);
        return new W3CDom().fromJsoup(doc);
    }

    private static void htmlToPdf(String inputHTML, String outputPdf) throws IOException {
        Document doc = html5ParseDocument(inputHTML);
        String baseUri = FileSystems.getDefault()
                .getPath("C:\\", "Users\\safee\\Desktop\\techela\\", "\\techela_hackathon\\untitled1")
                .toUri()
                .toString();
        OutputStream os = new FileOutputStream(outputPdf);
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withUri(outputPdf);
        builder.toStream(os);
        // using absolute path here
        builder.useFont(new File("C:\\Users\\safee\\Desktop\\techela\\techela_hackathon\\untitled1\\devnagri.ttf"),
                "Gabriola");
        builder.withW3cDocument(doc, baseUri);
        //builder.useUriResolver(new MyResolver());
        builder.run();
        System.out.println("PDF generation completed");
        os.close();
    }


/*    private static void generatePDFFromHTML(File file) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream("html.pdf"));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(file));
        document.close();
    }*/
}
