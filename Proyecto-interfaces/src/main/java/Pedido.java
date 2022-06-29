/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import excepciones.Stock_excepcion;

/**
 *
 * @author alumno
 */
public class Pedido {

    //Mapa donde se guardará los productos y la cantidad de estos
    private HashMap<Producto,Integer> pedido;
    private int codigo_pedido;
    private double peso_pedido;
    private double precio_pedido;
    private String transporte;

    public Pedido() {
        this.pedido = new HashMap<>();
        this.codigo_pedido=codigo_pedido;
        calcular();

        
    }
    public ArrayList<Producto> dameproductos(){

        ArrayList<Producto> p=new ArrayList<Producto>();
        for (Producto key : pedido.keySet()) {
            p.add(key);
        }
        return p;
    }
    
    public void anyadirProducto(Producto cod, int cant){
                

        pedido.put(cod,cant);
         
        calcular();

    }
    public void eliminarProducto(Producto cod, int cant){

        pedido.remove(cod, cant);
        calcular();
    }

    public HashMap<Producto, Integer> getPedido() {
        return pedido;
    }

    public void setPedido(HashMap<Producto, Integer> pedido) {
        this.pedido = pedido;
    }

    
    public int getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(int codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    public double getPeso_pedido() {
        return peso_pedido;
    }

    public void setPeso_pedido(double peso_pedido) {
        this.peso_pedido = peso_pedido;
    }

    public double getPrecio_pedido() {
        return precio_pedido;
    }

    public void setPrecio_pedido(double precio_pedido) {
        this.precio_pedido = precio_pedido;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }
    
    public void calcularpesototal(){
        peso_pedido=0;
        for (Map.Entry<Producto, Integer> entry : pedido.entrySet()) {
            Producto key = entry.getKey();
            Integer cantidad = entry.getValue();
            
            peso_pedido+=cantidad*key.getPeso();
    
    }
        
    }
    public void calcularpreciototal(){
        precio_pedido=0;
        for (Map.Entry<Producto, Integer> entry : pedido.entrySet()) {
            Producto key = entry.getKey();
            Integer cantidad = entry.getValue();
            precio_pedido+=cantidad*key.getPrecio();
        }
    }
//    public double calcularpeso(Producto p){
//        double peso_producto=0;
//        for (Map.Entry<Producto, Integer> entry : pedido.entrySet()) {
//            Producto key = entry.getKey();
//            Integer cantidad = entry.getValue();
//            if(key.equals(p)){
//                 peso_producto=cantidad*key.getPeso();
//            }
//         }   
//        return peso_producto;
//              
//    }
//    public double calcularprecio(Producto p){
//         double precio_producto=0;
//        for (Map.Entry<Producto, Integer> entry : pedido.entrySet()) {
//            Producto key = entry.getKey();
//            Integer cantidad = entry.getValue();
//            if(key.equals(p)){
//            precio_producto=cantidad*key.getPrecio();
//            }
//        }
//        return precio_producto;
//    }
    public void calculoTransporte(){
        
        //        
        if(peso_pedido<10){
            transporte="Andando";
        }else if(peso_pedido<20){
            transporte="Bici";
        }else{
            transporte="Coche";
        }

    }
    

    public void calcular(){
        calcularpreciototal();
        calcularpesototal();
        calculoTransporte();
    }
}
