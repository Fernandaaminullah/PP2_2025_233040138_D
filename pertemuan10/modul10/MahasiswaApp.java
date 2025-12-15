package id.ac.unpas.modul10;

import id.ac.unpas.modul10.view.MahasiswaView;
import id.ac.unpas.modul10.controller.MahasiswaController;

import javax.swing.*;

public class MahasiswaApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaView view = new MahasiswaView();
            new MahasiswaController(view);
            view.setVisible(true);
        });
    }
}
