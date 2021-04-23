package com.techela.hackathon.db.pdf;

import java.io.FileNotFoundException; 
import java.io.FileOutputStream;
import java.util.ArrayList;

import java.util.Locale;
import java.util.ResourceBundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.techela.hackathon.db.model.Student;

public class Pdf {
	
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
        	PdfPCell cell1=new PdfPCell(new Paragraph(cols.getPrn()));
        	addCell(cell1,tbl);    		            
        		            
	        PdfPCell cell2=new PdfPCell(new Paragraph(cols.getFirst_name()));
            addCell(cell2,tbl);
            
            PdfPCell cell3=new PdfPCell(new Paragraph(cols.getYear()));
            addCell(cell3,tbl);
                    
            PdfPCell cell4=new PdfPCell(new Paragraph(cols.getId()));
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
	
}
