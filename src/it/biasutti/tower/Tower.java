package it.biasutti.tower;

import static java.util.Objects.isNull;

public class Tower {
    private int _height;
    Tower _N;
    Tower _E;
    Tower _S;
    Tower _W;

    public Tower(int height) {
        _height = height;
    }
    public Tower() {
        _height = 0;
    }

    public boolean over() {
        return _height > 3;
    }

    public int getHeight() {
        return _height;
    }

    public void load(int cubes) {
        _height += cubes;
        if (over()) {
            unload();
        }
    }

    public void load() {
        load(1);
    }

    public void unload() {
        if (isNull(_N)) {
            esploraN();
        }
        if (isNull(_E)) {
            esploraE();
        }
        if (isNull(_S)) {
            esploraS();
        }
        if (isNull(_W)) {
            esploraW();
        }
        _height -= 4;
        _N.load();
        _E.load();
        _S.load();
        _W.load();
    }

    public void esploraN() {
        _N = createNeighbourN(this);
        try {
            _N.setENeighbour(_E._N);
        } catch (Exception e) {
            //return;
        }
        try {
            _N.setWNeighbour(_W._N);
        } catch (Exception e) {
            //return;
        }
    }

    public void esploraE() {
        _E = createNeighbourE(this);
        try {
            _E.setSNeighbour(_S._E);
        } catch (Exception e) {
            //return;
        }
        try {
            _E.setNNeighbour(_N._E);
        } catch (Exception e) {
            //return;
        }
    }

    public void esploraS() {
        _S = createNeighbourS(this);
        try {
            _S.setWNeighbour(_W._S);
        } catch (Exception e) {
            //return;
        }
        try {
            _S.setENeighbour(_E._S);
        } catch (Exception e) {
            //return;
        }

    }

    public void esploraW() {
        _W = createNeighbourW(this);
        try {
            _W.setNNeighbour(_N._W);
        } catch (Exception e) {
            //return;
        }
        try {
            _W.setSNeighbour(_S._W);
        } catch (Exception e) {
            //return;
        }

    }

    /**
     * aggiunge un vicino a NORD
     */
    public static Tower createNeighbourN(Tower caller) {
        Tower t = new Tower();
        t.setSNeighbour(caller);
        return t;
    }

    public void setSNeighbour(Tower caller) {
        _S = caller;
        caller._N = this;
    }

    /**
     * aggiunge un vicino a SUD
     */
    public static Tower createNeighbourS(Tower caller) {
        Tower t = new Tower();
        t.setNNeighbour(caller);
        return t;
    }

    public void setNNeighbour(Tower caller) {
        _N = caller;
        caller._S = this;
    }

    /**
     * aggiunge un vicino a EAST
     */
    public static Tower createNeighbourE(Tower caller) {
        Tower t = new Tower();
        t.setWNeighbour(caller);
        return t;
    }

    public void setWNeighbour(Tower caller) {
        _W = caller;
        caller._E = this;
    }

    /**
     * aggiunge un vicino a WEST
     */
    public static Tower createNeighbourW(Tower caller) {
        Tower t = new Tower();
        t.setENeighbour(caller);
        return t;
    }

    public void setENeighbour(Tower caller) {
        _E = caller;
        caller._W = this;
    }
    public static int getDistanceN(Tower caller, int n) {
        if (isNull(caller._N)){
            return n;
        }
        return getDistanceN(caller._N,n+1);
    }
    public static Tower getTopN(Tower caller) {
        if (isNull(caller._N)){
            return caller;
        }
        return getTopN(caller._N);
    }

    public static int[][] map(Tower r) {
        int i = getDistanceN(r,0);
        //if (i==0) {return null;}
        int m[][] =new int[i*2+1][ i*2+1];
        Tower t = getTopN(r);

        //System.out.println("elemnts: " + (i * 2 + 1) + ", central: "+i);
        for (int row=0; row<i*2+1; row++) {
            int col = i;
            Tower next = t;
            do {
                //System.out.println("    r: "+row +", col: "+col);
                m[row][col] = next._height+1;
                next = next._W;
                col--;
            } while (!isNull(next));
            next = t._E;
            col = i+1;
            while (!isNull(next)) {
                m[row][col] = next._height+1;
                next = next._E;
                col++;
            }
            t = t._S;
        }


        return m;
    }
    public static int[] mapRGB(int[][] map) {
        int[] mapped = new int[map.length*map[0].length];
        int[] cRGB = {0x0, 0x0000FF, 0x00FF00, 0xFF0000, 0xFFFF00};
        int i=0;
        for (int r = 0; r< map.length; r++) {
            for (int c = 0; c< map[r].length; c++) {
                mapped[i++] = cRGB[map[r][c]];
            }
        }
        return mapped;
    }
    public static void printMap(int[][] map){
        char[] s = {' ',' ','+','X','@'};

        int i=0;
        for (int r = 0; r< map.length; r++) {
            System.out.println("");
            for (int c = 0; c< map[r].length; c++) {
                System.out.print(s[map[r][c]]+" ");
                i += map[r][c];
            }
        }
        System.out.println("\ntotale:"+i);
    }
}
