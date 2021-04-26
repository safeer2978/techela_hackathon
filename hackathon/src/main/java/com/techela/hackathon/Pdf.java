package com.techela.hackathon;

import java.io.*;
import java.util.ArrayList;

import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.techela.hackathon.db.model.ExamData;
import com.techela.hackathon.Language.Hindi;
import com.techela.hackathon.Language.LanguageStrings;
import com.techela.hackathon.Language.Telgu;
import com.techela.hackathon.db.model.Student;
import com.techela.hackathon.db.model.Subject;
import com.techela.hackathon.db.model.Teacher;

public class Pdf {

	//TODO: Change DIR as per system..
	final String PDF_OUTPUT_CHROME="C:\\Users\\safee\\Desktop\\techela\\techela_hackathon\\hackathon\\chrome_output.pdf";

	/**	Generates PDF using Chrome Headless.
	 *  Requires Chome 58+ installed on system. On windows, chrome needs to be added as a PATH variable
	 * */
	void createPdfUsingChrome(String htmlString){
		File f = new File("text.html");
		BufferedWriter bufferedWriter = null;

		try {
			bufferedWriter = new BufferedWriter(new FileWriter(f));
			bufferedWriter.write(htmlString);
			bufferedWriter.close();
			//Desktop.getDesktop().browse(f.toURI());
			Process p;
			p = Runtime.getRuntime().exec("chrome --headless --disable-gpu --print-to-pdf="+PDF_OUTPUT_CHROME+" " + f.getAbsolutePath());
			System.out.println("PDF successfully prepared using Chrome");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Generates PDF using Images.
	 *  Accepts HTML Sting an renders it as image using Aspose Library.
	 *  The Images are then Added to pdf using iTextPdf
	 * */
	void createPdfUsingImage(String htmlString){
		String html_path="text.html";
		String image_path="image_out.png";
		String pdf_path="pdf_out.pdf";

		File f = new File("text.html");
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(f));
			bufferedWriter.write(htmlString);
			bufferedWriter.close();

			com.aspose.html.HTMLDocument document_html = new com.aspose.html.HTMLDocument(html_path);

			// Initialize ImageSaveOptions
			com.aspose.html.saving.ImageSaveOptions options = new com.aspose.html.saving.ImageSaveOptions(com.aspose.html.rendering.image.ImageFormat.Jpeg);

			System.out.println("Starting html to image preparation");
			// Convert HTML to output JPG image
			com.aspose.html.converters.Converter.convertHTML(document_html, options, image_path);
			System.out.println("html to image done");
			Document document_image_pdf = new Document();

			String input = image_path; // .gif and .jpg are ok too!
			String output = pdf_path;

			FileOutputStream fos = new FileOutputStream(output);
			PdfWriter writer = PdfWriter.getInstance(document_image_pdf, fos);
			writer.open();
			System.out.println("Writing image to PDF");
			document_image_pdf.open();
			//ImageData data = ImageDataFactory.create(input);
			for(int i=1;i<4;i++) {
				//TODO: Change Path here as well
				Image image = Image.getInstance("C:\\Users\\safee\\Desktop\\techela\\techela_hackathon\\hackathon\\image_out_"+i+".png");
				image.scaleToFit(900f, 700f);
				document_image_pdf.add(image);
			}
			System.out.println("PDF ready using image");
			document_image_pdf.close();
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


	/** Generates Html using database values and hard-codded strings.
	 * 	Calls either @createPdfUsingChrome() or @createPdfUsingImage()
	 * */
	public void firstReport(ArrayList<Student> students, int choice){
		LanguageStrings strings = new LanguageStrings();

		switch(choice){
			case 1:
				strings=new Hindi();
				break;
			case 2:
				strings=new Telgu();
				break;
		}

		String data ="";
		for(Student student: students){
			data+=studentRow(student);
		}

		String htmlString = "<html>\n" +
				"<body>\n" +
				"\t<h1>"+strings.getStudent_report_ReportTitle()+"</h1>\n" +
				"\t<h4>"+strings.getStudent_report_subtitile()+"</h4>\n"+
				"\t<table border=\"1\">\n" +
				"\t\t<tr>\n" +
				"\t\t\t<td>"+strings.getStudent_report_id()+"</td>\n" +
				"\t\t\t<td>"+strings.getStudent_report_name()+"</td>\n" +
				"\t\t\t<td>"+strings.getStudent_report_prn()+"</td>\n" +
				"\t\t\t<td>"+strings.getStudent_report_year()+"</td>\n" +
				"\t\t</tr>" + data +
				"</tr>\n" +
				"\t</table>\n" +
				"</body>\n" +
				"\t\n" +
				"</html>";

		System.out.println("Html Prepared!");
		createPdfUsingChrome(htmlString);
	}

	/** Generates Html using database values and hard-codded strings.*/
	public void secondReport(Student std, ArrayList<ExamData> examDataList, int choice){
		LanguageStrings strings = new LanguageStrings();

		switch(choice){
			case 1:
				strings=new Hindi();
				break;
			case 2:
				strings=new Telgu();
				break;
		}

		String data ="";
		for(ExamData examData: examDataList){
			data+=examDataRow(examData);
		}

		String htmlString = "<html>\n" +
				"<style>\n" +
				"   .content-table{\n" +
				"\tborder-collapse: collapse;\n" +
				"\tmargin: 25px \n" +
				"\tfont-size: 0.9em;\n" +
				"\tmin-width: 400px\n" +
				" \tborder-radius: 5px 5px 0 0;\n" +
				"\toverflow: hidden;\n" +
				"\tbox-shadow: 0 0 20px rgba(0, 0, 0, 0.15);\n" +
				"}\n" +
				"\n" +
				".content-table thead tr {\n" +
				"  background-color: #009879;\n" +
				"  color: #ffffff;\n" +
				"  text-align: left;\n" +
				"  font-weight: bold;\n" +
				"}\n" +
				"\n" +
				".content-table th,\n" +
				".content-table td {\n" +
				"  padding: 12px 15px;\n" +
				"}\n" +
				"\n" +
				".content-table tbody tr {\n" +
				"  border-bottom: 1px solid #dddddd;\n" +
				"}\n" +
				"\n" +
				"</style>\n" +
				"  <body>\n" +"" +
				"<h1>"+strings.getSecond_report_title()+"</h1>"+
				"<h4>"+strings.getSecond_report_subtitle_1()+"</h4>"+
				"\n" +
				" <table class =\"content-table\" border=\"1\">\n" +
				"\t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+ strings.getStudent_report_name()+"</td> \n" +
				"\t<td>"+std.getFirst_name()+"</td> \n" +
				" \t</tr>\n" +
				"\t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+strings.getStudent_report_prn()+"</td>\n" +
				"\t<td>"+std.getPrn()+"</td>\n" +
				" \t</tr>\n" +
				"\t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+strings.getStudent_report_id()+"</td>\n" +
				"\t<td>"+std.getId()+"</td>\n" +
				" \t</tr>\n" +
				" \t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+strings.getStudent_report_year()+"</td>\n" +
				"\t<td>"+std.getYear()+"</td> \n" +
				" \t</tr>\t\n" +
				"   </table>"+
				"\n" +
				"<br>\n" +
				"<h4>"+strings.getSecond_report_subtitle_2()+"</h4>"+
				"<table class=content-table Border=\"1\">\n" +
				"<thead>\t<tr> \n" +
				"\t<td>"+strings.getStudent_report_exam_id()+"</td>\n" +
				"\t<td>"+strings.getStudent_report_ReportTitle()+"</td>\n" +
				"\t<td>"+strings.getStudent_report_subject_name()+"</td>\n" +
				"\t<td>"+strings.getStudent_report_name()+"</td>\n" +
				"\t<td>"+strings.getStudent_report_marks()+"</td>\n" +
				" \t</tr>\n" +
				"</thead>\t" +data+ "" +
				"</tbody>\n" +
				"</table>\n" +
				" </body>\n" +
				"</html>";

		System.out.println("Html Prepared!");
		createPdfUsingChrome(htmlString);
	}

	/** Generates Html using database values and hard-codded strings.*/
	public void thirdReport(Teacher tenu, Subject subject, ArrayList<Student> arstd, int choice){
		LanguageStrings strings = new LanguageStrings();

		switch(choice){
			case 1:
				strings=new Hindi();
				break;
			case 2:
				strings=new Telgu();
				break;
		}

		String data ="";
		for(Student student: arstd){
			data+=studentRow(student);
		}

		String htmlString = "<html>\n" +
				"<style>\n" +
				"   .content-table{\n" +
				"\tborder-collapse: collapse;\n" +
				"\tmargin: 25px \n" +
				"\tfont-size: 0.9em;\n" +
				"\tmin-width: 400px\n" +
				" \tborder-radius: 5px 5px 0 0;\n" +
				"\toverflow: hidden;\n" +
				"\tbox-shadow: 0 0 20px rgba(0, 0, 0, 0.15);\n" +
				"}\n" +
				"\n" +
				".content-table thead tr {\n" +
				"  background-color: #009879;\n" +
				"  color: #ffffff;\n" +
				"  text-align: left;\n" +
				"  font-weight: bold;\n" +
				"}\n" +
				"\n" +
				".content-table th,\n" +
				".content-table td {\n" +
				"  padding: 12px 15px;\n" +
				"}\n" +
				"\n" +
				".content-table tbody tr {\n" +
				"  border-bottom: 1px solid #dddddd;\n" +
				"}\n" +
				"\n" +
				"</style>\n" +
				"  <body>\n" +
				"  \t<h1>"+strings.getThird_report_title()+"</h1>\n" +
				"  \t<h4>"+strings.getThird_report_subtitle_1()+"</h4>\n" +
				"    <table class =\"content-table\" border=\"1\">\n" +
				"\t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+strings.getStudent_report_id()+"</td> \n" +
				"\t<td >"+tenu.getId()+"</td>\n" +
				" \t</tr>\n" +
				"\t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+strings.getStudent_report_name()+"</td>\n" +
				"\t<td>"+tenu.getName()+"</td>\n" +
				" \t</tr>\n" +
				"\t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+strings.getQualification()+"</td>\n" +
				"\t<td>"+tenu.getQualification()+"</td> \n" +
				" \t</tr>\n" +
				" \t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+strings.getAverageMarks()+"</td>\n" +
				"\t<td>74</td> \n" +
				" \t</tr>\n" +
				"\t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+strings.getMax_marks()+"</td>\n" +
				"\t<td>\"90\"</td> \n" +
				" \t</tr>\n" +
				"\t<tr> \n" +
				"\t<td style=\"color: white;\" bgcolor=\" #009879\">"+strings.getMin_marks()+"</td>\n" +
				"\t<td>\"57\"</td> \n" +
				" \t</tr>\n" +
				"   </table>\n" +
				"<br>\n" +
				"<h4>"+strings.getThird_report_subtitle_2()+"</h4>\n" +
				"<table class=content-table Border=\"1\">\n" +
				"<thead>\t<tr> \n" +
				"\t\t\t<td>"+strings.getStudent_report_id()+"</td>\n" +
				"\t\t\t<td>"+strings.getStudent_report_name()+"</td>\n" +
				"\t\t\t<td>"+strings.getStudent_report_prn()+"</td>\n" +
				"\t\t\t<td>"+strings.getStudent_report_year()+"</td>\n" +
				" \t</tr>\n" +
				"</thead>\t\n" +
				"<tbody>"+data+"</tbody>\n" +
				"</table>\n" +
				" </body>\n" +
				"</html>";

		System.out.println("Html Prepared!");
		createPdfUsingChrome(htmlString);
	}

	/*Helper functions*/
	private String examDataRow(ExamData examData) {
		return "<tbody>\n" +
				"\t<tr> \n" +
				"\t<td>"+examData.getId()+"</td>\n" +
				"\t<td>"+examData.getTitle()+"</td>\n" +
				"\t<td>"+examData.getName()+"</td>\n" +
				"\t<td>"+examData.getSubjectTeacher()+"</td>\n" +
				"\t<td>"+examData.getMarksOutOff()+"</td>\n" +
				" \t</tr>\n" +
				"</tbody>";
	}
	private String studentRow(Student student){
		return "<tr>\n" +
				"\t\t\t<td>"+student.getId()+"</td>\n" +
				"\t\t\t<td>"+student.getFirst_name()+"</td> \n" +
				"\t\t\t<td>"+student.getPrn()+"</td>\n" +
				"\t\t\t<td>"+student.getYear()+"</td>\n" +
				"\t\t</tr>";
	}

}
