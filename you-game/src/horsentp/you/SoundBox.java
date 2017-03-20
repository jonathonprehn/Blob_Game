/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import bropals.lib.simplegame.io.AssetManager;
import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.sound.SoundEffect;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author Jonathon
 */
public class SoundBox {

    private static SoundBox box;

    public static void createSoundBox(AssetManager assetManager) {
        box = new SoundBox(assetManager);
    }
    
    public static SoundBox getSoundBox() {
        return box;
    }

    private Receiver receiver = null;
    private int counterMinor;
    private int counterMajor;
    private AssetManager assetManager;
    private SoundEffect upgrade;
    private SoundEffect brain;
    private SoundEffect lung;
    private SoundEffect heart;
    private SoundEffect pancreas;
    private SoundEffect grow;
    private SoundEffect die;
    private SoundEffect winState;
    private SoundEffect stomach;

    public SoundBox(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    private final int NOTE_1 = 28;
    private final int NOTE_2 = 31;
    private final int NOTE_3 = 34;
    private final int NOTE_4 = 37;
    private final int NOTE_SHIFT = 10;
    private final int NOTE_NULL = 0;

    public static final int MUSIC_PROGRAM = 57;
    public static final int HEART_PROGRAM = 55;
    public static final int BRAIN_PROGRAM = 53;
    public static final int LUNG_PROGRAM = 51;
    public static final int PANCREAS_PROGRAM = 49;

    public void playGrowSound() {
        if (grow == null) {
            grow = assetManager.getSoundEffect("grow");
        }
        grow.play();
    }

    public void playHeartSound() {
        if (heart == null) {
            heart = assetManager.getSoundEffect("heart");
        }
        heart.play();
    }
    
    public void playBrainSound() {
        if (brain == null) {
            brain = assetManager.getSoundEffect("brain");
        }
        brain.play();
    }
    
    public void playWinStateSound() {
        if (winState == null) {
            winState = assetManager.getSoundEffect("winState");
        }
        winState.play();
    }
    
    public void playLungSound() {
        if (lung == null) {
            lung = assetManager.getSoundEffect("lung");
        }
        lung.play();
    }
    
    public void playPancreasSound() {
        if (pancreas == null) {
            pancreas = assetManager.getSoundEffect("pancreas");
        }
        pancreas.play();
    }
    
    public void playDieSound() {
        if (die == null) {
            die = assetManager.getSoundEffect("die");
        }
        die.play();
    }
    
    public void playStomachSound() {
        if (stomach == null) {
            stomach = assetManager.getSoundEffect("stomach");
        }
        stomach.play();
    }
    
    public void playUpgradeSound() {
        if (upgrade == null) {
            upgrade = assetManager.getSoundEffect("upgrade");
        }
        upgrade.play();
    }

    public void playRandomLowNote() {
        
    }

    public void playSound(int note) {
        try {
            receiver.send(new ShortMessage(ShortMessage.NOTE_ON, 0, note, 127), -1);
        } catch (InvalidMidiDataException ex) {
            System.out.println("Invalid note" + note + ": " + ex + "");
        }
    }

    public void setProgram(int program) {
        try {
            receiver.send(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, program, 0), -1);
        } catch (InvalidMidiDataException ex) {
            System.out.println("Invalid program" + program + ": " + ex + "");
        }
    }

    public void init() {
        try {
            receiver = MidiSystem.getReceiver();
            setProgram(MUSIC_PROGRAM);
        } catch (MidiUnavailableException ex) {
            ErrorLogger.println("Could not get MIDI reciever: " + ex);
        }
        notesSimple = new int[]{
            NOTE_1, NOTE_1, NOTE_1, NOTE_1, NOTE_1, NOTE_1, NOTE_1, NOTE_1,
            NOTE_2, NOTE_2, NOTE_2, NOTE_2, NOTE_2, NOTE_2, NOTE_2, NOTE_2,
            NOTE_1, NOTE_2, NOTE_3,
            NOTE_4, NOTE_4,
            NOTE_3, NOTE_NULL,
            NOTE_4, NOTE_4,
            NOTE_NULL, NOTE_NULL,
            NOTE_2, NOTE_2, NOTE_3, NOTE_NULL,
            NOTE_2, NOTE_2, NOTE_3, NOTE_NULL, NOTE_NULL,
            NOTE_1, NOTE_1, NOTE_NULL, NOTE_4, NOTE_4,
            NOTE_NULL, NOTE_3, NOTE_2, NOTE_1, NOTE_NULL,
            NOTE_1, NOTE_1, NOTE_1, NOTE_1, NOTE_1, NOTE_1, NOTE_1, NOTE_1,
            NOTE_3, NOTE_3, NOTE_3, NOTE_3, NOTE_3, NOTE_3, NOTE_3, NOTE_3,
            NOTE_1, NOTE_2, NOTE_3,
            NOTE_4, NOTE_4,
            NOTE_3, NOTE_NULL,
            NOTE_4, NOTE_4,
            NOTE_NULL, NOTE_NULL,
            NOTE_2, NOTE_2, NOTE_3, NOTE_NULL,
            NOTE_2, NOTE_2, NOTE_3, NOTE_NULL, NOTE_NULL,
            NOTE_1, NOTE_1, NOTE_NULL, NOTE_4, NOTE_4,
            NOTE_NULL, NOTE_3, NOTE_2, NOTE_1, NOTE_NULL,
            NOTE_1 + NOTE_SHIFT, NOTE_1 + NOTE_SHIFT, NOTE_1 + NOTE_SHIFT, NOTE_1 + NOTE_SHIFT, NOTE_1 + NOTE_SHIFT, NOTE_1 + NOTE_SHIFT, NOTE_1 + NOTE_SHIFT, NOTE_1 + NOTE_SHIFT,
            NOTE_2 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT,
            NOTE_1 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_3 + NOTE_SHIFT,
            NOTE_4 + NOTE_SHIFT, NOTE_4 + NOTE_SHIFT,
            NOTE_3 + NOTE_SHIFT, NOTE_NULL,
            NOTE_4 + NOTE_SHIFT, NOTE_4 + NOTE_SHIFT,
            NOTE_NULL, NOTE_NULL,
            NOTE_2 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_3 + NOTE_SHIFT, NOTE_NULL,
            NOTE_2 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_3 + NOTE_SHIFT, NOTE_NULL, NOTE_NULL,
            NOTE_1 + NOTE_SHIFT, NOTE_1 + NOTE_SHIFT, NOTE_NULL, NOTE_4 + NOTE_SHIFT, NOTE_4 + NOTE_SHIFT,
            NOTE_NULL, NOTE_3 + NOTE_SHIFT, NOTE_2 + NOTE_SHIFT, NOTE_1 + NOTE_SHIFT, NOTE_NULL,};
        try {
            notes = new ShortMessage[notesSimple.length][2];
            for (int i = 0; i < notes.length; i++) {
                if (i != 0) {
                    notes[i] = new ShortMessage[]{new ShortMessage(ShortMessage.NOTE_OFF, 0, notesSimple[i - 1], 127), new ShortMessage(ShortMessage.NOTE_ON, 0, notesSimple[i], 127)};
                } else {
                    notes[0] = new ShortMessage[]{new ShortMessage(ShortMessage.NOTE_OFF, 0, notesSimple[notesSimple.length - 1], 127), new ShortMessage(ShortMessage.NOTE_ON, 0, notesSimple[i], 127)};
                }
            }
        } catch (Exception e) {
            System.err.println("Cannot make MIDI music: " + e);
        }
        /*
         try {
         MidiFileWriter mfw = new StandardMidiFileWriter();
         Sequence s = new Sequence(Sequence.SMPTE_24, 100);
         Track t1 = s.createTrack();
         t1.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 57, 0), 0));
         int ticksBetweenNotes = 633;
         for (int i=0; i<notes.length; i++) {
         MidiEvent me1 = new MidiEvent(notes[i][0], i*ticksBetweenNotes);
         MidiEvent me2 = new MidiEvent(notes[i][1], i*ticksBetweenNotes);
         t1.add(me1);
         t1.add(me2);
         }
         mfw.write(s, 0, new File("assets/sounds/music.midi"));
         } catch(InvalidMidiDataException | IOException e) {
         System.err.println("Could not write MIDI file: " + e);
         }
         */
    }

    private int[] notesSimple;

    private ShortMessage[][] notes;

    public void update(int dt) {
        if (receiver != null) {
            counterMinor += dt;
            if (counterMinor > 240) {
                if (notes[counterMajor] != null) {
                    setProgram(MUSIC_PROGRAM);
                    for (int i = 0; i < notes[counterMajor].length; i++) {
                        receiver.send(notes[counterMajor][i], -1);
                    }
                }
                counterMinor = 0;
                counterMajor++;
                if (counterMajor >= notes.length) {
                    counterMajor = 0;
                }
            }
        }
    }
}
