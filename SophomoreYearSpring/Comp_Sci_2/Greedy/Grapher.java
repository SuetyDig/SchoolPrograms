   import java.io.*;
   import java.util.*;

   public class Grapher{
   
      protected Hashtable<String, NodeInfo> H;
		protected ArrayList<String> Seq;
   
      public Grapher(String fileName)throws IOException{
      
         this.H = new Hashtable<String, NodeInfo>();
			this.Seq = new ArrayList<String>();
      	
         BufferedReader Buffer = new BufferedReader(new FileReader(fileName));
         while (Buffer.ready()){
            StringTokenizer T = new StringTokenizer(Buffer.readLine());
            String Name = T.nextToken();
         	
				Seq.add(Name);
            NodeInfo state = refNode(Name);
         	//System.out.print("["+state.getName());
         
            while(T.hasMoreTokens()){
               Name = T.nextToken();
            	//System.out.print("\t-"+Name);
               NodeInfo Nhbr = refNode(Name);
               state.addNeighbor(Nhbr);
            }
         	//System.out.println("]");
         	/**/
            System.out.print("["+state.getName()+"("+state.noOfNeighbors()+")");
            for(int i=0; i<state.noOfNeighbors(); i++){
               System.out.print("\t"+state.Neighbor.get(i).getName());
            }
            System.out.println("]");
         	/**/
         }
         Buffer.close();
      
      }
   	
      public NodeInfo refNode(String name){
         if(!H.containsKey(name)){
         	//System.out.print("++");
            H.put(name, new NodeInfo(name));
         }
      		//System.out.print("**"+H.get(name).getName()+"\t");
         return H.get(name);
      }
   	
      public Hashtable<String, NodeInfo> refTable(){
         return H;
      }
		
		public ArrayList<String> getSeq(){
			return Seq;
		}
   }