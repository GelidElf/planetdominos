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
			try{
				p = (Player) Class.forName("app.planetdominos.engine.bots." + botName).newInstance();
				p.setID(id);
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return p;
	}
}
