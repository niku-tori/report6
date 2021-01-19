package jp.ac.uryukyu.ie.e205706;

class Omotemen {
    private int tate;
    private int yoko;
    private String men[][];

    public Omotemen(int _tate,int _yoko){
        tate = _tate;
        yoko = _yoko;
        men = new String[tate][yoko];
    }


    public String[][] createbanmen(){   
        for(int i = 0; i < tate; i++){
            for(int j = 0; j < yoko; j++){
                men[i][j] = "■";
            }
        }
        return men;
    }

    public void setMen(String mozi,int tate,int yoko){
        men[tate][yoko] = mozi;
    }

    public String[][] getMen(){
        return men;
    }

    public String getMen(int tate ,int yoko){
        return men[tate][yoko];
    }
    
}