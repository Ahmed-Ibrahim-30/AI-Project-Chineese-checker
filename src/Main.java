import java.util.ArrayList;

public class Main {
    public static void main(String []args){

//        ArrayList<point> tmp= new ArrayList<>();
//        point p = new point(1,2);
//        point p1 = new point(3,4);
//        tmp.add(p);
//        tmp.add(p1);
//
//        point p2 = new point(3,4);
//        System.out.println(tmp.contains(p2));

        Board B = Board.getInstance();
        B.Print();
        GameController obj = new GameController();
        Player player1 = new Player(1);
        player1.setName("Ahmed");
        player1.setColor('R');
        obj.move(player1);

//        Board B = Board.getInstance();
//        Player player1 = new Player(1);
//        player1.setName("Ahmed");
//        player1.setColor('R');
//
//        Player player2 = new Player(4);
//        player2.setName("Khaled");
//        player2.setColor('G');
//
//        GameController obj = new GameController();
//        obj.move(player1);
//        for (int i=0;i<10;i++)
//        {
//            if(i%2==0)
//            {
////                obj.move(player1);
//            }else
//            {
//                //obj.move(player2);
//            }
//
//        }
    }
}
