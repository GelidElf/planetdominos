package app.gpwars.viewer;

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
// Author: Patrick Paskaris
//
// This class is for the viewer JFrame.

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewGame extends JFrame {
	private ViewerPanel vp;
	
  // There are two ways of launching this viewer.
  //   1. Send command line arguments, sending a playback string over stdin.
  //   3. Just call the constructor with the stuff.
  //   Arguments are:
  //		player1name
  //		player2name
  //		[more_players]
  //   Separated by spaces.
  public static void main(String args[]) {
    String players[] = new String[4];
    for (int i = 1; i <= 4; i++) {
      players[i-1] = "P" + i;
    }
    try {
    StringBuilder playbackString = new StringBuilder();
    InputStreamReader input;

		input = new InputStreamReader(new FileInputStream("src/app/gpwars/alternativeViewerLog.txt"));
	
    BufferedReader reader = new BufferedReader(input);
    String string;
      while((string = reader.readLine()) != null)
        playbackString.append(string);
      ViewGame c = new ViewGame(players, playbackString.toString().trim());
      c.show();
      
    } catch (FileNotFoundException e1) {
  		// TODO Auto-generated catch block
  		e1.printStackTrace();
  	} catch (Exception e) {
      System.err.println("ERROR: game player failed while reading game " +
        "playback string.");
      System.exit(1);
    } 
    
  }

	public ViewGame (String players[], String gameData) {
		try {
			// house keeping
			setTitle("Planet Wars Visualizer");
			setSize(640, 480);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			vp = new ViewerPanel(players, gameData);
			
			add(vp);
		} catch (Exception err) {
			// Add a text handler for VizPanel to display errors or something
			JLabel error = new JLabel("<HTML><STRONG>Error:  Visualizer was unable to correctly parse the game data!</STRONG><BR><BR>"
									  + err.getMessage());
			add(error);
		}
	}
}
