package bsguiloader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author david
 */
public class pbHandler extends Thread {
    // Variablen
    private boolean life;
    private boolean order;
    private int prog;
    private int Value;
    private javax.swing.JProgressBar progBar;
    
    // Constructor
    public pbHandler(Object in) {
        this.progBar = (javax.swing.JProgressBar) in;
        this.life = true;
        this.prog = 0;
        this.Value = 0;
    }
    
    // Methods
    public void kill() {
        this.life = false;
    }
    public void cmd(boolean in) {
        this.order = in;
    }
    public void setMinimum(int Val) {
        this.progBar.setMinimum(Val);
    }
    public void setMaximum(int Val) {
        this.progBar.setMaximum(Val);
    }
    public void setValue(int Value) {
        this.progBar.setValue(Value);
        System.out.println("Durchlauf");
    }
    
    //public void run() {
        /*while (life) {
            if (order) {
                System.out.println("Thread läuft: " + this.prog);
                this.prog++;
                this.progBar.setValue(this.prog);
                try {
                    Thread.sleep(125);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (this.prog == this.progBar.getMaximum()) {
                    life = false;
                    System.out.println("Thread beendet.");
                }
            }
        }*/
        /*while (life) {
            if (order) {
                System.out.println("Thread läuft: " + this.progBar.getValue());
            }
        }*/
    //}
}
