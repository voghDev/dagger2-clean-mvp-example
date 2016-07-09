package es.voghdev.prjdagger2.ui.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.squareup.picasso.Cache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PicassoImageCache implements Cache {
    private Context mContext;
    private final File mCacheDir;

    public PicassoImageCache(Context mContext, File picturesDir) {
        this.mContext = mContext;
        mCacheDir = picturesDir;
    }

    @Override
    public Bitmap get(String key) {
        key = String.format("%s.png", transform(key) );
        File f = new File(mCacheDir, key);
        return getBitmapFromFile(f.getAbsolutePath());
    }

    @Override
    public void set(String key, Bitmap bitmap) {
        key = String.format("%s.png", transform(key) );
        File f = new File(mCacheDir, key);
        saveBitmapToFile(bitmap, f.getAbsolutePath());
    }

    @Override
    public int size() {
        String[] children = mCacheDir.list();
        return children != null ? children.length : 0;
    }

    @Override
    public int maxSize() {
        return 1000;
    }

    @Override
    public void clear() {
        String[] children = mCacheDir.list();
        for (int i = 0; i < children.length; i++) {
            new File(mCacheDir, children[i]).delete();
        }
    }

    private String transform(String s) {
        return s.replaceAll("[^A-Za-z0-9]", "").substring(15);
    }

    //region Util methods (could be moved to another Util class)
    public void saveBitmapToFile(Bitmap bmp, String absolutePath){
        bitmapToFile( getBytesFromBitmap(bmp), absolutePath);
    }

    private void bitmapToFile(byte[] data, String absolutePath) {
        if (absolutePath == null) {
            return;
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(absolutePath);
            fos.write(data, 0, data.length);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private byte[] getBytesFromBitmap(Bitmap bmp) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }

    public static Bitmap getBitmapFromFile(String absolutePath){
        File file = new File(absolutePath);
        if (file.exists()) {
            return BitmapFactory.decodeFile(absolutePath);
        }
        return null;
    }
    //endregion
}