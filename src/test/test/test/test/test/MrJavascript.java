package test.test.test.test.test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MrJavascript {

  public static void main(String[] args) {
    /*
     * <script type="text/javascript" src="http://www.cloopen.com/js/jquery-1.8.1.min.js"></script>
     * <script type="text/javascript" src="http://www.cloopen.com/js/voip/swfobject.js"></script>
     * <script type="text/javascript" src="http://www.cloopen.com/js/voip/Cloopen_sandbox.js">
     * </script>
     */
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine se = sem.getEngineByName("javascript");
    try {
//      se.eval("println('111');");
//      String tmpstr = "test string";
//      se.eval(("println('" + tmpstr + "');"));

      se.eval("function sayHello() {"
          + " print('Hello '+strname+'!');return 'my name is '+strname;" + "}");
      Invocable invocableEngine = (Invocable) se;
      se.put("strname", "testname");
      String callbackvalue = (String) invocableEngine.invokeFunction("sayHello");
      System.out.println(callbackvalue);

      se.eval("function sayHello2(strname2) {"
          + " print('Hello '+strname+'!');return 'my name is '+strname2;" + "}");
      callbackvalue = (String) invocableEngine.invokeFunction("sayHello2", "testname2");
      System.out.println(callbackvalue);

    } catch (ScriptException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
  }
}
