package com.zhbit.admin.administerclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
/**
 *
 * @author Administrator
 * @date 2018/5/17 0017
 */

public class Administer extends AppCompatActivity{
    private String S_Num;
    private String welcome;

    private TextView WelcomeText;

    private ImageButton personalCenterBt;
    private ImageButton ReservationBt;
    private ImageButton succeedBt;
    private ImageButton repairBt;
    private ImageButton opinionBt;
    private ImageButton helpBt;
    private ImageButton forumBt;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students);

        initButton();
        WelcomeText=(TextView)findViewById(R.id.welcomeText);

        //取得启动该Activity的Intent对象
        Intent intent =getIntent();
        S_Num=intent.getStringExtra("S_Num");
        welcome="当前登录账号："+S_Num;
        WelcomeText.setText(welcome);

        personalCenterBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PersonalCenterIntent=new Intent();
                PersonalCenterIntent.setClass(Administer.this,PersonalCenter.class);

                Bundle personalCenterBd=new Bundle();
                personalCenterBd.putString("personalCenterS_Num",S_Num);

                PersonalCenterIntent.putExtras(personalCenterBd);
                startActivity(PersonalCenterIntent);
            }
        });
        ReservationBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Administer.this,Reservation.class);
                intent.putExtra("S_Num",S_Num);
                startActivity(intent);
            }
        });
        succeedBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HelpIntent=new Intent();
                HelpIntent.setClass(Administer.this,Succeed.class);
                HelpIntent.putExtra("S_Num",S_Num);
                startActivity(HelpIntent);
            }
        });
        repairBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RepairIntent=new Intent();
                RepairIntent.setClass(Administer.this,Repair.class);

                Bundle repairBd=new Bundle();
                repairBd.putString("repairS_Num",S_Num);

                RepairIntent.putExtras(repairBd);
                startActivity(RepairIntent);
            }
        });
        opinionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpinionIntent=new Intent();
                OpinionIntent.setClass(Administer.this,Opinion.class);

                Bundle opinionBd=new Bundle();
                opinionBd.putString("opinionS_Num",S_Num);

                OpinionIntent.putExtras(opinionBd);
                startActivity(OpinionIntent);
            }
        });
        helpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HelpIntent=new Intent();
                HelpIntent.setClass(Administer.this,Help.class);
                HelpIntent.putExtra("S_Num",S_Num);
                startActivity(HelpIntent);
            }
        });
        forumBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HelpIntent=new Intent();
                HelpIntent.setClass(Administer.this,Forum.class);
                HelpIntent.putExtra("S_Num",S_Num);
                startActivity(HelpIntent);
            }
        });

    }

    protected void initButton(){
        personalCenterBt=(ImageButton)findViewById(R.id.personalCenterBt);
        ReservationBt=(ImageButton)findViewById(R.id.ReservationBt);
        succeedBt=(ImageButton)findViewById(R.id.succeedBt);
        repairBt=(ImageButton)findViewById(R.id.repairBt);
        opinionBt=(ImageButton)findViewById(R.id.opinionBt);
        helpBt=(ImageButton)findViewById(R.id.helpBt);
        forumBt=(ImageButton)findViewById(R.id.forumBt);
    }

}

