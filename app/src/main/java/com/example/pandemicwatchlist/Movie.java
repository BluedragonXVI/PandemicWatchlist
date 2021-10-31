package com.example.pandemicwatchlist;
// Movie images were all found with creative commons licenses
public class Movie {
    // claim the name, description and image resource
    private String name, description, trailerURL;
    private int imageResID;
    // movies and tv shows will be arrays
    public static final Movie[] movies = {
            new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given " +
                    "the inverse task of planting an idea into the mind of a C.E.O.", R.drawable.inception, "https://www.youtube.com/watch?v=YoHD9XEInc0"),
            new Movie("Harry Potter and the Chamber of Secrets", "An ancient prophecy seems to be coming true when a mysterious " +
                    "presence begins stalking the corridors of a school of magic and leaving its victims paralyzed.", R.drawable.chamberofsecrets, "https://www.youtube.com/watch?v=1bq0qff4iF8"),
            new Movie("Breaking Dawn Part 2", "After the birth of Renesmee/Nessie, the Cullens gather other vampire clans in order to protect " +
                    "the child from a false allegation that puts the family in front of the Volturi.", R.drawable.breakingdawnpart2, "https://www.youtube.com/watch?v=ti0H-bvMi3I")
    };

    private Movie(String name, String description, int imageResID, String trailerURL) {
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
