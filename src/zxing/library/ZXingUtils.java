package zxing.library;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.*;

/**
 * Created by rusfearuth on 2/21/14.
 */
public class ZXingUtils
{
    public static void generateAndSet(final BarcodeFormat aFormat, final ImageView aView, final String aBarCode)
    {
        Bitmap barCodeImage = null;
        com.google.zxing.Writer codeWriter = null;
        ViewGroup.LayoutParams params = aView.getLayoutParams();

        switch (aFormat)
        {
            case CODE_128:
                codeWriter = new Code128Writer();
                break;
            case CODE_39:
                codeWriter = new Code39Writer();
                break;
            case EAN_8:
                codeWriter = new EAN8Writer();
                break;
            case EAN_13:
                codeWriter = new EAN13Writer();
                break;
        }

        if (codeWriter != null)
        {
            try
            {
                BitMatrix bm = codeWriter.encode(aBarCode, aFormat, params.width, params.height);
                barCodeImage = Bitmap.createBitmap(params.width, params.height, Bitmap.Config.ARGB_8888);

                for (int i = 0; i < params.width; i++)
                {
                    for (int j = 0; j < params.height; j++)
                    {

                    barCodeImage.setPixel(i, j, bm.get(i, j) ? Color.BLACK : Color.WHITE);
                    }
                }
            }
            catch (WriterException e)
            {
                e.printStackTrace();
            }
            if (barCodeImage != null)
            {
                aView.setImageBitmap(barCodeImage);
            }
        }
    }
}
