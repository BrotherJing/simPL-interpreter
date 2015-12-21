package simpl;

/**
 * Created by Brotherjing on 2015-12-21.
 */
public class Logger {

    public static boolean printLog = true;

    public static void i(Object msg){
        if(printLog)
            System.out.println(msg);
    }

    public static void on(){printLog = true;}

    public static void off(){printLog = false;}

}
