package controller;

import dao.ExamenDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Examen;
import view.ExamenFrm;

public class ExamenCtrl implements ActionListener {

    private Examen model;
    private ExamenDao dao;
    private ExamenFrm vista;

    public ExamenCtrl(Examen model, ExamenDao dao, ExamenFrm vista) {
        this.model = model;
        this.dao = dao;
        this.vista = vista;
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnAdicionar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnBuscar) {
            this.buscar();
        }
        if (e.getSource() == this.vista.btnLimpiar) {
            this.limpiar();
        }
        if (e.getSource() == this.vista.btnAdicionar) {
            this.adicionar();
        }
        if (e.getSource() == this.vista.btnModificar) {
            this.modificar();
        }
        if (e.getSource() == this.vista.btnEliminar) {
            this.eliminar();
        }

    }

    private void adicionar() {
        this.model.setExaDescripcion(this.vista.txtDescripcion.getText());
        this.model.setExaValor(Integer.parseInt(this.vista.txtValor.getText()));
        this.model.setExaTipo(this.vista.txtTipo.getText());
        if (dao.adicionar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro creado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    private void buscar() {
        this.model.setExaId(Integer.parseInt(this.vista.txtBuscar.getText()));
        if (dao.buscarId(this.model)) {
            this.limpiar();
            this.vista.txtDescripcion.setText(this.model.getExaDescripcion());
            this.vista.txtValor.setText(String.valueOf(this.model.getExaValor()));
            this.vista.txtTipo.setText(this.model.getExaTipo());
        }
    }

    private void eliminar() {
        if (dao.eliminar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }
    }

    public void iniciar() {
        this.vista.setTitle("Exámenes");
        this.vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vista.pack();
        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
        this.listar();
    }

    private void listar() {
        DefaultTableModel model = (DefaultTableModel) vista.dataTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "Id",
            "Examen",
            "Valor",
            "Tipo",
            "Estado",
            "Uusario anula",
            "Fecha anula"
        });
        model.setRowCount(0);
        for (Examen e : dao.getTodos(1)) {
            model.addRow(new Object[]{
                e.getExaId(),
                 e.getExaDescripcion(),
                 e.getExaValor(),
                 e.getExaTipo(),
                 e.getExaEstado(),
                 e.getExaUsuAnu(),
                 e.getExaFechaAnu()
            });
        }
    }

    private void limpiar() {
        this.vista.txtBuscar.setText("");
        this.vista.txtDescripcion.setText("");
        this.vista.txtValor.setText("");
        this.vista.txtTipo.setText("");
    }

    private void modificar() {
        this.model.setExaDescripcion(this.vista.txtDescripcion.getText());
        this.model.setExaValor(Integer.parseInt(this.vista.txtValor.getText()));
        this.model.setExaTipo(this.vista.txtTipo.getText());
        if (dao.modificar(this.model)) {
            listar();
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
        }

    }

}
