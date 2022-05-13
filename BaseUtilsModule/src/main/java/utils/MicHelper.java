package utils;

import android.annotation.TargetApi;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;

/**
 * User: Zephyr
 * Date: 2022/5/13
 * Time: 11:10
 */
public class MicHelper {

    /**
     * 用户是否同意录音权限
     * @return
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static boolean isVoicePermission() {
//        AudioRecord record = null;
//        try {
//            record = new AudioRecord(MediaRecorder.AudioSource.MIC, 22050,
//                    AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT,
//                    AudioRecord.getMinBufferSize(22050, AudioFormat.CHANNEL_CONFIGURATION_MONO,
//                            AudioFormat.ENCODING_PCM_16BIT));
//            record.startRecording();
//            int recordingState = record.getRecordingState();
//            if (recordingState == AudioRecord.RECORDSTATE_STOPPED) {
//                return false;
//            }
//            return true;
//        } catch (Exception e) {
//            return false;
//        } finally {
//            if (record != null) {
//                record.release();
//                record = null;
//            }
//        }
        return true;
    }
}
