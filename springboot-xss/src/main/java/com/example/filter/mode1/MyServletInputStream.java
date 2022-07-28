package com.example.filter.mode1;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author 成大事
 * @since 2022/7/27 13:11
 */
public class MyServletInputStream extends ServletInputStream {
    private ByteArrayInputStream bis;

    public MyServletInputStream(ByteArrayInputStream bis) {
        this.bis = bis;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }

    @Override
    public int read() throws IOException {
        return bis.read();
    }

}
