package maríaumaña.proyecto2;

import java.util.UUID;
import javax.swing.JOptionPane;

public class IngresarEstudiantesFrame extends javax.swing.JInternalFrame {

    //Se crean los atributos necesarios o a los que es necesario acceder desde diferentes métodos en esta clase
    Persona persona1;
    String id;
    int promedio;
    int numEst = 0;
    boolean continuar;

    public IngresarEstudiantesFrame() {
        initComponents();

        //Se llama al método de creación de id único para que al abrir la ventana ya se encuentre uno creado.
        crearID();
    }

    private int numeroEst() {
        //Método para definir el número de estudiantes a registrar
        try {
            //Se obtiene el texto ingresado 
            String numEstStr = txtNumEst.getText();

            //Se esta en blancoe l campo se lanza una excepción
            if (numEstStr.isEmpty()) {
                throw new IllegalArgumentException("Ingrese el número de estudiantes");
            }

            //Para verificar que sea un número mayor a 0 se conveirte a integer
            numEst = Integer.parseInt(numEstStr);

            //Se crea la conficional para revisar que sea mayor a 0
            if (0 >= numEst) {
                throw new IllegalArgumentException("El número de estudiantes debe ser mayor a 0");
            }

        } catch (IllegalArgumentException e) {
            // Se captura la excepción si el campo está vacío
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return numEst;
    }

    private void guardarDatos() {

        //Se llama al metodo para hacer la verificacion del número de estudiantes por registrar
        numeroEst();

        if (numEst > 0) {
            try {
                //Se llama al metodo para la validacion de los datos ingresados
                validacionDatos();

                if (continuar == true) {
                    //Si la validacion es correcta se crean los archivos
                    creacionDatos();
                    if (numEst == 0) {
                        JOptionPane.showMessageDialog(null, "¡Todos los estudiantes han sido ingresados!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Estudiante ingresado correctamente. Faltan " + (numEst - 1) + " estudiantes por ingresar.");
                    }
                    //Se limpian todos los campos al terminar el registro
                    limpiarCampos();
                }

            } catch (IllegalArgumentException e) {
                // Se captura la excepción si el campo está vacío
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private boolean validacionDatos() {
        continuar = false;
        boolean infoCorrecta = false;
        boolean calCorrectas = false;
        boolean camposLlenos = false;
        try {
            //Se obtienen los datos de los campos
            String nom = txtNom.getText();
            String pApellido = txtpApellido.getText();
            String sApellido = txtsApellido.getText();
            String carrera = txtCarrera.getText();

            //Se revisa la longitud de nombre, primer apellido y segundo apellido 
            if (nom.length() < 3 || pApellido.length() < 3 || sApellido.length() < 3) {
                throw new IllegalArgumentException("El nombre, Primer y segundo apellido deben tener más de 3 caracteres. Por favor, ingrese un nombre válido.");
            } else {
                infoCorrecta = true;
            }

            //Para revisar que los campos tengan datos es necesario que este en el tipod e dato String
            String pProyectoStr = txtPp.getText();
            String sProyectoStr = txtPs.getText();
            String foroStr = txtForo.getText();
            String encuestaStr = txtEncuesta.getText();
            String juegoStr = txtJuego.getText();

            //Se revisa que no hayan cmapos vacíos en el registro
            if (nom.isEmpty() || pApellido.isEmpty() || sApellido.isEmpty() || pProyectoStr.isEmpty() || sProyectoStr.isEmpty() || foroStr.isEmpty() || encuestaStr.isEmpty() || juegoStr.isEmpty() || carrera.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben tener su información correspondiente.");
            } else {
                camposLlenos = true;
            }

            //Para poder comprobar si estan en el rango correcto se hace la conversión a integer
            int pProyecto = Integer.parseInt(pProyectoStr);
            int sProyecto = Integer.parseInt(sProyectoStr);
            int foro = Integer.parseInt(foroStr);
            int encuesta = Integer.parseInt(encuestaStr);
            int juego = Integer.parseInt(juegoStr);

            //Las calificaciones deben estar en el rango correspondiente para poder realizar el registro del estudiante
            if ((0 > pProyecto || pProyecto > 40) || (0 > sProyecto || sProyecto > 40) || (0 > foro || foro > 10)
                    || (0 > encuesta || encuesta > 5) || (0 > juego || juego > 5)) {
                throw new IllegalArgumentException("Digite en las calificaciones números válidos");
            } else {
                calCorrectas = true;
            }

            //Si se cumple con que las condicionales para la información del registro se puede proceder con el registro de la información
            if (infoCorrecta == true && calCorrectas == true && camposLlenos == true) {
                continuar = true;
            }

        } catch (IllegalArgumentException e) {
            // Se captura la excepción si el campo está vacío
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return continuar;
    }

    private void creacionDatos() {
        //Se crea un objeto de tipo Persona con los argumentos correspondientes 
        persona1 = new Persona(id, txtNom.getText(), txtpApellido.getText(), txtsApellido.getText());

        //Se asignan los valores al objeto persona1 
        persona1.setId(id);
        persona1.setNom(txtNom.getText());
        persona1.setpApellido(txtpApellido.getText());
        persona1.setsApellido(txtsApellido.getText());

        //Para el calculo del promedio se conveierten a int los datos necesarios
        int pProyecto = Integer.parseInt(txtPp.getText());
        int sProyecto = Integer.parseInt(txtPs.getText());
        int foro = Integer.parseInt(txtForo.getText());
        int encuesta = Integer.parseInt(txtEncuesta.getText());
        int juego = Integer.parseInt(txtJuego.getText());

        //Se crea un objeto de tipo Calculos con los argumentos correspondientes 
        Calculos cal = new Calculos(pProyecto, sProyecto, foro, encuesta, juego);

        //Se asignan los valores al objeto cal
        cal.setpProyecro(pProyecto);
        cal.setsProyecto(sProyecto);
        cal.setForo(foro);
        cal.setEncuesta(encuesta);
        cal.setJuego(juego);

        //Se crea un objeto de tipo Estudiante con los argumentos correspondientes 
        Estudiante info = new Estudiante(id, txtNom.getText(), txtpApellido.getText(), txtsApellido.getText(), txtCarrera.getText(), promedio);

        //Se llama al metodo para el calculo del promedio y se guarda en una variable
        promedio = cal.calcularPromedio();

        //Se asignan los valores al objeto info       
        info.setPromedio(promedio);
        info.setCarrera(txtCarrera.getText());

        //Se llaman los metodos para agregar la información del estudiante en sus respectivos registros
        info.creacionArchivoEstudiante();
        cal.creacionArchivoNotas();
        info.creacionArchivoPromedios();

        crearID();

    }

    //Método para la creación de id único de 9 números
    //Blog Unelike(2024). UUID EN JAVA (IDENTIFICADORES UNICOS). https://blog.unelink.es/wiki/uuid-en-java-identificadores-unicos/
    private void crearID() {
        id = UUID.randomUUID().toString();

        //Se eliminan los giones y letras
        id = id.replaceAll("[^0-9]", "");

        //Se limitan a 9 numeros
        id = id.substring(0, 9);

        txtId.setText(id);
    }

    private void limpiarCampos() {
        //Cada vez que se guarda un registro se borra lo que habia en cada campo
        numEst--;//Se busca que la cantidad de estudiantes a registrar retroceda
        txtNumEst.setText(String.valueOf(numEst));
        txtNom.setText("");
        txtpApellido.setText("");
        txtsApellido.setText("");
        txtCarrera.setText("");
        txtPp.setText("");
        txtPs.setText("");
        txtForo.setText("");
        txtEncuesta.setText("");
        txtJuego.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCarrera = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtpApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsApellido = new javax.swing.JTextField();
        btnGuardarRegistro = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtPs = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        txtJuego = new javax.swing.JTextField();
        txtEncuesta = new javax.swing.JTextField();
        txtForo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNumEst = new javax.swing.JTextField();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registro de estudiantes");

        jPanel1.setBackground(java.awt.Color.lightGray);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel1.setText("ID Estudiante :");

        txtPp.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setText("Foro Académico");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setText("Carrera Universitaria : ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel4.setText("Segundo Apellido : ");

        btnGuardarRegistro.setBackground(new java.awt.Color(51, 51, 51));
        btnGuardarRegistro.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        btnGuardarRegistro.setText("Guardar");
        btnGuardarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRegistroActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel5.setText("Nombre : ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel7.setText("Informacion");

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(153, 153, 153));
        txtId.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel8.setText("Calificaciones");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel9.setText("Proyecto 2");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel10.setText("Proyecto 1");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel11.setText("Primer Apellido : ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel12.setText("Encuesta");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel13.setText("Juego");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(177, 177, 177))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(btnGuardarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addGap(340, 340, 340))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNom)
                            .addComponent(txtCarrera)
                            .addComponent(txtsApellido)
                            .addComponent(txtpApellido, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtId))
                        .addGap(131, 131, 131))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPp)
                        .addComponent(txtJuego, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtEncuesta, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtForo, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtPs, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel10)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPs, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtForo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEncuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnGuardarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ingrese el número de estudiantes a registrar :");

        txtNumEst.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel6)
                .addGap(81, 81, 81)
                .addComponent(txtNumEst, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumEst, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRegistroActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnGuardarRegistroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCarrera;
    private javax.swing.JTextField txtEncuesta;
    private javax.swing.JTextField txtForo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtJuego;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtNumEst;
    private javax.swing.JTextField txtPp;
    private javax.swing.JTextField txtPs;
    private javax.swing.JTextField txtpApellido;
    private javax.swing.JTextField txtsApellido;
    // End of variables declaration//GEN-END:variables
}
