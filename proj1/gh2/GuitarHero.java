package gh2;

import deque.Deque;
import deque.LinkedListDeque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final double CONCERT_A = 440.0;
    public static final int NOTES = 37;
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


    public static Deque<GuitarString> getGuitarStrings() {
        Deque<GuitarString> guitarStrings = new LinkedListDeque<>();
        for (int i = 0; i < NOTES; i++) {
            double concert = CONCERT_A * Math.pow(2, (i - 24.0) / 12.0);
            guitarStrings.addLast(new GuitarString(concert));
        }
        return guitarStrings;
    }

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        Deque<GuitarString> guitarStrings = getGuitarStrings();
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) == -1) {
                    return;
                }
                int index = keyboard.indexOf(key);
                guitarStrings.get(index).pluck();

            }



            /* compute the superposition of samples */
            int size = guitarStrings.size();
            double sample =0;
            for (int i = 0; i < size; i++) {
                sample += guitarStrings.get(i).sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < size; i++) {
                guitarStrings.get(i).tic();
            }
        }
    }
}
