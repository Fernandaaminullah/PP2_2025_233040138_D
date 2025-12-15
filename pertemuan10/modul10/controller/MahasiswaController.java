package id.ac.unpas.modul10.controller;

import id.ac.unpas.modul10.KoneksiDB;
import id.ac.unpas.modul10.view.MahasiswaView;
import java.awt.HeadlessException;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class MahasiswaController {

    private final MahasiswaView view;

    public MahasiswaController(MahasiswaView view) {
        this.view = view;
        loadData();
        initAction();
    }

    private void loadData() {
        view.model.setRowCount(0);
        try {
            String sql = "SELECT * FROM mahasiswa";
            Connection conn = KoneksiDB.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                view.model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("nim"),
                        rs.getString("jurusan")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private void initAction() {

        view.btnSimpan.addActionListener(e -> {
            try {
                String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, view.txtNama.getText());
                pst.setString(2, view.txtNIM.getText());
                pst.setString(3, view.txtJurusan.getText());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(view, "Data berhasil disimpan");
                loadData();
                view.clearForm();
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        });

        view.btnEdit.addActionListener(e -> {
            int row = view.tableMahasiswa.getSelectedRow();
            if (row >= 0) {
                try {
                    String sql = "UPDATE mahasiswa SET nama=?, nim=?, jurusan=? WHERE id=?";
                    Connection conn = KoneksiDB.configDB();
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, view.txtNama.getText());
                    pst.setString(2, view.txtNIM.getText());
                    pst.setString(3, view.txtJurusan.getText());
                    pst.setInt(4, Integer.parseInt(view.model.getValueAt(row, 0).toString()));
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(view, "Data berhasil diupdate");
                    loadData();
                    view.clearForm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }
            }
        });

        view.btnHapus.addActionListener(e -> {
            int row = view.tableMahasiswa.getSelectedRow();
            if (row >= 0) {
                try {
                    String sql = "DELETE FROM mahasiswa WHERE id=?";
                    Connection conn = KoneksiDB.configDB();
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(view.model.getValueAt(row, 0).toString()));
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(view, "Data berhasil dihapus");
                    loadData();
                    view.clearForm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }
            }
        });

        view.btnClear.addActionListener(e -> view.clearForm());

        view.tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tableMahasiswa.getSelectedRow();
                view.txtNama.setText(view.model.getValueAt(row, 1).toString());
                view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
                view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
            }
        });
    }
}
