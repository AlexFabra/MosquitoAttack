package cat.dam.alex.mosquitoattack;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SegonaActivity extends AppCompatActivity {

    private TextView tv_scorepoints;
    private int points=0;
    ImageView iv_mosquit;
    AnimationDrawable mosquit_animat;
    Random rand = new Random();
    boolean mosquitoLiving=true;
    Handler handler = new Handler(); //per pausar
    Runnable runnable;
    Runnable runnablePaused;
    int mosquitoFlyingVelocity= 300;
    int pause = 1500;

    //@RequiresApi(api = Build.VERSION_CODES.P)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segona);

        tv_scorepoints = (TextView) findViewById(R.id.tv_scorepoints);
        tv_scorepoints.setText(Integer.toString(points));

        mosquit_animat = new AnimationDrawable();
        // Carrega el ImageView que contindrà l'animació i actualiza el fons d'imatge amb el recurs XML on es defineix les imatges
        // i temps d'animació del mosquit
        iv_mosquit = (ImageView) findViewById(R.id.iv_mosquit);
        // Situem la imatge en la pantalla
        iv_mosquit.setX(100);
        iv_mosquit.setY(150);
        setMosquitoImage();
        mosquitoAlive();

        iv_mosquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBloodImage();
                handler.removeCallbacks(runnable); //per parar el runnable.
                incrementScore();
                restartMosquito();
            }
        });
    }
    public void setMosquitoImage(){
        iv_mosquit.setBackgroundResource(R.drawable.mosquit_animat);
        // Obté el fons que ha estat compilat amb un objecte AnimationDrawable
        mosquit_animat = (AnimationDrawable) iv_mosquit.getBackground();
        // Comença l'animació (per defecte repetició de cicle).
        mosquit_animat.start();
    }
    public void setBloodImage(){
        // En cas de que es cliqui el mosquit actualiza el fons d'imatge amb el recurs XML on es defineix les imatges
        // i temps d'animació de la taca de sang
        iv_mosquit.setBackgroundResource(R.drawable.sang_animat);
        mosquit_animat = (AnimationDrawable) iv_mosquit.getBackground();
        System.out.println(iv_mosquit.getBackground());
        // Fes l'animació (només un cicle).
        mosquit_animat.start();
    }
    public void mosquitoAlive(){
        handler.postDelayed(runnable = new Runnable(){
            public void run(){
                iv_mosquit.setX(rand.nextInt(500));
                iv_mosquit.setY(rand.nextInt(500));
                handler.postDelayed(this, mosquitoFlyingVelocity);
            }
        }, mosquitoFlyingVelocity);
    }
    public void restartMosquito(){
        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("Pausa");
                setMosquitoImage();
                mosquitoAlive();
            }
        }, pause);
    }
    public void incrementScore(){
        points+=1;
        setScore(points);
    }
    public void restartScore(){
        points=0;
        setScore(points);
    }
    public void setScore(int points){
        tv_scorepoints.setText(Integer.toString(points));
    }
    public void restart(View restart){
        handler.removeCallbacks(runnable); //per parar el runnable.
        restartScore();
        restartMosquito();
    }
}
