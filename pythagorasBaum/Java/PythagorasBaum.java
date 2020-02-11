package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class PythagorasBaum{


    int maxTiefe;
    float hue = 0.7f;
    int x1;
    int x2;
    int y1;
    int y2;
    int tiefe;
    int decision;
    Canvas canvas = new Canvas(700, 700);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    public PythagorasBaum(int x1, int x2, int y1, int y2, int tiefe, int maxTiefe, int direction) {

        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.tiefe = tiefe;
        this.maxTiefe = maxTiefe;
        this.decision = direction;
        init();


    }

    public void init(){

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

    }

    public double[] berechnePc(double alpha, double c, double pax, double pay,double dx, double dy){

        double[] x5y5 = new double[2];
        //alpha = 360-alpha;
        //alpha = 360- (alpha) - Math.toDegrees(Math.acos(dx/c));
        double gamma = 90;
        double beta = 180-alpha-gamma;

        double sinBeta = Math.sin(Math.toRadians(alpha));
        double sinGamma = Math.sin(Math.toRadians(gamma));

        double b = ((c*sinBeta)/sinGamma);
        double a = Math.sqrt(Math.pow(b,2)-2*b*c*Math.cos(Math.toRadians(beta))+Math.pow(c,2));

        double x5 = (Math.cos(Math.toRadians(alpha))*a)+pax;
        //x5 = Math.toDegrees(x5);
        double y5 = (Math.sin(Math.toRadians(alpha))*a)+pay;
        //y5 = Math.toDegrees(y5);
        System.out.println(beta);

        /**
        System.out.print("x5 ");
        System.out.println(x5);
        System.out.print("y5 ");
        System.out.println(y5);
        System.out.println();*/

        x5y5[0] = x5;
        x5y5[1] = y5;

        return x5y5;

    }

    public void drawTree(double x1, double y1, double x2, double y2, int tiefe, float direction, Color color){


        if (tiefe == maxTiefe)
            return;

        double dx = x2 - x1;
        double dy = y1 - y2;

        double x3 = x2 - dy;
        double y3 = y2 - dx;
        double x4 = x1 - dy;
        double y4 = y1 - dx;

        double alpha = direction;

        double x5 = x4 + (Math.tan(Math.toRadians(alpha))/2)  * (dx - dy);
        double y5 = y4 - (Math.tan(Math.toRadians(alpha))/2)  * (dx + dy);


        double c = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));

        //double[] x5y5 = berechnePc(direction,c,x4,y4,dx,dy);

        /**
        double x5 = x5y5[0];
        double y5 = x5y5[1];*/

        /**
         *         int dx = (int)((double)schritt * Math.sin(this.b));
         *         int dy = (int)((double)schritt * Math.cos(this.b));
         *         int xe = this.xa + dx;
         *         int ye = this.ya - dy;
         *         g.drawLine(this.xa, this.ya, xe, ye);
         *         this.xa = xe;
         *         this.ya = ye;
         */


        //Quadrat
        gc.beginPath();
        gc.moveTo(x1, y1);
        gc.lineTo(x2, y2);
        gc.lineTo(x3, y3);
        gc.lineTo(x4, y4);
        gc.closePath();


        gc.setFill(color);
        gc.fill();

        //Dreieck
        gc.beginPath();
        gc.moveTo(x3, y3);

        gc.lineTo(x4, y4);
        gc.lineTo(x5, y5);
        gc.closePath();

        gc.setFill(Color.GREY);
        gc.fill();


        //left
        drawTree(x4, y4, x5, y5, tiefe + 1,direction,Color.hsb(color.getHue()-(tiefe*10),1,1));

        //right
        drawTree(x5, y5, x3, y3, tiefe + 1,direction,Color.hsb(color.getHue()-(tiefe*10),1,0.8));

    }

    public void setMaxTiefe(int maxTiefe){
        this.maxTiefe = maxTiefe;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public GraphicsContext getGc(){
        return gc;
    }


}


