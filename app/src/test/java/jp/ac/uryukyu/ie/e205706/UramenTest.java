package jp.ac.uryukyu.ie.e205706;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UramenTest{
    /**
     * Uramenクラスから爆弾と付近の爆弾数が書いてある配列を３回呼び出す。この時、nullとなっていなければ良い。
     */
    @Test
    void UramenTest(){
        int tate = 10;
        int yoko = 10;
        int bakudannsuu = 20;

        for(int h=0; h<3; h++){
            Uramen f1 = new Uramen(tate, yoko, bakudannsuu);
            f1.createUramen();
            for(int i = 0; i < tate ;i++){
                for(int j = 0; j < yoko ;j++){
                    assertNotNull(f1.getUramen(i, j)); 
                }
            }
        }
    }
}
    
