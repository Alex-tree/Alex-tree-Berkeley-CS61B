package HW1.es.datastructur.synthesizer;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class Harp {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public Harp(double frequency) {
        int cap=(int)Math.round(SR/frequency);
        buffer = new ArrayRingBuffer<Double>(cap*2);
        for (int i =0 ;i < buffer.capacity();i++)
            buffer.enqueue(0.0);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < buffer.capacity();i++){
            buffer.dequeue();
            buffer.enqueue(Math.random()-0.5);
        }
        //  Dequeue everything in buffer, and replace with random numbers
        //  between -0.5 and 0.5. You can get such a number by using:
        //  double r = Math.random() - 0.5;
        //
        //  Make sure that your random numbers are different from each
        //  other.
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double first=buffer.dequeue();
        double avg=-(first+buffer.peek())/2*DECAY;
        buffer.enqueue(avg);
        //  Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
