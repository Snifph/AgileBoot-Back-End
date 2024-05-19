package com.agileboot.common.utils.push;



import com.agileboot.common.utils.push.wxclient.CreateQrcodeReq;
import com.agileboot.common.utils.push.wxclient.CreateQrcodeResp;
import com.agileboot.common.utils.push.wxclient.HttpUtils;
import com.agileboot.common.utils.push.wxclient.Message;
import com.agileboot.common.utils.push.wxclient.MessageResult;
import com.agileboot.common.utils.push.wxclient.Page;
import com.agileboot.common.utils.push.wxclient.Result;
import com.agileboot.common.utils.push.wxclient.ResultCode;
import com.agileboot.common.utils.push.wxclient.WxUser;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class WxPusher {
private  static String AppToken ="AT_4sZnzQsJmILzRE1LKSor8VgW6LVZsw1y";
private  static String Uid ="UID_o3aIXPbCNpyQ8zr38zTNnRrTXNei";

    public static void main(String[] args) {
        Message message = new Message();
        message.setAppToken(AppToken);
        message.setContentType(Message.CONTENT_TYPE_TEXT);
        message.setContent("不加限制的自由是很可怕的，因为很容易让任何人滑向深渊。");
        message.setUid(Uid);
        message.setUrl("http://wxpuser.zjiecode.com");//可选参数

        Result<Page<WxUser>> pageResult = queryWxUser(AppToken, Uid);
        Page<WxUser> data = pageResult.getData();


        List<WxUser> records = data.getRecords();
        for (WxUser record : records) {
            String nickName = record.getNickName();
            System.out.println(nickName);

        }

        //Result<List<MessageResult>> result = WxPusher.send(message);
    }
    private WxPusher() {
    }

    /**
     * 发送消息
     */
    public static Result<List<MessageResult>> send(Message message) {
        Result result = verify(message);
        if (result != null) {
            return result;
        }
        Result sendResult = HttpUtils.post(message, "/api/send/message");
        if (sendResult.isSuccess()) {
            //转换，方便调用
            Object data = sendResult.getData();
            String s = JSONObject.toJSONString(data);
            List<MessageResult> messageResults = JSONObject.parseObject(s, new TypeReference<List<MessageResult>>() {
            });
            sendResult.setData(messageResults);
        }
        return sendResult;
    }

    /**
     * 查询消息发送状态
     */
    public static Result queryMessageStatus(Long messageId) {
        if (messageId == null || messageId <= 0) {
            return new Result(ResultCode.BIZ_FAIL, "messageId为空");
        }
        return HttpUtils.get(String.format("/api/send/query/%s", messageId));
    }

    /**
     * 创建带参数的app临时二维码
     */
    public static Result<CreateQrcodeResp> createAppTempQrcode(CreateQrcodeReq createQrcodeReq) {
        Result result = HttpUtils.post(createQrcodeReq, "/api/fun/create/qrcode");
        if (result.getData() != null) {
            String jsonString = JSONObject.toJSONString(result.getData());
            CreateQrcodeResp createQrcodeResp = JSONObject.parseObject(jsonString, CreateQrcodeResp.class);
            result.setData(createQrcodeResp);
        }
        return result;
    }

    /**
     * 查询关注你App的微信用户
     *
     * @param appToken 应用token
     * @param page     页码
     * @param pageSize 分页大小
     * @return 查询的数据
     * @deprecated 请使用 {@link #queryWxUserV2(String, Integer, Integer, String, boolean, int)} 代替
     */
    public static Result<Page<WxUser>> queryWxUser(String appToken, Integer page, Integer pageSize) {
        return queryWxUser(appToken, page, pageSize, null);
    }

    /**
     * 查询关注你App的微信用户
     *
     * @param appToken 应用token
     * @param uid      根据UID过滤用户
     * @return 查询的数据
     * @deprecated 请使用 {@link #queryWxUserV2(String, Integer, Integer, String, boolean, int)} 代替
     */
    public static Result<Page<WxUser>> queryWxUser(String appToken, String uid) {
        return queryWxUser(appToken, 1, 1, uid);
    }

    /**
     * 查询关注你App的微信用户
     *
     * @param appToken 应用token
     * @param page     页码
     * @param pageSize 分页大小
     * @param uid      根据UID过滤用户
     * @return 查询的数据
     * @deprecated 请使用 {@link #queryWxUserV2(String, Integer, Integer, String, boolean, int)} 代替
     */
    public static Result<Page<WxUser>> queryWxUser(String appToken, Integer page, Integer pageSize, String uid) {
        if (appToken == null || appToken.isEmpty()) {
            return new Result(ResultCode.BIZ_FAIL, "appToken不能为空");
        }
        if (page == null || page <= 0) {
            return new Result(ResultCode.BIZ_FAIL, "page不合法");
        }
        if (pageSize == null || pageSize <= 0 || pageSize > 100) {
            return new Result(ResultCode.BIZ_FAIL, "pageSize不合法");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("appToken", appToken);
        params.put("page", page);
        params.put("pageSize", pageSize);
        if (uid != null && !uid.isEmpty()) {
            params.put("uid", uid);
        }
        Result result = HttpUtils.get(params, "/api/fun/wxuser");
        if (result.getData() != null) {
            String jsonString = JSONObject.toJSONString(result.getData());
            Page pageData = JSONObject.parseObject(jsonString, new TypeReference<Page<WxUser>>() {
            });
            result.setData(pageData);
        }
        return result;
    }

    /**
     * 查询用户
     *
     * @param appToken 应用密钥标志
     * @param page     请求数据的页码
     * @param pageSize 分页大小，不能超过100
     * @param uid      用户的uid，可选，如果不传就是查询所有用户，传uid就是查某个用户的信息。
     * @param isBlock  查询拉黑用户，可选，不传查询所有用户，true查询拉黑用户，false查询没有拉黑的用户
     * @param type     关注的类型，可选，不传查询所有用户，0是应用，1是主题
     */
    public static Result<Page<WxUser>> queryWxUserV2(String appToken, Integer page, Integer pageSize,
        String uid, boolean isBlock, int type) {
        if (appToken == null || appToken.isEmpty()) {
            return new Result(ResultCode.BIZ_FAIL, "appToken不能为空");
        }
        if (page == null || page <= 0) {
            return new Result(ResultCode.BIZ_FAIL, "page不合法");
        }
        if (pageSize == null || pageSize <= 0 || pageSize > 100) {
            return new Result(ResultCode.BIZ_FAIL, "pageSize不合法");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("appToken", appToken);
        params.put("page", page);
        params.put("pageSize", pageSize);
        params.put("isBlock", isBlock);
        params.put("type", type);
        if (uid != null && !uid.isEmpty()) {
            params.put("uid", uid);
        }
        Result result = HttpUtils.get(params, "/api/fun/wxuser/v2");
        if (result.getData() != null) {
            String jsonString = JSONObject.toJSONString(result.getData());
            Page pageData = JSONObject.parseObject(jsonString, new TypeReference<Page<WxUser>>() {
            });
            result.setData(pageData);
        }
        return result;
    }

    /**
     * 验证消息合法性，客户端验证比较宽松，主要在服务端进行校验
     */
    private static Result verify(Message message) {
        if (message == null) {
            return new Result(ResultCode.BIZ_FAIL, "消息不能为空");
        }
        if (message.getAppToken() == null || message.getAppToken().length() <= 0) {
            return new Result(ResultCode.BIZ_FAIL, "appToken不能为空");
        }
        if (message.getContent() == null || message.getContent().length() <= 0) {
            return new Result(ResultCode.BIZ_FAIL, "content内容不能为空");
        }
        return null;
    }

}