import java.awt.Graphics;



import java.awt.Rectangle;





public class Paddle {


    private int x;


    private int y;


    private int width;


    private int height;


    private int speed;





    public Paddle(int x, int y, int width, int height) {


        this.x = x;


        this.y = y;


        this.width = width;


        this.height = height;


        this.speed = 5;


    }





    public void draw(Graphics g) {


        g.fillRect(x, y, width, height);


    }





    public void moveUp() {


        if (y > 0) {


            y -= speed;


        }


    }





    public void moveDown() {


        if (y < 500) { // Assuming game height is 600


            y += speed;


        }


    }





    public Rectangle getBounds() {


        return new Rectangle(x, y, width, height);


    }


}