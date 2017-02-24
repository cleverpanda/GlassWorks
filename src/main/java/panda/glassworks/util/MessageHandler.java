package panda.glassworks.util;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import panda.glassworks.gui.ContainerBlowpipe;

//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.
public class MessageHandler implements IMessageHandler<Message, IMessage> {
//Do note that the default constructor is required, but implicitly defined in this case
	
	@Override public IMessage onMessage(Message message, MessageContext ctx) {
		ContainerBlowpipe blowpipe = ((ContainerBlowpipe) ctx.getServerHandler().playerEntity.openContainer);
		blowpipe.setSelection(message.toSend);
		blowpipe.forceUpdate();
		((BlowpipeOutputSlot) blowpipe.getSlot(1)).setSelection(message.toSend);
		return null;
}
}