package hu.undieb.nyilvantarto.activites;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import java.util.ArrayList;

import hu.undieb.nyilvantarto.R;
import hu.undieb.nyilvantarto.model.Jelenlet;
import hu.undieb.nyilvantarto.model.KurzusokUtils;
import hu.undieb.nyilvantarto.model.Ora;

public class JelenletRog extends AppCompatActivity {
    TextView name;
    Button button;
    ImageView QrCode;
    Context context;
    NfcAdapter nfcAdapter;
    Intent intent;
    PendingIntent pendingIntent;
    Tag tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jelenlet_rog);
        name=findViewById(R.id.txtName);
        button=findViewById(R.id.btnJelenlet);
        QrCode=findViewById(R.id.imgQR);
        Intent it=getIntent();
        name.setText(it.getStringExtra("tanulo_nev"));
        int position=it.getIntExtra("pos",0);
        nfcAdapter=NfcAdapter.getDefaultAdapter(this);
        intent=new Intent(this,getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        SharedPreferences sharedPref = getSharedPreferences("KurzusNev", Context.MODE_PRIVATE);
        String kurzus_nev = sharedPref.getString("kurzus_nev", null);
        pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE);
        context=getApplicationContext();
        button.setOnClickListener(v ->{
            //KurzusokUtils.getInstance().updateJelenlet(kurzus_nev,Integer.toString(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1),position,new Jelenlet(name.getText().toString(), Jelenlet.Status.RECORDEDBYTEACHER));
            ArrayList<Jelenlet> temp=new ArrayList<>();
            temp.addAll(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().get(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1).getJelenlevok());
            temp.set(position,new Jelenlet(name.getText().toString(), Jelenlet.Status.PRESENT));
            KurzusokUtils.getInstance().updateOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size(),temp),Integer.toString(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1));

            Intent i=new Intent(this, HallgatokActivity.class);
            startActivity(i);

        } );
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
        QrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanner
                        .startScan()
                        .addOnSuccessListener(
                                barcode -> {
                                    String rawValue = barcode.getRawValue();
                                    String[] temp=rawValue.split("/");
                                    //txtBArCOdeValue.setText(temp[temp.length-1]);
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