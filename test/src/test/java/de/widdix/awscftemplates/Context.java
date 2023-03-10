package de.widdix.awscftemplates;

import java.util.concurrent.ConcurrentHashMap;

public class Context {

    private final ConcurrentHashMap<String, Boolean> stacks = new ConcurrentHashMap<>(8);

    private volatile boolean failure = false;

    public void addStack(final String stackName) {
        stacks.put(stackName, true);
    }
    public void reportStackFailure(final String stackName) {
        if (!stacks.containsKey(stackName)) {
            throw new RuntimeException("stack not in context");
        }
        this.failure = true;
    }

    public void reportFailure() {
        this.failure = true;
    }

    public boolean hasFailure() {
        return this.failure;
    }
}
