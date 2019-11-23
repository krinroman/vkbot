package com.krinroman.vkbot.parse.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class MyPDFParser {
    public static void ParseToHTML(String filename) throws IOException, ParserConfigurationException {
        PDDocument pdf = PDDocument.load(new File(filename));
        Writer output = new PrintWriter("C:\\Users\\rkrin\\Desktop\\IdeaProjects\\vkbot\\pdf.html", "utf-8");
        new PDFDomTree().writeText(pdf, output);

        output.close();

    }

    public static void ParseToText(String filename) throws IOException {

        PdfReader reader = new PdfReader(filename);
        FileWriter writer = new FileWriter("pdf.txt", false);
        // не забываем, что нумерация страниц в PDF начинается с единицы.
        for (int i = 1; i <= reader.getNumberOfPages(); ++i) {
            TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
            String text = PdfTextExtractor.getTextFromPage(reader, i, strategy);

            //text = text.replace(" \n", "");
            System.out.println(text);
            writer.write(text);

        }

        // убираем за собой
        reader.close();
        writer.close();
    }
}
