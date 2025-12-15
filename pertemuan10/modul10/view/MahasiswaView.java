package id.ac.unpas.modul10.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaView extends JFrame {

    public JTextField txtNama, txtNIM, txtJurusan;
    public JButton btnSimpan, btnEdit, btnHapus, btnClear;
    public JTable tableMahasiswa;
    public DefaultTableModel model;

    public MahasiswaView() {
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initForm();
        initTable();
    }

    private void initForm() {
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(panelForm, BorderLayout.CENTER);
        topPanel.add(panelTombol, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
    }

    private void initTable() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);
    }

    public void clearForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
        tableMahasiswa.clearSelection();
    }
}
