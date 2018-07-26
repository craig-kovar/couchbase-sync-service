package com.cb.sync.repo;

import com.couchbase.client.java.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerCounterRepo {

    private static final long INITIAL_COUNTER_VALUE = 1;
    private static final String USER_COUNTER_KEY = "customer::counter";

    @Autowired
    private Bucket bucket;

    public Long counter() {
        return bucket.counter(USER_COUNTER_KEY, 1, INITIAL_COUNTER_VALUE).content();
    }

    public void inc() {
        bucket.counter(USER_COUNTER_KEY, 1, INITIAL_COUNTER_VALUE);
    }

    public void dec() {
        bucket.counter(USER_COUNTER_KEY, -1, INITIAL_COUNTER_VALUE);
    }

    public Long getValue() {
        return bucket.counter(USER_COUNTER_KEY, 0).content();
    }

}
