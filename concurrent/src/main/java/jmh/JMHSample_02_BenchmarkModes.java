package jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class JMHSample_02_BenchmarkModes {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * Mode.AverageTime measures the average execution time, and it does it in the
     * way similar to Mode.Throughput.
     *
     * Some might say it is the reciprocal throughput, and it really is. There are
     * workloads where measuring times is more convenient though.
     */

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAvgTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * Mode.SampleTime samples the execution time. With this mode, we are still
     * running the method in a time-bound iteration, but instead of measuring the
     * total time, we measure the time spent in *some* of the benchmark method
     * calls.
     *
     * This allows us to infer the distributions, percentiles, etc.
     *
     * JMH also tries to auto-adjust sampling frequency: if the method is long
     * enough, you will end up capturing all the samples.
     */
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSamples() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * Mode.SingleShotTime measures the single method invocation time. As the
     * Javadoc suggests, we do only the single benchmark method invocation. The
     * iteration time is meaningless in this mode: as soon as benchmark method
     * stops, the iteration is over.
     *
     * This mode is useful to do cold startup tests, when you specifically do not
     * want to call the benchmark method continuously.
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSingleShot() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * We can also ask for multiple benchmark modes at once. All the tests above can
     * be replaced with just a single test like this:
     */
    @Benchmark
    @BenchmarkMode({ Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime })
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureMultiple() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * Or even...
     */

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * ============================== HOW TO RUN THIS TEST:
     * ====================================
     *
     * You are expected to see the different run modes for the same benchmark. Note
     * the units are different, scores are consistent with each other.
     *
     * You can run this test:
     *
     * a) Via the command line: $ mvn clean install $ java -jar
     * target/benchmarks.jar JMHSample_02 -f 1 (we requested a single fork; there
     * are also other options, see -h)
     *
     * b) Via the Java API: (see the JMH homepage for possible caveats when running
     * from IDE: http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JMHSample_02_BenchmarkModes.class.getSimpleName()).forks(1).build();

        new Runner(opt).run();
    }

}