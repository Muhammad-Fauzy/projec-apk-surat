package util;

import model.Surat;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;

public class PDFExporter {

    public static void export(ArrayList<Surat> data) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("DataSurat.pdf"));

            document.open();
            document.add(new Paragraph("LAPORAN DATA SURAT"));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.addCell("Jenis");
            table.addCell("Nomor");
            table.addCell("Tanggal");
            table.addCell("Pengirim");
            table.addCell("Perihal");

            for (Surat s : data) {
                table.addCell(s.getJenis());
                table.addCell(s.getNomor());
                table.addCell(s.getTanggal());
                table.addCell(s.getPengirim());
                table.addCell(s.getPerihal());
            }

            document.add(table);
            document.close();   // ⬅️ setelah ini file PDF selesai dibuat

            // tampilkan pesan
            JOptionPane.showMessageDialog(null, "PDF berhasil dibuat\nDataSurat.pdf");

            // otomatis buka file PDF dengan aplikasi default
            try {
                File pdfFile = new File("DataSurat.pdf");
                if (pdfFile.exists()) {
                    Desktop.getDesktop().open(pdfFile);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
