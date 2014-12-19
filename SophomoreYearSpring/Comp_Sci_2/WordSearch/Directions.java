import java.util.*;

public class Directions implements Iterator{
	Direction[] D = new Direction[8];
	Integer index;
	
	public Directions(){
		this.D[0] = new Direction("north"    , -1, 0);
		this.D[1] = new Direction("northeast", -1, 1);
		this.D[2] = new Direction("east"     , 0, 1);
		this.D[3] = new Direction("southeast", 1, 1);
		this.D[4] = new Direction("south"    , 1, 0);
		this.D[5] = new Direction("southwest", 1, -1);
		this.D[6] = new Direction("west"     , 0, -1);
		this.D[7] = new Direction("northwest", -1, -1);
		this.index = 0;
	}
	
	public Object next(){
		assert this.index<8: "Iteration error";
		return D[this.index++];
	}
	
	public boolean hasNext(){
		return this.index<8;
	}
	
	public void remove(){
	
	}

}
