package com.panda.esportingplus.common.tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

public class QrCodeUtils {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static int FRONT_COLOR = 0xFF000000;     //前景色
    private static int OFF_COLOR = 0xFFFFFFFF;    //背景色
    /**
     * 图片的格式
     */
    private static final String IMAGE_TYPE = "jpg";

    public static InputStream createCodeStream(String content) {
        HashMap hints = new HashMap();
        //指定字符编码为“utf-8”
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //指定二维码的纠错等级为中级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置图片的边距
        hints.put(EncodeHintType.MARGIN, 1);
        ByteArrayOutputStream bs = null;
        ImageOutputStream imOut = null;
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            bitMatrix=updateBit(bitMatrix,5);
            //因为二维码生成时，白边无法控制，去掉原有的白边，再添加自定义白边后，二维码大小与size大小就存在差异了，为了让新生成的二维码大小还是size大小，根据size重新生成图片
            BufferedImage image =  MatrixToImageWriter.toBufferedImage(bitMatrix);
            image = zoomInImage(image,300,300);//根据size放大、缩小生成的二维码
            bs = new ByteArrayOutputStream();
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(image, IMAGE_TYPE, imOut);
            return new ByteArrayInputStream(bs.toByteArray());
        } catch (Exception e) {
            throw new BusinessException(BizExceptionEnum.CREATE_TWO_CODE_FAIL, content, e);
        } finally {
            closeStream(bs);
            closeStream(imOut);
        }

    }
    private static BitMatrix updateBit(BitMatrix matrix, int margin){
        int tempM = margin*2;
        int[] rec = matrix.getEnclosingRectangle();   //获取二维码图案的属性
        int resWidth = rec[2] + tempM;
        int resHeight = rec[3] + tempM;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
        resMatrix.clear();
        for(int i= margin; i < resWidth- margin; i++){   //循环，将二维码图案绘制到新的bitMatrix中
            for(int j=margin; j < resHeight-margin; j++){
                if(matrix.get(i-margin + rec[0], j-margin + rec[1])){
                    resMatrix.set(i,j);
                }
            }
        }
        return resMatrix;
    }



    public static BufferedImage  zoomInImage(BufferedImage  originalImage, int width, int height){
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        return newImage;

    }
    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }
}
