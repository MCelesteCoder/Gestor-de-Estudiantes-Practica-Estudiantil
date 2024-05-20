package maríaumaña.proyecto2;

import java.io.*;
import static maríaumaña.proyecto2.Calculos.getArchivoNotas;

public class Estudiante extends Persona {

    //Se decalaran los atributos de la clase Estudiante
    private String carrera;
    private int promedio;

    public static File archivoEst;
    public static File archivoProm;

    //Se crea el constructor de la clase Estudiante
    public Estudiante(String id, String nom, String pApellido, String sApellido, String carrera, int promedio) {

        super(id, nom, pApellido, sApellido);

        this.carrera = carrera;
        this.promedio = promedio;

    }

    //Creación de los metodos getter's y setter's
    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    //Creación de los metodos necesarios
    //Método para la creación del archivo Estudiante
    //La Tecnología Avanza.(30 jul 2020).134 . Curso de Java II Archivos II Escribir , leer y añadir contenido a un archivo de texto. https://www.youtube.com/watch?v=zzN5ksCu-Zc
    public void creacionArchivoEstudiante() {
        String nomArchivo = "Estudiantes.txt";
        archivoEst = new File(nomArchivo);

        try {
            //Se verifica que si no existe el archivo se cree
            if (!archivoEst.exists()) {
                archivoEst.createNewFile();
            }

            FileWriter escribirInfo = new FileWriter(archivoEst, true);

            //Se escribe en el archivo 
            escribirInfo.write(id + "\n");
            escribirInfo.write(nom + "\n");
            escribirInfo.write(pApellido + "\n");
            escribirInfo.write(sApellido + "\n");
            escribirInfo.write(carrera + "\n");

            //Se cierra el fileWriter
            escribirInfo.close();

        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }

    //Metodo para retornar el archivo Estudiante
    public static File getArchivoEstudiantes() {
        String nomArchivo = "Estudiantes.txt";
        return new File(nomArchivo);
    }

    public void creacionArchivoPromedios() {
        String arch = "Promedio.txt";
        archivoProm = new File(arch);

        try {
            //Se verifica que si no existe el archivo se cree
            if (!archivoProm.exists()) {
                archivoProm.createNewFile();
            }

            //Se escribe en el archivo 
            FileWriter escribirProm = new FileWriter(archivoProm, true);

            //Se convierte a String para poder escribirlo en el archivo
            String prom = String.valueOf(promedio);

            //Se escribe en el archivo 
            escribirProm.write(prom + "\n");

            //Se cierra el archivo
            escribirProm.close();

        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }

    //Metodo para retornar el archivo Promedio
    public static File getArchivoPromedios() {
        String arch = "Promedio.txt";
        return new File(arch);
    }

    //Metodo para eliminar la informacion de un estudiante de todos los archivos
    public static void eliminarEstudiante(String idE, int indice) {
        //Se obtienen los archivos con la información del estudiante
        File EstudiantesArchivo = getArchivoEstudiantes();
        File promediosArchivo = getArchivoPromedios();
        File notasArchivo = getArchivoNotas();

        //Se crean archivos temporales
        File notasArchivoTemp = new File("NotasTemp.txt");
        File estArchivoTemp = new File("EstTemp.txt");
        File promArchivoTemp = new File("PromedioTemp.txt");

        try {
            //Los objetos de BufferedReader se crean para leer los archivos ya creados
            BufferedReader leerEstudiantes = new BufferedReader(new FileReader(EstudiantesArchivo));
            BufferedReader leerPromedios = new BufferedReader(new FileReader(promediosArchivo));
            BufferedReader leerNotas = new BufferedReader(new FileReader(notasArchivo));

            //Los objetos BufferedWriter se crean para escribir en los archivos temporales
            BufferedWriter escribirNotas = new BufferedWriter(new FileWriter(notasArchivoTemp));
            BufferedWriter escribirEstudiantes = new BufferedWriter(new FileWriter(estArchivoTemp));
            BufferedWriter escribirPromedios = new BufferedWriter(new FileWriter(promArchivoTemp));

            //Se crean algunas variables con el fin de controlar las lineas leidas, almacenar las mismas o determinar si se encontro el estudiante
            String cadena;
            int contador = 0;
            boolean encontrado = false;

            //Se lee el archivo de los Estudiantes ya creado 
            while ((cadena = leerEstudiantes.readLine()) != null) {
                //Si el contador es multiplo de 5 y la cadena es igual al id recibido en los parametros se ingresa
                if (contador % 5 == 0 && cadena.equals(idE)) {
                    //Si se encuentra se saltan las 5 lineas correspondientes a la informacion y se eliminan de esta manera
                    encontrado = true;
                    for (int i = 0; i < 4; i++) {
                        leerEstudiantes.readLine();
                    }
                } else {
                    //Si no coincide el id sollo se escribe la cadena en el archivo
                    escribirEstudiantes.write(cadena + "\n");
                }
                contador++;
            }

            //Se cierran los objetos de lectura y escritura
            leerEstudiantes.close();
            escribirEstudiantes.close();

            //Se reinicia el contador
            contador = 0;

            //Se recorre el archivo 
            while ((cadena = leerPromedios.readLine()) != null) {
                //Si no se encontro el estudiante o si el contador por 5 es diferente al contador se excluyen las lineas del estudiante a eliminar
                if (!encontrado || contador * 5 != contador) {
                    escribirPromedios.write(cadena + "\n");
                }
                contador++;
            }

            //Se cierran los objetos de lectura y escritura
            leerPromedios.close();
            escribirPromedios.close();

            //Se reinicia el contador
            contador = 0;

             //Se recorre el archivo 
            while ((cadena = leerNotas.readLine()) != null) {
                //Si el contador es multiplo de 5 y contador es igual al parametro del indice se omiten la siguiente 5 lineas
                if (contador % 5 == 0 && contador == indice) {
                    for (int i = 0; i < 4; i++) {
                        leerNotas.readLine();
                    }
                } else {
                    escribirNotas.write(cadena + "\n");
                }
                contador++;
            }

            //Se cierran los objetos de lectura y escritura
            leerNotas.close();
            escribirNotas.close();

            //Se eliminan los archivos con los  datos del estuidante
            EstudiantesArchivo.delete();
            promediosArchivo.delete();
            notasArchivo.delete();

            //Se renombran los archivos temporales con los nombres de los archivos recien eliminados
            notasArchivoTemp.renameTo(notasArchivo);
            estArchivoTemp.renameTo(EstudiantesArchivo);
            promArchivoTemp.renameTo(promediosArchivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para modificar la informacion del estudiante de acuerdo a lo que ingrese el usuario
    public static void modificarEstudiante(int indice, String idM, String nomM, String primerM, String segundoM, String carreraM, String pProyectoM, String sProyectoM, String foroM, String encuestaM, String juegoM, String promM) {
        //Se obtienen los archivos con la información del estudiante
        File EstudiantesArchivoMod = getArchivoEstudiantes();
        File promediosArchivoMod = getArchivoPromedios();
        File notasArchivoMod = getArchivoNotas();

        //Se crean archivos temporales
        File notasArchivoTempMod = new File("NotasTemp.txt");
        File estArchivoTempMod = new File("EstTemp.txt");
        File promArchivoTempMod = new File("PromedioTemp.txt");

        try {
            //Los objetos de BufferedReader se crean para leer los archivos ya creados
            BufferedReader leerEstudiantesMod = new BufferedReader(new FileReader(EstudiantesArchivoMod));
            BufferedReader leerPromediosMod = new BufferedReader(new FileReader(promediosArchivoMod));
            BufferedReader leerNotasMod = new BufferedReader(new FileReader(notasArchivoMod));

            //Los objetos BufferedWriter se crean para escribir en los archivos temporales
            BufferedWriter escribirNotasMod = new BufferedWriter(new FileWriter(notasArchivoTempMod));
            BufferedWriter escribirEstudiantesMod = new BufferedWriter(new FileWriter(estArchivoTempMod));
            BufferedWriter escribirPromediosMod = new BufferedWriter(new FileWriter(promArchivoTempMod));

            //Se crean algunas variables con el fin de controlar las lineas leidas y almacenar las mismas 
            String cadena;
            int contador = 0;

            while ((cadena = leerEstudiantesMod.readLine()) != null) {
                if (contador % 5 == 0 && cadena.equals(idM)) {

                    // Si se encuentra el estudiante que se está modificando se escribe la nueva información
                    escribirEstudiantesMod.write(idM + "\n");
                    escribirEstudiantesMod.write(nomM + "\n");
                    escribirEstudiantesMod.write(primerM + "\n");
                    escribirEstudiantesMod.write(segundoM + "\n");
                    escribirEstudiantesMod.write(carreraM + "\n");
                    // Se slatan las líneas del estudiante que se está modificando
                    for (int i = 0; i < 4; i++) {
                        leerEstudiantesMod.readLine();
                    }
                } else {
                    escribirEstudiantesMod.write(cadena + "\n");

                }
                contador++;
            }
            //Se cierran los objetos de lectura y escritura
            leerEstudiantesMod.close();
            escribirEstudiantesMod.close();

            //Se reinicia el contador
            contador = 0;

            while ((cadena = leerPromediosMod.readLine()) != null) {
                if (contador * 5 == indice) {
                    //Si el contador por 5 es igual la indice se escribe el promedio a modificar
                    escribirPromediosMod.write(promM + "\n");

                } else {
                    escribirPromediosMod.write(cadena + "\n");

                }
                contador++;
            }

            //Se cierran los objetos de lectura y escritura
            leerPromediosMod.close();
            escribirPromediosMod.close();

            String linea;
            //Se reinicia el contador
            contador = 0;
            while ((linea = leerNotasMod.readLine()) != null) {
                if (contador == indice) {
                    escribirNotasMod.write(pProyectoM + "\n");
                    escribirNotasMod.write(sProyectoM + "\n");
                    escribirNotasMod.write(foroM + "\n");
                    escribirNotasMod.write(encuestaM + "\n");
                    escribirNotasMod.write(juegoM + "\n");
                    for (int i = 0; i < 4; i++) {
                        leerNotasMod.readLine();
                    }
                } else {
                    escribirNotasMod.write(linea + "\n");
                }
                contador++;
            }
            //Se cierran los objetos de lectura y escritura
            leerNotasMod.close();
            escribirNotasMod.close();

            //Se eliminan los archivos con los  datos del estuidante
            EstudiantesArchivoMod.delete();
            promediosArchivoMod.delete();
            notasArchivoMod.delete();

            //Se renombran los archivos temporales con los nombres de los archivos recien eliminados
            notasArchivoTempMod.renameTo(notasArchivoMod);
            estArchivoTempMod.renameTo(EstudiantesArchivoMod);
            promArchivoTempMod.renameTo(promediosArchivoMod);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
