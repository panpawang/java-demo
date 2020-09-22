package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class MapTest {

    static Map hashMap = new HashMap();
    static Map syncHashMap = Collections.synchronizedMap(new HashMap());
    static Map concurrentHashMap = new ConcurrentHashMap();

    @Setup
    public void setup() {
        for (int i = 0; i < 10000; i++) {
            hashMap.put(Integer.toString(i), Integer.toString(i));
            syncHashMap.put(Integer.toString(i), Integer.toString(i));
            concurrentHashMap.put(Integer.toString(i), Integer.toString(i));
        }

    }

    @Benchmark
    public void hashMapGet() {
        hashMap.get("4");
    }

    @Benchmark
    public void syncHashMapGet() {
        syncHashMap.get("4");
    }

    @Benchmark
    public void concurrentHashMapGet() {
        concurrentHashMap.get("4");
    }

    @Benchmark
    public void hashMapSize() {
        hashMap.size();
    }

    @Benchmark
    public void syncHashMapSize() {
        syncHashMap.size();
    }

    @Benchmark
    public void concurrentHashMapSize() {
        concurrentHashMap.size();
    }

    /*
     *
     * a) Via command-line: $ mvn clean install $ java -jar target/benchmarks.jar
     * JMHSample_01
     *
     * JMH generates self-contained JARs, bundling JMH together with it. The runtime
     * options for the JMH are available with "-h": $ java -jar
     * target/benchmarks.jar -h
     *
     * b) Via the Java API: (see the JMH homepage for possible caveats when running
     * from IDE: http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
//        new Thread() {
//            public void run() {
//                while (true) {
//                    if (syncHashMap.size() > 1000) {
//                        syncHashMap.clear();
//                    }
//                    syncHashMap.put(Integer.toString(new Random().nextInt()), "");
//                    if (concurrentHashMap.size() > 1000) {
//                        concurrentHashMap.clear();
//                    }
//                    concurrentHashMap.put(Integer.toString(new Random().nextInt()), "");
//                }
//            }
//        }.start();
        Options opt = new OptionsBuilder().include(MapTest.class.getSimpleName()).forks(1).warmupIterations(5)
                .measurementIterations(5).threads(2).build();
        new Runner(opt).run();
    }
}
