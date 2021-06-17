package hyperquiz.dao.impl;



import hyperquiz.dao.KeyGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class LongKeyGenerator  implements KeyGenerator<Long>, Serializable {
    private AtomicLong sequence = new AtomicLong();
    @Override
    public Long getNextId() {
        return sequence.incrementAndGet();
    }

    public LongKeyGenerator() {
    }
}
