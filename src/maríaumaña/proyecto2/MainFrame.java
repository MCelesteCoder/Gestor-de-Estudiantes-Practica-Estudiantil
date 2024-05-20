/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package maríaumaña.proyecto2;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotones = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnIngresarEstudiantes = new javax.swing.JToggleButton();
        btnModificarEliminar = new javax.swing.JToggleButton();
        btnFiltrarPromedio = new javax.swing.JToggleButton();
        btnListadoEstudiantes = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        contenedorOP = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        btnIngresarEstudiantes.setBackground(java.awt.Color.gray);
        grupoBotones.add(btnIngresarEstudiantes);
        btnIngresarEstudiantes.setFont(new java.awt.Font("Sitka Text", 2, 18)); // NOI18N
        btnIngresarEstudiantes.setForeground(new java.awt.Color(51, 51, 51));
        btnIngresarEstudiantes.setText("Ingresar Estudiantes");
        btnIngresarEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarEstudiantesActionPerformed(evt);
            }
        });

        btnModificarEliminar.setBackground(java.awt.Color.gray);
        grupoBotones.add(btnModificarEliminar);
        btnModificarEliminar.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        btnModificarEliminar.setForeground(new java.awt.Color(51, 51, 51));
        btnModificarEliminar.setText("Modificar/Eliminar ");
        btnModificarEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEliminarActionPerformed(evt);
            }
        });

        btnFiltrarPromedio.setBackground(java.awt.Color.gray);
        grupoBotones.add(btnFiltrarPromedio);
        btnFiltrarPromedio.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        btnFiltrarPromedio.setForeground(new java.awt.Color(51, 51, 51));
        btnFiltrarPromedio.setText("Filtrar por...");
        btnFiltrarPromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarPromedioActionPerformed(evt);
            }
        });

        btnListadoEstudiantes.setBackground(java.awt.Color.gray);
        grupoBotones.add(btnListadoEstudiantes);
        btnListadoEstudiantes.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        btnListadoEstudiantes.setForeground(new java.awt.Color(51, 51, 51));
        btnListadoEstudiantes.setText("Listado Estudiantes");
        btnListadoEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListadoEstudiantesActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Sitka Text", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Registro Estudiantes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 137, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIngresarEstudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificarEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFiltrarPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnListadoEstudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(btnIngresarEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnListadoEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnModificarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnFiltrarPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        contenedorOP.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout contenedorOPLayout = new javax.swing.GroupLayout(contenedorOP);
        contenedorOP.setLayout(contenedorOPLayout);
        contenedorOPLayout.setHorizontalGroup(
            contenedorOPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1023, Short.MAX_VALUE)
        );
        contenedorOPLayout.setVerticalGroup(
            contenedorOPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedorOP)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedorOP)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListadoEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListadoEstudiantesActionPerformed
        //Se crea y iniciailiza la ventana de Listado de estudiantes
        ListadoFrame listado = new ListadoFrame();
        contenedorOP.getDesktopManager().maximizeFrame(listado);
        contenedorOP.add(listado);
        listado.setVisible(true);
    }//GEN-LAST:event_btnListadoEstudiantesActionPerformed

    private void btnFiltrarPromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarPromedioActionPerformed
        //Se crea y iniciailiza la ventana de filtrado de promedios
        FiltrarPromedioFrame filtrar = new FiltrarPromedioFrame();
        contenedorOP.getDesktopManager().maximizeFrame(filtrar);
        contenedorOP.add(filtrar);
        filtrar.setVisible(true);
    }//GEN-LAST:event_btnFiltrarPromedioActionPerformed


    private void btnModificarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEliminarActionPerformed
        //Se crea y iniciailiza la ventana de eliminación y modificación de estudiantes
        ModificarYEliminarFrame mYe = new ModificarYEliminarFrame();
        contenedorOP.getDesktopManager().maximizeFrame(mYe);
        contenedorOP.add(mYe);
        mYe.setVisible(true);
    }//GEN-LAST:event_btnModificarEliminarActionPerformed

    IngresarEstudiantesFrame ingresar;
    private void btnIngresarEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarEstudiantesActionPerformed
        if (ingresar == null) {

            //Se inicailiza la ventana de ingresar
            ingresar = new IngresarEstudiantesFrame();

            // Se agrega la ventana de ingreso al contenedor y se maximiza
            contenedorOP.getDesktopManager().maximizeFrame(ingresar);

            //Se agrega ingreso al contenedorOpc
            contenedorOP.add(ingresar);

            //Se le indica que ingresar sea visible
            ingresar.setVisible(true);
        } else {
            // Si la ventana de ingreso ya está abierta solo se maximiza
            contenedorOP.getDesktopManager().maximizeFrame(ingresar);
        }


    }//GEN-LAST:event_btnIngresarEstudiantesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnFiltrarPromedio;
    private javax.swing.JToggleButton btnIngresarEstudiantes;
    private javax.swing.JToggleButton btnListadoEstudiantes;
    private javax.swing.JToggleButton btnModificarEliminar;
    private javax.swing.JDesktopPane contenedorOP;
    private javax.swing.ButtonGroup grupoBotones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
