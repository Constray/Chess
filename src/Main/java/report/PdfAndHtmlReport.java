package report;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PdfAndHtmlReport {

    /**
     * Creates PDF file report.
     * @param filePath Report file path.
     * @param outputData Data to output.
     */
    public static void writePdfFile(String filePath, ArrayList<String> outputData) {
        Table table = new Table(new float[]{3, 5, 5});
        table.addHeaderCell(new Cell().add(new Paragraph("Number")));
        table.addHeaderCell(new Cell().add(new Paragraph("From")));
        table.addHeaderCell(new Cell().add(new Paragraph("To")));
        for (int i = 0; i < outputData.toArray().length; i ++) {
            table.addCell(new Cell().add(new Paragraph((Integer.toString(i + 1)))));
            table.addCell(new Cell().add(new Paragraph(outputData.get(i).substring(0, 2))));
            table.addCell(new Cell().add(new Paragraph(outputData.get(i).substring(2))));
        }
        PdfWriter writer;
        try {
            writer = new PdfWriter(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);
        doc.add(table);
        doc.close();
    }

    /**
     *  Creates HTML report file.
     * @param filePath Report file path.
     * @param outputData Data to output.
     */
    public static void writeHtmlFile (String filePath, ArrayList<String> outputData) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        writer.println("<TABLE BORDER><TR><TH>Number<TH>From<TH>To</TR>");
        for (int i = 0; i < outputData.toArray().length; i++) {
            writer.println("<TR><TD>" + Integer.toString(i + 1)
                    + "<TD>" + outputData.get(i).substring(0, 2)
                    + "<TD>" + outputData.get(i).substring(2));
        }
        writer.println("</TABLE>");
        writer.close();
    }
}
