package jp.ac.uryukyu.ie.e205706;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

class Uramen {
    int tate;
    int yoko;
    int bakudansuu;
    String[][] uramen;

    public Uramen(int _tate, int _yoko, int _bakudansuu){
        tate = _tate;
        yoko = _yoko;
        bakudansuu = _bakudansuu;
        uramen = new String[tate][yoko];
    }

    /**
     * 爆弾を作成する。(9×9の場合,爆弾数20個とする)
        ①listに縦×横分の要素をいれる　　list = [0,1,2,3,4...78,79,80] 
        ②listの要素をシャッフルする    list = [7,11,15,3...14,16...45,34,27] 
        ③爆弾の数分listの長さを削る　   list = [7,11,15,3...14,16] (20個に減らした。)
        ④listの要素を縦で割る　　　　　 7 / 9 = 0 余り 7, 11 / 9 = 1 余り 2 (今回は9×9なので9で割った。)
        ⑤商と余りを別々のリストにいれる　syou = [0,1,1,0...1,1] amari = [7,2,6,3...5,7]
        ⑥商を縦、余りを横の要素として下のnullのような配列に爆弾（"E"）いれる

            0    1    2   3    4    5    6    7    8 　　　　　　　　　　　　　    0    1    2   3    4    5    6    7    8 
        a null null null null null null null null null                     a null null null null null null null  E   null
        b null null null null null null null null null                     b null null  E   null null null  E   null null
        c null null null null null null null null null                     c null null null null null null null null null
        d null null null null null null null null null    [7,11,15...]     d null null null null null null null null null
        e null null null null null null null null null    ==========>　  　 e null null null null null null null null null
        f null null null null null null null null null                     f null null null null null null null null null
        g null null null null null null null null null                     g null null null null null null null null null
        h null null null null null null null null null                     h null null null null null null null null null 
        i null null null null null null null null null                     i null null null null null null null null null 
     */

    public void createUramen(){
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < tate * yoko; i++){
            list.add(i);
        }
        Collections.shuffle(list);
        list.subList(bakudansuu, tate*yoko).clear();

        for(int i = 0; i < list.size(); i++){
            uramen[list.get(i) / tate][list.get(i) % yoko] = "E";
        }
        createNumCount();
    }


    /**
     * 近くに爆弾が何個あるかを教えてくれるメソッド。
     * （例）
     *       null null null
     *         E  null  E
     *       null null null
     * 
     * この時、左上のnullから、そこを基準として、
     * 上、下、右、左、右上、左上、右下、左下の８方向のいずれかに爆弾が何個あるかを調べる。
     * 調べ終えたら、隣のnullに移動し、そこを基準に８方向を確認し、爆弾の数を調べる。
     * 右下まで操作を終えると下のようになる。
     * 
     *         1  2  1
     *         E  2  E
     *         1  2  1
     * 
     */
    public void createNumCount(){
        for(int i = 0; i<tate; i++){
            for(int j=0; j<yoko; j++){
                int count = 0;
                if(uramen[i][j] == null){
                        if(i > 0){
                            if(uramen[i - 1][j] == "E" ){
                                count++;
                            }
                        }
                        if(i  < tate - 1){
                            if(uramen[i + 1][j] == "E"){
                                count++;
                            }
                        }
                        if(j > 0){
                            if(uramen[i][j - 1] == "E" & j - 1 >= 0 ){
                                count++;
                            }
                        }
                        if(j  < yoko - 1){
                            if(uramen[i][j + 1] == "E"){
                                count++;
                            }
                        }   
                        if(i > 0 & j > 0){
                            if(uramen[i - 1][j - 1] == "E"){
                                count++;
                            }
                        }
                        if(i > 0 & j < yoko - 1){
                            if(uramen[i - 1][j + 1] == "E" & i - 1 >= 0 & j + 1 <= yoko){
                                count++;
                            }
                        }   
                        if(i < tate - 1 & j < yoko - 1){
                            if(uramen[i + 1][j + 1] == "E"){
                                count++;
                            }
                        }
                        if(i < tate - 1 & j > 0){
                            if(uramen[i + 1][j - 1] == "E"){
                                count++;
                            }
                        }
                    if(count == 0){uramen[i][j] = " ";}
                    else{uramen[i][j] = String.valueOf(count);}
                }
            }
        }
    }

    public String getUramen(int uratate,int urayoko){
        return uramen[uratate][urayoko];
    }
}