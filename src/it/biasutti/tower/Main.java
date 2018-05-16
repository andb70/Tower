package it.biasutti.tower;

public class Main {

    public static void main(String[] args) {
        // write your code here
        ImageManager im = new ImageManager("C:/Users/andb7/IdeaProjects/tissino/gif/");
        Tower root = new Tower (20);
        for (int i = 0 ; i<29; i++) {
            root.load(i*i*20);
            while (root.over()) {
               /* System.out.println("altezza root: " + root.getHeight());
                Tower.printMap(Tower.map(root));*/
                root.unload();
            }
            //System.out.println("altezza: " + root.getHeight());
            System.out.println("altezza: " + Tower.getDistanceN(root,0)*2+1);
            //Tower.printMap( Tower.map(root));
            int[][] m= Tower.map(root);
            im.save(701, m.length, Tower.mapRGB(m), String.format("gif%d.png",i));
        }

        for (int i = 29 ; i<35; i++) {
            root.load(i);
            while (root.over()) {
               /* System.out.println("altezza root: " + root.getHeight());
                Tower.printMap(Tower.map(root));*/
                root.unload();
            }
            //System.out.println("altezza: " + root.getHeight());
            System.out.println("altezza: " + Tower.getDistanceN(root,0)*2+1);
            //Tower.printMap( Tower.map(root));
            int[][] m= Tower.map(root);
            im.save(701, m.length, Tower.mapRGB(m), String.format("gif%d.png",i));
        }
    }
}
