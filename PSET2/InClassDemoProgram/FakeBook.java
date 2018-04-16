import java.util.ArrayList;
import java.util.Date;

class FakeBook {

    ArrayList<> s;


    void updateSubscriber() {
    }
}


class Subscriber {
    ArrayList<String> feed;
}

class User extends Subscriber {

    ArrayList<String> posts;

    void newPost(String content) {
        Post post = new Post(content);
        post.index = posts.size();
        posts.add(post);
    }

    void removePost()

}

class Post {
    int id;
    String timestamp;
    String content;
    Post(String content) {
        this.content = content;
        timestamp = new Date().toString();
    }
}