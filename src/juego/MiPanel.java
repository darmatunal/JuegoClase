/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @Se obliga a que se implementen los métodos de Action y Mouse Listener
 * Para tener interración con la pantalla.
 */
public class MiPanel  extends JPanel implements ActionListener,  MouseListener{
    
    // Se crea la variable timer que será la que va a coordinar los
    // tiempos de ejecucion, esta llama la clase Timer
   private final Timer timer;
   Toolkit t = Toolkit.getDefaultToolkit();
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  int x=0;
  int xmalo=0;
  int xExt=screenSize.width;
  int ymin=screenSize.height-80;
  int y;
  int secx=0;
  int j;
  int tiempo;
  int perdio;
  // En términs de delay
  int tsubida=100;
  int taux=0;
  // Variables Booleanas para direccion
  int DER=0;
  int IZQ=0;
  int ARR=0;
  int bajo=0;
  int aux;
    //private Object thread;
    
    // Constructor del panel, este tiene como objetivo inicializar el Timer
    // Y agregar el Listener del teclado y del Mouse.
    
     public MiPanel(){
         
         
    //para el teclado inicializa la clase interna Tapadter.
     addKeyListener(new Tadapter());
     setFocusable(true);
     // Agrega el funcionamiento en modo receptivo del Mouse.   
    this.addMouseListener(this);
    // Inicializa el Timer para pasos de 200ms En este caso    
    this.timer=new Timer(50,this);
    //Inicializa el Timer
    this.timer.start();
    }
    // Se crea una clase que va a extender la clase KeyApater,
    // Esta  clase permite trabajar las funciones del teclado
    private class Tadapter extends KeyAdapter{

     // Libera las teclas
     @Override
     public void keyReleased(KeyEvent e){
      }
     @Override
     // Reacciona ante el evento de presionar una tecla
     public void keyPressed(KeyEvent e){
     //circle.keyReleased(e);
 
     int key=e.getKeyCode();
     if (key==KeyEvent.VK_SPACE){
      System.out.println("Espacio");
     }
     if (key==KeyEvent.VK_LEFT){
     x=x-5;
     IZQ=1;
     }
     if (key==KeyEvent.VK_RIGHT){
         x=x+5;
         DER=1;
     }
      if (key==KeyEvent.VK_UP){
          ARR=1;
          System.out.println("Arriba");
     }
     if (key==KeyEvent.VK_DOWN){
        
     }
     }
  
 }  
  
    
  // En esta parte se deben definir todas las imagenes que se deben definir en el 
  //Panel
   @Override
   protected void paintComponent(Graphics g){
   super.paintComponent(g);
   // ajuste de pantalla
   
   
   Image fondo = loadImage("Fondo1.PNG");
   g.drawImage(fondo,0,0,screenSize.width ,screenSize.height, null);
   if(perdio==1){
   g.setColor(Color.RED);
   g.setFont( new Font( "Serif", Font.ITALIC, 50 ) );
   g.drawString("PERDISTE ",screenSize.width/2 ,screenSize.height/2);
   timer.stop();
   }
// Dibujo del protagonista  
   Image Hero= loadImage("Hero.png");
   Image Hero1= loadImage("Salta.png");
  //Rectanguilo hero
   g.drawRect(200+x, ymin-134-y, 100,134);
   //Rectangulo malo
   g.drawRect(xExt-100-xmalo, ymin-101, 100,101);
           if (ARR==0) {
        
       g.drawImage(Hero,x+200,ymin-134,x+300,ymin,secx*115,0,secx*115+115,134,this);    
       
       }
       else{
       g.drawImage(Hero1,x+200,ymin-134-y,x+300,ymin-y,0,0,96,130,this);
        
       
       if (taux<=tsubida && aux==0) { 
           taux=taux+5;
       if (taux==tsubida) {
           aux=1;
           }
           }
       if (aux==1) {
           taux=taux-5;
       if (taux==0) {
           bajo=1;
           ARR=0;
           }
       }
       }    
   //g.drawImage(Hero,x+205,ymin-134,x+305,ymin,115,0,230,134,this);
   //g.drawImage(Hero,x+210,ymin-134,x+310,ymin,230,0,345,134,this);
   //g.drawImage(Hero,x+215,ymin-134,x+315,ymin,345,0,460,134,this);
  
// Dibujo del malo
   Image Malo= loadImage("Malo.png");
   
   
   g.drawImage(Malo,xExt-100-xmalo,ymin-101,xExt-xmalo,ymin,0,0,105,101,this);
   
   
   g.setColor(java.awt.Color.red);
  
   
   }
   // Método para cargar imagenes  
   
   public Image loadImage(String imageName) {
   ImageIcon ii = new ImageIcon(imageName);
   Image image = ii.getImage();
   return image;
    }

  // Este es un método que se debe implementar
   //Variable para secuencia
  
  @Override
  public void actionPerformed(ActionEvent e) {
  tiempo=tiempo++;
  checkCollisions();
  if(DER==1){
  if(this.secx == 4){
          this.secx = 0;
         x=x-1;
      }else        
  { this.secx++;}
  DER=0;
  }
  if(IZQ==1){
  if(this.secx == 4){
          this.secx = 0;
         x=x-1;
      }else        
  {this.secx++;}
  IZQ=0;
  }
  if(ARR==1){
  y=2*taux;
      System.out.println(aux);
      if (bajo==1) {
          ARR=0;
          bajo=0;
          aux=0;
          
      }
     
  }
  
  
  //
 if(xmalo<xExt){
  if(tiempo<1000){
  xmalo=xmalo+15;}
  else{xmalo=xmalo+20;}
 }
 else{
 xmalo=0;
 }
 
  repaint();
  }
  // Se crea un método que permite identificar Colisiones
      public void checkCollisions(){
         
       Rectangle Rhero=new Rectangle(200+x, ymin-134-y, 90,134);
       Rectangle Rmalo=new Rectangle(xExt-100-xmalo, ymin-101, 100,101);
       if(Rhero.intersects(Rmalo)){
           perdio=1;
       }
      
      }

      // Como Se definió, Implements obliga a definir todos los métodos del
        // Mouse Los primeros se dejan vacíos, el uso está princupalmente en MouseClicked.
        @Override
        public void mouseClicked(MouseEvent e) { 
        System.out.println("Probemos el Clikc");
        //  this.timer.stop(); 
        //  this.timer.start();
        }
       
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }


   }
    

