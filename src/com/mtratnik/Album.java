package com.mtratnik;

import java.util.LinkedList;
import java.util.ListIterator;

public class Album {
    // Create a program that implements a playlist for songs
    // Create a Song class having Title and Duration for a song.
    // The program will have an Album class containing a list of songs.
    // The albums will be stored in an ArrayList
    // Songs from different albums can be added to the playlist and will appear in the list in the order
    // they are added.
    // Once the songs have been added to the playlist, create a menu of options to:-
    // Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.
    // List the songs in the playlist
    // A song must exist in an album before it can be added to the playlist (so you can only play songs that
    // you own).
    // Hint:  To replay a song, consider what happened when we went back and forth from a city before we
    // started tracking the direction we were going.
    // As an optional extra, provide an option to remove the current song from the playlist
    // (hint: listiterator.remove()

    private String albumTitle;
    private LinkedList<Song> songList;

    public Album(String albumTitle) {
        this.albumTitle = albumTitle;
        this.songList = new LinkedList<Song>();
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public LinkedList<Song> getSongList() {
        return songList;
    }

    public void setSongList(LinkedList<Song> songList) {
        this.songList = songList;
    }

    public boolean addSong(String songTitle,
                           String songDuration) {
        ListIterator<Song> songListIterator = songList.listIterator();

        while (songListIterator.hasNext()) {

            if (songTitle.equals(songListIterator.next().getSongTitle())) {
                System.out.println("Song '" + songTitle + "' already exists " +
                        "on album '" + this.albumTitle + "'.");
                return false;
            }
        }

        songListIterator.add(new Song(songTitle, songDuration));
        System.out.println("Song '" + songTitle + "' added to album '" + this.albumTitle + "'.");
        return true;
    }

    public void listSongs(String albumTitle) {
        ListIterator<Song> songListIterator = songList.listIterator();

        while (songListIterator.hasNext()) {
            System.out.println("Song #" + songListIterator.nextIndex() + ": " + songListIterator.next().getSongTitle() + " ---- " + songListIterator.next().getSongDuration());
        }
    }
}
