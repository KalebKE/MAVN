/*
 BlinkerButton -- a class within the Machine Artificial Vision Network(Machine Artificial Vision Network).
 Copyright (C) 2012, Kaleb Kircher.

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package util.components;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.SwingWorker;

/**
 * A special JButton that can blink text in different colors.
 * @author Kaleb
 */
public class BlinkerButton extends JButton
{

    private String foregroundColor;
    private boolean blink;

    /**
     * Initialize a Blinker Button.
     * @param text the text for the button
     * @param color the desired color
     */
    public BlinkerButton(String text, String foregroundColor)
    {
        super(text);
        this.foregroundColor = foregroundColor;
    }

    public boolean isBlink()
    {
        return blink;
    }

    public void setBlink(boolean blink)
    {
        this.blink = blink;

        if(blink)
        {
            blinkButton.execute();
        }
    }
    // Task to blink button font when a Transition Matrix has been loaded.
    SwingWorker blinkButton = new SwingWorker<Void, Void>()
    {
        // variable will be incremented and decremented to change color

        int color = 0;
        // boolean flips the color from acending to decending
        boolean increasing = false;

        public Void doInBackground()
        {
            // Get a new timer object
            Timer timer = new Timer();
            // And schedule some TimerTaks
            timer.scheduleAtFixedRate(new Blink(), 0, 20);

            return null;
        }

        // Blinks edit button font
        class Blink extends TimerTask
        {

            public void run()
            {
                if (foregroundColor.equals("Blue"))
                {
                    // Flip from acending to decending
                    if (color == 255 || color == 0)
                    {
                        increasing = !increasing;
                    }
                    // What to do when the color is getting brighter
                    if (increasing)
                    {
                        color++;
                        BlinkerButton.this.setForeground(new Color(0, color / 2, color));
                    }
                    // What to do when the color is getting darker
                    if (!increasing)
                    {
                        color--;
                        BlinkerButton.this.setForeground(new Color(0, color / 2, color));
                    }
                    // When to stop
                    if (!isBlink())
                    {
                        done();
                    }
                }
                if (foregroundColor.equals("Green"))
                {
                    // Flip from acending to decending
                    if (color == 255 || color == 0)
                    {
                        increasing = !increasing;
                    }
                    // What to do when the color is getting brighter
                    if (increasing)
                    {
                        color++;
                        BlinkerButton.this.setForeground(new Color(0, color, 0));
                    }
                    // What to do when the color is getting darker
                    if (!increasing)
                    {
                        color--;
                        BlinkerButton.this.setForeground(new Color(0, color, 0));
                    }
                    // When to stop
                    if (!isBlink())
                    {
                        done();
                    }
                }
                if (foregroundColor.equals("Orange"))
                {
                    // Flip from acending to decending
                    if (color == 255 || color == 0)
                    {
                        increasing = !increasing;
                    }
                    // What to do when the color is getting brighter
                    if (increasing)
                    {
                        color++;
                        BlinkerButton.this.setForeground(new Color(color, color / 2, 0));
                    }
                    // What to do when the color is getting darker
                    if (!increasing)
                    {
                        color--;
                        BlinkerButton.this.setForeground(new Color(color, color / 2, 0));
                    }
                    // When to shut off
                    if (!isBlink())
                    {
                        done();
                    }
                }

                if (foregroundColor.equals("Red"))
                {
                    // Flip from acending to decending
                    if (color == 255 || color == 0)
                    {
                        increasing = !increasing;
                    }
                    // What to do when the color is getting brighter
                    if (increasing)
                    {
                        color++;
                        BlinkerButton.this.setForeground(new Color(255, color / 4, 0));
                    }
                    // What to do when the color is getting darker
                    if (!increasing)
                    {
                        color--;
                        BlinkerButton.this.setForeground(new Color(255, color / 4, 0));
                    }
                    // When to stop
                    if (!isBlink())
                    {
                        done();
                    }
                }
            }
        }

        @Override
        public void done()
        {
            // When done, make the fonts black
            BlinkerButton.this.setForeground(Color.BLACK);
            BlinkerButton.this.setForeground(Color.BLACK);
        }
    };
}
