package org.pabk.application.emanager.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javafx.scene.control.TextArea;

public class TextAreaOutputStream extends OutputStream {

	private TextArea textArea;
	private Charset charset;
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	public TextAreaOutputStream (TextArea textArea, String encoding) throws UnsupportedEncodingException {
		this.setTextArea(textArea);
		this.charset = Charset.availableCharsets().get(encoding);
		if(this.charset == null) {
			throw new UnsupportedEncodingException(encoding);
		}


	}




	@Override
	public void flush() throws IOException {
		super.flush();
		this.getTextArea().appendText((new String (out.toByteArray(), charset)));
	}




	@Override
	public void write(int b) throws IOException {
		out.write(b);


	/*	if(charset.toString().equalsIgnoreCase(Const.UTF_8_ENCODING)) {
			buffer.add(b);
			this.getTextArea().appendText(String.valueOf((char) decodeUTF8()));
		}
		else {
			throw new UnsupportedEncodingException(charset.toString());
		}

*/




		//this.getTextArea().appendText(String.valueOf((char) arg0));
	}
/*
	private int decodeUTF8() {
		int c = -1;
		while(buffer.size() > 0) {
			c = buffer.remove(0);
			if ((c & Const.UTF_8_BYTE_1_MASK) == 0) {
				return c;
			}
			if((c & Const.UTF_8_BYTE_2_MASK) == Const.UTF_8_BYTE_2_INDICATOR && buffer.size() == 1) {
				c = c & (Const.UTF_8_BYTE_2_MASK ^ 0xFF);
			}

			if((c & Const.UTF_8_BYTE_3_MASK) == Const.UTF_8_BYTE_3_INDICATOR && buffer.size() == 2) {
				c = c & (Const.UTF_8_BYTE_3_MASK ^ 0xFF);
			}
			if((c & Const.UTF_8_BYTE_4_MASK) == Const.UTF_8_BYTE_4_INDICATOR && buffer.size() == 3) {
				c = c & (Const.UTF_8_BYTE_4_MASK ^ 0xFF);
			}
			if((c & Const.UTF_8_BYTE_X_MASK) == Const.UTF_8_BYTE_X_INDICATOR) {
				c = c << 0x06;
				c = c | (Const.UTF_8_BYTE_X_MASK ^ 0xFF);
			}
		}
		return c;
	}
*/
	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

}
