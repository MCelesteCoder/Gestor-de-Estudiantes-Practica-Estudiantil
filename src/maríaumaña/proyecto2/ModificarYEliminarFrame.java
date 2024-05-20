package maríaumaña.proyecto2;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModificarYEliminarFrame extends javax.swing.JInternalFrame {

    DefaultTableModel tablaModificar = new DefaultTableModel();
    DefaultTableModel tablaEliminar = new DefaultTableModel();

    ArrayList<String> promedio;
    ArrayList<String> info;
    ArrayList<String> notas;

    boolean eliminar = false;
    boolean modificar = false;

    public ModificarYEliminarFrame() {
        initComponents();

        cargarModeloDeTablas();
    }

    private void modificarInfo() {

        tablaEliminar.setRowCount(0);

        llenarArrayEst();
        llenarArrayNotas();

        String mod = txtMod.getText();

        for (int i = 0; i < info.size(); i += 5) {
            String est = info.get(i);

            if (est.equals(mod)) {

                String id = info.get(i);

                //Depende del button que haya oprimido el usuario 
                if (modificar == false) {

                    //Si el id coindide con el que se busca se obtiene la informacion de los arrayList para agregarlos a la tabal
                    id = info.get(i);
                    String nom = info.get(i + 1);
                    String primer = info.get(i + 2);
                    String segundo = info.get(i + 3);
                    String carrera = info.get(i + 4);
                    String pProyectoS = notas.get(i);
                    String sProyectoS = notas.get(i + 1);
                    String foroS = notas.get(i + 2);
                    String encuestaS = notas.get(i + 3);
                    String juegoS = notas.get(i + 4);

                    //En los espacios para la modificacion se muestra la informació detallada del usuario
                    txtId.setText(id);
                    txtNom.setText(nom);
                    txtpApe.setText(primer);
                    txtsApe.setText(segundo);
                    txtCarrera.setText(carrera);
                    txtPP.setText(pProyectoS);
                    txtSP.setText(sProyectoS);
                    txtForo.setText(foroS);
                    txtEncuesta.setText(encuestaS);
                    txtJuego.setText(juegoS);

                    //Se agrega una nueva fila con la informacion
                    tablaModificar.addRow(new Object[]{id, nom, primer, segundo, carrera, pProyectoS, sProyectoS, foroS, encuestaS, juegoS});
                }

                if (modificar == true) {

                    try {
                        
                        //Para el calculo nuevo del promedio se obtiene la informacion de los campos modificados pero convirtiendolod en int
                        int pProyecto = Integer.parseInt(txtPP.getText());
                        int sProyecto = Integer.parseInt(txtSP.getText());
                        int foro = Integer.parseInt(txtForo.getText());
                        int encuesta = Integer.parseInt(txtEncuesta.getText());
                        int juego = Integer.parseInt(txtJuego.getText());

                        //Se verifica que este en los rangos correctos
                        if ((0 > pProyecto || pProyecto > 40) || (0 > sProyecto || sProyecto > 40) || (0 > foro || foro > 10)
                                || (0 > encuesta || encuesta > 5) || (0 > juego || juego > 5)) {
                            throw new IllegalArgumentException("Digite en las calificaciones números válidos");
                        }

                        //Se crea una instancia de claculos donde se pasan  los argumentos correspondientes 
                        Calculos calNuevo = new Calculos(pProyecto, sProyecto, foro, encuesta, juego);
                        calNuevo.setpProyecro(pProyecto);
                        calNuevo.setsProyecto(sProyecto);
                        calNuevo.setForo(foro);
                        calNuevo.setEncuesta(encuesta);
                        calNuevo.setJuego(juego);

                        //Se calcula el nuevo promedio y se convierte en string para poder agregarlo d emanera correcta al archivo
                        String prom = String.valueOf(calNuevo.calcularPromedio());

                        //Se obtiene toda la informacion de los campos
                        String idM = txtId.getText();
                        String nomM = txtNom.getText();
                        String primerM = txtpApe.getText();
                        String segundoM = txtsApe.getText();
                        String carreraM = txtCarrera.getText();
                        String pProyectoM = txtPP.getText();
                        String sProyectoM = txtSP.getText();
                        String foroM = txtForo.getText();
                        String encuestaM = txtEncuesta.getText();
                        String juegoM = txtJuego.getText();

                        // Actualizar la información del estudiante en las listas
                        info.set(i, id);
                        info.set(i + 1, nomM);
                        info.set(i + 2, primerM);
                        info.set(i + 3, segundoM);
                        info.set(i + 4, carreraM);

                        notas.set(i, String.valueOf(pProyectoM));
                        notas.set(i + 1, String.valueOf(sProyectoM));
                        notas.set(i + 2, String.valueOf(foroM));
                        notas.set(i + 3, String.valueOf(encuestaM));
                        notas.set(i + 4, String.valueOf(juegoM));

                        llenarArrayEst();
                        llenarArrayNotas();

                        tablaModificar.setRowCount(0);

                        //Se muestra la tabla con la informacion modificada
                        tablaModificar.addRow(new Object[]{idM, nomM, primerM, segundoM, carreraM, pProyectoM, sProyectoM, foroM, encuestaM, juegoM});

                        //Se llama al metodo de modificacion de datos en el archivo
                        Estudiante.modificarEstudiante(i, idM, nomM, primerM, segundoM, carreraM, pProyectoM, sProyectoM, foroM, encuestaM, juegoM, prom);

                    } catch (IllegalArgumentException e) {
                        // Se captura la excepción si el campo está vacío
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        }

    }

    private void eliminarEstudiante() {

        tablaEliminar.setRowCount(0);
        String eli = txtIdEli.getText();

        llenarArrayEst();
        llenarArraysProm();

        for (int i = 0; i < info.size(); i += 5) {
            String p = info.get(i);

            if (p.equals(eli)) {
                String id = info.get(i);

                 //Depende del button que haya oprimido el usuario 
                if (eliminar == false) {
                    
                    //Se obtiene la informacion del estudiante que corrsponde con el id
                    id = info.get(i);
                    String nom = info.get(i + 1);
                    String primer = info.get(i + 2);
                    String segundo = info.get(i + 3);
                    String carrera = info.get(i + 4);
                    String prom = promedio.get(i / 5);

                    //Se agrega una fila con la informacion
                    tablaEliminar.addRow(new Object[]{id, nom, primer, segundo, carrera, prom});
                }

                if (eliminar == true) {
                    //Se llama al metodo encargado de la eliminacion de los datos del estudainte pasando los argumentos correspondientes
                    Estudiante.eliminarEstudiante(id, i);
                    
                    tablaEliminar.setRowCount(0);
                    eliminar = false;
                    break;
                }

            }
        }

    }

    private void cargarModeloDeTablas() {
        //Se agregan las columnas a la tablaEliminar y tablaModificar
        tablaModificar.addColumn("ID");
        tablaModificar.addColumn("Nombre");
        tablaModificar.addColumn("1°Apellido");
        tablaModificar.addColumn("2°Apellido");
        tablaModificar.addColumn("Carrera");
        tablaModificar.addColumn("1°Proyecto");
        tablaModificar.addColumn("2°Proyecto");
        tablaModificar.addColumn("Foro");
        tablaModificar.addColumn("Encuesta");
        tablaModificar.addColumn("Juego");

        tablaEliminar.addColumn("ID");
        tablaEliminar.addColumn("Nombre");
        tablaEliminar.addColumn("1°Apellido");
        tablaEliminar.addColumn("2°Apellido");
        tablaEliminar.addColumn("Carrera");
        tablaEliminar.addColumn("Promedio");
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

    private void llenarArrayNotas() {

        notas = new ArrayList();

        String not;

        //Se obtiene la ruta del archivo mediante el llamado de el metodo corrspondiente
        File archivoNotasCreado = Calculos.getArchivoNotas();
        
        //Se lee el archivo 
        try (BufferedReader n = new BufferedReader(new FileReader(archivoNotasCreado))) {
            
            //Se lee cada linea y se almacena en not hasta que sea null, lo cuals ignifica que llegue al finald el archivo
            while ((not = n.readLine()) != null) {
                //Se agrega al arrayList not con cada linea 
                notas.add(not);
            }
        } catch (IOException excepcion) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMod = new javax.swing.JTextField();
        btnBuscarMod = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIdEli = new javax.swing.JTextField();
        btnBuscarEli = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtNom = new javax.swing.JTextField();
        txtpApe = new javax.swing.JTextField();
        txtsApe = new javax.swing.JTextField();
        txtCarrera = new javax.swing.JTextField();
        txtPP = new javax.swing.JTextField();
        txtSP = new javax.swing.JTextField();
        txtForo = new javax.swing.JTextField();
        txtEncuesta = new javax.swing.JTextField();
        txtJuego = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnEli = new javax.swing.JButton();
        btnMod = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Modificar o eliminar datos de estudiantes");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ingrese el ID del estudiante para modificar su informacion:");

        btnBuscarMod.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarMod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscarMod.setText("Buscar");
        btnBuscarMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarModActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtMod, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarMod)
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMod, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscarMod))
                .addGap(8, 8, 8))
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingrese el ID del estudiante que desea eliminar:");

        btnBuscarEli.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarEli.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscarEli.setText("Buscar");
        btnBuscarEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(txtIdEli, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarEli)
                .addGap(56, 56, 56))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdEli, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(btnBuscarEli))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        jLabel5.setText("Nombre");

        jLabel6.setText("1° Apellido");

        jLabel7.setText("2°Apellido");

        jLabel8.setText("Carrera");

        jLabel9.setText("1° Proyecto");

        jLabel10.setText("2° Proyecto");

        jLabel11.setText("Foro");

        jLabel12.setText("Encuesta");

        jLabel13.setText("Juego");

        jLabel14.setText("ID");

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(102, 102, 102));
        txtId.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtpApe, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtsApe, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPP, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSP, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtForo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtEncuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(47, 47, 47)
                .addComponent(jLabel6)
                .addGap(39, 39, 39)
                .addComponent(jLabel7)
                .addGap(52, 52, 52)
                .addComponent(jLabel8)
                .addGap(52, 52, 52)
                .addComponent(jLabel9)
                .addGap(41, 41, 41)
                .addComponent(jLabel10)
                .addGap(58, 58, 58)
                .addComponent(jLabel11)
                .addGap(64, 64, 64)
                .addComponent(jLabel12)
                .addGap(70, 70, 70)
                .addComponent(jLabel13)
                .addGap(33, 33, 33))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpApe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsApe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtForo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEncuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbl.setModel(tablaModificar);
        jScrollPane2.setViewportView(tbl);

        table.setModel(tablaEliminar);
        jScrollPane1.setViewportView(table);

        btnEli.setBackground(new java.awt.Color(102, 102, 102));
        btnEli.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        btnEli.setText("Eliminar");
        btnEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliActionPerformed(evt);
            }
        });

        btnMod.setBackground(new java.awt.Color(102, 102, 102));
        btnMod.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        btnMod.setText("Modificar");
        btnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEli, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(379, 379, 379))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(415, Short.MAX_VALUE)
                    .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(395, 395, 395)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(btnEli, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(208, 208, 208)
                    .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(342, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliActionPerformed
        eliminar = true;
        eliminarEstudiante();
    }//GEN-LAST:event_btnEliActionPerformed

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        modificar = true;
        modificarInfo();
    }//GEN-LAST:event_btnModActionPerformed

    private void btnBuscarEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEliActionPerformed
        eliminarEstudiante();
    }//GEN-LAST:event_btnBuscarEliActionPerformed

    private void btnBuscarModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarModActionPerformed

        modificarInfo();
    }//GEN-LAST:event_btnBuscarModActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarEli;
    private javax.swing.JButton btnBuscarMod;
    private javax.swing.JButton btnEli;
    private javax.swing.JButton btnMod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtCarrera;
    private javax.swing.JTextField txtEncuesta;
    private javax.swing.JTextField txtForo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdEli;
    private javax.swing.JTextField txtJuego;
    private javax.swing.JTextField txtMod;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtPP;
    private javax.swing.JTextField txtSP;
    private javax.swing.JTextField txtpApe;
    private javax.swing.JTextField txtsApe;
    // End of variables declaration//GEN-END:variables
}
