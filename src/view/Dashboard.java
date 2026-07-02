package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Dashboard extends JFrame {

    private JTextField txtNomor;
    private JTextField txtTanggal;
    private JTextField txtPengirim;
    private JTextField txtPerihal;

    private JComboBox<String> cmbJenis;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnTambah;
    private JButton btnEdit;
    private JButton btnHapus;
    private JButton btnSort;
    private JButton btnFilter;
    private JButton btnPDF;

    public Dashboard() {
        // Gunakan Look and Feel modern (Nimbus)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("📂 Aplikasi Arsip Surat");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponent();
    }

    private void initComponent() {
        JPanel panelUtama = new JPanel(new BorderLayout());
        panelUtama.setBackground(new Color(245, 245, 250));

        //---------------- FORM ----------------//
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("✏️ Input Data Surat"));
        form.setBackground(new Color(250, 250, 255));

        cmbJenis = new JComboBox<>();
        cmbJenis.addItem("Surat Masuk");
        cmbJenis.addItem("Surat Keluar");

        txtNomor = new JTextField();
        txtTanggal = new JTextField();
        txtPengirim = new JTextField();
        txtPerihal = new JTextField();

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontField = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel lblJenis = new JLabel("Jenis Surat");
        lblJenis.setFont(fontLabel);
        form.add(lblJenis);
        form.add(cmbJenis);

        JLabel lblNomor = new JLabel("Nomor Surat");
        lblNomor.setFont(fontLabel);
        form.add(lblNomor);
        form.add(txtNomor);

        JLabel lblTanggal = new JLabel("Tanggal (dd-MM-yyyy)");
        lblTanggal.setFont(fontLabel);
        form.add(lblTanggal);
        form.add(txtTanggal);

        JLabel lblPengirim = new JLabel("Pengirim / Tujuan");
        lblPengirim.setFont(fontLabel);
        form.add(lblPengirim);
        form.add(txtPengirim);

        JLabel lblPerihal = new JLabel("Perihal");
        lblPerihal.setFont(fontLabel);
        form.add(lblPerihal);
        form.add(txtPerihal);

        //---------------- BUTTON ----------------//
        JPanel tombol = new JPanel(new FlowLayout());
        tombol.setBackground(new Color(245, 245, 250));

        Font fontButton = new Font("Segoe UI", Font.BOLD, 14);

        btnTambah = new JButton("➕ Tambah");
        btnTambah.setBackground(new Color(76, 175, 80));
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFont(fontButton);

        btnEdit = new JButton("✏️ Edit");
        btnEdit.setBackground(new Color(33, 150, 243));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(fontButton);

        btnHapus = new JButton("🗑️ Hapus");
        btnHapus.setBackground(new Color(244, 67, 54));
        btnHapus.setForeground(Color.WHITE);
        btnHapus.setFont(fontButton);

        btnSort = new JButton("📅 Sorting Tanggal");
        btnSort.setBackground(new Color(255, 193, 7));
        btnSort.setForeground(Color.WHITE);
        btnSort.setFont(fontButton);

        btnFilter = new JButton("🔍 Filter");
        btnFilter.setBackground(new Color(156, 39, 176));
        btnFilter.setForeground(Color.WHITE);
        btnFilter.setFont(fontButton);

        btnPDF = new JButton("📄 Export PDF");
        btnPDF.setBackground(new Color(0, 150, 136));
        btnPDF.setForeground(Color.WHITE);
        btnPDF.setFont(fontButton);

        tombol.add(btnTambah);
        tombol.add(btnEdit);
        tombol.add(btnHapus);
        tombol.add(btnSort);
        tombol.add(btnFilter);
        tombol.add(btnPDF);

        //---------------- TABLE ----------------//
        model = new DefaultTableModel();
        model.addColumn("Jenis");
        model.addColumn("Nomor");
        model.addColumn("Tanggal");
        model.addColumn("Pengirim");
        model.addColumn("Perihal");

        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(22);

        table.getTableHeader().setBackground(new Color(63, 81, 181));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(table);

        //---------------- DATA DUMMY ----------------//
        model.addRow(new Object[]{
                "Surat Masuk",
                "SM001",
                "01-06-2026",
                "Kecamatan",
                "Undangan"
        });

        model.addRow(new Object[]{
                "Surat Keluar",
                "SK001",
                "05-06-2026",
                "Dinas Pendidikan",
                "Permohonan"
        });

        //---------------- LAYOUT ----------------//
        panelUtama.add(form, BorderLayout.NORTH);
        panelUtama.add(scroll, BorderLayout.CENTER);
        panelUtama.add(tombol, BorderLayout.SOUTH);

        add(panelUtama);
    }

    //================ GETTER =================//
    public JTextField getTxtNomor() { return txtNomor; }
    public JTextField getTxtTanggal() { return txtTanggal; }
    public JTextField getTxtPengirim() { return txtPengirim; }
    public JTextField getTxtPerihal() { return txtPerihal; }
    public JComboBox<String> getCmbJenis() { return cmbJenis; }
    public JTable getTable() { return table; }
    public DefaultTableModel getModel() { return model; }
    public JButton getBtnTambah() { return btnTambah; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnSort() { return btnSort; }
    public JButton