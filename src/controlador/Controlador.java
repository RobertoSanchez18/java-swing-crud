package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import modelo.PersonaDAO;
import java.util.List;
import javax.swing.JOptionPane;
import vista.Vista;


public class Controlador implements ActionListener{
    
    PersonaDAO dao = new PersonaDAO();
    Persona p = new Persona();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Vista v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnOk.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        listar(vista.table);
    }
    
 
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == vista.btnListar){
            limpiarTabla();
            listar(vista.table);
        }
        if(ae.getSource() == vista.btnGuardar) {
            agregar();
            limpiarTabla();
            listar(vista.table);
        }
        if(ae.getSource() == vista.btnEditar) {
            int fila = vista.table.getSelectedRow();
            if(fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            } else {
                int id = Integer.parseInt(vista.table.getValueAt(fila, 0).toString());
                String nom = (String) vista.table.getValueAt(fila, 1);
                String correo = (String) vista.table.getValueAt(fila, 2);
                String tel = (String) vista.table.getValueAt(fila, 3);
                vista.txtId.setText("" + id);
                vista.txtNombre.setText(nom);
                vista.txtCorreo.setText(correo);
                vista.txtTelefono.setText(tel);
            }
        }
        if(ae.getSource() == vista.btnOk) {
            actualizar();
            limpiarTabla();
            listar(vista.table);
        }
        
        if(ae.getSource() == vista.btnEliminar) {
            delete();
            limpiarTabla();
            listar(vista.table);
        }
    }
    
    
    void limpiarTabla() {
        for (int i = 0; i < vista.table.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i-1;
        }
    }
    
    public void delete() {
        int fila = vista.table.getSelectedRow();
            if(fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            } else {
                int id = Integer.parseInt((String)vista.table.getValueAt(fila, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(vista, "Usuario eliminado");
            }
    }
    
    public void actualizar() {
        int id = Integer.parseInt(vista.txtId.getText());
        String nom = vista.txtNombre.getText();
        String correo = vista.txtCorreo.getText();
        String tel = vista.txtTelefono.getText();
        p.setId(id);
        p.setNombre(nom);
        p.setCorreo(correo);
        p.setTelefono(tel);
        int r = dao.Actualizar(p);
        if(r == 1 ) {
            JOptionPane.showMessageDialog(vista, "Usuario actualizado con exito");
            vista.txtId.setText("");
            vista.txtNombre.setText("");
            vista.txtCorreo.setText("");
            vista.txtTelefono.setText("");
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error");
        }
    }
    
    
    public void agregar() {
        String nom = vista.txtNombre.getText();
        String correo = vista.txtCorreo.getText();
        String tel = vista.txtTelefono.getText();
        
        if (nom.isEmpty() || correo.isEmpty() || tel.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        p.setNombre(nom);
        p.setCorreo(correo);
        p.setTelefono(tel);
        
        int r = dao.agregar(p);
        if(r==1) {
            JOptionPane.showMessageDialog(vista, "Usuario agregado con exito");
            vista.txtNombre.setText("");
            vista.txtCorreo.setText("");
            vista.txtTelefono.setText("");
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error");
        }
    }
    
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        List<Persona> lista = dao.listar();
        Object[]object = new Object[4];
        for (int i = 0; i < lista.size() ;i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getCorreo();
            object[3] = lista.get(i).getTelefono();
            modelo.addRow(object);
        }
        vista.table.setModel(modelo);
    }
    
    
    
}
