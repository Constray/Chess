package game;

public class Pair {

    public void setX(int x) {
        if (x < 0) {
            this.x = 0;
        } else if (x > 7) {
            this.x = 7;
        } else
            this.x = x;
    }

    private int x;

    public void setY(int y) {
        if (y < 0) {
            this.y = 0;
        } else if (y > 7) {
            this.y = 7;
        } else
            this.y = y;
    }

    public void setPair(Pair pair) {
        this.setX(pair.getX());
        this.setY(pair.getY());
    }

    private int y;

    public Pair(int x, int y) {
        this.setX(x);;
        this.setY(y);
    }

    public  Pair(Pair old, int px, int py) {
        this.setX(x = old.x + px);
        this.setY(y = old.y + py);
    }

    @Override
    public String toString() {
        char[] ch = new char[2];
        ch[0] = (char)('a' + this.getX());
        ch[1] = (char)(this.getY() + 1 + '0');
        return new String(ch);
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