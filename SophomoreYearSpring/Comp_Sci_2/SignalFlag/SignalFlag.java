   import scranton.visual.*;
   import java.awt.*;
   import javax.swing.*;
   import java.awt.event.*;


   public class SignalFlag {
   
      VJFrame f;
   	
      public SignalFlag(VJFrame f){
         this.f = f;
      }
   	
      public void A(int x, int y, int size){
      
         f.add(new JRectangle(x, y, size, size, Color.white, Color.white), 50);
      
         JPolygon BluePart = new JPolygon(Color.blue, Color.blue);
			BluePart.addPoint(x+size*50/100, y);
			BluePart.addPoint(x+size*100/100, y);
			BluePart.addPoint(x+size*75/100, y+size*50/100);
			BluePart.addPoint(x+size*100/100, y+size*100/100);
			BluePart.addPoint(x+size*50/100, y+size*100/100);
         f.add(BluePart, 50);
      }
   	
      public void B(int x, int y, int size){
      	
      }
   	
      public void C(int x, int y, int size){
      	
      }
   	
      public void D(int x, int y, int size){
      	
      }
   	
      public void E(int x, int y, int size){
      	
      }
   	
      public void F(int x, int y, int size){
      	
      }
   	
      public void G(int x, int y, int size){
      	f.add(new JRectangle(x, y, size, size, Color.blue, Color.blue), 100);
         
         f.add(new JRectangle(x, y, size/6, size, Color.yellow, Color.yellow), 100);
         f.add(new JRectangle(x+(2*size/6), y, size/6, size, Color.yellow, Color.yellow), 100);
         f.add(new JRectangle(x+(4*size/6), y, size/6, size, Color.yellow, Color.yellow), 100);
      }
   	
      public void H(int x, int y, int size){
      	
      }
   	
      public void I(int x, int y, int size){
      	
      }
   	
      public void J(int x, int y, int size){
      	
      }
   	
      public void K(int x, int y, int size){
      	
      }
   	
      public void L(int x, int y, int size){
      	
      }
   	
      public void M(int x, int y, int size){
      	
      }
   	
      public void N(int x, int y, int size){
      	
      }
   	
      public void O(int x, int y, int size){
      	
      }
   	
      public void P(int x, int y, int size){
      	f.add(new JRectangle(x, y, size, size, Color.blue, Color.blue), 100);
         
         f.add(new JRectangle(x + size/3, y + size/3, size/3, size/3, Color.white, Color.white), 100);
      }
   	
      public void Q(int x, int y, int size){
      	
      }
   	
      public void R(int x, int y, int size){
      	
      }
   	
      public void S(int x, int y, int size){
      	
      }
   	
      public void T(int x, int y, int size){
      	
      }
   	
      public void U(int x, int y, int size){
      	
      }
   	
      public void V(int x, int y, int size){
      	
      }
   	
      public void W(int x, int y, int size){
      	
      }
   	
      public void X(int x, int y, int size){
      	
      }
   	
      public void Y(int x, int y, int size){
         f.add(new JRectangle(x, y, size, size, Color.red, Color.red), 100);
         
      	JPolygon yellowTri = new JPolygon(Color.yellow, Color.yellow);
         yellowTri.addPoint(x,y);
         yellowTri.addPoint(x + size/5, y);
         yellowTri.addPoint(x, y + size/5);
         f.add(yellowTri, 50);
         
         JPolygon yellowDiag1 = new JPolygon(Color.yellow, Color.yellow);
         yellowDiag1.addPoint(x + (3*size)/5, y);
         yellowDiag1.addPoint((x + (2*size)/5), y);
         yellowDiag1.addPoint(x, y + (2*size)/5);
         yellowDiag1.addPoint(x, y + (3*size)/5);
         f.add(yellowDiag1, 50);
         
         JPolygon yellowDiag2 = new JPolygon(Color.yellow, Color.yellow);
         yellowDiag2.addPoint(x + size, y);
         yellowDiag2.addPoint((x + (4*size)/5), y);
         yellowDiag2.addPoint(x, y + (4*size)/5);
         yellowDiag2.addPoint(x, y + size);
         f.add(yellowDiag2, 50);
         
         JPolygon yellowDiag3 = new JPolygon(Color.yellow, Color.yellow);
         yellowDiag3.addPoint(x + size/5, y + size);
         yellowDiag3.addPoint((x + (2*size)/5), y + size);
         yellowDiag3.addPoint(x + size, y + (2*size)/5);
         yellowDiag3.addPoint(x + size, y + size/5);
         f.add(yellowDiag3, 50);
         
         JPolygon yellowDiag4 = new JPolygon(Color.yellow, Color.yellow);
         yellowDiag4.addPoint(x + (3*size)/5, y + size);
         yellowDiag4.addPoint((x + (4*size)/5), y + size);
         yellowDiag4.addPoint(x + size, y + (4*size)/5);
         yellowDiag4.addPoint(x + size, y + (3*size)/5);
         f.add(yellowDiag4, 50);
      }
   	
      public void Z(int x, int y, int size){
      	
      }
   
      public void FirstSub(int x, int y, int size){
      
         JPolygon BigT = new JPolygon(Color.blue, Color.blue);
         BigT.addPoint(x+size*0/100,   y+size*0/100);
         BigT.addPoint(x+size*100/100, y+size*30/100);
         BigT.addPoint(x+size*0/100,   y+size*60/100);
         f.add(BigT, 50);
      
         JPolygon LittleT = new JPolygon(Color.yellow, Color.yellow);
         LittleT.addPoint(x+size*0/100,  y+size*15/100);
         LittleT.addPoint(x+size*60/100, y+size*30/100);
         LittleT.addPoint(x+size*0/100,  y+size*45/100);
         f.add(LittleT, 50);
      }
   
      public void SecondSub(int x, int y, int size){
      
         JPolygon BigT = new JPolygon(Color.white, Color.white);
         BigT.addPoint(x+size*0/100,   y+size*0/100);
         BigT.addPoint(x+size*100/100, y+size*30/100);
         BigT.addPoint(x+size*0/100,   y+size*60/100);
         f.add(BigT, 50);
      
         JPolygon Trapeziod = new JPolygon(Color.blue, Color.blue);
         Trapeziod.addPoint(x+size*0/100,  y+size*0/100);
         Trapeziod.addPoint(x+size*50/100, y+size*15/100);
         Trapeziod.addPoint(x+size*50/100, y+size*45/100);
         Trapeziod.addPoint(x+size*0/100,  y+size*60/100);
         f.add(Trapeziod, 50);
      }
   
      public void ThirdSub(int x, int y, int size){
      
         JPolygon BigT = new JPolygon(Color.white, Color.white);
         BigT.addPoint(x+size*0/100,   y+size*0/100);
         BigT.addPoint(x+size*100/100, y+size*30/100);
         BigT.addPoint(x+size*0/100,   y+size*60/100);
         f.add(BigT, 50);
      
         JPolygon Arrow = new JPolygon(Color.black, Color.black);
         Arrow.addPoint(x+size*0/100,  y+size*20/100);
         Arrow.addPoint(x+size*66/100, y+size*20/100);
         Arrow.addPoint(x+size*100/100,  y+size*30/100);
         Arrow.addPoint(x+size*66/100, y+size*40/100);
         Arrow.addPoint(x+size*0/100,  y+size*40/100);
         f.add(Arrow, 50);
      }
   
      public void FourthSub(int x, int y, int size){
      
         JPolygon BigT = new JPolygon(Color.red, Color.red);
         BigT.addPoint(x+size*0/100,   y+size*0/100);
         BigT.addPoint(x+size*100/100, y+size*30/100);
         BigT.addPoint(x+size*0/100,   y+size*60/100);
         f.add(BigT, 50);
      
         JPolygon YBlock = new JPolygon(Color.yellow, Color.yellow);
         YBlock.addPoint(x+size*0/100,  y+size*20/100);
         YBlock.addPoint(x+size*25/100,  y+size*20/100);
         YBlock.addPoint(x+size*25/100, y+size*40/100);
         YBlock.addPoint(x+size*0/100,  y+size*40/100);
         f.add(YBlock, 50);
      }
   
   }