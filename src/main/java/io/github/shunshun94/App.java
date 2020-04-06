package io.github.shunshun94;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// https://blog.mirakui.com/entry/20070727/1185559184 をもとに
    	ScriptEngineManager manager = new ScriptEngineManager();
    	ScriptEngine engine = manager.getEngineByName("jruby");
    	try {
			engine.put("msg", "Hello");
			engine.eval("puts msg");
			engine.eval("msg = 'Bye'");
			Object result1 = engine.get("msg");
			System.out.println(result1.getClass().getName() + ": " + result1);

			engine.put("count", 0);
			engine.eval("count += 3");
			engine.eval("puts count");
			engine.eval("count += 4");
			Object result2 = engine.get("count");
			System.out.println(result2.getClass().getName() + ": " + result2);
			System.out.println((Long)result2 + 10);

			// 実行ディレクトリを MyJRuby/src/main/resources/BCDice/src にしている
			engine.eval("$:.unshift File.dirname(__FILE__)");
			engine.eval("require \"./bcdiceCore\"\nrequire \"diceBot/DiceBot\"\nrequire \"diceBot/DiceBotLoader\"");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
    }
}
