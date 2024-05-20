package maríaumaña.proyecto2;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ListadoFrame extends javax.swing.JInternalFrame {

    DefaultTableModel tablaListado = new DefaultTableModel();

    ArrayList<String> promedio;
    ArrayList<String> info;

    public ListadoFrame() {
        initComponents();

        cargarModeloDeTablasListado();
    }

    private void cargarModeloDeTablasListado() {
        //Se agregan las columnas a la tablaListado
        tablaListado.addColumn("ID");
        tablaListado.addColumn("Nombre");
        tablaListado.addColumn("1°Apellido");
        tablaListado.addColumn("2°Apellido");
        tablaListado.addColumn("Carrera");
        tablaListado.addColumn("Promedio");

        llenarTabla();
    }

    private void llenarTabla() {

        //Se llama a los metodos de llenado de arrayList
        llenarArraysProm();
        llenarArrayEst();

        //Se recorre el arreglo y cada 5 se aumenta i
        for (int i = 0; i < info.size(); i += 5) {
            //Se obtienen los elementos del arrayList
            String id = info.get(i);
            String nom = info.get(i + 1);
            String primer = info.get(i + 2);
            String segundo = info.get(i + 3);
            String carrera = info.get(i + 4);
            String prom = promedio.get(i / 5);

            //Cuando se tiene los elementos corrspondientes de cada archivo se agrega una nueva fila
            tablaListado.addRow(new Object[]{id, nom, primer, segundo, carrera, prom});

        }

    }

    private void llenarArraysProm() {
        String cal;//Se almacenara cada linea leida en esta variable
        promedio = new ArrayList();

        //Se obtiene la ruta del archivo mediante el llamado de el metodo corrspondiente
        File archivoCalCreado = Estudiante.getArchivoPromedios();
        
        //Se lee el archivo 
        try (BufferedReader c = new BufferedReader(new FileReader(archivoCalCreado))) {
            
            //Se lee cada linea y se almacena en cal hasta que sea null, lo cuals ignifica que llegue al finald el archivo
            while ((cal = c.readLine()) != null) {
                //Se agrega al arrayList cal con cada linea 
                promedio.add(cal);
            }
        } catch (IOException excepcion) {
            
        }
    }

    private void llenarArrayEst() {
        info = new ArrayList();

        String est;

        //Se obtiene la ruta del archivo mediante el llamado de el metodo corrspondiente
        File archivoEstCreado = Estudiante.getArchivoEstudiantes();
        
        //Se lee el archivo 
        try (BufferedReader e = new BufferedReader(new FileReader(archivoEstCreado))) {
           
            //Se lee cada linea y se almacena en est hasta que sea null, lo cuals ignifica que llegue al finald el archivo
            while ((est = e.readLine()) != null) {
                //Se agrega al arrayList est con cada linea 
                info.add(est);
            }
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Listado de estudiantes");

        jTable1.setModel(tablaListado);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1011, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
