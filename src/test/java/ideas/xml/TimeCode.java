package ideas.xml;

public abstract class TimeCode {
    private static final int ITERATIONS = 1000;
    private final String name;
    private long startTime;
    private long endTime;

    protected TimeCode(String name) {
        this.name = name;
    }

    public TimeCode execute() throws Exception {
        startTime = System.nanoTime();
        for (int lp = 0; lp < ITERATIONS; lp += 1) {
            bodyToTime();
        }
        endTime = System.nanoTime();

        return this;
    }

    public TimeCode displayTiming() {
        System.out.println("Timing: " + name);
        System.out.println("  Duration: " + (endTime - startTime) + "ns    (" +     ((endTime-startTime)/1000000) + "ms)");
        System.out.println("  Per Iteration: " + (endTime - startTime) / ITERATIONS + "ns");
        return this;
    }

    public abstract String bodyToTime() throws Exception;
}
