package nxt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Poll {

    private static final ConcurrentMap<Long, Poll> polls = new ConcurrentHashMap<>();

    private final Long id;
    private final String name;
    private final String description;
    private final String[] options;
    private final byte minNumberOfOptions, maxNumberOfOptions;
    private final boolean optionsAreBinary;
    private final ConcurrentHashMap<Long, Long> voters;

    private Poll(Long id, String name, String description, String[] options, byte minNumberOfOptions, byte maxNumberOfOptions, boolean optionsAreBinary) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.options = options;
        this.minNumberOfOptions = minNumberOfOptions;
        this.maxNumberOfOptions = maxNumberOfOptions;
        this.optionsAreBinary = optionsAreBinary;
        this.voters = new ConcurrentHashMap<>();

    }

    public static void addPoll(Long id, String name, String description, String[] options, byte minNumberOfOptions, byte maxNumberOfOptions, boolean optionsAreBinary) {
        polls.put(id, new Poll(id, name, description, options, minNumberOfOptions, maxNumberOfOptions, optionsAreBinary));
    }

    public static ConcurrentMap<Long, Poll> getPolls() { return polls; }

    public static void clear() {
        polls.clear();
    }

    public static Poll getPoll(Long id) {
        return polls.get(id);
    }

    public Long getId() {
        return id;
    }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String[] getOptions() { return options; }

    public byte getMinNumberOfOptions() { return minNumberOfOptions; }

    public byte getMaxNumberOfOptions() { return maxNumberOfOptions; }

    public boolean isOptionsAreBinary() { return optionsAreBinary; }

    public ConcurrentHashMap<Long, Long> getVoters() { return voters; }

    public void addVoter(Long voterId, Long voteId) {
        voters.put(voterId, voteId);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Poll && this.getId().equals(((Poll)o).getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}