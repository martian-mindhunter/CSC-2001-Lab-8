import java.util.Random;

public record Spot(String name, double longitude, double latitude) implements Comparable<Spot>{

    // Generates a Spot with a randomized name, latitude, and longitude
    public static Spot randomSpot(){

        Random random = new Random();

        char[] letters = new char[]{
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
        };
        int randomInt = 0;
        String word = "";
        for(int i = 0; i < 4; i++){
            randomInt = random.nextInt(0,52);
            word += letters[randomInt];
        }
//        System.out.println(word);

        double randomLon = random.nextDouble(-180.0, 180.0);
        double tempLat = random.nextDouble(-1, Math.nextUp(1));
        double randomLat = Math.toDegrees(Math.asin(tempLat));
//        System.out.println(randomLat);
//        System.out.println(randomLon);

        return new Spot(word, randomLon, randomLat);

    }

    // Compares the longitude of the spot vs the other spot; <, ==, >
    public int compareTo(Spot other){
        if(other == null){
            return 0;
        }
        if(other.longitude < this.longitude){
            return -1;
        } else if(other.longitude > this.longitude){
            return 1;
        } else {
            return 0;
        }
    }

}
