package com.lwdevelop.backend.webSocket;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {
    public static void main(String[] args) throws AWTException, IOException {
		
		Robot robot = new Robot();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();//获取到远程桌面的屏幕大小信息
		Rectangle rectangle = new Rectangle(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());
		BufferedImage bufferedImage =  robot.createScreenCapture(rectangle);
		FileOutputStream baos = new FileOutputStream(new File("d:/temp/test.jpg"));
		ImageIO.write(bufferedImage, "jpg", baos);
		
	}
}
