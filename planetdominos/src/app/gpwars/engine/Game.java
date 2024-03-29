package app.gpwars.engine;

// Copyright 2010 owners of the AI Challenge project
//
// Licensed under the Apache License, Version 2.0 (the "License"); you may not
// use this file except in compliance with the License. You may obtain a copy
// of the License at http://www.apache.org/licenses/LICENSE-2.0 . Unless
// required by applicable law or agreed to in writing, software distributed
// under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
// CONDITIONS OF ANY KIND, either express or implied. See the License for the
// specific language governing permissions and limitations under the License.
//
// Author:      Jeff Cameron (jeff@jpcameron.com)
//
// Stores the game state.

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Game implements Cloneable {
	// Carries out the point-of-view switch operation, so that each player can
	// always assume that he is player number 1. There are three cases.
	// 1. If pov < 0 then no pov switching is being used. Return player_id.
	// 2. If player_id == pov then return 1 so that each player thinks he is
	// player number 1.
	// 3. If player_id == 1 then return pov so that the real player 1 looks
	// like he is player number "pov".
	// 4. Otherwise return player_id, since players other than 1 and pov are
	// unaffected by the pov switch.
	public static int PovSwitch(int pov, int playerID) {
		if (pov < 0) {
			return playerID;
		}
		if (playerID == pov) {
			return 1;
		}
		if (playerID == 1) {
			return pov;
		}
		return playerID;
	}

	private ArrayList<Fleet> fleets;

//	public void WriteLogMessage(String message) {
//		if (logFilename == null) {
//			return;
//		}
//		try {
//			if (logFile == null) {
//				logFile = new BufferedWriter(new FileWriter(logFilename, true));
//			}
//			logFile.write(message);
//			logFile.newLine();
//			logFile.flush();
//		} catch (Exception e) {
//			// whatev
//		}
//	}

	// This is the game playback string. It's a complete description of the
	// game. It can be read by a visualization program to visualize the game.
	private StringBuilder gamePlayback;

	private boolean createOutput;
	
	// Stores a mode identifier which determines how to initialize this object.
	// See the constructor for details.
	private int initMode;

	// The string of map data to parse.
	private String mapData;

	// The filename of the map that this game is being played on.
	private String mapFilename;

	// The maximum length of the game in turns. After this many turns, the game
	// will end, with whoever has the most ships as the winner. If there is no
	// player with the most ships, then the game is a draw.
	private int maxGameLength;

	private int numTurns;

	// Store all the planets and fleets. OMG we wouldn't wanna lose all the
	// planets and fleets, would we!?
	private ArrayList<Planet> planets;

	private Game(Game _g) {
		planets = new ArrayList<Planet>();
		for (Planet p : _g.planets) {
			planets.add((Planet) (p.clone()));
		}
		fleets = new ArrayList<Fleet>();
		for (Fleet f : _g.fleets) {
			fleets.add((Fleet) (f.clone()));
		}
		if (_g.mapFilename != null) {
			mapFilename = new String(_g.mapFilename);
		}
		if (_g.mapData != null) {
			mapData = new String(_g.mapData);
		}
		initMode = _g.initMode;
		if (_g.gamePlayback != null) {
			gamePlayback = new StringBuilder(_g.gamePlayback);
		}
		maxGameLength = _g.maxGameLength;
		numTurns = _g.numTurns;
		createOutput = _g.createOutput;
		// Dont need to init the drawing stuff (it does it itself)
	}

	// There are two modes:
	// * If mode == 0, then s is interpreted as a filename, and the game is
	// initialized by reading map data out of the named file.
	// * If mode == 1, then s is interpreted as a string that contains map
	// data directly. The string is parsed in the same way that the
	// contents of a map file would be.
	// This constructor does not actually initialize the game object. You must
	// always call Init() before the game object will be in any kind of
	// coherent state.
	public Game(String s, int maxGameLength, int mode, boolean createOutput) {
		planets = new ArrayList<Planet>();
		fleets = new ArrayList<Fleet>();
		gamePlayback = new StringBuilder();
		initMode = mode;
		switch (initMode) {
		case 0:
			mapFilename = s;
			break;
		case 1:
			mapData = s;
			break;
		default:
			break;
		}
		this.maxGameLength = maxGameLength;
		numTurns = 0;
		this.createOutput = createOutput;
	}

	public void AddFleet(Fleet f) {
		fleets.add(f);
	}

	@Override
	public Object clone() {
		return new Game(this);
	}

	// Returns the distance between two planets, rounded up to the next highest
	// integer. This is the number of discrete time steps it takes to get
	// between the two planets.
	public int Distance(int sourcePlanet, int destinationPlanet) {
		Planet source = planets.get(sourcePlanet);
		Planet destination = planets.get(destinationPlanet);
		double dx = source.X() - destination.X();
		double dy = source.Y() - destination.Y();
		return (int) Math.ceil(Math.sqrt(dx * dx + dy * dy));
	}
	//Mi (more efficient) version
	public int distance(Planet source, Planet destination) {
		double dx = source.X() - destination.X();
		double dy = source.Y() - destination.Y();
		return (int) Math.ceil(Math.sqrt(dx * dx + dy * dy));
	}

	// Executes one time step.
	// * Planet bonuses are added to non-neutral planets.
	// * Fleets are advanced towards their destinations.
	// * Fleets that arrive at their destination are dealt with.
	public void DoTimeStep() {
		// Add ships to each non-neutral planet according to its growth rate.
		for (Planet p : planets) {
			if (p.Owner() > 0) {
				p.AddShips(p.GrowthRate());
			}
		}
		// Advance all fleets by one time step.
		for (Fleet f : fleets) {
			f.TimeStep();
		}
		// Determine the result of any battles
		for (Planet p : planets) {
			FightBattle(p);
		}

		boolean needcomma = false;
		for (Planet p : planets) {
			if (needcomma) {
				appendToOutput(",");
			}
			appendToOutput(p.Owner());
			appendToOutput(".");
			appendToOutput(p.NumShips());
			needcomma = true;
		}
		for (Fleet f : fleets) {
			if (needcomma) {
				appendToOutput(",");
			}
			appendToOutput(f.Owner());
			appendToOutput(".");
			appendToOutput(f.NumShips());
			appendToOutput(".");
			appendToOutput(f.SourcePlanet());
			appendToOutput(".");
			appendToOutput(f.DestinationPlanet());
			appendToOutput(".");
			appendToOutput(f.TotalTripLength());
			appendToOutput(".");
			appendToOutput(f.TurnsRemaining());
		}
		appendToOutput(":");
		// Check to see if the maximum number of turns has been reached.
		++numTurns;
	}

	private StringBuilder appendToOutput(String obj) {
		if (createOutput){
			return gamePlayback.append(obj);
		}
			return null;
	}

	private StringBuilder appendToOutput(int obj) {
		if (createOutput){
			return gamePlayback.append(obj);
		}
			return null;
	}
	
	// Kicks a player out of the game. This is used in cases where a player
	// tries to give an illegal order or runs over the time limit.
	public void DropPlayer(int playerID) {
		System.out.println("Dropped player" + playerID + " y nº de turnos " + numTurns);
		for (Planet p : planets) {
			if (p.Owner() == playerID) {
				p.Owner(0);
			}
		}
		for (Fleet f : fleets) {
			if (f.Owner() == playerID) {
				f.Kill();
			}
		}
	}

	// Resolves the battle at planet p, if there is one.
	// * Removes all fleets involved in the battle
	// * Sets the number of ships and owner of the planet according the outcome
	private void FightBattle(Planet p) {

		Map<Integer, Integer> participants = new TreeMap<Integer, Integer>();

		participants.put(p.Owner(), p.NumShips());

		Iterator<Fleet> it = fleets.iterator();
		while (it.hasNext()) {
			Fleet f = it.next();
			if (f.TurnsRemaining() <= 0
					&& GetPlanet(f.DestinationPlanet()) == p) {
				if (!participants.containsKey(f.Owner())) {
					participants.put(f.Owner(), f.NumShips());
				} else {
					participants.put(f.Owner(), f.NumShips()
							+ participants.get(f.Owner()));
				}
				it.remove();
			}
		}

		Fleet winner = new Fleet(0, 0);
		Fleet second = new Fleet(0, 0);
		for (Map.Entry<Integer, Integer> f : participants.entrySet()) {
			if (f.getValue() > second.NumShips()) {
				if (f.getValue() > winner.NumShips()) {
					second = winner;
					winner = new Fleet(f.getKey(), f.getValue());
				} else {
					second = new Fleet(f.getKey(), f.getValue());
				}
			}
		}

		if (winner.NumShips() > second.NumShips()) {
			p.NumShips(winner.NumShips() - second.NumShips());
			p.Owner(winner.Owner());
		} else {
			p.NumShips(0);
		}
	}

	// Returns the playback string so far, then clears it.
	// Used for live streaming output
	public String FlushGamePlaybackString() {
		StringBuilder oldGamePlayback = gamePlayback;
		gamePlayback = new StringBuilder();
		return oldGamePlayback.toString();
	}

	// Returns the game playback string. This is a complete record of the game,
	// and can be passed to a visualization program to playback the game.
	public String GamePlaybackString() {
		return gamePlayback.toString();
	}

	// Gets a color for a player (clamped)
	private Color GetColor(int player, ArrayList<Color> colors) {
		if (player > colors.size()) {
			return Color.PINK;
		} else {
			return colors.get(player);
		}
	}

	// Returns the fleet with the given fleet_id. Fleets are numbered starting
	// with 0. There are NumFleets() fleets. fleet_id's are not consistent from
	// one turn to the next.
	public Fleet GetFleet(int fleetID) {
		return fleets.get(fleetID);
	}

	// Returns the planet with the given planet_id. There are NumPlanets()
	// planets. They are numbered starting at 0.
	public Planet GetPlanet(int planetID) {
		return planets.get(planetID);
	}

	private Point getPlanetPos(Planet p, double top, double left, double right,
			double bottom, int width, int height) {
		int x = (int) ((p.X() - left) / (right - left) * width);
		int y = height - (int) ((p.Y() - top) / (bottom - top) * height);
		return new Point(x, y);
	}

	// A planet's inherent radius is its radius before being transformed for
	// rendering. The final rendered radii of all the planets are proportional
	// to their inherent radii. The radii are scaled for maximum aesthetic
	// appeal.
	private double inherentRadius(Planet p) {
		return Math.sqrt(Math.max(1, p.GrowthRate()));
		// return Math.log(p.GrowthRate() + 3.0);
		// return p.GrowthRate();
	}

	// Initializes a game of Planet Wars. Loads the map data from the file
	// specified in the constructor. Returns 1 on success, 0 on failure.
	public int Init() {
		switch (initMode) {
		case 0:
			return LoadMapFromFile(mapFilename);
		case 1:
			return ParseGameState(mapData);
		default:
			return 0;
		}
	}

	// Returns true if the named player owns at least one planet or fleet.
	// Otherwise, the player is deemed to be dead and false is returned.
	public boolean IsAlive(int playerID) {
		for (Planet p : planets) {
			if (p.Owner() == playerID) {
				return true;
			}
		}
		for (Fleet f : fleets) {
			if (f.Owner() == playerID) {
				return true;
			}
		}
		return false;
	}

	public int issueOrder(Player player, Order order) {

		Planet source 		= order.getSourcePlanet();
		Planet destination	= order.getDestinationPlanet();
		int numShips		= order.getNumShips();

		//Check if the order is legal:
		if ( (source.belongsTo(player)) && (numShips <= source.NumShips()) && (numShips > 0) ) {
		//If it is legal:
			source.RemoveShips(numShips);
			int distance = distance(source, destination);
			Fleet f = new Fleet(source.Owner(), numShips, source.PlanetID(), destination.PlanetID(), distance, distance);
			fleets.add(f);
			return 0;
		} else {
		//otherwise:
			DropPlayer(player.getID());
			return -1;
		}
	}


	// Issue an order. This function takes num_ships off the source_planet,
	// puts them into a newly-created fleet, calculates the distance to the
	// destination_planet, and sets the fleet's total trip time to that
	// distance. Checks that the given player_id is allowed to give the given
	// order. If not, the offending player is kicked from the game. If the
	// order was carried out without any issue, and everything is peachy, then
	// 0 is returned. Otherwise, -1 is returned.
	public int IssueOrder(int playerID, int sourcePlanet, int destinationPlanet, int numShips) {
		Planet source = planets.get(sourcePlanet);
		if (source.Owner() != playerID || numShips > source.NumShips()
				|| numShips < 0) {
			/*WriteLogMessage("Dropping player " + playerID
					+ ". source.Owner() = " + source.Owner() + ", playerID = "
					+ playerID + ", numShips = " + numShips
					+ ", source.NumShips() = " + source.NumShips());*/
			DropPlayer(playerID);
			return -1;
		}
		source.RemoveShips(numShips);
		int distance = Distance(sourcePlanet, destinationPlanet);
		Fleet f = new Fleet(source.Owner(), numShips, sourcePlanet,
				destinationPlanet, distance, distance);
		fleets.add(f);
		return 0;
	}

	// Behaves just like the longer form of IssueOrder, but takes a string
	// of the form "source_planet destination_planet num_ships". That is, three
	// integers separated by space characters.
	public int IssueOrder(int playerID, String order) {
		String[] tokens = order.split(" ");
		if (tokens.length != 3) {
			return -1;
		}
		int sourcePlanet = Integer.parseInt(tokens[0]);
		int destinationPlanet = Integer.parseInt(tokens[1]);
		int numShips = Integer.parseInt(tokens[2]);
		return IssueOrder(playerID, sourcePlanet, destinationPlanet, numShips);
	}

	// Loads a map from a test file. The text file contains a description of
	// the starting state of a game. See the project wiki for a description of
	// the file format. It should be called the Planet Wars Point-in-Time
	// format. On success, return 1. On failure, returns 0.
	private int LoadMapFromFile(String mapFilename) {
		StringBuffer s = new StringBuffer();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(mapFilename));
			String line;
			while ((line = in.readLine()) != null) {
				s.append(line);
				s.append("\n");
			}
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				// Fucked.
			}
		}
		return ParseGameState(s.toString());
	}

	// Returns the number of fleets.
	public int NumFleets() {
		return fleets.size();
	}

	// Returns the number of planets. Planets are numbered starting with 0.
	public int NumPlanets() {
		return planets.size();
	}

	// Returns the number of ships that the current player has, either located
	// on planets or in flight.
	public int NumShips(int playerID) {
		int numShips = 0;
		for (Planet p : planets) {
			if (p.Owner() == playerID) {
				numShips += p.NumShips();
			}
		}
		for (Fleet f : fleets) {
			if (f.Owner() == playerID) {
				numShips += f.NumShips();
			}
		}
		return numShips;
	}
	// Parses a game state from a string. On success, returns 1. On failure,
	// returns 0.
	private int ParseGameState(String s) {
		planets.clear();
		fleets.clear();
		int planetID = 0;
		String[] lines = s.split("\n");
		for (int i = 0; i < lines.length; ++i) {
			String line = lines[i];
			int commentBegin = line.indexOf('#');
			if (commentBegin >= 0) {
				line = line.substring(0, commentBegin);
			}
			if (line.trim().length() == 0) {
				continue;
			}
			String[] tokens = line.split(" ");
			if (tokens.length == 0) {
				continue;
			}
			if (tokens[0].equals("P")) {
				if (tokens.length != 6) {
					return 0;
				}
				double x = Double.parseDouble(tokens[1]);
				double y = Double.parseDouble(tokens[2]);
				int owner = Integer.parseInt(tokens[3]);
				int numShips = Integer.parseInt(tokens[4]);
				int growthRate = Integer.parseInt(tokens[5]);
				Planet p = new Planet(planetID++, owner, numShips, growthRate,
						x, y);
				planets.add(p);
				if (gamePlayback.length() > 0) {
					appendToOutput(":");
				}
				appendToOutput("" + x + "," + y + "," + owner + ","
						+ numShips + "," + growthRate);
			} else if (tokens[0].equals("F")) {
				if (tokens.length != 7) {
					return 0;
				}
				int owner = Integer.parseInt(tokens[1]);
				int numShips = Integer.parseInt(tokens[2]);
				int source = Integer.parseInt(tokens[3]);
				int destination = Integer.parseInt(tokens[4]);
				int totalTripLength = Integer.parseInt(tokens[5]);
				int turnsRemaining = Integer.parseInt(tokens[6]);
				Fleet f = new Fleet(owner, numShips, source, destination,
						totalTripLength, turnsRemaining);
				fleets.add(f);
			} else {
				return 0;
			}
		}
		appendToOutput("|");
		return 1;
	}

	// Writes a string which represents the current game state. This string
	// conforms to the Point-in-Time format from the project Wiki.
	//
	// Optionally, you may specify the pov (Point of View) parameter. The pov
	// parameter is a player number. If specified, the player numbers 1 and pov
	// will be swapped in the game state output. This is used when sending the
	// game state to individual players, so that they can always assume that
	// they are player number 1.
	public String PovRepresentation(int pov) {
		StringBuilder s = new StringBuilder();
		for (Planet p : planets) {
			// We can't use String.format here because in certain locales, the ,
			// and . get switched for X and Y (yet just appending them using the
			// default toString methods apparently doesn't switch them?)
			s.append("P " + p.X() + " " + p.Y() + " "
					+ PovSwitch(pov, p.Owner()) + " " + p.NumShips() + " "
					+ p.GrowthRate() + "\n");

		}
		for (Fleet f : fleets) {
			s.append("F " + PovSwitch(pov, f.Owner()) + " " + f.NumShips()
					+ " " + f.SourcePlanet() + " " + f.DestinationPlanet()
					+ " " + f.TotalTripLength() + " " + f.TurnsRemaining()
					+ "\n");

		}
		return s.toString();
	}
	// Renders the current state of the game to a graphics object
	//
	// The offset is a number between 0 and 1 that specifies how far we are
	// past this game state, in units of time. As this parameter varies from
	// 0 to 1, the fleets all move in the forward direction. This is used to
	// fake smooth animation.
	//
	// On success, return an image. If something goes wrong, returns null.
	void Render(int width, // Desired image width
			int height, // Desired image height
			double offset, // Real number between 0 and 1
			BufferedImage bgImage, // Background image
			ArrayList<Color> colors, // Player colors
			Graphics2D g) { // Rendering context
		Font planetFont = new Font("Sans Serif", Font.BOLD, 12);
		Font fleetFont = new Font("Sans serif", Font.BOLD, 18);
		Color bgColor = new Color(188, 189, 172);
		Color textColor = Color.BLACK;
		if (bgImage != null) {
			g.drawImage(bgImage, 0, 0, null);
		}
		// Determine the dimensions of the viewport in game coordinates.
		double top = Double.MAX_VALUE;
		double left = Double.MAX_VALUE;
		double right = Double.MIN_VALUE;
		double bottom = Double.MIN_VALUE;
		for (Planet p : planets) {
			if (p.X() < left) {
				left = p.X();
			}
			if (p.X() > right) {
				right = p.X();
			}
			if (p.Y() > bottom) {
				bottom = p.Y();
			}
			if (p.Y() < top) {
				top = p.Y();
			}
		}
		double xRange = right - left;
		double yRange = bottom - top;
		double paddingFactor = 0.1;
		left -= xRange * paddingFactor;
		right += xRange * paddingFactor;
		top -= yRange * paddingFactor;
		bottom += yRange * paddingFactor;
		Point[] planetPos = new Point[planets.size()];
		g.setFont(planetFont);
		FontMetrics fm = g.getFontMetrics(planetFont);
		// Determine the best scaling factor for the sizes of the planets.
		double minSizeFactor = Double.MAX_VALUE;
		for (int i = 0; i < planets.size(); ++i) {
			for (int j = i + 1; j < planets.size(); ++j) {
				Planet a = planets.get(i);
				Planet b = planets.get(j);
				double dx = b.X() - a.X();
				double dy = b.Y() - a.Y();
				double dist = Math.sqrt(dx * dx + dy * dy);
				double aSize = inherentRadius(a);
				double bSize = inherentRadius(b);
				double sizeFactor = dist / (Math.sqrt(a.GrowthRate()));
				minSizeFactor = Math.min(sizeFactor, minSizeFactor);
			}
		}
		minSizeFactor *= 1.2;
		// Draw the planets.
		int i = 0;
		for (Planet p : planets) {
			Point pos = getPlanetPos(p, top, left, right, bottom, width, height);
			planetPos[i++] = pos;
			int x = pos.x;
			int y = pos.y;
			double size = minSizeFactor * inherentRadius(p);
			int r = (int) Math.min(size / (right - left) * width, size
					/ (bottom - top) * height);
			g.setColor(GetColor(p.Owner(), colors));
			int cx = x - r / 2;
			int cy = y - r / 2;
			g.fillOval(cx, cy, r, r);
			Color c = g.getColor();
			for (int step = 1; step >= 0; step--) {
				g.setColor(g.getColor().brighter());
				g.drawOval(x - (r - step) / 2, y - (r - step) / 2, r - step, r
						- step);
			}
			g.setColor(c);
			for (int step = 0; step < 3; step++) {
				g.drawOval(x - (r + step) / 2, y - (r + step) / 2, r + step, r
						+ step);
				g.setColor(g.getColor().darker());
			}

			java.awt.geom.Rectangle2D bounds = fm.getStringBounds(Integer
					.toString(p.NumShips()), g);
			x -= bounds.getWidth() / 2;
			y += fm.getAscent() / 2;

			g.setColor(textColor);
			g.drawString(Integer.toString(p.NumShips()), x, y);
		}
		// Draw fleets
		g.setFont(fleetFont);
		fm = g.getFontMetrics(fleetFont);
		for (Fleet f : fleets) {
			Point sPos = planetPos[f.SourcePlanet()];
			Point dPos = planetPos[f.DestinationPlanet()];
			double tripProgress = 1.0 - (double) f.TurnsRemaining()
					/ f.TotalTripLength();
			if (tripProgress > 0.99 || tripProgress < 0.01) {
				continue;
			}
			double dx = dPos.x - sPos.x;
			double dy = dPos.y - sPos.y;
			double x = sPos.x + dx * tripProgress;
			double y = sPos.y + dy * tripProgress;
			java.awt.geom.Rectangle2D textBounds = fm.getStringBounds(Integer
					.toString(f.NumShips()), g);
			g.setColor(GetColor(f.Owner(), colors).darker());
			g.drawString(Integer.toString(f.NumShips()), (int) (x - textBounds
					.getWidth() / 2), (int) (y + textBounds.getHeight() / 2));
		}
	}

	// Writes a string which represents the current game state. No point-of-
	// view switching is performed.
	@Override
	public String toString() {
		return PovRepresentation(-1);
	}

	// If the game is not yet over (ie: at least two players have planets or
	// fleets remaining), returns -1. If the game is over (ie: only one player
	// is left) then that player's number is returned. If there are no
	// remaining players, then the game is a draw and 0 is returned.
	public int Winner() {
		Set<Integer> remainingPlayers = new TreeSet<Integer>();
		for (Planet p : planets) {
			remainingPlayers.add(p.Owner());
		}
		for (Fleet f : fleets) {
			remainingPlayers.add(f.Owner());
		}
		remainingPlayers.remove(0);
		if (numTurns > maxGameLength) {
			int leadingPlayer = -1;
			int mostShips = -1;
			for (int playerID : remainingPlayers) {
				int numShips = NumShips(playerID);
				if (numShips == mostShips) {
					leadingPlayer = 0;
				} else if (numShips > mostShips) {
					leadingPlayer = playerID;
					mostShips = numShips;
				}
			}
			return leadingPlayer;
		}
		switch (remainingPlayers.size()) {
		case 0:
			return 0;
		case 1:
			return ((Integer) remainingPlayers.toArray()[0]).intValue();
		default:
			return -1;
		}
	}

	public List<Fleet> MyFleets(int playerID) {
		List<Fleet> r = new ArrayList<Fleet>();
		for (Fleet f : fleets) {
			if (f.Owner() == playerID) {
				r.add(f);
			}
		}
		return r;
	}

	// Return a list of all the planets owned by a player
	public List<Planet> MyPlanets(int playerID) {
		List<Planet> r = new ArrayList<Planet>();
		for (Planet p : planets) {
			if (p.Owner() == playerID) {
				r.add(p);
			}
		}
		return r;
	}

	// Return a list of all the planets owned by the enemy of a player
	public List<Planet> EnemyPlanets(int playerID) {
		List<Planet> r = new ArrayList<Planet>();
		for (Planet p : planets) {
			if ((p.Owner() != playerID) && (p.Owner() != 0)) {
				r.add(p);
			}
		}
		return r;
	}

	// Return a list of all the neutral planets
	public List<Planet> NeutralPlanets() {
		List<Planet> r = new ArrayList<Planet>();
		for (Planet p : planets) {
			if (p.Owner() == 0){
				r.add(p);
			}
		}
		return r;
	}


	// Return a list of all the planets that are not owned by a player.
	// This includes all enemy planets and neutral planets.
	public List<Planet> NotMyPlanets(int planetID) {
		List<Planet> r = new ArrayList<Planet>();
		for (Planet p : planets) {
			if (p.Owner() != planetID) {
				r.add(p);
			}
		}
		return r;
	}

	//Return all the planets
	public List<Planet> Planets() {
		return planets;
	}

	public int gonzaloCaneladaLorenaPrieto_getNumTurns() {
		return numTurns;
	}

	public Set<Integer> gonzaloCaneladaLorenaPrieto_getNumPlayersFromPlanets() {
		Set<Integer> planetOwners = new HashSet<Integer>();
		for (Planet p :planets){
			planetOwners.add(p.Owner());
		}
		return planetOwners;
	}

	public Set<Integer> gonzaloCaneladaLorenaPrieto_getNumPlayersFromFleets() {
		Set<Integer> fleetOwners = new HashSet<Integer>();
		for (Fleet f :fleets){
			fleetOwners.add(f.Owner());
		}
		return fleetOwners;
	}

	public Set<Integer> gonzaloCaneladaLorenaPrieto_getNumPlayersFromShips() {
		Set<Integer> shipOwners = new HashSet<Integer>();
		for (Planet p :planets){
			shipOwners.add(p.Owner());
		}
		for (Fleet f :fleets){
			shipOwners.add(f.Owner());
		}
		return shipOwners;
	}

}