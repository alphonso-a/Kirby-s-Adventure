import java.util.HashMap;
import javax.sound.sampled.*;
import java.io.File;


public final class SoundManager {
    private static SoundManager instance = null;

    HashMap<String, Clip> clips;

    private SoundManager(){
    	clips = new HashMap<String, Clip>();
		
		Clip clip;
		
		clip = loadClip("sounds/forest.wav");
		clips.put("forest", clip);
        
        clip = loadClip("sounds/cave.wav");
		clips.put("cave", clip);
		
		clip = loadClip("sounds/you_win.wav");
		clips.put("win", clip);
		
    }

    public static SoundManager getInstance(){

        if(instance == null)
            instance = new SoundManager();

        return instance;

    }

    public Clip getClip(String title){

        return clips.get(title);

    }

    public Clip loadClip(String fileString){

        AudioInputStream audioInput;
        Clip clip = null;

        try{

            File file = new File(fileString);
            audioInput = AudioSystem.getAudioInputStream(file.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioInput);

        }catch(Exception e){}

        return clip;

    }

    public void playClip(String title, boolean repeat){
        
        Clip clip = getClip(title);

        if(clip != null){
            clip.setFramePosition(0);
            if(repeat)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            else
                clip.start();
        }

    }

    public void stopClip(String title){
        
        Clip clip = getClip(title);

        if(clip != null)
            clip.stop();
            
    }
}
