package com.example.pandemicwatchlist;
// All TV show images used have creative commons license, they are just images of main characters
//  or title since there were very few results without copyright were available
public class TVShow {
    // claim the name, description and image resource
    private String name, description, trailerURL;
    private int imageResID;
    // movies and tv shows will be arrays
    public static final TVShow[] tvShows = {
            new TVShow("Black Clover", "In a world where magic is everything, Asta and Yuno are both found abandoned at a church on the same day. While Yuno is gifted " +
                    "with exceptional magical powers, Asta is the only one in this world without any. Being opposite but good rivals, Yuno and Asta are ready for the hardest of " +
                    "challenges to achieve their common dream: to be the Wizard King.", R.drawable.black_clover, "https://www.youtube.com/watch?v=uaDeobqouGQ"),
            new TVShow("Demon Slayer", "After a demon attack leaves his family slain and his sister cursed, Tanjiro embarks upon a perilous journey to find " +
                    "a cure and avenge those he's lost.", R.drawable.demon_slayer, "https://www.youtube.com/watch?v=VQGCKyvzIM4"),
            new TVShow("Naruto", "Naruto Uzumaki, a mischievous adolescent ninja, struggles as he searches for recognition and " +
                    "dreams of becoming the Hokage, the village's leader and strongest ninja.", R.drawable.naruto, "https://www.youtube.com/watch?v=j2hiC9BmJlQ")
    };

    private TVShow(String name, String description, int imageResID, String trailerURL) {
        this.name = name;
        this.description = description;
        this.imageResID = imageResID;
        this.trailerURL = trailerURL;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getImageResID(){
        return imageResID;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public String toString() {
        return this.name;
    }
}
