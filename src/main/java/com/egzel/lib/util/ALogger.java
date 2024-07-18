package com.egzel.lib.util;

import java.time.LocalTime;


/**
 * Generic logger
 * Instantiate in relevalt class and use
 * @param <T> Any type
 */
public class ALogger<T> {

    public static class TIMER{

        private  long start = System.currentTimeMillis();
        private long end = -1;

        public TIMER startTimer(){
            TIMER t = new TIMER();
            t.start = System.currentTimeMillis();
            return t;
        }

        public void getBenchmark(TIMER t){

            end = System.currentTimeMillis();
            long elapsed = t.end - t.start;

            ALogger<TIMER> LOGGER = new ALogger<>(TIMER.class);
            LOGGER.info("Benchmark:"+elapsed);

        }
        public void getBenchmark(TIMER t, String name){

            end = System.currentTimeMillis();
            long elapsed = t.end - t.start;

            ALogger<TIMER> LOGGER = new ALogger<>(TIMER.class);
            LOGGER.info(name+" Benchmark:"+elapsed);

        }
    }

    public enum LOG_LEVELS{
        TRACE,
        INFO,
        WARNING,
        ERROR,
        FATAL
    }

    private Class<?> clazz;

   private long startTime = System.currentTimeMillis();

    public ALogger(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void setStartTime(){
        startTime = System.currentTimeMillis();
        info("TIMER STARTED");
    }

    public void  getBenchmarh(){
        long endTime =  System.currentTimeMillis();
        long elapsed = (long) startTime-endTime;
        startTime = 0;
       info("BENCHMARK:"+(elapsed)+" ms");

    }


    public Object endApplication(Object message,Throwable trace){

        logWithLevel(LOG_LEVELS.FATAL,message,trace);

        System.exit(-1);

        return null;
    }

    /**
     * Prints log message to consol error
     * @param message message of log
     */
    public void info(Object message, Throwable stack){
        System.out.println(
                String.format(
                        "[%s] [%s] [%s]:[%s]:[%s]:[%s] - [%s]",
                        "lib.util.ALogger-INFO",
                        LocalTime.now().toString(),
                        this.clazz.getSimpleName()+"."+ stack.getStackTrace()[0].getMethodName(),
                        Thread.currentThread().getName(),
                        Thread.currentThread().getId(),
                        Thread.currentThread().getState().name(),
                        message));
    }



    /**
     * Prints log message to consol error
     * @param message message of log
     */
    public void logWithLevel(LOG_LEVELS level, Object message, Throwable stack){
        System.out.println(
                String.format(
                        "[%s] [%s] [%s]:[%s]:[%s]:[%s] - [%s]",
                        "lib.util.ALogger-"+level.toString(),
                        LocalTime.now().toString(),
                        this.clazz.getSimpleName()+"."+ stack.getStackTrace()[0].getMethodName(),
                        Thread.currentThread().getName(),
                        Thread.currentThread().getId(),
                        Thread.currentThread().getState().name(),
                        message));
    }


    /**
     * Prints log message to consol error
     * @param message message of log
     */
    public void info(Object message){
        System.out.println(
                String.format(
                        "[%s] [%s] [%s]:[%s]:[%s]:[%s] - [%s]",
                        "lib.util.ALogger-INFO",
                        LocalTime.now().toString(),
                        this.clazz.getSimpleName(),
                        Thread.currentThread().getName(),
                        Thread.currentThread().getId(),
                        Thread.currentThread().getState().name(),
                        message));
    }

    /**
     * Prints log message to consol error
     * @param message message of log
     */
    public void error(Object message){
        System.out.println(
                String.format(
                        "[%s] [%s] [%s]:[%s]:[%s]:[%s] - [%s]",
                        "lib.util.ALogger-INFO",
                        LocalTime.now().toString(),
                        this.clazz.getSimpleName(),
                        Thread.currentThread().getName(),
                        Thread.currentThread().getId(),
                        Thread.currentThread().getState().name(),
                        message));
    }

    /**
     * Prints log message to consol error
     * @param message message of log
     */
    public void logWithLevel(LOG_LEVELS level, Object message){
        System.out.println(
                String.format(
                        "[%s] [%s] [%s]:[%s]:[%s]:[%s] - [%s]",
                        "lib.util.ALogger-"+level.toString(),
                        LocalTime.now().toString(),
                        this.clazz.getSimpleName(),
                        Thread.currentThread().getName(),
                        Thread.currentThread().getId(),
                        Thread.currentThread().getState().name(),
                        message));
    }

}