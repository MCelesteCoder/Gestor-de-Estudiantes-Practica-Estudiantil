package maríaumaña.proyecto2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class FiltrarPromedioFrame extends javax.swing.JInternalFrame {

    DefaultTableModel tablaPromedios = new DefaultTableModel();
    int seleccionar;

    ArrayList<String> promedio;
    ArrayList<String> info;

    public FiltrarPromedioFrame() {
        initComponents();

        cargarTablaYComboBoxPromedio();
        dropDownListCarreras();
    }

    private void cargarTablaYComboBoxPromedio() {
        //Se agregan las columnas a la tablaPromedios
        tablaPromedios.addColumn("ID");
        tablaPromedios.addColumn("Nombre");
        tablaPromedios.addColumn("1°Apellido");
        tablaPromedios.addColumn("2°Apellido");
        tablaPromedios.addColumn("Carrera");
        tablaPromedios.addColumn("Promedio");
    }

    private void dropDownListCarreras() {

        //Se llama al metodo para llenar ek arrayList 
        llenarArrayEst();

        for (int i = 0; i < info.size(); i += 5) {
           
            String carrera = info.get(i + 4);
            //Si la carrera no existe en el comboBox se agrega
            if(!carreraExiste(carrera)){
                DDLCarreras.addItem(carrera);
            }
            
        }
    }

    //Metodo para verificar si existe la carrera ya en el comboBox
    private boolean carreraExiste(String carrera) {
        
        for (int i = 0; i < DDLCarreras.getItemCount(); i++) {
            if (carrera.equals(DDLCarreras.getItemAt(i))) {
                return true; // La carrera ya existe en el DropDownList
            }
        }
        return false; // La carrera no existe en el DropDownList
    }

    //Metodo para filtrar los promedios
    private void filtrarPorPromedio() {
        tablaPromedios.setRowCount(0);

        String promedioIngresado = String.valueOf(txtPromedio.getText());
        String carreraSeleccionada = (String) DDLCarreras.getSelectedItem();

        llenarArraysProm();
        llenarArrayEst();

        for (int i = 0; i < info.size(); i += 5) {
            String id = info.get(i);
            String nom = info.get(i + 1);
            String primer = info.get(i + 2);
            String segundo = info.get(i + 3);
            String carrera = info.get(i + 4);
            String prom = promedio.get(i / 5);

            //Dependiendo del button que haya oprimido el usuario se filtra por promedio o por carrera
            switch (seleccionar) {
                case 1:

                    if (carrera.equals(carreraSeleccionada)) {
                        // Se agrega una fila con los datos leídos
                        tablaPromedios.addRow(new Object[]{id, nom, primer, segundo, carrera, prom});
                    }
                    break;
                case 2:
                    if (prom.equals(promedioIngresado)) {
                        // Se agrega una fila con los datos leídos
                        tablaPromedios.addRow(new Object[]{id, nom, primer, segundo, carrera, prom});
                    }
                    break;
            }
        }

        limpiarCampos();
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

    private void limpiarCampos() {
        //Se limpian los campos y el JComboBox
        DDLCarreras.setSelectedIndex(0);
        txtPromedio.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DDLCarreras = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPromedio = new javax.swing.JTextField();
        btnFiltrarCar = new javax.swing.JButton();
        btnFiltrarProm = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Filtrar por...");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Carrera : ");

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Promedio : ");

        btnFiltrarCar.setBackground(new java.awt.Color(204, 204, 204));
        btnFiltrarCar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFiltrarCar.setText("Filtrar");
        btnFiltrarCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarCarActionPerformed(evt);
            }
        });

        btnFiltrarProm.setBackground(new java.awt.Color(204, 204, 204));
        btnFiltrarProm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFiltrarProm.setText("Filtrar");
        btnFiltrarProm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarPromActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(DDLCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFiltrarCar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFiltrarProm)
                .addGap(121, 121, 121))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnFiltrarProm)
                    .addComponent(jLabel2)
                    .addComponent(btnFiltrarCar)
                    .addComponent(DDLCarreras)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jTable1.setModel(tablaPromedios);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarCarActionPerformed
        seleccionar = 1;
        filtrarPorPromedio();
    }//GEN-LAST:event_btnFiltrarCarActionPerformed

    private void btnFiltrarPromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarPromActionPerformed
        seleccionar = 2;
        filtrarPorPromedio();
    }//GEN-LAST:event_btnFiltrarPromActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DDLCarreras;
    private javax.swing.JButton btnFiltrarCar;
    private javax.swing.JButton btnFiltrarProm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtPromedio;
    // End of variables declaration//GEN-END:variables
}
