package hu.undieb.nyilvantarto;



import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.nfc.NfcAdapter;

import android.nfc.Tag;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;


public class MainActivity extends AppCompatActivity {
    public TextView text;
    Context context;
    NfcAdapter nfcAdapter;
    Intent intent;
    PendingIntent pendingIntent;
    ImageView QrCodeScanner;
    TextView txtBArCOdeValue;
    Tag tag;
    int xd=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBArCOdeValue=findViewById(R.id.txtFromQrCode);
        QrCodeScanner=findViewById(R.id.btnQrCodeScanner);
        nfcAdapter=NfcAdapter.getDefaultAdapter(this);
        intent=new Intent(this,getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE);
        text=findViewById(R.id.txtNfctag);
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