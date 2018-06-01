package edu.skku.swp3.metroapp;

/**
 * Created by lufov on 2018-05-22.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import edu.skku.swp3.metroapp.MetroMap;
public class DrawView extends View {
    Paint paint = new Paint();
    private float []position;
    private void init() {
        paint.setColor(Color.BLACK);
    }

    public DrawView(Context context, float []position) {
        super(context);
        this.position=position;
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        //4호선
        paint.setColor(Color.BLUE);
        String text="";
        text=text+position[0];
        Log.v("hello", text);
        canvas.drawLine(position[0], position[1],position[2],position[3], paint);
        canvas.drawLine(0, 0, 20, 20, paint);
        canvas.drawLine(20, 0, 0, 20, paint);
/*        canvas.drawLine(this.saX, this.saY, this.chX, this.chY, paint);

        //7호선
        paint.setColor(Color.RED);
        canvas.drawLine(this.sungX, this.sungY, this.chX, this.chY, paint);
        canvas.drawLine(this.chX, this.chY, this.naeX, this.naeY, paint);

        //2호선
        paint.setColor(Color.GREEN);
        canvas.drawLine(this.nakX, this.nakY, this.saX, this.saY, paint);
        canvas.drawLine(this.saX, this.saY, this.bangX, this.bangY, paint);*/
    }
}