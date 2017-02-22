package com.example.shankarpentyala.labassignment5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class SignUpActivity extends AppCompatActivity {

    EditText Fname;
    EditText Lname;
    ImageView image1;
    byte[] byteArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Fname = (EditText) findViewById(R.id.FirstName);
        Lname = (EditText) findViewById(R.id.LastName);
        image1 = (ImageView) findViewById(R.id.imageView2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signup1 (View V)
    {
        String Fname1 = Fname.getText().toString();
        String Lname1 = Lname.getText().toString();
        if (Fname1.isEmpty()) {
            Toast.makeText(getApplicationContext(), "First Name is Blank", Toast.LENGTH_SHORT).show();
        }
        else if (Lname1.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Last Name is Blank", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent redirect = new Intent(SignUpActivity.this,Home.class);
            redirect.putExtra("picture", byteArray);
            startActivity(redirect);
        }
    }

    public void AddImageCamera (View V)
    {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture,0);
    }
    public void AddImageGallery (View V)
    {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto,1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    //Uri selectedImage = imageReturnedIntent.getData();
                    image1.setImageBitmap(photo);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byteArray = stream.toByteArray();

                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                // Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                   Uri selectedImage = imageReturnedIntent.getData();
                    image1.setImageURI(selectedImage);
                    image1.buildDrawingCache();
                    Bitmap photo = image1.getDrawingCache();
                   ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byteArray = stream.toByteArray();
                }
                break;
        }
    }
}
