package org.example.textreader.custom.utils;

import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.OutputFormatEnum;
import com.alibaba.nls.client.protocol.SampleRateEnum;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizer;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerListener;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.textreader.custom.entity.ReadInfo;
import org.example.textreader.custom.service.ReadInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 此示例演示了
 * 语音合成API调用
 * 流式合成TTS
 * 首包延迟计算
 * (仅作演示，需用户根据实际情况实现)
 */
@Slf4j
public class TextReadUtils {
    private static final Logger logger = LoggerFactory.getLogger(TextReadUtils.class);
    private static long startTime;
    private String appKey;
    NlsClient client;


    public TextReadUtils(String appKey, String token, String url) {
        this.appKey = appKey;
        //TODO 重要提示 创建NlsClient实例,应用全局创建一个即可,生命周期可和整个应用保持一致,默认服务地址为阿里云线上服务地址
        if(url.isEmpty()) {
            client = new NlsClient(token);
        }else {
            client = new NlsClient(url, token);
        }
    }
    public TextReadUtils(String appKey, String token) {
        new TextReadUtils(appKey,token,"");
    }

    private static SpeechSynthesizerListener getSynthesizerListener(ReadInfoService readInfoService,ReadInfo readInfo, String filePath) {
        SpeechSynthesizerListener listener = null;
        try {
            listener = new SpeechSynthesizerListener() {
                File file = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(file);
                private boolean firstRecvBinary = true;

                //语音合成结束
                @Override
                public void onComplete(SpeechSynthesizerResponse response) {
                    // TODO 当onComplete时表示所有TTS数据已经接收完成，因此这个是整个合成延迟，该延迟可能较大，未必满足实时场景
                    String fileName = response.getName();
                    int status = response.getStatus();
                    String filePath = file.getAbsolutePath();
                    readInfoService.successEnd(readInfo);
                    log.info("语音合成结束,文件名:[{}],合成状态:[{}],文件路径:[{}]", fileName, status, filePath);
                }

                //语音合成的语音二进制数据
                @Override
                public void onMessage(ByteBuffer message) {
                    try {
                        if (firstRecvBinary) {
                            // TODO 此处是计算首包语音流的延迟，收到第一包语音流时，即可以进行语音播放，以提升响应速度(特别是实时交互场景下)
                            firstRecvBinary = false;
                            long now = System.currentTimeMillis();
                            log.info("收到合成后的第一条数据包,延迟:[{}]ms", (now - TextReadUtils.startTime));
                        }
                        byte[] bytesArray = new byte[message.remaining()];
                        message.get(bytesArray, 0, bytesArray.length);
                        outputStream.write(bytesArray);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(SpeechSynthesizerResponse response) {
                    // TODO 重要提示： task_id很重要，是调用方和服务端通信的唯一ID标识，当遇到问题时，需要提供此task_id以便排查
                     String taskId = response.getTaskId();
                     int status = response.getStatus();
                     String message = response.getStatusText();
                    readInfoService.failEnd(readInfo);
                    log.error("语音合成失败,task_id:[{}],状态码:[{}],错误信息:[{}]", taskId ,status,message);
                }

                @Override
                public void onMetaInfo(SpeechSynthesizerResponse response) {
                    log.debug("语音合成中,task_id:[{}]", response.getTaskId());
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listener;
    }

    public void process(ReadInfoService readInfoService, ReadInfo readInfo, OutputFormatEnum format) {
        SpeechSynthesizer synthesizer = null;
        try {
            //创建实例,建立连接
             SpeechSynthesizerListener listener = getSynthesizerListener(readInfoService,readInfo,readInfo.getFilePath());
            synthesizer = new SpeechSynthesizer(client, listener);
            synthesizer.setAppKey(appKey);
            //设置返回音频的编码格式
            synthesizer.setFormat(format);
            //设置返回音频的采样率
            synthesizer.setSampleRate(SampleRateEnum.SAMPLE_RATE_16K);
            //发音人
            synthesizer.setVoice(readInfo.getVoice());
            //语调，范围是-500~500，可选，默认是0
            synthesizer.setPitchRate(readInfo.getPitchRate());
            //语速，范围是-500~500，默认是0
            synthesizer.setSpeechRate(readInfo.getSpeechRate());
            //设置用于语音合成的文本
            synthesizer.setText(readInfo.getPaper().getContent());
            synthesizer.addCustomedParam("enable_subtitle", true);
            //此方法将以上参数设置序列化为json发送给服务端,并等待服务端确认
            long start = System.currentTimeMillis();
            synthesizer.start();
            log.info("语音合成开始,时间:[{}]ms ", (System.currentTimeMillis() - start));
            TextReadUtils.startTime = System.currentTimeMillis();
            //等待语音合成结束
            synthesizer.waitForComplete();
            log.info("语音合成结束,时间:[{}]ms ", (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            if (null != synthesizer) {
                synthesizer.close();
            }
        }
    }

    public void shutdown() {
        client.shutdown();
    }

    public static void main(String[] args) throws Exception {

    }
}
