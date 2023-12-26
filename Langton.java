import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;


class Langston {
    private static RGBRotate[][] grid;
    private static Ant ant;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Expected command line: java " + 
                Langston.class.getSimpleName() + 
                " <width> <height> <7 character sequence of only Ls and Rs>");
            return;
        }

        RGBRotate[] sequence;
        
        try {
            grid = new RGBRotate[Integer.parseInt(args[1])][Integer.parseInt(args[0])];
            ant = new Ant(grid[0].length / 2, grid.length / 2);
            
            String[] characters = args[2].split("");
            Random randomizer = new Random();

            sequence = new RGBRotate[characters.length];
            for (int i = 0; i < sequence.length; i++) {
                boolean exists = false;
                do {
                    RGBRotate add_new = new RGBRotate(randomizer.nextInt(255), randomizer.nextInt(255), randomizer.nextInt(255), characters[i]);
                    exists = Arrays.stream(sequence)
                        .filter(s -> s != null && s.get_r() == add_new.get_r() && s.get_g() == add_new.get_g() && s.get_b() == add_new.get_b())
                        .toArray()
                        .length != 0;
                    if (!exists) {
                        sequence[i] = add_new;
                    }
                } while (exists);
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return;
        }

        long begin = System.nanoTime();
        while (within_bounds()) {
            RGBRotate this_color;
            if (grid[ant.get_y()][ant.get_x()] == null) {
                this_color = sequence[0];
            } else {
                this_color = grid[ant.get_y()][ant.get_x()];
                int next_index = Arrays.asList(sequence).indexOf(this_color) + 1;
                if (next_index >= sequence.length) {
                    next_index = 0;
                }
                this_color = sequence[next_index];
            }

            grid[ant.get_y()][ant.get_x()] = this_color;
            if (this_color.get_rotation().equals("L")) {
                ant.turn_left();
            } else {
                ant.turn_right();
            }
        }
        long end = System.nanoTime();

        System.out.println("Time taken for ant to exit bounds: " + (end - begin) / Math.pow(10, 9) + " seconds");

        BufferedImage img = new BufferedImage(grid[0].length, grid.length, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                RGBRotate this_color = grid[y][x];
                if (this_color == null) {
                    img.setRGB(x, y, new Color(255,255,255).getRGB());
                } else {
                    img.setRGB(x, y, new Color(this_color.get_r(), this_color.get_g(), this_color.get_b()).getRGB());
                }
            }
        }

        try {
            File imgFile = new File(String.format(java.util.Locale.US, "result-%sx%s-%s.jpeg", args[0], args[1], args[2]));
            ImageIO.write(img, "png", imgFile);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private static boolean within_bounds() {
        if (ant.get_x() < 0 || ant.get_x() == grid[0].length) {
            return false;
        }

        if (ant.get_y() < 0 || ant.get_y() == grid.length) {
            return false;
        }

        return true;
    }
}
