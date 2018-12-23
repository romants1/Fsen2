public class Game {
    public static String start (int n){
        if(n > 100 || n < 1)
            return "wrong input";
        String s = "";
        for(int i = 1; i <= n; i++){
            s +=
                    ((i % 3) == 0) && ((i % 5) == 0) ? "FizzBuzz, " :
                            ((i % 3) == 0) ? "Fizz, " :
                                    ((i % 5) == 0) ? "Buzz, " :
                                            i + ", " ;
        }
        return  s.substring(0,s.length() - 2) ;
    }
}
