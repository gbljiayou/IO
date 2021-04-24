package com.system.lession.test.rpcenv.connectpool;

import com.system.lession.test.packmode.MsgCallBackMapping;
import com.system.lession.test.rpcenv.PackageMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: 马士兵教育
 * @create: 2020-07-18 11:30
 */
public class ClientResponsesHandler extends ChannelInboundHandlerAdapter {

    //consumer.....
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PackageMsg pkg = (PackageMsg) msg;
        MsgCallBackMapping.runCallBack(pkg);
    }
}
