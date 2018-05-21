package com.lepus.cang2;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wang on 2018/5/15.
 */

public class ObjectSerializer {

    private final String TAG = getClass().getSimpleName();

    public static final String FILE_NAME_DATA = "db_data";
    public static final String INTENT_SERIALIZE_SUCCESS = "serialize.success";

    public static final String EXTERNAL_FILE_DIR = Environment.getExternalStorageDirectory() + "/apps/com.lepus.cang2";

    private Context context;

    private File getFilesDir(){
//        return context.getFilesDir();
        return new File(EXTERNAL_FILE_DIR);
    }

    private ObjectSerializer(){

	}

	public static ObjectSerializer newInstance(Context context){
		ObjectSerializer serializer = new ObjectSerializer();
		serializer.context = context;
		return serializer;
	}

	public void serialize(String filename, Object o) {
		try {
		    File dir = getFilesDir();
		    if(!dir.exists())
		        dir.mkdirs();
			File file = new File(dir, filename);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(o);
			oos.flush();
			oos.close();
		} catch (Exception e){
			Log.e(getClass().getSimpleName(), "serialize: " + e.getMessage());
		}
	}

	public <T> T deserialize(String filename){
		try {
		    if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
                File file = new File(getFilesDir(), filename);
//			    File file = new File(context.getFilesDir(), filename);
                if(!file.exists()) {
                    Data data = new Data();
                    data.init();
                    serialize(FILE_NAME_DATA, data);
                    return (T) data;
                }
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Object o = ois.readObject();
                ois.close();
                return (T) o;
            }else{
		        throw new RuntimeException("sd card not mounted");
            }
		} catch (Exception e){
			Log.e(getClass().getSimpleName(), "deserialize: " + e.getMessage());
		}
		return null;
	}

}
