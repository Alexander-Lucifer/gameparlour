import java.awt.Graphics;



import java.awt.Rectangle;





public class Ball {


    private int x;


    private int y;


    private int size;


    private int speedX;


    private int speedY;





    public Ball(int x, int y, int size) {


        this.x = x;


        this.y = y;


        this.size = size;


        this.speedX = 4;


        this.speedY = 4;


    }





    public void draw(Graphics g) {


        g.fillOval(x, y, size, size);


    }





    public void update() {


        x += speedX;


        y += speedY;


    }





    public void reverseX() {


        speedX = -speedX;


    }





    public void reverseY() {


        speedY = -speedY;


    }





    public void reset() {


        x = 400;


        y = 300;


        speedX = -speedX; // Reverse direction after scoring


    }





    public int getX() {


        return x;


    }





    public int getY() {


        return y;


    }





    public int getSize() {


        return size;


    }





    public Rectangle getBounds() {


        return new Rectangle(x, y, size, size);


    }


} 