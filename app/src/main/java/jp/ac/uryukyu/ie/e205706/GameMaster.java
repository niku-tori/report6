package jp.ac.uryukyu.ie.e205706;

import java.util.*;
import java.util.Scanner;

public class GameMaster{
    static int tate;
    static int yoko;
    int bakudansuu;
    Omotemen f1;
    Uramen f2;

    public GameMaster(int tate,int yoko,int bakudansuu){
        this.tate = tate;
        this.yoko = yoko;
        this.bakudansuu = bakudansuu;
        f1 = new Omotemen(tate, yoko);
        f2 = new Uramen(tate, yoko, bakudansuu);
        f1.createbanmen();
        f2.createUramen();
    }

    public boolean checkOpen(int tate, int yoko){
        if(f1.getMen(tate,yoko) != "■"){
            return true;
        }else{
            return false;
        }
    }


    public void checkZero(int tate, int yoko){
        if(f2.getUramen(tate, yoko) == " "){
            if(tate > 0){
                if(checkOpen(tate - 1, yoko) == false){
                f1.setMen(f2.getUramen(tate -1 ,yoko),tate -1 ,yoko);
                checkZero(tate - 1,yoko);}
            }
            if(yoko > 0){
                if(checkOpen(tate, yoko -1) == false){
                f1.setMen(f2.getUramen(tate ,yoko -1),tate ,yoko- 1);
                checkZero(tate,yoko - 1);}
            }
            if(tate < this.tate -1){
                if(checkOpen(tate + 1, yoko) == false){
                f1.setMen(f2.getUramen(tate + 1 ,yoko),tate + 1,yoko);
                checkZero(tate + 1,yoko);}
            }
            if(yoko < this.yoko -1){
                if(checkOpen(tate, yoko + 1) == false){
                f1.setMen(f2.getUramen(tate ,yoko + 1),tate ,yoko + 1);
                checkZero(tate,yoko + 1);}
            }
            if(tate > 0 & yoko > 0){
                if(checkOpen(tate - 1, yoko - 1) == false){
                f1.setMen(f2.getUramen(tate -1 ,yoko -1 ),tate -1 ,yoko -1);
                checkZero(tate - 1,yoko -1);}
            }
            if(tate > 0 & yoko < this.yoko - 1){
                if(checkOpen(tate - 1, yoko + 1) == false){
                f1.setMen(f2.getUramen(tate -1 ,yoko + 1 ),tate -1 ,yoko + 1);
                checkZero(tate - 1,yoko + 1);}
            }
            if(tate < this.tate - 1 & yoko > 0){
                if(checkOpen(tate + 1, yoko - 1) == false){
                f1.setMen(f2.getUramen(tate + 1 ,yoko - 1 ),tate + 1 ,yoko - 1);
                checkZero(tate + 1,yoko - 1);}
            }
            if(tate < this.tate - 1 & yoko < this.yoko - 1){
                if(checkOpen(tate + 1, yoko + 1) == false){
                f1.setMen(f2.getUramen(tate + 1 ,yoko + 1 ),tate + 1 ,yoko + 1);
                checkZero(tate + 1,yoko + 1);}
            }
        }
    }


    public void game(){
        printmen(f1.getMen());
        Scanner scanner = new Scanner(System.in);
        int tatesuuzi;
        char c;
        int yokosuuzi;
        boolean gamefrag = true;
        int clearOrOver = 0;

        while (gamefrag) {

            System.out.println("縦の数字を入力して下さい [0~]");
            while(true){
                try{
                    tatesuuzi = scanner.nextInt();
                    break;
                }catch(Exception e){
                    System.out.println("入力エラー");
                    System.out.print("縦の数字を入力して下さい [0~]");
                    scanner.next();
                }
            }

            System.out.print("横の文字を入力して下さい [a~]");
            while(true){
                try{
                    c = scanner.next().charAt(0);
                    yokosuuzi = c - 'a';

                    f1.setMen(f2.getUramen(tatesuuzi,yokosuuzi),tatesuuzi,yokosuuzi);
                    checkZero(tatesuuzi,yokosuuzi);
                    printmen(f1.getMen());
                    if(gameOver(tatesuuzi, yokosuuzi)){
                        System.out.println("GAME OVER !!");
                        gamefrag = false;
                        clearOrOver = 1;
                    }
                    if(gameClear() && clearOrOver == 0){
                        System.out.println("GAME CLEAR !!");
                        gamefrag = false;
                    }
                    break;
                }catch(Exception e){
                    System.out.println("入力エラー");
                    System.out.print("横の文字を入力して下さい [a~]");
                }
            }
        }
    }


    public void printmen(String[][] men){
        char c = 'a';

        //一列目を表示
        System.out.print("  ");
        for(int i = 0; i < men.length; i++){
        System.out.print(c);
        System.out.print(" ");
        c++;
        }
        System.out.println();

        //2列目移行を表示
        for(int i = 0; i < men.length; i++){
            System.out.print(i);
            System.out.print(" ");

            for(int j = 0; j < men[0].length; j++){
                System.out.print(men[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }

    public boolean gameClear(){
        int count = 0;
        for(int i = 0; i <tate; i++){
            for(int j = 0; j <tate; j++){
                if(f1.getMen(i,j) == "■"){
                    count++;
                }
            }
        }
        if(bakudansuu == count){
            return true;
        }
    return false;
    }

    public boolean gameOver(int tate,int yoko){
        if(f2.getUramen(tate, yoko) == "E"){
            return true;
        }
        return false;
    }
    
}