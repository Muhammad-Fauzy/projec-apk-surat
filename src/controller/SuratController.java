package controller;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import model.Surat;
import view.Dashboard;

public class SuratController {

    private Dashboard dashboard;
    private DefaultTableModel model;
    private ArrayList<Surat> daftarSurat;

    public SuratController(Dashboard dashboard) {
        this.dashboard = dashboard;
        this.model = dashboard.getModel();
        this.daftarSurat = new ArrayList<>();
        initEvent();
    }

    private void initEvent() {
        // Tombol
        dashboard.getBtnTambah().addActionListener(e -> tambahData());
        dashboard.getBtnEdit().addActionListener(e -> editData());
        dashboard.getBtnHapus().addActionListener(e -> hapusData());
        dashboard.getBtnSort().addActionListener(e -> sortTanggal());
        dashboard.getBtnFilter().addActionListener(e -> filterData());
        dashboard.getBtnPDF().addActionListener(e -> exportPdf());

        // Klik tabel
        dashboard.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int row = dashboard.getTable().getSelectedRow();
            if (row >= 0) {
                dashboard.getCmbJenis().setSelectedItem(model.getValueAt(row, 0).toString());
                dashboard.getTxtNomor().setText(model.getValueAt(row, 1).toString());
                dashboard.getTxtTanggal().setText(model.getValueAt(row, 2).toString());
                dashboard.getTxtPengirim().setText(model.getValueAt(row, 3).toString());
                dashboard.getTxtPerihal().setText(model.getValueAt(row, 4).toString());
            }
        });
    }

    // ================= TAMBAH =================
    private void tambahData() {
        String jenis = dashboard.getCmbJenis().getSelectedItem().toString();
        String nomor = dashboard.getTxtNomor().getText();
        String tanggal = dashboard.getTxtTanggal().getText();
        String pengirim = dashboard.getTxtPengirim().getText();
        String perihal = dashboard.getTxtPerihal().getText();

        if (nomor.isEmpty() || tanggal.isEmpty() || pengirim.isEmpty() || perihal.isEmpty()) {
            JOptionPane.showMessageDialog(dashboard, "Semua data harus diisi!");
            return;
        }

        Surat surat = new Surat(jenis, nomor, tanggal, pengirim, perihal);
        daftarSurat.add(surat);

        model.addRow(new Object[]{
                surat.getJenis(),
                surat.getNomor(),
                surat.getTanggal(),
                surat.getPengirim(),
                surat.getPerihal()
        });

        bersih();
        JOptionPane.showMessageDialog(dashboard, "Data berhasil ditambahkan");
    }

    // ================= EDIT =================
    private void editData() {
        int row = dashboard.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(dashboard, "Pilih data yang akan diedit!");
            return;
        }

        model.setValueAt(dashboard.getCmbJenis().getSelectedItem(), row, 0);
        model.setValueAt(dashboard.getTxtNomor().getText(), row, 1);
        model.setValueAt(dashboard.getTxtTanggal().getText(), row, 2);
        model.setValueAt(dashboard.getTxtPengirim().getText(), row, 3);
        model.setValueAt(dashboard.getTxtPerihal().getText(), row, 4);

        JOptionPane.showMessageDialog(dashboard, "Data berhasil diubah");
        bersih();
    }

    // ================= HAPUS =================
    private void hapusData() {
        int row = dashboard.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(dashboard, "Pilih data yang akan dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(dashboard, "Yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            model.removeRow(row);
            if (row < daftarSurat.size()) {
                daftarSurat.remove(row);
            }
            bersih();
            JOptionPane.showMessageDialog(dashboard, "Data berhasil dihapus");
        }
    }

    // ================= SORT TANGGAL =================
    private void sortTanggal() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        dashboard.getTable().setRowSorter(sorter);

        sorter.setComparator(2, (o1, o2) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date d1 = sdf.parse(o1.toString());
                Date d2 = sdf.parse(o2.toString());
                return d1.compareTo(d2);
            } catch (ParseException e) {
                return o1.toString().compareTo(o2.toString());
            }
        });

        sorter.toggleSortOrder(2); // Kolom tanggal
    }

    // ================= FILTER =================
    private void filterData() {
        String keyword = JOptionPane.showInputDialog(dashboard, "Masukkan kata kunci untuk filter (pengirim/perihal):");
        if (keyword == null || keyword.trim().isEmpty()) {
            return;
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        dashboard.getTable().setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
    }

    // ================= EXPORT PDF =================
    private void exportPdf() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("DataSurat.pdf"));
            document.open();

            Paragraph title = new Paragraph("Daftar Surat", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            PdfPTable tablePdf = new PdfPTable(5);
            tablePdf.setWidthPercentage(100);
            tablePdf.addCell("Jenis");
            tablePdf.addCell("Nomor");
            tablePdf.addCell("Tanggal");
            tablePdf.addCell("Pengirim");
            tablePdf.addCell("Perihal");

            for (int i = 0; i < model.getRowCount(); i++) {
                tablePdf.addCell(model.getValueAt(i, 0).toString());
                tablePdf.addCell(model.getValueAt(i, 1).toString());
                tablePdf.addCell(model.getValueAt(i, 2).toString());
                tablePdf.addCell(model.getValueAt(i, 3).toString());
                tablePdf.addCell(model.getValueAt(i, 4).toString());
            }

            document.add(tablePdf);
            document.close();

            JOptionPane.showMessageDialog(dashboard, "Data berhasil diexport ke DataSurat.pdf");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dashboard, "Terjadi kesalahan saat export PDF: " + e.getMessage());
        }
    }

    // ================= BERSIH =================
    private void bersih() {
        dashboard.getTxtNomor().setText("");
        dashboard.getTxtTanggal().setText("");
        dashboard.getTxtPengirim().setText("");
        dashboard.getTxtPerihal().setText("");
        dashboard.getCmbJenis().setSelectedIndex(0);
        dashboard.getTable().clearSelection();
    }
}
