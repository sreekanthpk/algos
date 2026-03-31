package com.practice.algo.io;

import java.io.RandomAccessFile;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.ByteOrder;

public class MappedJournal {
    private static final long WRITE_POS_OFFSET = 0;
    private static final long READ_POS_OFFSET = 8;
    private static final int HEADER_SIZE = 16;

    private static final VarHandle LONG_VIEW = MethodHandles.byteBufferViewVarHandle(
            long[].class, ByteOrder.nativeOrder());

    private final MappedByteBuffer buffer;
    private final int capacity;

    public MappedJournal(String filePath, int capacity) throws Exception {
        this.capacity = capacity;
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {

            this.buffer = raf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, HEADER_SIZE + capacity);
            this.buffer.order(ByteOrder.nativeOrder());
        }
    }

    private long getWritePosition() {
        return (long) LONG_VIEW.getVolatile(buffer, WRITE_POS_OFFSET);
    }

    private void setWritePosition(long pos) {
        LONG_VIEW.setVolatile(buffer, WRITE_POS_OFFSET, pos);
    }

    private long getReadPosition() {
        return (long) LONG_VIEW.getVolatile(buffer, READ_POS_OFFSET);
    }

    private void setReadPosition(long pos) {
        LONG_VIEW.setVolatile(buffer, READ_POS_OFFSET, pos);
    }

    public boolean write(byte[] data) {
        long currentWrite = getWritePosition();
        int messageLen = data.length;

        if (currentWrite + 4 + messageLen > capacity) {
            return false;
        }

        int bufferIdx = (int) (HEADER_SIZE + currentWrite);

        buffer.putInt(bufferIdx, messageLen);

        for (int i = 0; i < messageLen; i++) {
            buffer.put(bufferIdx + 4 + i, data[i]);
        }

        setWritePosition(currentWrite + 4 + messageLen);
        return true;
    }

    public byte[] read() {
        long readPos = getReadPosition();
        long writePos = getWritePosition();

        if (readPos >= writePos) return null; // Nothing to read

        int bufferIdx = (int) (HEADER_SIZE + readPos);
        int messageLen = buffer.getInt(bufferIdx);

        byte[] data = new byte[messageLen];
        for (int i = 0; i < messageLen; i++) {
            data[i] = buffer.get(bufferIdx + 4 + i);
        }

        setReadPosition(readPos + 4 + messageLen);
        return data;
    }
}