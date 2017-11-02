package demo;

class BenchmarkRunner {
	   static java.io.PrintStream out = System.out;
	   static java.io.InputStream in = System.in;
	   long startTime = 0;
	   long endTime = 0;
	   long[] timeRecords = null;

	   public static void main(String[] a) {
	      if (a.length<5) {
	    	  System.out.println("gree");
	        out.println("Missing arguments. Usage: ");
	        out.println(
	           "BenchmarkRunner class method warmups runs steps");
	        return;
	      }
	      try {
	      	 String className = a[0];
	      	 String methodName = a[1];
	      	 int numberOfWarmups = Integer.parseInt(a[2]);
	      	 int numberOfRuns = Integer.parseInt(a[3]);
	      	 int numberOfSteps = Integer.parseInt(a[4]);
	     
	         // Warming up the JVM
	         out.println("Are you ready?");
	         in.read(new byte[1]);

	         // Loading the benchmark class and method
	     	 Class testClass = Class.forName(className);
	     	 java.lang.reflect.Method testMethod 
	     	    = testClass.getMethod(methodName, int.class,
	     	    BenchmarkRunner.class);
	         BenchmarkRunner testRunner
	            = new BenchmarkRunner(numberOfRuns);
	         Object testObject = testClass.newInstance();

	         // JIT warmup
	         out.println();
	         out.println("Waking up the JIT compiler...");
	         for (int i=0; i<numberOfWarmups; i++) {
	            Object testResult = 
	            testMethod.invoke(testObject, numberOfSteps, testRunner);
	            out.println("Run: "+i+", Time: "+testRunner.returnTime()
	               +", Test returns: "+testResult);
	         }

	         // Benchmark runs
	         out.println();
	         out.println("Starting benchmark test runs...");
	         for (int i=0; i<numberOfRuns; i++) {
	            Object testResult = 
	            testMethod.invoke(testObject, numberOfSteps, testRunner);
	            testRunner.recordTime(i);
	            out.println("Run: "+i+", Time: "+testRunner.returnTime()
	               +", Test returns: "+testResult);
	         }

	         // Benchmark report
	         out.println();
	         out.println("Benchmark test time report...");
	         testRunner.report(numberOfRuns, numberOfSteps);

	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	   }

	   // Constructor
	   public BenchmarkRunner(int runs) {
	      timeRecords = new long[runs];
	   }

	   // Starting the timer - to be called by test method
	   public void startTimer() {
	      startTime = System.nanoTime();
	   }

	   // Stopping the timer - to be called by test method
	   public void stopTimer() {
	      endTime = System.nanoTime();
	   }

	   // Returning time from the timer
	   public long returnTime() {
	      return endTime - startTime;
	   }

	   // Recording time from the timer
	   public void recordTime(int i) {
	      timeRecords[i] = endTime - startTime;
	   }

	   // Reportting benchmark result
	   public void report(int runs, int steps) {
	      long total = 0;
	      long minimum = Long.MAX_VALUE;
	      long maximum = 0;
	      for (int i=0; i<runs; i++) {
	      	 long t = timeRecords[i];
	      	 total += t;
	      	 if (t>maximum) maximum = t;
	      	 if (t<minimum) minimum = t;
	      }
	      long average = total/runs;
	      out.println("Runs: "+runs+", Ave: "+average/steps
	         +", Min: "+minimum/steps
	         +", Max: "+maximum/steps
	         +" - Per step in nanoseconds");
	      out.println("Runs: "+runs+", Ave: "+average
	         +", Min: "+minimum+", Max: "+maximum
	         +" - All steps in nanoseconds");
	      out.println("Runs: "+runs+", Ave: "+average/1000000
	         +", Min: "+minimum/1000000
	         +", Max: "+maximum/1000000
	         +" - All steps in milliseconds");
	      out.println("Runs: "+runs+", Ave: "+average/1000000000
	         +", Min: "+minimum/1000000000
	         +", Max: "+maximum/1000000000
	         +" - All steps in seconds");
	   }
	   
	   // Constructor needed as a sample benchmark test class
	   public BenchmarkRunner() {
	   }

	   // A sample benchmark test method
	   public static long sampleTest(int steps, BenchmarkRunner runner) {
	      long total = 0;
	      runner.startTimer();
	      for (int i=0; i<steps; i++) {
	         total += i;
	      }
	      runner.stopTimer();
	      return total;
	   }
	}