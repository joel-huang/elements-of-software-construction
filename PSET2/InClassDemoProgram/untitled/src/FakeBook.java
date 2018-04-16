import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class FakeBook {
    public static void main(String[] args) {

        // Simulated sequence of conversation between two users.
        PostDB pdb = new PostDB();

        User tom = new User("magnanti", pdb);
        User anon = new User("anon", pdb);

        pdb.addUser(tom);

        tom.newPost("I made SUTD.");
        Post post1 = new Post(tom, "I am leaving SUTD.");
        tom.addPost(post1);
        tom.showFeed();

        pdb.addUser(anon);

        Post post2 = new Post(anon, "Nice day");
        anon.addPost(post2);
        Post post3 = new Post(anon, "Nice wether");
        anon.addPost(post3);

        anon.showFeed();

        Post comment0 = new Post(anon, "weather*");
        anon.addComment(post3, comment0);

        anon.showFeed();

        anon.subscribe(post1.id);
        anon.showFeed();
        tom.editPost(post1.id, "I am no longer leaving SUTD.");
        anon.showFeed();

        Post comment1 = new Post(anon, "That's fantastic, Tom.");
        anon.addComment(post1, comment1);
        anon.showFeed();
        tom.showFeed();

        Post comment2 = new Post(tom, "Bye");
        tom.addComment(comment1, comment2);

        anon.showFeed();
        anon.unsubscribe(post1.id);
        anon.showFeed();

        anon.removePost(post3.getID());
        anon.showFeed();
    }
}

// Publisher type class, receives inputs from the Users and then updates all the subscribed users.
class PostDB {
    public HashMap<Integer, Post> posts;
    public ArrayList<User> userList;

    PostDB() {
        posts = new HashMap<>();
        userList = new ArrayList<>();
    }

    void addUser(User u) {
        userList.add(u);
    }

    void removeUser(User u) {
        userList.remove(u);
    }

    void addPostToDB(Post p) {
        posts.put(p.id, p);
        update();
    }

    void replacePostOnDB(int id, Post p) {
        posts.replace(id, p);
        update();
    }

    void removePostFromDB(int id) {
        posts.remove(id);
        update();
    }
    Post getPost(int id) {
        return posts.get(id);
    }
    private void update() {
        for (User u : userList) {
            u.update();
        }
    }
}

// Subscriber type class, sends locally generated updates to the publisher and receives global updates.
class User {

    String username;
    PostDB subscription = null;
    ArrayList<Post> feed = new ArrayList<>();


    User(String username, PostDB db) {
        this.username = username;
        this.subscription = db;
    }

    void newPost(String content) {
        Post post = new Post(this, content);
        subscription.addPostToDB(post);
        this.subscribe(post.id);
    }

    void addPost(Post post) {
        subscription.addPostToDB(post);
        this.subscribe(post.id);
    }

    void removePost(int id) {
        System.out.println("Post " + Integer.toHexString(id) + " containing '" + subscription.getPost(id).getPostContent() + "' removed\n");
        subscription.removePostFromDB(id);
    }

    void editPost(int id, String content) {
        Post replacement = subscription.posts.get(id);
        replacement.setPostContent(content + " (edited)");
        subscription.replacePostOnDB(id, replacement);
        System.out.println(username + " edited post " + Integer.toHexString(replacement.getID()) + " to: " + replacement.getPostContent() + "\n");
    }

    void addComment(Post target, Post comment) {
        target.addChildComment(comment);
        subscription.addPostToDB(comment);
        if (!target.subscribers.contains(this)) {
            target.subscribers.add(this);
        }
    }

    void showFeed() {
        System.out.println("Showing " + this.username + "'s feed:");
        if (feed.size() != 0) {
            for (Post p : feed) {
                p.showCommentStack(0);
            }
        } else {
            System.out.println("Feed is cleansed.");
        }
        System.out.println("");
    }

    void subscribe(int id) {
        System.out.println(username + " subscribed to post id " + Integer.toHexString(id) + "\n");
        subscription.posts.get(id).subscribers.add(this);
        feed.add(subscription.posts.get(id));
        update();
    }

    void unsubscribe(int id) {
        System.out.println(username + " unsubscribed from post id " + Integer.toHexString(id) + "\n");
        subscription.posts.get(id).subscribers.remove(this);
        feed.remove(subscription.posts.get(id));
        update();
    }

    void update() {
        feed.clear();
        for (Post p : subscription.posts.values()) {
            if (p.subscribers.contains(this)) {
                feed.add(p);
            }
        }
    }
}

// Data structure for a social media text post.
class Post {
    int id;
    User author;
    String timestamp;
    String content;
    ArrayList<User> subscribers;
    ArrayList<Post> childComments;

    Post(User author, String content) {
        this.author = author;
        this.content = content;
        timestamp = new Date().toString();
        subscribers = new ArrayList<>();
        childComments = new ArrayList<>();
        String signature = author.username + content + timestamp;
        id = signature.hashCode();
        System.out.println("Post created by " + author.username + " with signature <" + signature + ">\nas post id " + Integer.toHexString(id) + ": '" + getPostContent() + "'\n");
    }
    String getTimestamp() {
        return timestamp;
    }
    int getID() {return id;}
    String getPostContent() {return content;}
    void setPostContent(String newContent) {content = newContent;}
    void addChildComment(Post comment) {childComments.add(comment);}
    void removeChildComment(Post comment) {childComments.remove(comment);}
    void showCommentStack(int depth) {
        ++depth;
        String comment = "(post_id: " + Integer.toHexString(id) + ") " + timestamp + " " + author.username + ": " + content;
        System.out.println(comment);
        for (Post childComment : childComments) {
            for (int i = 0; i < depth; i++) {
                System.out.printf("  ");
            }
            System.out.printf("+--");
            childComment.showCommentStack(depth);
        }
    }
}