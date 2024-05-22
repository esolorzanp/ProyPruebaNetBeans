
package controller;

import dao.RazaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Raza;
import view.RazaFrm;


public class RazaCtrl implements ActionListener{
    
    private final Raza modelo;
    private final RazaDao consultas;
    private final RazaFrm vista;
    
    public RazaCtrl(Raza modelo, RazaDao consultas, RazaFrm vista){
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
    }

     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnLimpiar) {
            Limpiar();
        } else if (e.getSource() == vista.btnModificar) {
            Modificar();
        } else if (e.getSource() == vista.btnGuardar) {
            Guardar();
        }else if (e.getSource() == vista.btnEliminar) {
        Eliminar();
        }else if (e.getSource() == vista.btnBuscar) {
        Buscar();
        }
    }
   
    public void Iniciar() {
        vista.setTitle("Raza");
        vista.setLocationRelativeTo(null);
       Listar();
    }
    
    public void Limpiar() {
        vista.txtRazaId.setText(null);
        vista.txtRazaDescripcion.setText(null);
        vista.txtRazaBuscar.setText(null);
    }
    
      public void Guardar() {
        String razaId = vista.txtRazaId.getText();
        String razaDescrip = vista.txtRazaDescripcion.getText();
        String razaEst = "Activo";
        String nombreEspecie = vista.cbRazaEspecie.getSelectedItem().toString();
        
        if (razaDescrip.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de guardar.");
            return; 
        }
   
        if (consultas.existeDescripcion(razaDescrip)) {
            JOptionPane.showMessageDialog(null, "La descripción ingresada ya está en uso. Por favor, ingrese una descripción diferente.");
            return; 
        }
        
        int idEspecie = consultas.getIdEspecie(nombreEspecie);
        
        
        if (idEspecie != -1) {
            
            modelo.setRazaId(razaId);
            modelo.setRazaDescrip(razaDescrip);
            modelo.setRazaEst(razaEst);
            modelo.setRazaEspID(String.valueOf(idEspecie));
    
        if (consultas.add(modelo)) {
            JOptionPane.showMessageDialog(null, "Registro Guardado");
            Limpiar();
            Listar();
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar el registro");
        }
           
        }
    }
    
      public void Modificar() {
    int fila = vista.jtbListar.getSelectedRow();
    if (fila >= 0) {
        int id = Integer.parseInt(this.vista.jtbListar.getValueAt(fila, 0).toString());
        String desc = vista.jtbListar.getValueAt(fila, 1).toString();
        String est = vista.jtbListar.getValueAt(fila, 2).toString();
        String espDesc = vista.jtbListar.getValueAt(fila, 3).toString(); // Obtener la descripción de la especie seleccionada
        
        // Realizar una consulta para obtener el ID de la especie correspondiente a la descripción seleccionada
        int espId = consultas.getIdEspecie(espDesc);
        if (espId == -1) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID de la especie.");
            return;
        }
        
        Raza raz = new Raza();
        raz.setRazaId(Integer.toString(id));
        raz.setRazaDescrip(desc);
        raz.setRazaEst(est);
        raz.setRazaEspID(Integer.toString(espId)); // Establecer el ID de la especie
        
        if (consultas.Update(raz)) {
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente");
            Listar();
        } else {
            JOptionPane.showMessageDialog(null, "No se realizó ninguna modificación");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione el registro a modificar antes de presionar el botón");
    }
}
    
    public void Eliminar() {
        int fila = vista.jtbListar.getSelectedRow();
    if (fila >= 0) {
        int id = Integer.parseInt(this.vista.jtbListar.getValueAt(fila, 0).toString());
        if (consultas.Eliminar(id)) {
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
            Listar();
           
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione el registro a eliminar antes de presionar el botón");
    }
    }
    
    
    public void Buscar() {
    String idTexto = vista.txtRazaBuscar.getText();
    String nombreEspecie = vista.cbRazaEspecie.getSelectedItem().toString();
    int idEspecie = consultas.getIdEspecie(nombreEspecie);

    try {
        int id = Integer.parseInt(idTexto);
        Raza raz = consultas.botonBuscar(id);

        if (raz != null) {

            vista.txtRazaId.setText(raz.getRazaId());
            vista.txtRazaDescripcion.setText(raz.getRazaDescrip());
            vista.txtRazaEst.setText(raz.getRazaEst());
            
            // Setear la especie en el combobox
            for (int i = 0; i < vista.cbRazaEspecie.getItemCount(); i++) {
                if (idEspecie == consultas.getIdEspecie(vista.cbRazaEspecie.getItemAt(i).toString())) {
                    vista.cbRazaEspecie.setSelectedIndex(i);
                    break;
                }
            }
        } else {

            JOptionPane.showMessageDialog(null, "No se encontró ningún registro con el ID proporcionado.");
        }
    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número entero válido para buscar.");
    }
}
    
    
    public void Listar(){
        List<Raza> rz = consultas.getAllRaza(1);
        DefaultTableModel model = (DefaultTableModel) vista.jtbListar.getModel();
        model.setRowCount(0); // Limpiar filas existentes
        model.setColumnIdentifiers(new Object[]{"Raza ID", "Descripción", "Estado", "Especie"});
        for (Raza razas : rz) {
            model.addRow(new Object[]{razas.getRazaId(), razas.getRazaDescrip(), razas.getRazaEst(), razas.getRazaDesEspecie()});
        }
    }
    
    
}
