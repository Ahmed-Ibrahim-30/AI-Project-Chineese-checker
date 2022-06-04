import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class point
{
    int row;
    int col;
    boolean isVisited;

    public point(int row, int col) {
        this.row = row;
        this.col = col;
        this.isVisited=false;
    }

    @Override
    public String toString() {
        return "point{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}

public class GameController {

    Board B = Board.getInstance();
    Map<point,ArrayList<point>> map = new HashMap<>();

    ArrayList<point> validJump = new ArrayList<>();

    Scanner scan = new Scanner(System.in);

    public void Game(Player obj)
    {
        System.out.println("\n");
        Board B = Board.getInstance();
        B.Print();

        System.out.println(obj.name + " Turn from : ");
        System.out.print("Row : ");
        int row = scan.nextInt();
        System.out.println("Col : ");
        int col = scan.nextInt();

        //B.MainBoard.get(row).get(col);

        System.out.println(obj.name + " Turn To : ");
        System.out.print("Row : ");
        int row2 = scan.nextInt();
        System.out.println("Col : ");
        int col2 = scan.nextInt();

        //B.MainBoard.get(row2).get(col2);

        if(B.MainBoard.get(row2).get(col2)=='W')
        {
            char a = B.MainBoard.get(row).get(col);

            B.MainBoard.get(row2).set(col2,a);
            B.MainBoard.get(row).set(col,'W');

        }else System.out.println("Not Empty");

    }
 /*
                                    ['B'],
                                  ['B','B'],
                                ['B','B','B'],
                              ['B','B','B','B'],
             ['W','W','W','W','W','W','W','W','W','W','W','W','W'],
                ['W','W','W','W','W','W','W','W','W','W','W','W'],
                  ['W','W','W','W','W','W','W','W','W','W','W'],
                    ['W','W','W','W','W','W','W','W','W','W'],
                      ['W','W','W','W','W','W','W','W','W'],
                    ['W','W','W','W','W','W','W','W','W','W'],
                  ['W','W','W','W','W','W','W','W','W','W','W'],
                ['W','W','W','R','W','W','W','W','W','W','W','W'],
             ['W','W','W','W','R','R','W','W','W','W','W','W','W'],
                              ['W','W','R','R'],
                                ['W','R','R'],
                                  ['R','R'],
                                    ['R'],

* */
    //                                                         [0],
//                                                            [0,1],
//                                                        ['0','1','2'],
//                                                       ['0','1','2','3'],
//                                     ['0','1','2','3','4','5','6','7','8','9','10','11','12'],
//                                      ['0','1','2','3','4','5','6','7','8','9','10','11'],
//                                        ['0','1','2','3','1','5','6','7','8','9','10'],
//                                           ['0','1','2','3','4','5','6','7','8','9'],
//                                             ['0','1','2','3','4','5','6','7','8'],
//                                           ['0','1','2','3','4','5','6','7','8','9'],
//                                        ['0','1','2','3  ','4','5','6','7','8','9','10'],
//                                      ['0','1','2','3','4','5','6','7','8','9','10','11'],
//                                    ['0','1','2','3','4','5  ','6','7','8','9','10','11','12'],
//                                                      ['0','1','2','3'],
//                                                        ['0','1','2'],
//                                                            [0,1],
//                                                             [0],




    /*
                                - - - - - - - - - - - - R - - - - - - - - - - - -
                                - - - - - - - - - - - R - R - - - - - - - - - - -
                                - - - - - - - - - - R - R - R - - - - - - - - - -
                                - - - - - - - - - R - R - H - R - - - - - - - - -
                                W - W - W - W - W - W - W - W - W - W - W - W - W
                                - W - W - W - W - W - W - W - W - W - W - W - W -
                                - - W - W - W - W - W - R - W - W - W - W - W - -
                                - - - W - W - W - W - W - W - W - W - W - W - - -
                                - - - - W - W - W - W - W - W - W - W - W - - - -
                                - - - W - W - W - W - G - G - W - W - W - W - - -
                                - - W - W - W - W - G - R - G - W - W - W - W - -
                                - W - W - W - W - W - G - W - W - W - W - W - W -
                                W - W - W - W - W - W - W - W - W - W - W - W - W
                                - - - - - - - - - G - G - G - G - - - - - - - - -
                                - - - - - - - - - - G - G - G - - - - - - - - - -
                                - - - - - - - - - - - G - G - - - - - - - - - - -
                                - - - - - - - - - - - - G - - - - - - - - - - - -
    * */
    public void move(Player obj)
    {
        ArrayList<point> pointResult = new ArrayList<>();
        Board B = Board.getInstance();

        for (int i=0;i<B.MainBoard.size();i++)
        {
            for(int j=0;j<B.MainBoard.get(i).size();j++){
                if(B.MainBoard.get(i).get(j)==obj.color)
                {
//                    System.out.println("[ " + i + " , " + j + " ]");
                    pointResult = validMoves(i,j);
                    validHops(i,j);
                    pointResult.addAll(validJump);
                    point p = new point(i,j);
                    map.put(p,pointResult);
                    validJump.clear();
                }
            }
        }
        System.out.println(map);
        // search All Bubble That Player Have
        // each Bubble Have List Of Available Moves
        // each Player Have List Of Available Bubbles to Move
    }
    int count;
    public ArrayList<point> validMoves(int r,int c)
    {
        count=0;
        ArrayList<point> points = new ArrayList<>();
        if(c>1)
        {
            //Left
            if( B.MainBoard.get(r).get(c-2) != '-' && B.MainBoard.get(r).get(c-2) =='W')
            {
                point p = new point(r,c-2);
                points.add(p);
            }else count++;

        }
        if(c < 23)
        {
            //Right

            if( B.MainBoard.get(r).get(c+2) != '-' &&  B.MainBoard.get(r).get(c+2)=='W')
            {
                point p = new point(r,c+2);
                points.add(p);
            }else count++;
        }
        if(r>0)
        {
            //top Left
            if(c>0 && B.MainBoard.get(r-1).get(c-1) != '-' && B.MainBoard.get(r-1).get(c-1)=='W')
            {
                point p = new point(r-1,c-1);
                points.add(p);
            }else count++;
            //top right

            if( c < 24 && B.MainBoard.get(r-1).get(c+1) != '-' && B.MainBoard.get(r-1).get(c+1)=='W')
            {
                point p = new point(r-1,c+1);
                points.add(p);
            }else count++;
        }
        if(r<16)
        {
            //Bottom right
            if(c < 24 &&  B.MainBoard.get(r+1).get(c+1) != '-' && B.MainBoard.get(r+1).get(c+1) =='W')
            {
                point p = new point(r+1,c+1);
                points.add(p);
            }else count++;
            //Bottom Left
            if( c>0 && B.MainBoard.get(r+1).get(c-1) != '-' && B.MainBoard.get(r+1).get(c-1)=='W')
            {
                point p = new point(r+1,c-1);
                points.add(p);
            }else count++;
        }
        return points;
    }

    public void validHops(int r,int c)
    {
        validMoves(r,c);
        if(count == 0)return;

        for (int i=0;i<validJump.size();i++)
        {
            if(validJump.get(i).row == r &&  validJump.get(i).col == c) return;
        }

        if(B.MainBoard.get(r).get(c)=='W')
        {
            point p = new point(r,c);
            validJump.add(p);
        }

        if(c>3)
        {
            //Left
            if( B.MainBoard.get(r).get(c-2) != '-' && B.MainBoard.get(r).get(c-2) !='W')
            {
                if(B.MainBoard.get(r).get(c-4) != '-' && B.MainBoard.get(r).get(c-4) =='W'){
                    validHops(r,c-4);
                }
            }
        }
        if(c < 21)
        {
            //Right
            if( B.MainBoard.get(r).get(c+2) != '-' &&  B.MainBoard.get(r).get(c+2)!='W')
            {
                if( B.MainBoard.get(r).get(c+4) != '-' &&  B.MainBoard.get(r).get(c+4)=='W'){
                    validHops(r,c+4);
                }
            }
        }
        if(r>1)
        {
            //top Left
            if(c>1 && B.MainBoard.get(r-1).get(c-1) != '-' && B.MainBoard.get(r-1).get(c-1)!='W')
            {
                if(B.MainBoard.get(r-2).get(c-2) != '-' && B.MainBoard.get(r-2).get(c-2)=='W')
                {
                    validHops(r-2,c-2);
                }

            }
            //top right
            if( c < 23 && B.MainBoard.get(r-1).get(c+1) != '-' && B.MainBoard.get(r-1).get(c+1)!='W')
            {
                if(B.MainBoard.get(r-2).get(c+2) != '-' && B.MainBoard.get(r-2).get(c+2)=='W')
                {
                    validHops(r-2,c+2);
                }
            }
        }
        if(r<15)
        {
            //Bottom Right
            if(c < 23 &&  B.MainBoard.get(r+1).get(c+1) != '-' && B.MainBoard.get(r+1).get(c+1) !='W')
            {
                if(B.MainBoard.get(r+2).get(c+2) != '-' && B.MainBoard.get(r+2).get(c+2) =='W'){
                    validHops(r+2,c+2);
                }
            }
            //Bottom Left
            if( c>1 && B.MainBoard.get(r+1).get(c-1) != '-' && B.MainBoard.get(r+1).get(c-1)!='W')
            {
                if(B.MainBoard.get(r+2).get(c-2) != '-' && B.MainBoard.get(r+2).get(c-2)=='W'){
                    validHops(r+2,c-2);
                }
            }
        }
    }
}