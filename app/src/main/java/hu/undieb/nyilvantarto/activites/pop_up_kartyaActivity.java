package hu.undieb.nyilvantarto.activites;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.activites.HallgatokActivity;

public class pop_up_kartyaActivity extends AppCompatActivity {
    public TextView text;
    Context context;
    NfcAdapter nfcAdapter;
    Intent intent;
    PendingIntent pendingIntent;
    ImageView QrCodeScanner;
    TextView txtBArCOdeValue;
    Tag tag;
    int xd=0;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_kartya);
        text=findViewById(R.id.txtName);
        button=findViewById(R.id.btnRecord);
        txtBArCOdeValue=findViewById(R.id.txtCadNumber);
        QrCodeScanner=findViewById(R.id.imgQrCode);
        Intent it=getIntent();
        text.setText(it.getStringExtra("tanulo_nev"));
        nfcAdapter=NfcAdapter.getDefaultAdapter(this);
        intent=new Intent(this,getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE);
        text=findViewById(R.id.txtNfc);
        context=getApplicationContext();
        if(tag!=null)
        {
            Toast.makeText(this,tag.toString(),Toast.LENGTH_LONG).show();
        }
        GmsBarcodeScannerOptions options = new GmsBarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                        Barcode.FORMAT_QR_CODE,
                        Barcode.FORMAT_AZTEC)
                .build();
        GmsBarcodeScanner scanner = GmsBarcodeScanning.getClient(this);
        QrCodeScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanner
                        .startScan()
                        .addOnSuccessListener(
                                barcode -> {
                                    String rawValue = barcode.getRawValue();
                                    String[] temp=rawValue.split("/");
                                    txtBArCOdeValue.setText(temp[temp.length-1]);
                                })
                        .addOnCanceledListener(
                                () -> {
                                    // Task canceled
                                })
                        .addOnFailureListener(
                                e -> {
                                    // Task failed with an exception
                                });
            }

        });
        button.setOnClickListener(v->{
            Intent i=new Intent(this, HallgatokActivity.class);
            startActivity(i);

        });

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] serialnuminbytes=tag.getId();
            StringBuffer sb=new StringBuffer();
            for(byte b:serialnuminbytes)
            {
                if(b==serialnuminbytes[serialnuminbytes.length-1])
                {
                    sb.append(String.format("%02X",b));
                }
                else{
                    sb.append(String.format("%02X",b)+":");
                }

            }
            text.setText(sb.toString());
        }


    }
    void ReadModeOn()
    {
        if(nfcAdapter!=null)
        {

            nfcAdapter.enableForegroundDispatch(this,pendingIntent,null,null);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        ReadModeOn();
    }

}
