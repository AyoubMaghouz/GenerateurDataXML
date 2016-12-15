package timer;

/**
 * Created by ayoubmaghouz on 30/11/16.
 */
public class Timer {

    long start, end, temps_dexec;

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getTemps_dexec_ns() {
        return temps_dexec;
    }

    public void start() {
        this.start = System.nanoTime();
    }

    public void stop() {
        this.end = System.nanoTime();
    }

    public String getExecutionTime() {
        temps_dexec = end - start;
        int ns = (int) (temps_dexec % 1000);
        int mics = (int) (temps_dexec / 1000) % 1000;
        int ms = (int) (temps_dexec / 1000000) % 1000;
        int s = (int) (temps_dexec / 1000000000) % 1000;
        return "" + s + "s " + ms + "ms " + mics + "mics " + ns + "ns";
    }
}
