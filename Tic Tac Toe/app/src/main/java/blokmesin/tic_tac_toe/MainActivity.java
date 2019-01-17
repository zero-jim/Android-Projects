package blokmesin.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private Button[][] buttons= new Button[3][3];
    private boolean player1Turn=true;

    private int roundCount;

    private int player1points;
    private int player2points;

    private TextView textview1;
    private TextView textview2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview1=findViewById(R.id.text_view_p1);
        textview2=findViewById(R.id.text_view_p2);

        Button button_Reset = findViewById(R.id.button_reset);
        button_Reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    resetGame();
            }
        });
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById((resID));
                buttons[i][j].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!((Button) v).getText().toString().equals("")){
                            return;
                        }
                        if(player1Turn){
                            ((Button) v).setText("X");
                        }
                        else{
                            ((Button) v).setText("O");
                        }
                        roundCount++;
                        if(checkForWin()){
                            if(player1Turn){
                                p1wins();
                            }
                            else{
                                p2wins();
                            }
                        }
                        else if(roundCount==9)
                            draw();
                        else
                            player1Turn=!player1Turn;
                    }
                    private boolean checkForWin(){
                        String[][] field = new String[3][3];

                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                field[i][j]=buttons[i][j].getText().toString();
                            }
                        }
                        for(int i=0;i<3;i++){
                            if(field[i][0].equals(field[i][1])
                                    && field[i][0].equals(field[i][2])
                                    && !field[i][0].equals("")){
                                return true;
                            }
                        }
                        for(int i=0;i<3;i++){
                            if(field[0][i].equals(field[1][i])
                                    && field[0][i].equals(field[2][i])
                                    && !field[0][i].equals("")){
                                return true;
                            }
                        }
                        if(field[0][0].equals(field[1][1])
                                && field[0][0].equals(field[2][2])
                                && !field[0][0].equals("")){
                            return true;
                        }
                        if(field[0][2].equals(field[1][1])
                                && field[0][2].equals(field[2][0])
                                && !field[0][2].equals("")){
                            return true;
                        }
                        return  false;
                    }
                    private void p1wins(){
                        player1points++;
                        Toast tost=Toast.makeText(getApplicationContext(),"Player 1 Wins!",Toast.LENGTH_SHORT);
                        tost.show();
                        update();
                        reset_board();
                    }
                    private void p2wins(){
                        player2points++;
                        Toast tost=Toast.makeText(getApplicationContext(),"Player 2 Wins!",Toast.LENGTH_SHORT);
                        tost.show();
                        update();
                        reset_board();
                    }
                    private void draw(){
                        Toast tost=Toast.makeText(getApplicationContext(),"Draw!",Toast.LENGTH_SHORT);
                        tost.show();
                        reset_board();
                    }
                    private  void update(){
                        textview1.setText("Player 1: "+player1points);
                        textview2.setText("Player 2: "+player2points);
                    }
                    private  void reset_board(){
                        for(int i=0;i<3;i++){
                            for (int j = 0; j < 3; j++) {
                                buttons[i][j].setText("");
                            }
                        }
                        roundCount=0;
                        player1Turn=true;
                    }
                    });
                }
            }
        }
    private void resetGame(){
        player1points=0;
        player2points=0;

        textview1.setText("Player 1:"+player1points);
        textview2.setText("Player 2:"+player2points);

        for(int i=0;i<3;i++){
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                }
            }
            roundCount=0;
            player1Turn=true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("player1points",player1points);
        outState.putInt("player2points",player2points);
        outState.putBoolean("player1Turn",player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1points = savedInstanceState.getInt("player1points");
        player2points = savedInstanceState.getInt("player2points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
