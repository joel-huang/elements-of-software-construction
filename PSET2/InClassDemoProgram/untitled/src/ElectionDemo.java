import java.util.ArrayList;

public class ElectionDemo {
    public static void main(String[] args) {

        ElectionsCommittee committee = new ElectionsCommittee(5);
        ElectorateFactory factory = new ElectorateFactory(committee);

        Electorate e1 = factory.setupElectorate("Alabama");
        Electorate e2 = factory.setupElectorate("California");
        Electorate e3 = factory.setupElectorate("Texas");
        Electorate e4 = factory.setupElectorate("Massachusetts");
        Electorate e5 = factory.setupElectorate("Tennessee");

        committee.subscribe(e1);
        committee.subscribe(e2);
        committee.subscribe(e3);
        committee.subscribe(e4);
        committee.subscribe(e5);

        e1.vote(0);
        e2.vote(1);
        e3.vote(0);

        e1.showReceivedResults();
        e2.showReceivedResults();
        e3.showReceivedResults();
        e4.showReceivedResults();
        e5.showReceivedResults();

        e4.vote(1);
        e5.vote(0);

        e1.showReceivedResults();
        e2.showReceivedResults();
        e3.showReceivedResults();
        e4.showReceivedResults();
        e5.showReceivedResults();



    }
}

class ElectionsCommittee {

    int participants;
    int participantsVoted;
    String winner;
    ArrayList<VoteSubscriber> subscribers = new ArrayList<>();
    int[] currentResults = {0,0};

    ElectionsCommittee(int participants) {
        this.participants = participants;
    }

    void subscribe(VoteSubscriber e) {
        subscribers.add(e);
    }

    void update() {
        for (VoteSubscriber v : subscribers) {
            v.update();
        }
    }

    void receiveVote(int candidate) {
        participantsVoted++;
        currentResults[candidate]++;
        update();
        if (participantsVoted == participants) {
            checkWinner();
            System.out.println("The election ended with " + participantsVoted + " votes.");
            System.out.println("The winner is: " + winner + "\n");
        }

    }

    void checkWinner() {
        if (currentResults[0] > currentResults[1]) {
            winner = "0";
        } else if (currentResults[1] > currentResults[0]) {
            winner = "1";
        } else {
            winner = "The election resulted in a draw.";
        }
    }

}

interface VoteSubscriber {
    void update();
}

class Electorate implements VoteSubscriber {

    String name;
    int[] currentResults = {0,0};
    ElectionsCommittee ec;

    Electorate(ElectionsCommittee comm, String name) {
        this.name = name;
        ec = comm;
    }

    public void update() {
        currentResults = ec.currentResults;
    }

    public void vote(int candidate) {
        System.out.println(name + " has voted.\n");
        ec.receiveVote(candidate);

    }

    void showReceivedResults() {
        System.out.println("Showing results for " + name);
        System.out.println("Candidate 0: " + currentResults[0]);
        System.out.println("Candidate 1: " + currentResults[1]);
        System.out.println("");
    }
}

class ElectorateFactory {
    ElectionsCommittee ec;
    ElectorateFactory(ElectionsCommittee comm) {
        ec = comm;
    }
    Electorate setupElectorate(String name) {
        Electorate e = new Electorate(ec, name);
        return e;
    }
}