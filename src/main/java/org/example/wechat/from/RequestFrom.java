package org.example.wechat.from;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFrom {

    /**
     * @todo 以下为http请求中附带过来的参数，以取出type为例，php可用$_POST['type']，java中可用request.getparameter("type")取出
     * @param int type                 =>   事件类型（事件列表可参考 - 事件列表demo）
     * @param int msg_type             =>   消息类型（仅在私聊和群消息事件中，代表消息的表现形式，如文字消息、语音、等等）
     * @param string from_wxid         =>   1级来源id（比如发消息的人的id）
     * @param string from_name         =>   1级来源昵称（比如发消息的人昵称）
     * @param string final_from_wxid   =>   2级来源id（群消息事件下，1级来源为群id，2级来源为发消息的成员id，私聊事件下都一样）
     * @param string final_nickname    =>   2级来源昵称
     * @param string robot_wxid        =>   当前登录的账号（机器人）标识id
     * @param string file_url          =>   如果是文件消息（图片、语音、视频、动态表情），这里则是可直接访问的网络地址，非文件消息时为空
     * @param string msg               =>   消息内容
     * @param string parameters        =>   附加参数（暂未用到，请忽略）
     * @param int time                 =>   请求时间(时间戳10位版本)
     */

    // 事件类型（事件列表可参考 - 事件列表demo）
    private int type;
    // 消息类型（仅在私聊和群消息事件中，代表消息的表现形式，如文字消息、语音、等等）
    private int msg_type;
    // 1级来源id（比如发消息的人的id）
    private String from_wxid;
    // 1级来源昵称（比如发消息的人昵称）
    private String from_name;
    // 2级来源id（群消息事件下，1级来源为群id，2级来源为发消息的成员id，私聊事件下都一样）
    private String final_from_wxid;
    // 2级来源昵称
    private String final_nickname;
    // 当前登录的账号（机器人）标识id
    private String robot_wxid;
    // 如果是文件消息（图片、语音、视频、动态表情），这里则是可直接访问的网络地址，非文件消息时为空
    private String file_url;
    // 消息内容
    private String msg;
    // 附加参数（暂未用到，请忽略）
    private String parameters;
    // 请求时间(时间戳10位版本)
    private Long time;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
