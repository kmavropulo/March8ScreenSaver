package com.screensaver.march8;

import com.screensaver.march8.utils.Line;
import com.screensaver.march8.utils.Point;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static javax.sound.midi.Sequence.PPQ;

/**
 * This @code{March8ScreenSaver} class declares the screen saver, that congratulates women from
 * all the Universe with the Woman's day.
 *
 * @author Konstantin Mavropulo, site temporary disabled
 * {@link <a href=http://www.kmavropulo.ru>kmavropulo</a>}.
 */
public class March8ScreenSaver {
    private static MyDrawingPanel myDrawingPanel;
    private static Sequencer sequencer;
    private static List<Line> lettersContours;
    private int currentVolume = 64;
    private List<List<Object>> rectanglesParameters = new ArrayList<>();

    //Initializes the letters
    static {
        //int startAdj = 80;
        int startAdj = 60;
        //int letterBorderSize = 14;
        int letterBorderSize = 14;
        //int letterWidth = 153;
        int letterWidth = 114;
        //int letterSpacing = letterWidth / 2;
        int letterSpacing = letterWidth / 3;
        //int letterHeight = 203;
        int letterHeight = 164;
        //int heightLevelFirst = 585;
        int heightLevelFirst = 450;
        int heightLevelSecond = heightLevelFirst - letterBorderSize;
        int heightLevelThird = heightLevelFirst - letterHeight / 2 + letterBorderSize / 2;
        int heightLevelForth = heightLevelFirst - letterHeight / 2 - letterBorderSize / 2;
        int heightLevelFifth = heightLevelFirst - letterHeight + letterBorderSize;
        int heightLevelSix = heightLevelFirst - letterHeight;

        lettersContours = asList(
                new Line(
                        new Point(letterWidth - startAdj, heightLevelFirst),
                        new Point(letterWidth - startAdj, heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth - startAdj, heightLevelSix),
                        new Point(letterWidth * 2 - startAdj, heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 2 - startAdj, heightLevelSix),
                        new Point(letterWidth * 2 - startAdj, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 2 - startAdj, heightLevelFifth),
                        new Point(letterWidth + letterBorderSize - startAdj,
                                heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth + letterBorderSize - startAdj,
                                heightLevelFifth),
                        new Point(letterWidth + letterBorderSize - startAdj,
                                heightLevelSecond)
                ),
                new Line(
                        new Point(letterWidth + letterBorderSize - startAdj,
                                heightLevelSecond),
                        new Point(letterWidth * 2 - startAdj, heightLevelSecond)
                ),
                new Line(
                        new Point(letterWidth * 2 - startAdj, heightLevelSecond),
                        new Point(letterWidth * 2 - startAdj, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 2 - startAdj, heightLevelFirst),
                        new Point(letterWidth - startAdj, heightLevelFirst)
                ),

                //8
                new Line(
                        new Point(letterWidth * 3 - startAdj, heightLevelFirst),
                        new Point(letterWidth * 3 - startAdj, heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 3 - startAdj, heightLevelSix),
                        new Point(letterWidth * 4 - startAdj, heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 4 - startAdj, heightLevelSix),
                        new Point(letterWidth * 4 - startAdj, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 4 - startAdj, heightLevelFirst),
                        new Point(letterWidth * 3 - startAdj, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 3 + letterBorderSize - startAdj,
                                heightLevelSecond),
                        new Point(letterWidth * 3 + letterBorderSize - startAdj,
                                heightLevelThird)
                ),
                new Line(
                        new Point(letterWidth * 3 + letterBorderSize - startAdj,
                                heightLevelThird),
                        new Point(letterWidth * 4 - letterBorderSize - startAdj,
                                heightLevelThird)
                ),
                new Line(
                        new Point(letterWidth * 4 - letterBorderSize - startAdj,
                                heightLevelThird),
                        new Point(letterWidth * 4 - letterBorderSize - startAdj,
                                heightLevelSecond)
                ),
                new Line(
                        new Point(letterWidth * 4 - letterBorderSize - startAdj,
                                heightLevelSecond),
                        new Point(letterWidth * 3 + letterBorderSize - startAdj,
                                heightLevelSecond)
                ),
                new Line(
                        new Point(letterWidth * 3 + letterBorderSize - startAdj,
                                heightLevelForth),
                        new Point(letterWidth * 3 + letterBorderSize - startAdj,
                                heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 3 + letterBorderSize - startAdj,
                                heightLevelFifth),
                        new Point(letterWidth * 4 - letterBorderSize - startAdj,
                                heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 4 - letterBorderSize - startAdj,
                                heightLevelFifth),
                        new Point(letterWidth * 4 - letterBorderSize - startAdj,
                                heightLevelForth)
                ),
                new Line(
                        new Point(letterWidth * 4 - letterBorderSize - startAdj,
                                heightLevelForth),
                        new Point(letterWidth * 3 + letterBorderSize - startAdj,
                                heightLevelForth)
                ),

                //M
                new Line(
                        new Point(letterWidth * 5 - startAdj, heightLevelFirst),
                        new Point(letterWidth * 5 - startAdj, heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 5 - startAdj, heightLevelSix),
                        new Point(letterWidth * 6 - startAdj, heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 6 - startAdj, heightLevelSix),
                        new Point(letterWidth * 6 - startAdj, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 6 - startAdj, heightLevelFirst),
                        new Point(letterWidth * 6 - letterBorderSize - startAdj,
                                heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 6 - letterBorderSize - startAdj,
                                heightLevelFirst),
                        new Point(letterWidth * 6 - letterBorderSize - startAdj,
                                heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 6 - letterBorderSize - startAdj,
                                heightLevelFifth),
                        new Point(letterWidth * 6 - letterWidth / 2 + letterBorderSize
                                / 2 - startAdj, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 6 - letterWidth / 2 + letterBorderSize
                                / 2 - startAdj, heightLevelFifth),
                        new Point(letterWidth * 6 - letterWidth / 2 + letterBorderSize
                                / 2 - startAdj, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 6 - letterWidth / 2 + letterBorderSize
                                / 2 - startAdj, heightLevelFirst),
                        new Point(letterWidth * 6 - letterWidth / 2 - letterBorderSize
                                / 2 - startAdj, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 6 - letterWidth / 2 - letterBorderSize
                                / 2 - startAdj, heightLevelFirst),
                        new Point(letterWidth * 6 - letterWidth / 2 - letterBorderSize
                                / 2 - startAdj, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 6 - letterWidth / 2 - letterBorderSize
                                / 2 - startAdj, heightLevelFifth),
                        new Point(letterWidth * 5 + letterBorderSize - startAdj,
                                heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 5 + letterBorderSize - startAdj,
                                heightLevelFifth),
                        new Point(letterWidth * 5 + letterBorderSize - startAdj,
                                heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 5 + letterBorderSize - startAdj,
                                heightLevelFirst),
                        new Point(letterWidth * 5 - startAdj, heightLevelFirst)
                ),

                //A
                new Line(
                        new Point(letterWidth * 6 - startAdj + letterSpacing,
                                heightLevelFirst),
                        new Point(letterWidth * 6 - startAdj + letterSpacing,
                                heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 6 - startAdj + letterSpacing,
                                heightLevelSix),
                        new Point(letterWidth * 7 - startAdj + letterSpacing,
                                heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing,
                                heightLevelSix),
                        new Point(letterWidth * 7 - startAdj + letterSpacing,
                                heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing,
                                heightLevelFirst),
                        new Point(letterWidth * 7 - startAdj + letterSpacing
                                - letterBorderSize, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing
                                - letterBorderSize, heightLevelFirst),
                        new Point(letterWidth * 7 - startAdj + letterSpacing
                                - letterBorderSize, heightLevelThird)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing
                                - letterBorderSize, heightLevelThird),
                        new Point(letterWidth * 6 - startAdj + letterSpacing
                                + letterBorderSize,
                                heightLevelThird)
                ),
                new Line(
                        new Point(letterWidth * 6 - startAdj + letterSpacing
                                + letterBorderSize, heightLevelThird),
                        new Point(letterWidth * 6 - startAdj + letterSpacing
                                + letterBorderSize,
                                heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 6 - startAdj + letterSpacing
                                + letterBorderSize, heightLevelFirst),
                        new Point(letterWidth * 6 - startAdj + letterSpacing,
                                heightLevelFirst)
                ),

                new Line(
                        new Point(letterWidth * 6 - startAdj + letterSpacing
                                + letterBorderSize, heightLevelForth),
                        new Point(letterWidth * 6 - startAdj + letterSpacing
                                + letterBorderSize,

                                heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 6 - startAdj + letterSpacing
                                + letterBorderSize, heightLevelFifth),
                        new Point(letterWidth * 7 - startAdj + letterSpacing
                                - letterBorderSize, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing
                                - letterBorderSize, heightLevelFifth),
                        new Point(letterWidth * 7 - startAdj + letterSpacing
                                - letterBorderSize, heightLevelForth)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing
                                - letterBorderSize, heightLevelForth),
                        new Point(letterWidth * 6 - startAdj + letterSpacing
                                + letterBorderSize, heightLevelForth)
                ),

                //P
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2,
                                heightLevelFirst),
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2,
                                heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2,
                                heightLevelSix),
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 2,
                                heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 2,
                                heightLevelSix),
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 2,
                                heightLevelThird)
                ),
                new Line(
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 2,
                                heightLevelThird),
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2
                                + letterBorderSize, heightLevelThird)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2
                                + letterBorderSize, heightLevelThird),
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2
                                + letterBorderSize, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2
                                + letterBorderSize, heightLevelFirst),
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2,
                                heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2
                                + letterBorderSize, heightLevelForth),
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2
                                + letterBorderSize, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2
                                + letterBorderSize, heightLevelFifth),
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 2
                                - letterBorderSize, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 2
                                - letterBorderSize, heightLevelFifth),
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 2
                                - letterBorderSize,
                                heightLevelForth)
                ),
                new Line(
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 2
                                - letterBorderSize,
                                heightLevelForth),
                        new Point(letterWidth * 7 - startAdj + letterSpacing * 2
                                + letterBorderSize, heightLevelForth)
                ),

                //T
                new Line(
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 3,
                                heightLevelFifth),
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 3,
                                heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 8 - startAdj + letterSpacing * 3,
                                heightLevelSix),
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 3,
                                heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 3,
                                heightLevelSix),
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 3,
                                heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 3,
                                heightLevelFifth),
                        new Point(letterWidth * 9 - startAdj + letterSpacing *
                                3 - letterWidth / 2 + letterBorderSize / 2, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing *
                                3 - letterWidth / 2 + letterBorderSize / 2, heightLevelFifth),
                        new Point(letterWidth * 9 - startAdj + letterSpacing *
                                3 - letterWidth / 2 + letterBorderSize / 2, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing *
                                3 - letterWidth / 2 + letterBorderSize / 2, heightLevelFirst),
                        new Point(letterWidth * 9 - startAdj + letterSpacing *
                                3 - letterWidth / 2 - letterBorderSize / 2, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing *
                                3 - letterWidth / 2 - letterBorderSize / 2, heightLevelFirst),
                        new Point(letterWidth * 9 - startAdj + letterSpacing *
                                3 - letterWidth / 2 - letterBorderSize / 2, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing *
                                3 - letterWidth / 2 - letterBorderSize / 2, heightLevelFifth),
                        new Point(letterWidth * 8 - startAdj + letterSpacing *
                                3 - letterWidth / 2, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 8 - startAdj + letterSpacing *
                                3 - letterWidth / 2, heightLevelFifth),
                        new Point(letterWidth * 8 - startAdj + letterSpacing *
                                3 - letterWidth / 2, heightLevelSix)
                ),

                //A
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4,
                                heightLevelFirst),
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4,
                                heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4,
                                heightLevelSix),
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4,
                                heightLevelSix)
                ),
                new Line(
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4,
                                heightLevelSix),
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4,
                                heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4,
                                heightLevelFirst),
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4 -
                                letterBorderSize, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4 -
                                letterBorderSize, heightLevelFirst),
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4 -
                                letterBorderSize, heightLevelThird)
                ),
                new Line(
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4 -
                                letterBorderSize, heightLevelThird),
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4 +
                                letterBorderSize, heightLevelThird)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4 +
                                letterBorderSize, heightLevelThird),
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4 +
                                letterBorderSize, heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4 +
                                letterBorderSize, heightLevelFirst),
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4,
                                heightLevelFirst)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4 +
                                letterBorderSize, heightLevelForth),
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4 +
                                letterBorderSize, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4 +
                                letterBorderSize, heightLevelFifth),
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4 -
                                letterBorderSize, heightLevelFifth)
                ),
                new Line(
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4 -
                                letterBorderSize, heightLevelFifth),
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4 -
                                letterBorderSize, heightLevelForth)
                ),
                new Line(
                        new Point(letterWidth * 10 - startAdj + letterSpacing * 4 -
                                letterBorderSize, heightLevelForth),
                        new Point(letterWidth * 9 - startAdj + letterSpacing * 4 +
                                letterBorderSize, heightLevelForth)
                )
        );
    }

    private boolean isPaused;

    public static void main(String[] args) throws Exception {
        new March8ScreenSaver().start();
    }

    /**
     * Sets the gui.
     */
    private void setGui() {
        myDrawingPanel = new MyDrawingPanel();
        RestartPlaying restartPlaying = new RestartPlaying();
        ContinuePlaying continuePlaying = new ContinuePlaying();
        StopPlaying stopPlaying = new StopPlaying();
        EnableSound enableSound = new EnableSound();
        MuteSound muteSound = new MuteSound();
        JFrame jFrame = new JFrame("March8ScreenSaver by kmavropulo");

        JMenuBar mp3MenuBar = new JMenuBar();
        JMenu screenSaverMenu = new JMenu("Menu");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem play = new JMenuItem("Play");
        JMenuItem stop = new JMenuItem("Pause");
        JMenuItem soundOn = new JMenuItem("Enable sound");
        JMenuItem soundOff = new JMenuItem("Mute sound");
        JMenuItem author = new JMenuItem("Author");

        restart.addActionListener(restartPlaying);
        play.addActionListener(continuePlaying);
        stop.addActionListener(stopPlaying);
        soundOn.addActionListener(enableSound);
        soundOff.addActionListener(muteSound);
        author.addActionListener(
                (e -> navigatePage(
                        "https://upsa.epam.com/workload/employeeView" +
                                ".do?employeeId=4060741400321290312")));

        screenSaverMenu.add(restart);
        screenSaverMenu.add(play);
        screenSaverMenu.add(stop);
        screenSaverMenu.add(soundOn);
        screenSaverMenu.add(soundOff);
        mp3MenuBar.add(screenSaverMenu);
        screenSaverMenu.add(author);

        jFrame.setJMenuBar(mp3MenuBar);
        jFrame.getContentPane().add(BorderLayout.CENTER, myDrawingPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int width = 1366;
        int height = 768;
        jFrame.setSize(width, height);
        jFrame.setVisible(true);
    }

    /**
     * Starts the screen saver.
     *
     * @throws MidiUnavailableException  see
     *                                   {@link javax.sound.midi.MidiUnavailableException}.
     * @throws InvalidMidiDataException, see
     *                                   {@link javax.sound.midi.MidiUnavailableException}.
     */
    private void start() throws MidiUnavailableException, InvalidMidiDataException,
            InterruptedException {
        setGui();
        sequencer = MidiSystem.getSequencer();
        sequencer.open();
        int[] midiControllerNumber = {127};
        sequencer.addControllerEventListener(myDrawingPanel, midiControllerNumber);

        while (true) {
            while (isPaused) {
                Thread.sleep(50);
            }
            playSequence();
            while (sequencer.isRunning()) {
                Thread.sleep(50);
            }
        }
    }

    /**
     * Starts to play new @code{Sequence}.
     *
     * @throws InvalidMidiDataException see
     *                                  {@link javax.sound.midi.InvalidMidiDataException}.
     */
    private void playSequence() throws InvalidMidiDataException, InterruptedException {
        Sequence sequence = new Sequence(PPQ, 4);

        Track track = sequence.createTrack();
        setTrack(track);

        sequencer.setSequence(sequence);
        int BPM = 6433;
        sequencer.setTempoInBPM(BPM);
        sequencer.setSequence(sequence);

        sequencer.start();
    }

    /**
     * Sets @code{Track}.
     *
     * @param toSet track to set/
     * @throws InvalidMidiDataException see
     *                                  {@link javax.sound.midi.InvalidMidiDataException}.
     */
    private void setTrack(Track toSet) throws InvalidMidiDataException {
        int currentCounterValue;
        int note = 65;
        int channel = 1;

        for (currentCounterValue = 1; currentCounterValue < 133333; currentCounterValue += 4) {
            toSet.add(newSingleMidiEvent(
                    192, channel, 14, 0, 1));
            toSet.add(newSingleMidiEvent(
                    144, channel, randomNote(note), currentVolume,
                    currentCounterValue));
            toSet.add(newSingleMidiEvent(
                    176, channel, 127, 0, currentCounterValue));
            toSet.add(newSingleMidiEvent(
                    128, channel, randomNote(note), currentVolume,
                    currentCounterValue + 2));
        }
    }

    /**
     * Constructs new @code{MidiEvent} with given parameters.
     *
     * @param midiCommand command to set.
     * @param instrument  instrument to set.
     * @param channel     channel to set.
     * @param volume      volume to set.
     * @param tick        to set.
     * @return @code{MidiEvent}
     * @throws InvalidMidiDataException see {@link javax.sound.midi.InvalidMidiDataException}.
     */
    private MidiEvent newSingleMidiEvent(int midiCommand, int channel, int instrument, int
            volume, long tick) throws InvalidMidiDataException {
        return new MidiEvent(new ShortMessage(midiCommand, channel, instrument, volume), tick);
    }

    /**
     * Gets random note.
     *
     * @param max @code{int} value for the note.
     * @return @code{int} note.
     */
    private int randomNote(int max) {
        return (int) (Math.random() * max);
    }

    /**
     * This @code{ContinuePlaying} class declares the @code{ActionListener} implementation.
     */
    public class RestartPlaying implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (isPaused) {
                isPaused = false;
            } else {
                sequencer.stop();
            }
            rectanglesParameters = new ArrayList<>();
        }
    }

    /**
     * This @code{ContinuePlaying} class declares the @code{ActionListener} implementation.
     */
    public class ContinuePlaying implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            isPaused = false;
            //??
            //sequencer.start();
        }
    }

    /**
     * This @code{StopPlaying} class declares the @code{ActionListener} implementation.
     */
    public class StopPlaying implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            isPaused = true;
            sequencer.stop();
        }
    }

    /**
     * This @code{EnableSound} class declares the @code{ActionListener} implementation.
     */
    public class EnableSound implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (currentVolume == 0) {
                currentVolume = 64;
                sequencer.stop();
            }

        }
    }

    /**
     * This @code{MuteSound} class declares the @code{ActionListener} implementation.
     */
    public class MuteSound implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (currentVolume == 64) {
                currentVolume = 0;
                sequencer.stop();
            }
        }
    }

    private void navigatePage(String URI) {
        if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)
                && (Desktop.getDesktop() != null)) {
            try {
                Desktop.getDesktop().browse(new URI(URI));
            } catch (URISyntaxException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This @code{MyDrawingPanel} class declares the @code{ControllerEventListener}
     * implementation.
     */
    class MyDrawingPanel extends JPanel implements ControllerEventListener {
        private boolean eventTestMart;
        //private int xMaxRandomPosition = 1885;
        private int xMaxRandomPosition = 1321;
        //private int yMaxRandomPosition = 1010;
        private int yMaxRandomPosition = 698;
        //private int minRectangleSize = 16;
        private int minRectangleSize = 16;
        //private int adjMaxRectangleSize = 53;
        private int adjMaxRectangleSize = 89;

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (eventTestMart) {
                addRect(rectanglesParameters);
                for (Object anArrayList : rectanglesParameters) {
                    ArrayList arrayListTemp = (ArrayList) anArrayList;
                    g.setColor((Color) arrayListTemp.get(2));
                    g.fillRect((int) arrayListTemp.get(0), (int) arrayListTemp.get(1), (int)
                            arrayListTemp.get(3), (int) arrayListTemp.get(4));
                }
            }
            eventTestMart = false;
        }

        @Override
        public void controlChange(ShortMessage event) {
            eventTestMart = true;
            myDrawingPanel.repaint();
        }

        /**
         * Adds new rectangle to given collection.
         *
         * @param toUpgrade collection to supplement.
         */
        private void addRect(List<List<Object>> toUpgrade) {
            List<Object> arrayListInternal = new ArrayList<>();
            if (rectanglesParameters.size() > 33332) {
                rectanglesParameters = new ArrayList<>();
            }

            int colorRgbParameter = 245;
            int xFirst = 0;
            int xSecond = 0;
            int yFirst = 0;
            int ySecond = 0;

            boolean isIntersects = true;

            while (isIntersects) {
                xFirst = xRandomPosition(xMaxRandomPosition);
                xSecond = xFirst + minRectangleSize + xRandomPosition
                        (xMaxRandomPosition)
                        / adjMaxRectangleSize;
                yFirst = yRandomPosition(yMaxRandomPosition);
                ySecond = yFirst + minRectangleSize + yRandomPosition
                        (yMaxRandomPosition)
                        / adjMaxRectangleSize;

                Line horizontalFirst = new Line(
                        new Point(xFirst, yFirst),
                        new Point(xSecond, yFirst));
                Line verticalFirst = new Line(
                        new Point(xSecond, yFirst),
                        new Point(xSecond, ySecond));
                Line horizontalSecond = new Line(
                        new Point(xSecond, ySecond),
                        new Point(xFirst, ySecond));
                Line verticalSecond = new Line(
                        new Point(xFirst, ySecond),
                        new Point(xFirst, yFirst));

                isIntersects = checkIntersection(horizontalFirst) ||
                        checkIntersection(horizontalSecond) ||
                        checkIntersection(verticalFirst) ||
                        checkIntersection(verticalSecond);
            }

            arrayListInternal.add(xFirst);
            arrayListInternal.add(yFirst);
            arrayListInternal.add(randomColor(colorRgbParameter));
            arrayListInternal.add(xSecond - xFirst);
            arrayListInternal.add(ySecond - yFirst);

            toUpgrade.add(arrayListInternal);
        }

        /**
         * Gets random position by OX axe.
         *
         * @param max maximum @code{int} value for the position.
         * @return @code{int} random position by OX axe.
         */
        private int xRandomPosition(int max) {
            return (int) (Math.random() * max);
        }

        /**
         * Gets random position by OY axe.
         *
         * @param max maximum @code{int} value for the position.
         * @return @code{int} random position by OY axe.
         */
        private int yRandomPosition(int max) {
            return (int) (Math.random() * max);
        }

        /**
         * Gets random @code{Color}.
         *
         * @param max maximum @code{int} value for each of the color parameter (RGB).
         * @return @code{Color} random color.
         */
        private Color randomColor(int max) {
            int red = (int) (Math.random() * max);
            int green = (int) (Math.random() * max);
            int blue = (int) (Math.random() * max);
            return new Color(red, green, blue);
        }

        /**
         * Checks if the given @code{Line} intersects with any from the collection of letters,
         * represented by the collection of lines.
         *
         * @param toCheck @code{Line} to check.
         * @return @code{boolean} true if intersection is exists.
         */
        private boolean checkIntersection(Line toCheck) {
            return lettersContours.stream()
                    .anyMatch(l -> l.intersectionPoint(toCheck, true) != null);
        }
    }
}