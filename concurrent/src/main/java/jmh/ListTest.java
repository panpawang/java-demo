package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class ListTest {

	CopyOnWriteArrayList smallCopyOnWriteList = new CopyOnWriteArrayList();
	ConcurrentLinkedQueue smallConcurrentList = new ConcurrentLinkedQueue();
	CopyOnWriteArrayList bigCopyOnWriteList = new CopyOnWriteArrayList();
	ConcurrentLinkedQueue bigConcurrentList = new ConcurrentLinkedQueue();

	@Setup
	public void setup() {
		for (int i = 0; i < 10; i++) {
			smallCopyOnWriteList.add(new Object());
			smallConcurrentList.add(new Object());
		}

		for (int i = 0; i < 1000; i++) {
			bigCopyOnWriteList.add(new Object());
			bigCopyOnWriteList.add(new Object());
		}
	}

	@Benchmark
	public void copyOnWriteGet() {
		smallCopyOnWriteList.get(0);
	}

	@Benchmark
	public void copyOnWriteSize() {
		smallCopyOnWriteList.size();
	}

	@Benchmark
	public void concurrentListGet() {
		smallConcurrentList.peek();
	}

	@Benchmark
	public void concurrentListSize() {
		smallConcurrentList.size();
	}

	@Benchmark
	public void smallCopyOnWriteWrite() {
		smallCopyOnWriteList.add(new Object());
		smallCopyOnWriteList.remove(0);
	}

	@Benchmark
	public void smallConcurrentListWrite() {
		smallConcurrentList.add(new Object());
		smallConcurrentList.remove(0);
	}

	@Benchmark
	public void bigCopyOnWriteWrite() {
		bigCopyOnWriteList.add(new Object());
		bigCopyOnWriteList.remove(0);
	}

	@Benchmark
	public void bigConcurrentListWrite() {
		bigConcurrentList.offer(new Object());
		bigConcurrentList.remove(0);
	}
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(ListTest.class.getSimpleName()).forks(1).warmupIterations(5)
				.measurementIterations(5).threads(4).build();
		new Runner(opt).run();
	}
}
