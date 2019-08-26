package com.mtratnik;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Album> albumArrayList  = new ArrayList<Album>();
    private static LinkedList<Song> playlist = new LinkedList<Song>();
//    private static Album testAlbum = new Album("testAlbum");
//    private static Album testAlbum2 = new Album("testAlbum2");

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

//        addNewAlbum("testAlbum");
        addSongToAlbum("album1", "song11", "123");
        addSongToAlbum("album1", "song12", "123");
        addSongToAlbum("album1", "song13", "123");
        addSongToAlbum("album2", "song21", "456");
        addSongToAlbum("album2", "song22", "456");
        addSongToAlbum("album2", "song23", "456");
        addSongToAlbum("album3", "song31", "789");
        addSongToAlbum("album3", "song32", "789");
        addSongToAlbum("album3", "song33", "789");

        addToPlaylist("album1", "song11");
        addToPlaylist("testAlbum", "testAlbumSong5");
        addToPlaylist("album1", "song13");
        addToPlaylist("album3", "song32");
        addToPlaylist("testAlbum", "testAlbumSong5");
        listPlaylist();
        removeFromPlaylist("song13");
        addToPlaylist("album2", "song22");
        listPlaylist();
        playFromBeginning();

    }

    private static void options() {
        System.out.println("1 - Add new album");
        System.out.println("2 - Add a song to existing album");
        System.out.println("3 - List all albums");
        System.out.println("4 - Add song to playlist");
        System.out.println("5 - Remove song from playlist");
        System.out.println("6 - Play from the beginning");
        System.out.println("7 - Replay the current song");
        System.out.println("8 - Skip forward to the next song");
        System.out.println("9 - Skip backwards to the previous song");
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
            System.out.println("'" + songTitle + "' -- '" + songDuration + "'" +
                    " added " +
                    "to Album '" + albumTitle + "'.");
            return true;
        } else {
            Album album = new Album(albumTitle);
            album.addSong(songTitle, songDuration);
            albumArrayList.add(album);
            System.out.println("'" + songTitle + "' -- '" + songDuration + "'" +
                    " added " +
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

    private static void addToPlaylist(String albumTitle, String songTitle) {
        if (getAlbumIndex(albumTitle) < 0) {
            System.out.println("Album '" + albumTitle + "' not found in your collection");
        } else {
            Song song =
                    albumArrayList.get(getAlbumIndex(albumTitle)).getSong(songTitle);

            if (song == null) {
                System.out.println("Song is not included in the album");
            } else {
                playlist.add(song);
                System.out.println(song.getSongTitle() + " added to playlist.");
            }
        }
    }

    private static void removeFromPlaylist(String songTitle) {
        ListIterator<Song> playlistIterator = playlist.listIterator();

        while (playlistIterator.hasNext()) {

            if (songTitle.equals(playlistIterator.next().getSongTitle())) {
                playlistIterator.remove();
            }
        }
    }


    // Play From Beginning option (replay, skip forward, skip backwards, list
    // songs in playlist, quit playlist
    private static void playFromBeginning() {
        ListIterator<Song> playlistIterator = playlist.listIterator();

        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty. Please add songs.");
        } else {
            System.out.println("Now playing '" + playlistIterator.next().getSongTitle() +
                    "'");
        }

        System.out.println("Please enter\n1: List the songs in playlist\n2: " +
                "Skip " +
                "forward to the next song\n3: Skip backwards to the " +
                "previous song\n4: Replay the song\n5: Quit");

        boolean operation = true;
        boolean goingForward = true;

        while (operation) {

            int selection = scanner.nextInt();
            scanner.nextLine();

            switch (selection) {
                case 1:
                    listPlaylist();
                    break;
                case 2:
                    if (playlistIterator.hasNext()) {
                        if (goingForward) {
                            System.out.println("Now playing " + playlistIterator.next().getSongTitle());
                        } else {
                            playlistIterator.next();
                            System.out.println("Now playing " + playlistIterator.next().getSongTitle());
                        }
                    } else {
                        System.out.println("No more songs in the playlist");
                    }
                    goingForward = true;
                    break;
                case 3:
                    if (playlistIterator.hasPrevious()) {
                        if (goingForward) {
                            playlistIterator.previous();
                            System.out.println("Now playing " + playlistIterator.previous().getSongTitle());
                        } else {
                            System.out.println("Now playing " + playlistIterator.previous().getSongTitle());
                        }
                    } else {
                        System.out.println("You are playing 1st song on the " +
                                "playlist");
                    }
                    goingForward = false;
                    break;
                case 4:
                    if (playlistIterator.hasPrevious()) {
                        if (goingForward) {
                            playlistIterator.previous();
                            System.out.println("Now playing " + playlistIterator.next().getSongTitle());
                        } else {
                            System.out.println("Now playing " + playlistIterator.next().getSongTitle());
                        }
                    } else {
                        System.out.println("Now playing " + playlistIterator.next().getSongTitle());
                    }
                    break;
                case 5:
                    System.out.println("Quiting playlist operation");
                    operation = false;
            }
        }
    }

    private static void listPlaylist() {
        ListIterator<Song> playlistIterator = playlist.listIterator();

        while (playlistIterator.hasNext()) {
            System.out.println(playlistIterator.next().getSongInfo());
        }
    }
}
