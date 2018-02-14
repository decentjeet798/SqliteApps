package com.ranjeet.sqliteapps.activities;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.ranjeet.sqliteapps.JobseekersList;
import com.ranjeet.sqliteapps.R;
import com.ranjeet.sqliteapps.dbhelper.DatabaseHelper8;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity8 extends AppCompatActivity {
    public static DatabaseHelper8 mydb;
    EditText etname,etmail,etphone;
    Button button1,button2,button3;
    ImageView image;
    final int REQUEST_CODE_GALLERY=999;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        init();
         mydb=new DatabaseHelper8(this,"JOBSEEKER.sqlite",null,1);
         mydb.queryData("CREATE TABLE  IF NOT EXISTS SEEKER(id INTEGER PRIMARY KEY  AUTOINCREMENT,name VARCHAR, email VARCHAR,phone VARCHAR,image BLOG)");
         button1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ActivityCompat.requestPermissions(MainActivity8.this,new  String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                         REQUEST_CODE_GALLERY);
             }
         });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    mydb.insert(etname.getText().toString().trim(),
                                etmail.getText().toString().trim(),
                                etphone.getText().toString().trim(),
                                imageViewToByte(image)
                    );
                    Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
                    etname.setText("");
                    etmail.setText("");
                    etphone.setText("");
                    image.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity8.this,JobseekersList.class);
                startActivity(intent);
            }
        });
    }
    private byte[] imageViewToByte(ImageView image) {
       Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return  byteArray;
    }
    public  void  init(){
        etname= (EditText) findViewById(R.id.name);
        etphone= (EditText) findViewById(R.id.email);
        etmail= (EditText) findViewById(R.id.phone);
        button1= (Button) findViewById(R.id.button);
        button2= (Button) findViewById(R.id.add);
        button3= (Button) findViewById(R.id.display);
        image= (ImageView) findViewById(R.id.imageView);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "u dont have access perimissions locations", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data != null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
