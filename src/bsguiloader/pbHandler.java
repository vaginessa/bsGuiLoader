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
    private javax.swing.JProgressBar progBar;
    
    // Constructor
    public pbHandler(Object in) {
        this.progBar = (javax.swing.JProgressBar) in;
    }
    // Methoden
    public void setMinimum(int Val) {
        this.progBar.setMinimum(Val);
    }
    public void setMaximum(int Val) {
        this.progBar.setMaximum(Val);
    }
    public void setValue(int Value) {
        this.progBar.setValue(Value);
    }
}
