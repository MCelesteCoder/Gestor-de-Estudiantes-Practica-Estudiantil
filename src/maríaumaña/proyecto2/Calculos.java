package maríaumaña.proyecto2;

import java.io.*;
import java.io.IOException;

public class Calculos {
    //Se declaran los atributos de la clase claculos

    private int pProyecto;
    private int sProyecto;
    private int foro;
    private int encuesta;
    private int juego;

    //Se decalara el archivo donde se guardan las notas
    public static File archivoNotas;

    //Se crea el constructor de Calculos
    public Calculos(int pProyecto, int sProyecto, int foro, int encuesta, int juego) {
        this.pProyecto = pProyecto;
        this.sProyecto = sProyecto;
        this.foro = foro;
        this.encuesta = encuesta;
        this.juego = juego;
    }

    //Creacion de los gette´s y setter's
    public double getpProyecto() {
        return pProyecto;
    }

    public void setpProyecro(int pProyecto) {
        this.pProyecto = pProyecto;
    }

    public int getsProyecto() {
        return sProyecto;
    }

    public void setsProyecto(int sProyecto) {
        this.sProyecto = sProyecto;
    }

    public int getForo() {
        return foro;
    }

    public void setForo(int foro) {
        this.foro = foro;
    }

    public int getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(int encuesta) {
        this.encuesta = encuesta;
    }

    public int getJuego() {
        return juego;
    }

    public void setJuego(int juego) {
        this.juego = juego;
    }

    //Metodo para el calculo del promedio
    public int calcularPromedio() {
        int resultado = (pProyecto + sProyecto + foro + encuesta + juego);
        return resultado;
    }

    //Metodo para la creacion del archivo donde se guardan las notas
    public void creacionArchivoNotas() {
        String arc = "Notas.txt";
        archivoNotas = new File(arc);

        try {
            
            //Se verifica que si no existe el archivo se cree
            if (!archivoNotas.exists()) {
                archivoNotas.createNewFile();
            }

            FileWriter escribirNotas = new FileWriter(archivoNotas, true);

            //Se escribe en el archivo 
            escribirNotas.write(String.valueOf(pProyecto) + "\n");
            escribirNotas.write(String.valueOf(sProyecto) + "\n");
            escribirNotas.write(String.valueOf(foro) + "\n");
            escribirNotas.write(String.valueOf(encuesta) + "\n");
            escribirNotas.write(String.valueOf(juego) + "\n");

            //Se cierra el fileWriter
            escribirNotas.close();

        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }

    //Metodo para retornar el archivo de las notas
    public static File getArchivoNotas() {
        String arc = "Notas.txt";
        return new File(arc);
    }

}
