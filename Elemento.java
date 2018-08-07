package maze;

public class Elemento {
	int posx;
	int posy;
	int visitado = 0;
	
	public void setx(int x) {
		posx = x;
	}
	
	public void sety(int y){
		posy = y;
	}
	
	public int getx() {
		return posx;
	}
	
	public int gety(){
		return posy;
	}
	
	public int getv() {
		return visitado; 
	}
	
	public void visitar() {
		visitado++;
	}

}
