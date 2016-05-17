package com.android.tools.pixelprobe;

import com.android.tools.chunkio.ChunkUtils;
import com.android.tools.chunkio.RangedInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;

final class PSDDecoder$ImageResourceBlock$$ChunkIO {
    static PSDDecoder.ImageResourceBlock read(RangedInputStream in, LinkedList<Object> stack) throws IOException {
        PSDDecoder.ImageResourceBlock imageResourceBlock = new PSDDecoder.ImageResourceBlock();
        if (stack == null) stack = new LinkedList<Object>();
        stack.addFirst(imageResourceBlock);

        int size = 0;
        long byteCount = 0;

        imageResourceBlock.signature = ChunkUtils.readString(in, 4, Charset.forName("ISO-8859-1"));
        ChunkUtils.checkState(imageResourceBlock.signature.equals("8BIM"),
                "Value read in signature does not match expected value");
        imageResourceBlock.id = in.readUnsignedShort();
        imageResourceBlock.nameLength = (short) (in.readByte() & 0xff);
        byteCount = imageResourceBlock.nameLength;
        imageResourceBlock.name = ChunkUtils.readString(in, byteCount, Charset.forName("ISO-8859-1"));
        byteCount = Math.max(1, imageResourceBlock.nameLength & 1);
        /* imageResourceBlock.padding */
        ChunkUtils.skip(in, byteCount);
        imageResourceBlock.length = in.readInt() & 0xffffffffL;
        byteCount = imageResourceBlock.length + (imageResourceBlock.length & 1);
        in.pushRange(byteCount);
        if (imageResourceBlock.id == 0x0408) {
            imageResourceBlock.data = PSDDecoder$GuidesResourceBlock$$ChunkIO.read(in, stack);
        } else if (imageResourceBlock.id == 0x040C) {
            imageResourceBlock.data = PSDDecoder$ThumbnailResourceBlock$$ChunkIO.read(in, stack);
        } else if (imageResourceBlock.id == 0x03ED) {
            imageResourceBlock.data = PSDDecoder$ResolutionInfoBlock$$ChunkIO.read(in, stack);
        }
        in.popRange();

        stack.removeFirst();
        return imageResourceBlock;
    }
}
