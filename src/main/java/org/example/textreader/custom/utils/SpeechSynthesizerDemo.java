package org.example.textreader.custom.utils;

import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.OutputFormatEnum;
import com.alibaba.nls.client.protocol.SampleRateEnum;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizer;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerListener;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 此示例演示了
 *      语音合成API调用
 *      流式合成TTS
 *      首包延迟计算
 * (仅作演示，需用户根据实际情况实现)
 */
public class SpeechSynthesizerDemo {
    private static final Logger logger = LoggerFactory.getLogger(SpeechSynthesizerDemo.class);
    private static long startTime;
    private String appKey;
    NlsClient client;

    public SpeechSynthesizerDemo(String appKey, String token, String url) {
        this.appKey = appKey;
        //TODO 重要提示 创建NlsClient实例,应用全局创建一个即可,生命周期可和整个应用保持一致,默认服务地址为阿里云线上服务地址
        if(url.isEmpty()) {
            client = new NlsClient(token);
        }else {
            client = new NlsClient(url, token);
        }
    }

    private static SpeechSynthesizerListener getSynthesizerListener() {
        SpeechSynthesizerListener listener = null;
        try {
            listener = new SpeechSynthesizerListener() {
                File f=new File("tts_test.wav");
                FileOutputStream fout = new FileOutputStream(f);
                private boolean firstRecvBinary = true;

                //语音合成结束
                @Override
                public void onComplete(SpeechSynthesizerResponse response) {
                    // TODO 当onComplete时表示所有TTS数据已经接收完成，因此这个是整个合成延迟，该延迟可能较大，未必满足实时场景
                    System.out.println("name: " + response.getName() + ", status: " + response.getStatus()+", output file :"+f.getAbsolutePath());
                }

                //语音合成的语音二进制数据
                @Override
                public void onMessage(ByteBuffer message) {
                    try {
                        if(firstRecvBinary) {
                            // TODO 此处是计算首包语音流的延迟，收到第一包语音流时，即可以进行语音播放，以提升响应速度(特别是实时交互场景下)
                            firstRecvBinary = false;
                            long now = System.currentTimeMillis();
                            logger.info("tts first latency : " + (now - SpeechSynthesizerDemo.startTime) + " ms");
                        }
                        byte[] bytesArray = new byte[message.remaining()];
                        message.get(bytesArray, 0, bytesArray.length);
                        //System.out.println("write array:" + bytesArray.length);
                        fout.write(bytesArray);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(SpeechSynthesizerResponse response){
                    // TODO 重要提示： task_id很重要，是调用方和服务端通信的唯一ID标识，当遇到问题时，需要提供此task_id以便排查
                    System.out.println(
                        "task_id: " + response.getTaskId() +
                            //状态码 20000000 表示识别成功
                            ", status: " + response.getStatus() +
                            //错误信息
                            ", status_text: " + response.getStatusText());
                }

                @Override
                public void onMetaInfo(SpeechSynthesizerResponse response) {
                    System.out.println("MetaInfo event:{}" +  response.getTaskId());
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listener;
    }

    public void process() {
        SpeechSynthesizer synthesizer = null;
        try {
            //创建实例,建立连接
            synthesizer = new SpeechSynthesizer(client, getSynthesizerListener());
            synthesizer.setAppKey(appKey);
            //设置返回音频的编码格式
            synthesizer.setFormat(OutputFormatEnum.WAV);
            //设置返回音频的采样率
            synthesizer.setSampleRate(SampleRateEnum.SAMPLE_RATE_16K);
            //发音人
            synthesizer.setVoice("siyue");
            //语调，范围是-500~500，可选，默认是0
            synthesizer.setPitchRate(100);
            //语速，范围是-500~500，默认是0
            synthesizer.setSpeechRate(100);
            //设置用于语音合成的文本
            synthesizer.setText("欢迎使用阿里巴巴智能语音合成服务，您可以说北京明天天气怎么样啊");

            synthesizer.addCustomedParam("enable_subtitle", true);

            //此方法将以上参数设置序列化为json发送给服务端,并等待服务端确认
            long start = System.currentTimeMillis();
            synthesizer.start();
            logger.info("tts start latency " + (System.currentTimeMillis() - start) + " ms");

            SpeechSynthesizerDemo.startTime = System.currentTimeMillis();

            //等待语音合成结束
            synthesizer.waitForComplete();
            logger.info("tts stop latency " + (System.currentTimeMillis() - start) + " ms");
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
//        AccessToken accessToken = new AccessToken("LTAI4GAALLoyDKTJszgDKnZj", "Es7pbWaugvgnxeiuQCmzbKTH9ikvza");
//        accessToken.apply();
//        System.err.println(accessToken);
//        String accessToken = token.getToken();
//        long expireTime = token.getExpireTime();
        String appKey = "k0HM86Aa69N19eaS";
//        String token = accessToken.getToken();
        String token = "b8c1f67f2373483abc92123a1fb45fe9";
        System.err.println(token);
        System.err.println(args.length);
        String url = ""; // 默认即可，默认值：wss://nls-gateway.cn-shanghai.aliyuncs.com/ws/v1
        SpeechSynthesizerDemo demo = new SpeechSynthesizerDemo(appKey, token, url);
        demo.process();
        demo.shutdown();
    }
}
