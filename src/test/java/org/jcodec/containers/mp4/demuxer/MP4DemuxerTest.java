package org.jcodec.containers.mp4.demuxer;

import java.io.File;
import java.net.URL;
import java.nio.ByteBuffer;

import org.jcodec.common.AutoFileChannelWrapper;
import org.jcodec.common.SeekableByteChannel;
import org.jcodec.common.model.Packet;
import org.junit.Test;

public class MP4DemuxerTest {

    // broken file
    // iphone generated video has 171 samples at 84 samples per chunk but only 2 chunks
    // 2 chunk * 84 samples == 168 expected samples
    // but 171 actual samples
    @Test
    public void testAudioTrack() throws Exception {
        URL resource = this.getClass().getResource("37.mp4");
        System.out.println(resource);
        File source = new File(resource.getFile());
        SeekableByteChannel input = new AutoFileChannelWrapper(source);
        MP4Demuxer demuxer = new MP4Demuxer(input);
        AbstractMP4DemuxerTrack track = demuxer.getAudioTracks().get(0);
        Packet packet;
        while (null != (packet = track.nextFrame())) {
            ByteBuffer data = packet.getData();
        }
    }

}
