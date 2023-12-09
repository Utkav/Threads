package com.example.threads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String TEXTTOVIEW ="И снова Новый год пред хатой,\n" +
                                    "Где я живу, стряхает снег\n" +
                                    "С усталых ног. Прельшая платой\n" +
                                    "Хозяев, просит дать ночлег.\n" +
                                    "Мне истекает тридцать пятый,\n" +
                                    "Ему идет двадцатый век.\n" +
                                    "Но он совсем молодцеватый\n" +
                                    "И моложавый человек —\n" +
                                    "Былых столетий соглядатай,\n" +
                                    "Грядущих прорицатель нег,\n" +
                                    "Цивилизации вожатый,\n" +
                                    "Сам некультурный печенег.\n" +
                                    "Его с классической заплатой\n" +
                                    "На шубе знал еще Олег.\n" +
                                    "Он входит. Пол трещит дощатый\n" +
                                    "Под ним: ведь шаг его рассек\n" +
                                    "Все почвы мира. Вид помятый\n" +
                                    "Его надежил всех калек\n" +
                                    "И обездоленных. Под ватой\n" +
                                    "Шубенки старой — сердца бег,\n" +
                                    "Бессмертной юностью объятый:\n" +
                                    "Его приемлет дровосек —\n" +
                                    "Ваятеля античных статуй,\n" +
                                    "Виновника зачатья рек…";
    TextView textView;
    Button button;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.TV);
        button = findViewById(R.id.But);
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
                char[] chars = (char[]) msg.obj;
                textView.setText(String.valueOf(chars));
            }
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyThread myThread = new MyThread(TEXTTOVIEW);
                myThread.start();
            }
        });
    }

    class MyThread extends Thread{

        private String str;
        private char[] textToView;
        public MyThread(String str){
            this.str = str;
            this.textToView = new char[str.length()];
        }
        @Override
        public void run() {
            super.run();
            char[] chars = str.toCharArray();
            String punct500 = ".!?;";
            String punct200 = ",-:";
            for(int i = 0; i < str.length(); i++){
                textToView[i] = chars[i];

                Message msg = new Message();
                msg.obj = textToView;
                handler.sendMessage(msg);
                if(punct500.contains(String.valueOf(chars[i]))){
                    try{
                        sleep(500);
                    } catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                } else if (punct200.contains(String.valueOf(chars[i]))){
                    try{
                        sleep(200);
                    } catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                } else{
                    try{
                        sleep(100);
                    } catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }
//                   sleep();
            }
        }
    }
}