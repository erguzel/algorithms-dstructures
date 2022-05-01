package lib.util.exception;

import lib.util.ALogger;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseException extends RuntimeException {

    private ALogger<BaseException> LOGGER = new ALogger<>(BaseException.class);
    private final LinkedHashMap<Object, Object> _exceptionData = new LinkedHashMap<>();
    private boolean _killApp;
    private boolean _throwIt;
    private boolean _logIt;

    public BaseException(String message, Throwable cause, boolean _killApp, boolean _throwIt, boolean _logIt) {
        super(message, cause);
        this._killApp = _killApp;
        this._throwIt = _throwIt;
        this._logIt = _logIt;
        LOGGER = new ALogger<>(this.getClass());
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
//        this._killApp = _killApp;
//        this._throwIt = _throwIt;
//        this._logIt = _logIt;
        LOGGER = new ALogger<>(this.getClass());
    }

    public BaseException killApp(){
        this._killApp = true;
        return this;
    }

    public BaseException throwIt(){
        this._throwIt = true;
        return this;
    }

    public BaseException logIt(){
        this._logIt = true;
        return this;
    }

    public Map<Object,Object> getData(){
        return this._exceptionData;
    }

    public BaseException AddTada(Map.Entry<?, ?> dataPair) {
        this._exceptionData.put(dataPair.getKey(), dataPair.getValue());
        return this;
    }

    public BaseException AddDataPair(Object key, Object value) {
        this._exceptionData.put(key, value);
        return this;
    }

    private void Log() {

        boolean nullCause = this.getCause() == null;
        boolean logData = this._exceptionData.size()>0;

        LOGGER.error(
                String.format(
                        "\n-----START-----%1$s-----START-----\n"+
                                "\t - Message: %2$s\n"+
                                "\t - InnerType: %3$s\n" +
                                "\t - InnerMessage: %4$s\n" +
                                "\t - lastStackTraceElement: %5$s\n"+
                                "\t - StackTrace: \n%6$s\n"+
                                "\t - InnerStackTrace: \n%7$s\n"+
                                "\t - CUSTOMDATA:\n" +
                                "\t  %8$s\n"+
                                "--------END-----%1$s--------\t%9$s",
                        this.getClass().getSimpleName(),
                        this.getMessage(),
                        nullCause?"NONE":this.getCause().getClass().getSimpleName(),
                        nullCause?"NONE":this.getCause().getMessage(),
                        this.getStackTrace()[0].toString(),
                        //  this.getStackTrace()[this.getStackTrace().length - 1].toString(),
                        Arrays.stream(this.getStackTrace()).map(a -> "\t\t" + a.toString()).collect(Collectors.joining("\n")),
                        nullCause?"\t\tNONE":
                                Arrays.stream(this.getCause().getStackTrace()).map(a -> "\t\t" + a.toString()).collect(Collectors.joining("\n")),
                        logData?
                                this._exceptionData.entrySet().stream().map(a -> "\t\t" + a.getKey() + ": " + a.getValue()).collect(Collectors.joining("\n\t"))+"\n":"NONE",
                        _killApp?"Terminating application":""
                )
        );

    }
    public void Act() throws BaseException {
        if (this._logIt) {
            this.Log();
        }
        if (this._killApp) {
            System.exit(0);
        }
        if (this._throwIt)
            throw this;
    }
    public void Act(boolean withoutThrowSignature){
        if (this._logIt) {
            this.Log();
        }
        if (this._killApp) {
            System.exit(0);
        }
    }

    public static void Handle(String message,
                              boolean killApp,
                              boolean logIt,
                              boolean throwIt, Throwable t) throws BaseException {

        BaseException em = null;
        boolean shouldPatch = t!=null &&
                (t.getClass().getSuperclass() == BaseException.class
                        || t.getClass() == BaseException.class);


        if (shouldPatch) {
            em = (BaseException) t;
            em._killApp = killApp;
            em._logIt = logIt;
            em._throwIt = throwIt;

        } else {
            em = new BaseException(message, t, killApp, throwIt, logIt);
        }


        em.Act();
    }


    public static void HandleSlient(String message,
                                    boolean killApp,
                                    boolean logIt, Throwable t) {

        BaseException em = null;
        boolean shouldPatch = t!=null &&
                (t.getClass().getSuperclass() == BaseException.class
                        || t.getClass() == BaseException.class);


        if (shouldPatch) {
            em = (BaseException) t;
            em._killApp = killApp;
            em._logIt = logIt;
            em._throwIt = false;

        } else {
            em = new BaseException(message, t, killApp, false, logIt);
        }
        em.Act(true);
    }

}
