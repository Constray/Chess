package Game;

public class Pair{

    private int x;
    private int y;

    public Pair(int nx, int ny) {
        this.x = nx;
        this.y = ny;
    }

    public  Pair(Pair old, int px, int py){
        this.x = old.x + px;
        this.y = old.y + py;
    }

    public boolean OnBoard(){
        return (x >= 0 && x <= 7 && y >=0 && y <= 7);
    }

    public boolean OnBoard(int xn, int yn){
        return ((x+xn) >= 0 && (x+xn) <= 7 && (y+yn) >=0 && (y+yn) <= 7);
    }

    // F added just for overload
    public static boolean OnBoard(int xx, int yy, boolean F)  {
        return (xx >= 0 && xx <= 7 && yy >=0 && yy <= 7);
    }

    @Override
    public boolean equals(Object other){
        if (this.getClass() != other.getClass()) return false;
        Pair ot = (Pair)other;
        return (this.x == ot.getX() && this.y == ot.getY() );
    }

    public int getX() { return x; }
    public int getY() { return y; }
}