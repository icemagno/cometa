package br.com.drwars.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfHeaderAndFooterUtil {

    private final String titulo;
    private final String path;
    private static Font footerFont = new Font(FontFamily.TIMES_ROMAN, 10, 0, BaseColor.GRAY);

    public PdfHeaderAndFooterUtil(String path) {
        this(path, null);
    }

    public PdfHeaderAndFooterUtil(String path, String titulo) {
        super();
        this.titulo = titulo;
        this.path = path;
    }

    public void generate(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        ColumnText.showTextAligned( cb, Element.ALIGN_CENTER, new Phrase("PÚBLICA", footerFont), document.leftMargin() - 1, document.bottom() - 20, 0);

    }

}
