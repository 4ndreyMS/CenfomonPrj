package com.cenfotec.cenfomon.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public class AudioManager implements Disposable {
    private static AudioManager instance;
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
            instance.preloadAudioAssets();
        }

        return instance;
    }

    public Music currentMusic = null;

    private AssetManager assetManager;
    private String[] soundPaths = new String[] {
            "Audio/breakblock.wav",
            "Audio/bump.wav",
            "Audio/coin.wav",
            "Audio/powerup_use.wav",
            "Audio/powerup_appears.wav",
            "Audio/pipe.wav",
            "Audio/stomp.wav",
            "Audio/kick.wav",
            "Audio/mariodie.wav"
    };

    private String[] musicPaths = new String[0];

    public AudioManager() {
        assetManager = new AssetManager();
    }

    private void preloadAudioAssets() {
        for (int i = 0; i < soundPaths.length; i++) {
            assetManager.load(soundPaths[i], Sound.class);
        }
        assetManager.finishLoading();
    }

    public void playSound(String p_soundPath) {
        Sound foundSound = assetManager.get(p_soundPath, Sound.class);
        if (foundSound != null)
            foundSound.play();
    }

    public void startMusic(String p_musicPath, boolean p_looping) {
        Music foundMusic = assetManager.get(p_musicPath, Music.class);
        if (foundMusic != null) {
            stopMusic();

            foundMusic.setLooping(p_looping);
            foundMusic.play();
            currentMusic = foundMusic;
        }
    }

    public void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
        }
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
