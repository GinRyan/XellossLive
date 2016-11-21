package org.xellossryan.playerlib.widgets;

/**
 * MediaPlayerController Event
 */
public interface MediaPlayerControlEvent {
    /**
     * run code on start button pressed
     */
    void start();

    /**
     * run code on pause button pressed
     */
    void pause();

    /**
     * provide the time during of the media file.
     * @return
     */
    int getDuration();

    /**
     * provide current position millis.
     * @return
     */
    int getCurrentPosition();

    /**
     * seek to specified time
     * @param pos
     */
    void seekTo(int pos);

    /**
     * playing state
     * @return
     */
    boolean isPlaying();

    /**
     * file buffered percentage
     * @return
     */
    int getBufferPercentage();

    boolean canPause();

    boolean canSeekBackward();

    boolean canSeekForward();

    /**
     * Get the audio session id for the player used by this VideoView. This can be used to
     * apply audio effects to the audio track of a video.
     *
     * @return The audio session, or 0 if there was an error.
     */
    int getAudioSessionId();
}