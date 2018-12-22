package e.o.krytponote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class KrytpoNoteActivity extends AppCompatActivity {
    Cipher newCipher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

    //encrypt
    public void onEncryppt(View v){
        try {
            String newKey = ((EditText) findViewById(R.id.key)).getText().toString();
            String newEncrypt = ((EditText) findViewById(R.id.data)).getText().toString();
            newCipher = new Cipher(newKey);
            String answer = newCipher.encrypt(newEncrypt);
            ((EditText)findViewById(R.id.data)).setText(answer);
        }
        catch (Exception e){

        }
    }

    // decrpt
    public void onDecryppt(View v){
        try {
            String newKey = ((EditText) findViewById(R.id.key)).getText().toString();
            String newEncrypt = ((EditText) findViewById(R.id.data)).getText().toString();
            newCipher = new Cipher(newKey);
            String answer = newCipher.decrypt(newEncrypt);
            ((TextView) findViewById(R.id.data)).setText(answer);
        }
        catch (Exception e){

        }
    }

    public void onSave(View v){
        try{
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir,name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();

            Toast.makeText(this,"Note Saved.", Toast.LENGTH_LONG).show();
        }
        catch ( Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onLoad(View v)
    {
        try {
            String show = "";
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir,name);
            FileReader fr = new FileReader(file);
            for (int c = fr.read(); c != -1; c = fr.read())
            {
                show += (char) c;
            }
            ((TextView)findViewById(R.id.data)).setText(show);
            Toast.makeText(this,"Note Shown", Toast.LENGTH_LONG).show();
    }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }





}
