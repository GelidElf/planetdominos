package app.gpwars.engine.bots;

import app.gpwars.engine.Player;

public class BotFactory {
	public static Player makeBot(String botName, int id) {
		Player p = null;
		
		try {
			p = (Player) Class.forName("app.gpwars.engine.bots." + botName).newInstance();
			p.setID(id);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
}
