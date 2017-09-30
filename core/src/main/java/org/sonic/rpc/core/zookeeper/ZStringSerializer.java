package org.sonic.rpc.core.zookeeper;

import java.nio.charset.Charset;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

public class ZStringSerializer implements ZkSerializer {
	private final Charset charset = Charset.forName("UTF8");

	@Override
	public byte[] serialize(Object data) throws ZkMarshallingError {
		return (data == null ? null : ((String) data).getBytes(charset));
	}

	@Override
	public String deserialize(byte[] bytes) throws ZkMarshallingError {
		return (bytes == null ? null : new String(bytes, charset));
	}
}
