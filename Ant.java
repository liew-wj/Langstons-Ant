class Ant {
    private int x;
    private int y;

    private String direction;

    public Ant(int x, int y) {
        this.x = x - 1;
        this.y = y - 1;

        this.direction = "UP";
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }

    public void turn_left() {
        if (direction == "UP") {
            direction = "LEFT";
            x--;
        } else if (direction == "LEFT") {
            direction = "DOWN";
            y++;
        }else if (direction == "DOWN") {
            direction = "RIGHT";
            x++;   
        }else if (direction == "RIGHT") {
            direction = "UP";
            y--;
        }
    }

    public void turn_right() {
        if (direction == "UP") {
            direction = "RIGHT";
            x++;
        }else if (direction == "RIGHT") {
            direction = "DOWN";
            y++;
        }else if (direction == "DOWN") {
            direction = "LEFT";
            x--;   
        }else if (direction == "LEFT") {
            direction = "UP";
            y--;
        }
    }
}