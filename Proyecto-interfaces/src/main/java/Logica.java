
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Logica {
    //lista de la interfaz del catalogo
    private List<producto_interfaz> lpi;
    //lista de la interfaz de los articulos pedidos
    private List<pedido_interfaz> lped;
    
    
    //objeto pedido
    private Pedido pedido;
    
    Conexion conexion;
    
    //panel para poder actualizar


    public Logica() {
        
        conexion=new Conexion();
        
        lpi= new ArrayList<>();
        ArrayList<Producto> listaProductos = conexion.coger_datosproductos();
        for(Producto p:listaProductos){
            lpi.add(new producto_interfaz(p));
        }
        lped= new ArrayList<>();
        pedido=new Pedido();

        pedido.setCodigo_pedido(conexion.crearPedido(pedido));
  
        
    }

    //funcion que añade en el panel los productos que tenemos
    public void llenarProductos(JPanel jPanel2){
        

        for(producto_interfaz p:lpi){

            jPanel2.add(p);
        }
        
       
    }
     
    //funcion que añade en la parte de reserva 
    public void anyadirProductos(JPanel jPanel2,JPanel jPanel3, Logica logica) {
        Component []comp =jPanel2.getComponents();
         
         
        for(Component c:comp){
            if(c instanceof producto_interfaz){
                producto_interfaz pi=(producto_interfaz) c;
                if(pi.getSeleccion().isSelected()==true){
                    //cosas del elemento a añadir o a modificar
                    Producto cod=pi.getProducto();
                    int cant=(Integer)pi.getjSpinner1().getValue();
                    if(cant!=0){
                    pedido.anyadirProducto(cod, cant);
                    int a=compruebaProductos(pi);
                        if(a!=-1){
                            lped.set(a, new pedido_interfaz(cod,cant,logica,jPanel3));
                        }else{
                            lped.add(new pedido_interfaz(cod,cant,logica,jPanel3));
                        }
                    }
                    pi.getjSpinner1().setValue(0);
                    pi.getjSpinner1().setEnabled(false);
                    pi.getSeleccion().setSelected(false);
                    
                }
            }
        }
        jPanel3.removeAll();
        for(pedido_interfaz p:lped){

            jPanel3.add(p);
        }
          
    }
    
    private int compruebaProductos(producto_interfaz pi){
        int flag=-1;
        
        for(int i=0;i<lped.size();i++){
            if(lped.get(i).getProducto().getCodigo()==pi.getProducto().getCodigo()){
                
                flag=i;
                break;
            }
            
        }
        
        return flag;
    }
      

    JPanel eliminarElementoPanel(JPanel jPanel3) {
        JPanel jPanel=jPanel3;
        Component[] components =jPanel.getComponents();
        for (int i=0;i<components.length;i++){
            
            if(components[i] instanceof pedido_interfaz){
                pedido_interfaz pedi=(pedido_interfaz)components[i];
                if(pedi.isBorre()){
                    Producto cod=pedi.getProducto();
                    int cant =pedi.getCantidad();
                    lped.remove(i);
                    pedido.eliminarProducto(cod,cant);
                    
                    jPanel=actualizarPanel(jPanel);
                    break;

                }
            }
        }
        
        return jPanel;
    }

    public JPanel actualizarPanel( JPanel jPanel) {
        jPanel.removeAll();
        
        for(pedido_interfaz p:lped){

            jPanel.add(p);
        }
        jPanel.updateUI();
        
        
        return jPanel;
    }

    public JPanel mostrarPedido( JPanel jPanel) {
     
        
        for (Map.Entry<Producto, Integer> entry : pedido.getPedido().entrySet()) {
            Producto key = entry.getKey();
            Integer cantidad = entry.getValue();
            jPanel.add(new res_reserva(key, cantidad));
            
    
    }
        //jPanel.updateUI();
        
        
        return jPanel;
    }
   
   	

    
    
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
