/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metricas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brian
 */
public class Metricas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList<ArrayList<Integer>> productos = new ArrayList<ArrayList<Integer>>();
            ArrayList<Double> soporteA = new ArrayList<>();
            ArrayList<Double> soporteB = new ArrayList<>();
            ArrayList<Double> confianza = new ArrayList<>();
            ArrayList<Double> lift = new ArrayList<>();
            ArrayList<String> debilidad = new ArrayList<>();
            ArrayList<String> datos = new ArrayList<>();
            
            FileReader fr = null;
            BufferedReader br = null;
            String ruta = "E:";
            File archivo = new File(ruta, "metricas2.csv");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String s = br.readLine();
            String[] p = s.split(";");
            int d = 0;
            int cantDatos = 0;
            int cantProd = p.length;

            
//------------------------------------------------------------------------------
            
            
            //INICIALIZO UNA CANTIDAD DE VECTORES PARA CADA PRODUCTO QUE SE LLENARA DE DATOS DESPUÉS
            for (int i = 0; i < cantProd; i++) productos.add(new ArrayList<>());

            
            //NORMAL
            while ((s = br.readLine()) != null) {
                p = s.split(";");
                ArrayList<Integer> l = new ArrayList<>();
                for (int i = 0; i < p.length; i++) {
                    ArrayList<Integer> pr = productos.get(i);
                    pr.add(Integer.parseInt(p[i]));
                    productos.set(i, pr);
                }
                cantDatos += 1;
            }


//------------------------------------------------------------------------------
//
//
//            //VERTICAL
//            ArrayList<Integer> l1 = new ArrayList<>();
//            ArrayList<String> l2 = new ArrayList<String>();
//            int x=0;
//            while ((s = br.readLine()) != null) {
//                p = s.split(";");
//                for (int i = 0; i < p.length; i++) {
//                    if(x==0){
//                        l1.add(Integer.parseInt(p[0]));
//                        l2.add(p[1]);
//                        x=1;
//                    }else x=0;
//                }
//            }
//
//            Set<String> prd = new HashSet<>();
//            Set<Integer> prd3 = new HashSet<>();
//            ArrayList<String> prd2 = new ArrayList<String>();
//            ArrayList<Integer> prd4 = new ArrayList<Integer>();
//            for (int i = 0; i < l2.size(); i++) prd.add(l2.get(i));
//            for (int i = 0; i < l1.size(); i++) prd3.add(l1.get(i));
//            prd2.addAll(prd);
//            prd4.addAll(prd3);
//
//            cantProd = prd2.size();
//            cantDatos = prd4.size();
//            
//            ArrayList<Integer> tmp = new ArrayList<Integer>();
//            for (int j = 0; j < cantDatos; j++) tmp.add(0);
//            for (int i = 0; i < cantProd; i++) productos.add(tmp);
//            
//            for (int i = 0; i < cantProd; i++) {
//                ArrayList<Integer> prx = productos.get(i);
//                for (int j = 0; j < cantDatos; j++) prx.set(j, 0);
//
//                for (int j = 0; j < l2.size(); j++) {
//                    if((l2.get(j)).equals(prd2.get(i))){
//                        //System.out.println("/" + pos);
//                        int pos = l1.get(j)-1;
//                        //System.out.println("\n"+i+ ")->" + l2.get(j)+" - "+prd2.get(i) +" - " + pos);
//                        //System.out.println("add 1 " + pos);
//                        prx.set(pos, 1);
//                    }
//                }
//                ArrayList<Integer> prxx = new ArrayList<>();
//                prxx.addAll(prx);
//                productos.set(i, prxx);
//            }
//            
//            for (int k = 0; k < cantProd; k++) {
//                ArrayList<Integer> pr = productos.get(k);
//                for (int j = 0; j < cantDatos; j++) {
//                    //System.out.print(pr.get(j) + " ");
//                }
//                //System.out.println("");
//            }
            
//------------------------------------------------------------------------------


            System.out.println("Cantidad de productos: " + cantProd);
            System.out.println("Cantidad de datos: " + cantDatos);
            
            //SOPORTE A
            for (int i = 0; i < cantProd; i++) {
                ArrayList<Integer> pr = productos.get(i);
                double cont = 0;
                for (int j = 0; j < cantDatos; j++) {
                    if(pr.get(j)==1){
                        cont+=1;
                    }
                }
                soporteA.add((cont/cantDatos));
            }
            System.out.println("\nSoporte A");
            for (int i = 0; i < soporteA.size(); i++) System.out.println("(" + (i+1) + ") = " + soporteA.get(i));
            
            //SOPORTE B
            for (int i = 0; i < cantProd; i++) {
                ArrayList<Integer> v1 = productos.get(i);
                for (int j = i+1; j < cantProd; j++) {
                    ArrayList<Integer> v2 = productos.get(j);
                    double cont = 0;
                    for (int k = 0; k < cantDatos; k++) {
                        if((v1.get(k)==v2.get(k)) && v2.get(k)==1){ 
                            cont++;
                        }
                    }
                    soporteB.add(cont/cantDatos);
                    datos.add(""+((i+1)+"-"+(j+1)));
                    //System.out.println("--<" + v1 + "\n - " + v2);
                    //System.out.println("\n->" + cont + " - " +  cantDatos + " - " + cont/cantDatos);
                }
            }
            System.out.println("\nSoporte B");
            for (int i = 0; i < soporteB.size(); i++) System.out.println("(" + datos.get(i) + ") = " + soporteB.get(i));
            
            //CONFIANZA
            int i=0;
            int cont=0, cont2=2;
            for (int j = 0; j < soporteB.size(); j++) {
                double val = soporteA.get(i);
                //System.out.println("\n+ " + cont + " " + cantProd + " " + cont2 + " " + (cantProd-cont2) + " " + i);
                if(cont==(cantProd-cont2)){
                    i+=1;
                    cont2+=1;
                    cont=0;
                } else{
                    cont++;
                }
                confianza.add(soporteB.get(j)/val);
                //System.out.println("->" + soporteB.get(j) + " - " + val + " - " + soporteB.get(j)/val);
            }
            System.out.println("\nConfianza");
            for (i = 0; i < confianza.size(); i++) System.out.println("(" + datos.get(i) + ") = " + confianza.get(i));
            
            //LIFT
            i = 0;
            cont = 0;
            cont2 = 2;
            int cont3 = 1, k = 1;
            
            for (int j = 0; j < soporteB.size(); j++) {
                double val = soporteA.get(i);
                double val2 = soporteA.get(k);
                //System.out.println("\n->" + soporteB.get(j) + " " + val + " " + val2 + " - " + (soporteB.get(j)/(val*val2)));
                if(cont==(cantProd-cont2)){
                    i+=1;
                    cont2++;
                    cont=0;
                } else {
                    cont++;
                }
                if(k==cantProd-1){
                    cont3++;
                    k = cont3;
                } else {
                    k++;
                }
                lift.add(soporteB.get(j)/(val*val2));
            }
            System.out.println("\nLift");
            for (i = 0; i < lift.size(); i++) System.out.println("(" + datos.get(i) + ") = " + lift.get(i));
            
            //RESULTADO
            String rs = "";
            for (i = 0; i < lift.size(); i++) {
                if(lift.get(i)==1){
                    int rnd = (int) (Math.random() * 1) + 0;
                    System.out.println("rnd " + rnd);
                    if(rnd==1) rs="Fuerte";
                    else rs="Débil";
                } else if(lift.get(i)>1){
                    rs = "Fuerte";
                } else {
                    rs = "Débil";
                }
                debilidad.add(rs);
            }
            System.out.println("\nResultado Lift");
            for (i = 0; i < debilidad.size(); i++) System.out.println("(" + datos.get(i) + ") -> " + debilidad.get(i));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Metricas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metricas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}