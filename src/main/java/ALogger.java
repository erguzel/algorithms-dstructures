import java.time.LocalTime;


/**
 * Generic logger
 * Instantiate in relevalt class and use
 * @param <T> Any type
 */
public class ALogger<T> {

    public enum LOG_LEVELS{
        TRACE,
        INFO,
        WARNING,
        ERROR,
        FATAL
    }

    private Class<?> clazz;

    double startTime = 0;

    public ALogger(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void setStartTime(){
        startTime = System.currentTimeMillis();
        info("TIMER STARTED");
    }

    public void  getBenchmarh(){
        long endTime =  System.currentTimeMillis();
       info("BENCHMARK:"+(endTime-startTime)+" ms");

    }


    public Object endApplication(String message,Throwable trace){

        logWithLevel(LOG_LEVELS.FATAL,message,trace);

        System.exit(-1);

        return null;
    }

    /**
     * Prints log message to consol error
     * @param message message of log
     */
    public void info(String message, Throwable stack){
        System.out.println(
                String.format(
                        "[%s] [%s] [%s]:[%s]:[%s]:[%s] - [%s]",
                        "ALogger-INFO",
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
    public void logWithLevel(LOG_LEVELS level, String message, Throwable stack){
        System.out.println(
                String.format(
                        "[%s] [%s] [%s]:[%s]:[%s]:[%s] - [%s]",
                        "ALogger-"+level.toString(),
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
    public void info(String message){
        System.out.println(
                String.format(
                        "[%s] [%s] [%s]:[%s]:[%s]:[%s] - [%s]",
                        "ALogger-INFO",
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
    public void logWithLevel(LOG_LEVELS level, String message){
        System.out.println(
                String.format(
                        "[%s] [%s] [%s]:[%s]:[%s]:[%s] - [%s]",
                        "ALogger-"+level.toString(),
                        LocalTime.now().toString(),
                        this.clazz.getSimpleName(),
                        Thread.currentThread().getName(),
                        Thread.currentThread().getId(),
                        Thread.currentThread().getState().name(),
                        message));
    }

}