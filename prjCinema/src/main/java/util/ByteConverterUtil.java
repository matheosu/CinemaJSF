package util;

import org.apache.log4j.Logger;

public abstract class ByteConverterUtil {
	
	private static final Logger logger = Logger.getLogger(ByteConverterUtil.class);

	public static Byte[] parseByteToObject(byte[] primitiveByte) {
		if (primitiveByte == null || primitiveByte.length <= 0){
			logger.error("PrimitiveByte is null!");
			return null;
		}

		Byte[] objectByte = new Byte[primitiveByte.length];

		for (int i = 0; i < primitiveByte.length; i++) {
			objectByte[i] = primitiveByte[i];
		}

		return objectByte;
	}

	public static byte[] parsetByteToPrimite(Byte[] objectByte) {
		if (objectByte == null || objectByte.length <= 0){
			logger.error("ObjectByte is null!");
			return null;
		}

		byte[] primitiveByte = new byte[objectByte.length];

		for (int i = 0; i < objectByte.length; i++) {
			primitiveByte[i] = objectByte[i].byteValue();
		}

		return primitiveByte;

	}
}
