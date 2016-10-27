/**
 * Created by qzhou on 10/25/16.
 */
public class Tweet {
    public int id;
    public int user_id;
    public String text;
    public static Tweet create(int user_id, String tweet_text) {
        Tweet obj = new Tweet();
        obj.user_id = user_id;
        obj.text = tweet_text;
        return obj;
    }
}
