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
			// 実行ディレクトリを MyJRuby/src/main/resources/BCDice/src にしている
			// ソースコードは https://github.com/ysakasin/bcdice-api/blob/master/lib/bcdice_wrap.rb からコピペしたのをいじいじ。
			engine.eval("$:.unshift File.dirname(__FILE__)");
			engine.eval("require \"./bcdiceCore\"\nrequire \"diceBot/DiceBot\"\nrequire \"diceBot/DiceBotLoader\"");
			engine.eval("DICEBOTS = ([DiceBot.new] + DiceBotLoader.collectDiceBots).\r\n" + 
					"    map { |diceBot| [diceBot.id, diceBot] }.\r\n" + 
					"    to_h.\r\n" + 
					"    freeze\r\n\r\n" +
			        "    SYSTEMS = DICEBOTS.keys.\r\n" + 
					"    sort.\r\n" + 
					"    freeze\r\n\r\n" +
					"    NAMES = DICEBOTS.\r\n" + 
					"    map { |gameType, diceBot| {system: diceBot.id, name: diceBot.name} }.\r\n" + 
					"    freeze\r\n" + 
					"");
			engine.eval("dicebot = DICEBOTS['SwordWorld2.0']");
			engine.eval("bcdice = BCDiceMaker.new.newBcDice");
			engine.eval("dicebot.bcdice = bcdice");
			engine.eval("puts dicebot.dice_command('aaaaa', 'SwordWorld2.0')");
			engine.eval("puts dicebot.dice_command('FT', 'SwordWorld2.0')");
			engine.eval("puts dicebot.dice_command('2d6', 'SwordWorld2.0')");
			engine.eval("puts dicebot.dice_command('K20', 'SwordWorld2.0')");
			engine.eval("puts dicebot.dice_command('k20', 'SwordWorld2.0')");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
    }
}
