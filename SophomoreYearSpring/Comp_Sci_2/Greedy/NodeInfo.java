   import java.io.*;
   import java.util.*;

   public class NodeInfo {
   
      protected String name;
      protected ArrayList<NodeInfo> Neighbor;
      protected Integer color; // 0 = not colored, 1-4 = colors
		protected Boolean Qed = false;
		protected NodeInfo Parent;
		
		public void inQ(){
			Qed = true;
		}
		
		public Boolean getQ(){
			return Qed;
		}
		
		public void setParent(NodeInfo parent){
			Parent = parent;
		}
		
		public NodeInfo getParent(){
			return this.Parent;
		}
   	
      public NodeInfo(String name){
         this.Neighbor = new ArrayList<NodeInfo>();
         this.name = name;
      }
   	
      public String getName(){
         return this.name;
      }
   	
      public void addNeighbor(NodeInfo n){
         Neighbor.add(n);
      }
   	
      public NodeInfo getNeighbor(Integer i){
         return Neighbor.get(i);
      }
   	
      public Integer noOfNeighbors(){
         return this.Neighbor.size();
      }
   	
      protected void setColor(Integer i){
         this.color = i;
      }
   	
      public Integer getColor(){
         return this.color;
      }
   	
      public Boolean validColoring(){ // return false if a neighbor has this color
         for(int i=0; i<this.Neighbor.size(); i++)
            if(this.getColor().equals(this.Neighbor.get(i).getColor()) )
               return false;
         return true;
      }
   	
   }