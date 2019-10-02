class RGBRotate {
    private int r;
    private int g;
    private int b;

    private String rotate;

    public RGBRotate(int r, int g, int b, String rotate) {
        this.r = r;
        this.g = g;
        this.b = b;

        this.rotate = rotate;
    }

    public int get_r() {
        return r;
    }

    public int get_g() {
        return g;
    }

    public int get_b() {
        return b;
    }

    public String get_rotation() {
        return rotate;
    }
}