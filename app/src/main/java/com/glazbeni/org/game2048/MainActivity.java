package com.glazbeni.org.game2048;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore, tvBestScore;
    private int score = 0;
    private static MainActivity mainActivity = null;
    private  SharedPreferences.Editor editor;

    public MainActivity() {
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = (TextView) findViewById(R.id.tv_score);
        tvBestScore = (TextView) findViewById(R.id.tv_best_score);
        editor = getSharedPreferences("score", MODE_PRIVATE).edit();
        findViewById(R.id.btn_restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("你吼啊！！！").setMessage("当前续命："
                        + score + "s\n" + "历史最佳：" + getSharedPreferences("score", MODE_PRIVATE)
                        .getInt("bestScore", 0) + "s")
                        .setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                GameView.startGame();
                            }
                        }).setNegativeButton("返回游戏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                }).show().setCanceledOnTouchOutside(false);
            }
        });
    }

    public void clearScore() {
        if (showBestScore() == 0) {
            tvBestScore.setText("这是最佳：" + getSharedPreferences("score", MODE_PRIVATE).getInt("bestScore", 0));
        } else {
            tvBestScore.setText("这是最佳：" + showBestScore());
        }
        score = 0;
        showScore();
    }

    public void showScore() {
        tvScore.setText("这是分数：" + score);
    }

    int bestScore;
    public Integer showBestScore() {
        int saveScore = getSharedPreferences("score", MODE_PRIVATE).getInt("bestScore", 0);
        if (score >= saveScore) {
            bestScore = score;
            editor.putInt("bestScore", bestScore).commit();
        }
        return bestScore;
    }

    public Integer showLatestScore() {
        return score;
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

//    public class Save {
//        int saveScore;
//        public Integer save(int saveBest) {
//            getSharedPreferences("score", MODE_PRIVATE).edit().putInt("bestScore", saveBest).commit();
//            int saveScore = getSharedPreferences("score", MODE_PRIVATE).getInt("bestScore", 0);
//            return saveScore;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        int saveScore = getSharedPreferences("score", MODE_PRIVATE).getInt("bestScore", 0);
        if (saveScore < showBestScore()) {
            editor.putInt("bestScore", showBestScore()).commit();
            System.out.println("2333333333");
        }
        System.out.println(getSharedPreferences("score", MODE_PRIVATE).getInt("bestScore", 3));
    }
}
