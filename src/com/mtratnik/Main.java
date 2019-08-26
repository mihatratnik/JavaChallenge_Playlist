package com.mtratnik;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Album> albumArrayList  = new ArrayList<Album>();
    private static Album testAlbum = new Album("testAlbum");

    public static void main(String[] args) {
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

        testAlbum.addSong("testAlbumSong1", "123");
        testAlbum.addSong("testAlbumSong2", "123");
        testAlbum.addSong("testAlbumSong3", "123");
        testAlbum.addSong("testAlbumSong4", "123");
        testAlbum.listSongs();

//        addSongToAlbum("album1", "song11", "123");
//        addSongToAlbum("album1", "song12", "123");
//        addSongToAlbum("album1", "song13", "123");
//        addSongToAlbum("album2", "song21", "456");
//        addSongToAlbum("album2", "song22", "456");
//        addSongToAlbum("album2", "song23", "456");
//        addSongToAlbum("album3", "song31", "789");
//        addSongToAlbum("album3", "song32", "789");
//        addSongToAlbum("album3", "song33", "789");
//        listAlbums();


        boolean operation = true;




    }

    private static void options() {
        System.out.println("1 - Add new album");
        System.out.println("2 - Add a song to existing album");
        System.out.println("3 - List all albums");
        System.out.println("4 - Add song to playlist");
        System.out.println("5 - Remove song from playlist");
        System.out.println("6 - Play");
    }

    private static boolean addNewAlbum(String albumTitle) {
        if (albumArrayList.isEmpty()) {
            albumArrayList.add(new Album(albumTitle));
            System.out.println("Album " + albumTitle + " added to " +
                    "your " +
                    "collection");;
            // TODO: 24. 08. 19 addSong method
            return true;
        } else {
            for (int i = 0; i < albumArrayList.size(); i++) {

                if (!albumTitle.equals(albumArrayList.get(i).getAlbumTitle())) {
                    albumArrayList.add(new Album(albumTitle));
                    System.out.println("Album " + albumTitle + " added to " +
                            "your " +
                            "collection");
                    // TODO: 24. 08. 19 addSong method
                    return true;
                } else {
                    System.out.println("Album is already in your collection.\n" +
                            "Would you like to add a song (Yes or No)?");
                    // TODO: 24. 08. 19 addSong method
                }
            }
        }

        return false;
    }

    private static boolean addSongToAlbum(String albumTitle, String songTitle,
                                          String songDuration) {
        if (albumArrayList.isEmpty()) {
            addNewAlbum(albumTitle);
        }

        if (getAlbumIndex(albumTitle) >= 0) {
            Album album = albumArrayList.get(getAlbumIndex(albumTitle));
            album.addSong(songTitle, songDuration);
            System.out.println(songTitle + " -- " + songDuration + " added " +
                    "to Album '" + albumTitle + "'.");
            return true;
        } else {
            Album album = new Album(albumTitle);
            album.addSong(songTitle, songDuration);
            albumArrayList.add(album);
            System.out.println(songTitle + " -- " + songDuration + " added " +
                    "to Album '" + albumTitle + "'.");
            return true;
        }
    }

    private static void listAlbums() {
        for (int i = 0; i < albumArrayList.size(); i++) {
            System.out.println("Album no. " + (i+1) + " is " + albumArrayList.get(i).getAlbumTitle());
        }
    }

    private static void listSong(String albumTitle) {
        if (albumArrayList.isEmpty() || getAlbumIndex(albumTitle) < 0) {
            System.out.println("Album does not exist in your collection.");
        } else {
            Album album = albumArrayList.get(getAlbumIndex(albumTitle));
            album.listSongs();
        }
    }

    private static int getAlbumIndex(String albumTitle) {
        for (int i = 0; i < albumArrayList.size(); i++) {
            if (albumTitle.equals(albumArrayList.get(i).getAlbumTitle())) {
                return i;
            }
        }

        return -1;
    }
}
