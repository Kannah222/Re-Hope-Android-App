package util;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class Typefaces {
    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    public static Typeface get(Context c, String assetPath) {

        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    cache.put(assetPath, Typeface.createFromAsset(c.getAssets(), assetPath));
                } catch (Exception e) {
                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }
}
