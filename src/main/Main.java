/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.EspecieCtrl;
import controller.RazaCtrl;
import dao.EspecieDao;
import dao.RazaDao;
import model.Especie;
import model.Raza;
import view.EspecieFrm;
import view.RazaFrm;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        * Modelo para cargar un CRUD, de momento temporal. 
        * Modique por el modelo, dao, vista y contorlador que esta probando
        */
        Especie model = new Especie();
        EspecieDao dao = new EspecieDao();
        EspecieFrm vista = new EspecieFrm();
        EspecieCtrl control = new EspecieCtrl(model, dao, vista);
        control.iniciar();
        
        
        
        
        Raza objModel = new Raza();
        RazaDao objDao = new RazaDao();
        RazaFrm objVista = new RazaFrm();
        RazaCtrl objController = new RazaCtrl(objModel, objDao, objVista);

        objController.Iniciar();
        objVista.setVisible(true);
    
    }

}
