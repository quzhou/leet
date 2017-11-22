import java.util.*;

/* 
 * write first to avoid starvation
 *
 * A thread is granted read reentrance if it can get read access (no writers or 
 write requests), or if it already has read access (regardless of write requests).
 * 
 * Write reentrance is granted only if the thread has already write access.
 */

public class ReadWriteLock {
    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
    private int writeReq = 0;
    private Thread writingThread = null;
    private int writeAccess = 0;

    private boolean canRead(Thread thread) {
        // writer can read
        if (thread == writingThread) {
            return true;
        }

        // there is other writer
        if (writingThread != null) {
            return false;
        }

        // reader can read again
        if (readingThreads.containsKey(thread)) {
            return true;
        }

        // writer first
        if (writeReq > 0) {
            return false;
        }

        return true;
    }

    private boolean canWrite(Thread thread) {
        // reader can write
        if (readingThreads.size() == 1 && readingThreads.containsKey(thread)) {
            return true;
        }

        // there is other reader
        if (readingThreads.size() > 0) {
            return false;
        }

        // no write
        if (writingThread == null) {
            return true;
        }

        // writer can write again, otherwise not allowed
        if (wrtingThread != thread) {
            return false;
        }

        return true;
    }

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();

        while (! canRead(callingThread)) {
            wait();
        }

        readingThreads.put(callingThread, readingThreads.get(callingThread) + 1);
    }

    public synchronized void unlockRead() {
        Thread thread = Thread.currentThread();
        
        int num = readingThreads.get(thread) - 1;
        if (num <= 0) {
            readingThreads.remove(thread);
        } else {
            readingThreads.put(thread, num);
        }
        
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeReq++;

        Thread callingThread = Thread.currentThread();

        while (! canWrite(callingThread)) {
            wait();
        }

        writeReq--;
        writeAccess++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() {
        writeAccess--;
        if (writeAccess == 0) {
            writingThread = null;
        }

        notifyAll();
    }
}