package com.techela.hackathon.db.pdf;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.techela.hackathon.ExamData;
import com.techela.hackathon.Language.Hindi;
import com.techela.hackathon.Language.LanguageStrings;
import com.techela.hackathon.Language.Telgu;
import com.techela.hackathon.db.model.Student;
import com.techela.hackathon.db.model.Subject;
import com.techela.hackathon.db.model.Teacher;

public class Pdf {

	void createPdf(String htmlString){
		File f = new File("text.html");
		BufferedWriter bufferedWriter = null;


		try {
			bufferedWriter = new BufferedWriter(new FileWriter(f));
			bufferedWriter.write(htmlString);
			bufferedWriter.close();
			//Desktop.getDesktop().browse(f.toURI());
			Process p;
			p = Runtime.getRuntime().exec("chrome --headless --disable-gpu --print-to-pdf=C:\\Users\\safee\\Desktop\\techela\\techela_hackathon\\hackathon\\output12345.pdf " + f.getAbsolutePath());
			System.out.println("PDF successfully prepared using Chrome");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String html_path="text.html";
		String image_path="image_out.png";
		String pdf_path="pdf_out.pdf";


		com.aspose.html.HTMLDocument document_html = new com.aspose.html.HTMLDocument(html_path);

		try {
			// Initialize ImageSaveOptions
			com.aspose.html.saving.ImageSaveOptions options = new com.aspose.html.saving.ImageSaveOptions(com.aspose.html.rendering.image.ImageFormat.Jpeg);

			System.out.println("Starting html to image preparation");
			// Convert HTML to output JPG image
			com.aspose.html.converters.Converter.convertHTML(document_html, options, image_path);
			System.out.println("html to image done");
		} finally {
			if (document_html!= null) {
				document_html.dispose();
			}
		}

		Document document_image_pdf = new Document();
		String input = image_path; // .gif and .jpg are ok too!
		String output = pdf_path;
		try {
			FileOutputStream fos = new FileOutputStream(output);
			PdfWriter writer = PdfWriter.getInstance(document_image_pdf, fos);
			writer.open();
			System.out.println("Writing image to PDF");
			document_image_pdf.open();
			//ImageData data = ImageDataFactory.create(input);
			for(int i=1;i<4;i++) {
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

	public void create1sthtml(ArrayList<Student> students, int choice){
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
		createPdf(htmlString);
	}

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
		createPdf(htmlString);
	}

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
		createPdf(htmlString);
	}

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
	
	public static void addCell(PdfPCell cell1,PdfPTable tbl) {
		cell1.setBorderColor(BaseColor.BLACK);
        cell1.setBorder(Rectangle.BOX);
        cell1.setPaddingLeft(10);            
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tbl.addCell(cell1);
	}

	public void mainPdf(ArrayList<Student> arl) {


		Locale l=new Locale("en","US");
		
		ResourceBundle r= ResourceBundle.getBundle("Bundle", l);
		String str=r.getString("text");
		System.out.println(str);
		//SAMPLE COLM
		//HEADING KA PART
		ArrayList<String> heads=new ArrayList<String>();
		heads.add("PRN");
		heads.add("Name");
		heads.add("Branch");
		heads.add("Batch");
		Document doc=new Document();

	try {
		PdfWriter writer=PdfWriter.getInstance(doc, new FileOutputStream("AddingTable.pdf"));
		doc.open();
						
		PdfPTable tbl=new PdfPTable(4);
		tbl.setWidthPercentage(100);
		tbl.setSpacingAfter(10f);
		//tbl.setSpacingBefore(10f);
		
		float[] columnWidths = {1f, 1f, 1f, 1f};
        tbl.setWidths(columnWidths);        
        
        for(String heading:heads) {
		PdfPCell cell1=new PdfPCell(new Paragraph(heading));
		cell1.setBorderColor(BaseColor.BLACK);
        cell1.setBorder(Rectangle.BOX);
        cell1.setPaddingLeft(10);
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tbl.addCell(cell1);
		}
        
        for(int i=0;i<arl.size();i++) {
        	Student cols=arl.get(i);
        	PdfPCell cell1=new PdfPCell(new Paragraph(String.valueOf(cols.getPrn())));
        	addCell(cell1,tbl);    		            
        		            
	        PdfPCell cell2=new PdfPCell(new Paragraph(cols.getFirst_name()));
            addCell(cell2,tbl);
            
            PdfPCell cell3=new PdfPCell(new Paragraph(String.valueOf(cols.getYear())));
            addCell(cell3,tbl);
                    
            PdfPCell cell4=new PdfPCell(new Paragraph(String.valueOf(cols.getId())));
            addCell(cell4,tbl);            
    		}
		
        doc.add(tbl);                
		doc.close();
		writer.close();
		
	}catch(DocumentException e) {
		e.printStackTrace();
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}
	
	}

	private static void addHeader(PdfPCell cell1,PdfPTable tbl) {
		cell1.setBorderColor(BaseColor.BLACK);
		cell1.setBorder(Rectangle.BOX);
		cell1.setPaddingLeft(10);
		cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tbl.addCell(cell1);
	}

	public void pdfStudent(Student std, ArrayList<ExamData> examDataList) {



		Locale l=new Locale("hi","IN");
		ResourceBundle r= ResourceBundle.getBundle("Bundle", l);

		ArrayList<String> headstd=new ArrayList<String>();
		headstd.add(r.getString("student_report.name"));
		headstd.add(r.getString("student_report.prn"));
		headstd.add(r.getString("student_report.id"));
		headstd.add(r.getString("student_report.year"));

		ArrayList<String> headExam=new ArrayList<String>();
		headExam.add(r.getString("student_report.exam_id"));
		headExam.add(r.getString("student_report.title"));
		headExam.add(r.getString("student_report.subject_name"));
		headExam.add(r.getString("student_report.teacher_name"));
		headExam.add(r.getString("student_report.marks"));

		Document doc=new Document();

		try {
			PdfWriter writer=PdfWriter.getInstance(doc, new FileOutputStream("AddingTable.pdf"));
			doc.open();
			byte[] bytesArray = new byte[150000];

			PdfPTable tbl1=new PdfPTable(2);
			tbl1.setWidthPercentage(100);
			tbl1.setSpacingAfter(10f);
			tbl1.setSpacingBefore(10f);

			float[] columnWidths = {1f, 1f};
			tbl1.setWidths(columnWidths);

			PdfPCell cellh1=new PdfPCell(new Paragraph(headstd.get(0)));
			addHeader(cellh1,tbl1);
			PdfPCell cell1=new PdfPCell(new Paragraph(std.getFirst_name()));
			addCell(cell1,tbl1);

			PdfPCell cellh2=new PdfPCell(new Paragraph(headstd.get(1)));
			addHeader(cellh2,tbl1);
			PdfPCell cell2=new PdfPCell(new Paragraph(String.valueOf(std.getId())));
			addCell(cell2,tbl1);

			PdfPCell cellh3=new PdfPCell(new Paragraph(headstd.get(2)));
			addHeader(cellh3,tbl1);
			PdfPCell cell3=new PdfPCell(new Paragraph(String.valueOf(std.getPrn())));
			addCell(cell3,tbl1);

			PdfPCell cellh4=new PdfPCell(new Paragraph(headstd.get(3)));
			addHeader(cellh4,tbl1);
			PdfPCell cell4=new PdfPCell(new Paragraph(String.valueOf(std.getYear())));
			addCell(cell4,tbl1);

			PdfPTable tbl2=new PdfPTable(5);
			tbl2.setWidthPercentage(100);
			tbl2.setSpacingAfter(10f);
			tbl2.setSpacingBefore(10f);

			float[] columnWidths2 = {1f, 1f,1f,1f,1f};
			tbl2.setWidths(columnWidths2);

			for(String heading:headExam) {
				PdfPCell cellh=new PdfPCell(new Paragraph(heading));
				cellh.setBorderColor(BaseColor.BLACK);
				cellh.setBorder(Rectangle.BOX);
				cellh.setPaddingLeft(10);
				cellh.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellh.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellh.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tbl2.addCell(cellh);
			}

			for(int i=0;i<examDataList.size();i++) {
				ExamData exam=examDataList.get(i);
				PdfPCell celle1=new PdfPCell(new Paragraph(String.valueOf(exam.getId())));
				addCell(celle1,tbl2);

				PdfPCell celle2=new PdfPCell(new Paragraph(String.valueOf(exam.getTitle())));
				addCell(celle2,tbl2);

				PdfPCell celle3=new PdfPCell(new Paragraph(String.valueOf(exam.getTitle())));
				addCell(celle3,tbl2);

				PdfPCell celle4=new PdfPCell(new Paragraph(String.valueOf(exam.getSubjectTeacher())));
				addCell(celle4,tbl2);

				PdfPCell celle5=new PdfPCell(new Paragraph(String.valueOf(exam.getMarksOutOff())));
				addCell(celle5,tbl2);
			}

//	        for(int i=0;i<arl.size();i++) {
//	        	Colm cols=arl.get(i);
//	        	PdfPCell cell1=new PdfPCell(new Paragraph(cols.getPrn()));
//	        	addCell(cell1,tbl);
//
//		        PdfPCell cell2=new PdfPCell(new Paragraph(cols.getName()));
//	            addCell(cell2,tbl);
//
//	            PdfPCell cell3=new PdfPCell(new Paragraph(cols.getBranch()));
//	            addCell(cell3,tbl);
//
//	            PdfPCell cell4=new PdfPCell(new Paragraph(cols.getBatch()));
//	            addCell(cell4,tbl);
//	    		}
//
			doc.add(tbl1);
			doc.add(tbl2);
			doc.close();
			writer.close();

		}catch(DocumentException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void mainSecond(Teacher tea) {
		ArrayList<Teacher> art=new ArrayList<Teacher>();

		art.add(tea);

		ArrayList<String> headTea=new ArrayList<String>();
		headTea.add("Id");
		headTea.add("Name");
		headTea.add("Qual");
		headTea.add("Subname");
		headTea.add("num");


		Document doc=new Document();

		try {
			PdfWriter writer=PdfWriter.getInstance(doc, new FileOutputStream("AddingTable.pdf"));
			doc.open();

			PdfPTable tbl2=new PdfPTable(5);
			tbl2.setWidthPercentage(100);
			tbl2.setSpacingAfter(10f);
			tbl2.setSpacingBefore(10f);

			float[] columnWidths2 = {1f, 1f,1f,1f,1f};
			tbl2.setWidths(columnWidths2);

			for(String heading:headTea) {
				PdfPCell cellh=new PdfPCell(new Paragraph(heading));
				cellh.setBorderColor(BaseColor.BLACK);
				cellh.setBorder(Rectangle.BOX);
				cellh.setPaddingLeft(10);
				cellh.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellh.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellh.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tbl2.addCell(cellh);
			}

			for(int i=0;i<art.size();i++) {
				Teacher teac=art.get(i);
				PdfPCell cellt1=new PdfPCell(new Paragraph(teac.getId()));
				addCell(cellt1,tbl2);

				PdfPCell cellt2=new PdfPCell(new Paragraph(teac.getName()));
				addCell(cellt2,tbl2);

				PdfPCell cellet3=new PdfPCell(new Paragraph(teac.getQualification()));
				addCell(cellet3,tbl2);

				PdfPCell cellet4=new PdfPCell(new Paragraph("Subject1"));
				addCell(cellet4,tbl2);

				PdfPCell celle1=new PdfPCell(new Paragraph("34"));
				addCell(celle1,tbl2);

			}


			Paragraph para=new Paragraph("Teacher Report");
			para.setAlignment(Element.ALIGN_CENTER);
			doc.add(new Paragraph(para));

	          Paragraph parag=new Paragraph("Student Report");
	         parag.setAlignment(Element.ALIGN_CENTER);
	        doc.add(new Paragraph(parag));



			doc.add(tbl2);
			doc.close();
			writer.close();

		}catch(DocumentException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}


/*public void function() throws IOException {
	PdfDocument doc = new PdfDocument();
	try {
		PdfFont fontMangal
				= PdfFont.create("C:\\Users\\safee\\AppData\\Local\\Microsoft\\Windows\\Fonts\\Mangal 400.ttf",
				10,
				PdfEncodings.UTF_16BE,
				PdfFont.EMBED_SUBSET);

		String strHindi
				= "अंग्रेजी का आसमान";

		doc.writeText(strHindi, fontMangal, 150,300);

		doc.setOpenAfterSave(true);
		doc.save("Write_Non_English_Text_To_PDF.pdf");
		doc.close();

	} catch (PdfException e) {
		e.printStackTrace();
	}

}*/

	public void  mainThird(Teacher tenu, Subject subject, ArrayList<Student> arstd) {
		ArrayList<String> headstd=new ArrayList<String>();
		headstd.add("Name");
		headstd.add("PRN");
		headstd.add("ID");
		headstd.add("Year");

		ArrayList<String> headDets=new ArrayList<String>();
		headDets.add("ID");
		headDets.add("Name");
		headDets.add("Qualification");
		headDets.add("Avg Marks");
		headDets.add("Top Marks");
		headDets.add("Max Marks");




		Document doc=new Document();
		try {
			PdfWriter writer=PdfWriter.getInstance(doc, new FileOutputStream("AddingTable.pdf"));
			doc.open();

			PdfPTable tbl1=new PdfPTable(2);
			tbl1.setWidthPercentage(100);
			tbl1.setSpacingAfter(10f);
			tbl1.setSpacingBefore(10f);

			float[] columnWidths = {1f, 1f};
			tbl1.setWidths(columnWidths);

			PdfPCell cellh1=new PdfPCell(new Paragraph(headDets.get(0)));
			addHeader(cellh1,tbl1);
			PdfPCell cell1=new PdfPCell(new Paragraph(String.valueOf(tenu.getId())));
			addCell(cell1,tbl1);

			PdfPCell cellh2=new PdfPCell(new Paragraph(headDets.get(1)));
			addHeader(cellh2,tbl1);
			PdfPCell cell2=new PdfPCell(new Paragraph(tenu.getName()));
			addCell(cell2,tbl1);

			PdfPCell cellh3=new PdfPCell(new Paragraph(headDets.get(2)));
			addHeader(cellh3,tbl1);
			PdfPCell cell3=new PdfPCell(new Paragraph(tenu.getQualification()));
			addCell(cell3,tbl1);

			PdfPCell cellh4=new PdfPCell(new Paragraph(headDets.get(3)));
			addHeader(cellh4,tbl1);
			PdfPCell cell4=new PdfPCell(new Paragraph("76"));
			addCell(cell4,tbl1);

			PdfPCell cellh5=new PdfPCell(new Paragraph(headDets.get(4)));
			addHeader(cellh5,tbl1);
			PdfPCell cell5=new PdfPCell(new Paragraph("98"));
			addCell(cell5,tbl1);

			PdfPCell cellh6=new PdfPCell(new Paragraph(headDets.get(5)));
			addHeader(cellh6,tbl1);
			PdfPCell cell6=new PdfPCell(new Paragraph("56"));
			addCell(cell6,tbl1);




			PdfPTable tbl2=new PdfPTable(4);
			tbl2.setWidthPercentage(100);
			tbl2.setSpacingAfter(10f);
			tbl2.setSpacingBefore(10f);

			float[] columnWidths2 = {1f, 1f,1f,1f};
			tbl2.setWidths(columnWidths2);

			for(String heading:headstd) {
				PdfPCell cellh=new PdfPCell(new Paragraph(heading));
				cellh.setBorderColor(BaseColor.BLACK);
				cellh.setBorder(Rectangle.BOX);
				cellh.setPaddingLeft(10);
				cellh.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cellh.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellh.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tbl2.addCell(cell1);
			}

			for(int i=0;i<arstd.size();i++) {
				Student exam=arstd.get(i);
				PdfPCell celle1=new PdfPCell(new Paragraph(String.valueOf(exam.getFirst_name())));
				addCell(celle1,tbl2);

				PdfPCell celle2=new PdfPCell(new Paragraph(String.valueOf(exam.getPrn())));
				addCell(celle2,tbl2);

				PdfPCell celle3=new PdfPCell(new Paragraph(String.valueOf(exam.getId())));
				addCell(celle3,tbl2);

				PdfPCell celle4=new PdfPCell(new Paragraph(String.valueOf(exam.getYear())));
				addCell(celle4,tbl2);

			}

			doc.add(tbl1);
			doc.add(tbl2);
			doc.close();
			writer.close();

		}catch(DocumentException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
