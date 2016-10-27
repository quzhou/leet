/**
 * Created by qzhou on 10/25/16.
 * http://www.lintcode.com/en/problem/mini-twitter/
 */

    /**
     * Definition of Tweet:
     * public class Tweet {
     *     public int id;
     *     public int user_id;
     *     public String text;
     *     public static Tweet create(int user_id, String tweet_text) {
     *         // This will create a new tweet object,
     *         // and auto fill id
     *     }
     * }
     */

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Date;
import java.util.Collections;

public class MiniTwitter {
    // use pull model

    class Node {
        public long ts;
        public Tweet tweet;
        public Node(long ts, Tweet tweet) {
            this.ts = ts;
            this.tweet = tweet;
        }
    }

    private Map<Integer, Set<Integer>> friends;
    private Map<Integer, List<Node>> data;

    public MiniTwitter() {
        this.friends = new HashMap<Integer, Set<Integer>>();
        this.data = new HashMap<Integer, List<Node>>();
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        List<Node> nodes = data.get(user_id);
        if (nodes == null) {
            nodes = new LinkedList<Node>();
        }

        Tweet tw = Tweet.create(user_id, tweet_text);
        Date date = new Date();
        Node node = new Node(date.getTime(), tw);
        nodes.add(0, node);

        data.put(user_id, nodes);
        return tw;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // better sulution is to merge k sorted

        List<Tweet> result = new LinkedList<Tweet>();
        List<Node> candidates = new LinkedList<Node>();
        int size;

        Set<Integer> flist = friends.get(user_id);
        List<Node> ownContent = data.get(user_id);
        if (flist == null && ownContent == null) {
            return null;
        }

        if (flist != null) {
            for (Integer i : flist) {
                List<Node> content = data.get(i);
                if (content != null) {
                    size = content.size();
                    if (size > 0) {
                        if (size > 10) {
                            size = 10;
                        }
                        candidates.addAll(content.subList(0, size));
                    }
                }
            }
        }

        if (ownContent != null) {
            size = ownContent.size();
            if (size > 0) {
                if (size > 10) {
                    size = 10;
                }
                candidates.addAll(ownContent.subList(0, size));
            }
        }

        Comparator<Node> myComp = new Comparator<Node>() {
            public int compare(Node left, Node right) {
                long ret = right.ts - left.ts;
                if (ret > 0) {
                    return 1;
                } else if (ret < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(candidates, myComp);

        int count = 0;
        for (Node elem : candidates) {
            result.add(elem.tweet);
            count++;
            if (count == 10) {
                break;
            }
        }
        return result;
    }

    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet>  getTimeline(int user_id) {
        List<Node> nodeList = data.get(user_id);
        if (nodeList == null) {
            return null;
        }

        List<Tweet> tweetList = new LinkedList<Tweet>();
        int count = 0;
        for (Node node : nodeList) {
            tweetList.add(node.tweet);
            count++;
            if (count == 10) {
                break;
            }
        }

        return tweetList;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        Set<Integer> list = friends.get(from_user_id);
        if (list == null) {
            list = new HashSet<Integer>();
        }
        list.add(to_user_id);
        friends.put(from_user_id, list);

        //todo: get tweets for newsfeed
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        Set<Integer> list = friends.get(from_user_id);
        if (list != null) {
            list.remove(to_user_id);
        }
    }
}
