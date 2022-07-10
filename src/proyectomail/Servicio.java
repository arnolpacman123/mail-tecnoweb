package proyectomail;

import Comunicaciones.Analizador;
import javax.mail.MessagingException;

public class Servicio extends Thread {

    private boolean isRunning;

    public Servicio() {
        this.isRunning = false;
    }

    public void iniciarAnalizador() {
        System.out.println("-- ANALIZADOR INCIADO --");
        this.isRunning = true;
        this.start();
    }

    public void deteneraAnalizador() {
        this.isRunning = false;
        this.stop();
        System.out.println("-- ANALIZADOR DETENIDO --");
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        Analizador a = new Analizador();
        a.setStat();//60 cantidad de mensajes 
        int statAnterior = a.getStat();//60
        while (true) {
            try {
                Thread.sleep(1500);//1.5s
                if (statAnterior < a.getStat()) {
                    statAnterior++; 
                    a.setStat(statAnterior); 
                    a.setFrom(); //guarda el correo de destino
                    a.separar(); //separa el subjet
                    a.analizar(); 
                }
                a.setStat();//61
            } catch (InterruptedException | MessagingException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}