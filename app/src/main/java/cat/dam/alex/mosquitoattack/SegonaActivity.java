package cat.dam.alex.mosquitoattack;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SegonaActivity extends AppCompatActivity {

    private TextView tv_scorepoints;
    private int points=0;
    ImageView iv_mosquit;
    AnimationDrawable mosquit_animat;

    @RequiresApi(api = Build.VERSION_CODES.P)
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
        iv_mosquit.setBackgroundResource(R.drawable.mosquit_animat);
        // Obté el fons que ha estat compilat amb un objecte AnimationDrawable
        mosquit_animat = (AnimationDrawable) iv_mosquit.getBackground();
        // Comença l'animació (per defecte repetició de cicle).
        mosquit_animat.start();

        iv_mosquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // En cas de que es cliqui el mosquit actualiza el fons d'imatge amb el recurs XML on es defineix les imatges
                // i temps d'animació de la taca de sang
                iv_mosquit.setBackgroundResource(R.drawable.sang_animat);
                mosquit_animat = (AnimationDrawable) iv_mosquit.getBackground();
                // Fes l'animació (només un cicle).
                mosquit_animat.start();
            }
        });
        Handler handler = new Handler(); //per pausar
        handler.postDelayed(new Runnable(){
            public void run(){
                iv_mosquit.setX(200);
                iv_mosquit.setY(250);
            }
        }, 2000);
    }

}
