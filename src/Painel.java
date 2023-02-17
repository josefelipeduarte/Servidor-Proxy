import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.ServerException;

import javax.swing.*;
public class Painel extends JFrame {
	
	
	
	  private ServerSocket serverSocket;
	  
  public static void main(String args[]) {
	  
	  new Painel().run();
	  
  }
  public void run() {
		//Criação e dimensionamento do Painel
	    JFrame painel = new JFrame("Interface - Proxy ");
	    painel.setSize(300, 300);
	    painel.setLayout(null);
	    painel.setVisible(true);
	    
	    
	   //Campo Legenda
	    JLabel legenda;  
	    legenda=new JLabel("Insira a Porta:");  
	    legenda.setBounds(100,65, 100,30);
	    painel.add(legenda);
	    
	    //Campo Input da Porta
	    JTextField input_porta = new JTextField();
	    input_porta.setBounds(50, 100, 200, 30);
	    painel.add(input_porta);
	    
	    //Campo Ligar Programa
	    JButton btn_on = new JButton("Ligar");
	    btn_on.setBounds(50, 150, 80, 30);
	    painel.add(btn_on);
	    
	    
	    //Quando clicar em Ligar ele pega o valor do Input e envia pro metodo na outra class pra iniciar o proxy
	    btn_on.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    			String valor_porta_on = input_porta.getText();
	    				int estado;
	    				int valor_porta_converted = Integer.parseInt(valor_porta_on);
						try {
							serverSocket = new ServerSocket(valor_porta_converted);
							Thread t1 = new Thread(new Server(serverSocket));
				            t1.start();
				            System.out.println("Servidor Ligado!");

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    	        }  
	    	    }); 
	    
	    
	  //Campo Desligar Programa
	    JButton btn_off = new JButton("Desligar");
	    btn_off.setBounds(150, 150, 90, 30);
	    painel.add(btn_off);
	    
	    //Quando clicar em Desligar ele pega o valor do Input e envia pro metodo na outra class pra iniciar o proxy
	    btn_off.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    			try {
	    				if(serverSocket != null) {
	    					serverSocket.close();
							serverSocket = null;
							 System.out.println("Servidor Desligado!");	
	    				}	
					}catch (IOException e1) {
						// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Alert", JOptionPane.ERROR_MESSAGE);
					}
	    			
	 
	    	        }  
	    	    });  
  }
}