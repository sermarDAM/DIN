/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Conexiones.CantantesDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sermar
 */
public class Controller {
    
    public void añadir(Cantantes cantante) throws SQLException{
        CantantesDAO cantantesdao = new CantantesDAO();
        cantantesdao.insertar(cantante);
    }
    
    public void eliminar(Cantantes cantante) throws SQLException{
        CantantesDAO cantantesdao = new CantantesDAO();
        cantantesdao.eliminar(cantante);  
    }
    
     public List<Cantantes> verCantantes() {
        List<Cantantes> cantantes = new ArrayList<Cantantes>();
        CantantesDAO cantantesDAO = new CantantesDAO();
        cantantes = cantantesDAO.seleccionar();

        return cantantes;
    }
     
      public void actualizar(Cantantes cantante) throws SQLException {
        CantantesDAO cantantesDAO = new CantantesDAO();
        System.out.println(cantantesDAO.actualizar(cantante));
    }
    
   
}
