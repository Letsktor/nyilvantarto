package hu.undieb.nyilvantarto.activites;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
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
import hu.undieb.nyilvantarto.model.Hallgato;
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
        Intent i=new Intent(this, HallgatokActivity.class);
        button.setOnClickListener(v ->{
            //KurzusokUtils.getInstance().updateJelenlet(kurzus_nev,Integer.toString(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1),position,new Jelenlet(name.getText().toString(), Jelenlet.Status.RECORDEDBYTEACHER));
            ArrayList<Jelenlet> temp=new ArrayList<>();
            temp.addAll(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().get(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1).getJelenlevok());
            Log.d("XDXD",temp.toString());
            if(temp.size()<1)
            {
                temp.add(new Jelenlet(name.getText().toString(), Jelenlet.Status.RECORDEDBYTEACHER));
            }
            else{
                Jelenlet jel=new Jelenlet(name.getText().toString(), Jelenlet.Status.RECORDEDBYTEACHER);
                boolean tt=false;
                int ind=-1;
                for (Jelenlet s:temp
                     ) {
                    if(s.getName().equals(jel.getName()))
                    {
                        tt=true;
                        ind=temp.indexOf(s);
                        break;
                    }
                }
                if(tt)
                {
                    temp.set(ind,jel);
                }
                else{
                    temp.add(jel);
                }


            }

            KurzusokUtils.getInstance().updateOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().get(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1).getJelenlevok_szama()+1,temp),Integer.toString(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1));


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
                                    for(Hallgato hal:KurzusokUtils.getInstance().getHallgatok(kurzus_nev).getValue())
                                    {
                                        if(hal.getCardNumber().equals(temp[temp.length-1]))
                                        {
                                            ArrayList<Jelenlet> temp1=new ArrayList<>();
                                            temp1.addAll(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().get(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1).getJelenlevok());
                                            if(temp1.size()<1)
                                            {
                                                temp1.add(new Jelenlet(name.getText().toString(), Jelenlet.Status.PRESENT));
                                            }
                                            else{
                                                Jelenlet jel=new Jelenlet(name.getText().toString(), Jelenlet.Status.PRESENT);
                                                boolean tt=false;
                                                int ind=-1;
                                                for (Jelenlet s:temp1
                                                ) {
                                                    if(s.getName().equals(jel.getName()))
                                                    {
                                                        tt=true;
                                                        ind=temp1.indexOf(s);
                                                        break;
                                                    }
                                                }
                                                if(tt)
                                                {
                                                    temp1.set(ind,jel);
                                                }
                                                else{
                                                    temp1.add(jel);
                                                }
                                            }
                                            KurzusokUtils.getInstance().updateOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().get(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1).getJelenlevok_szama()+1,temp1),Integer.toString(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1));
                                            break;
                                        }

                                    }

                                })
                        .addOnCanceledListener(
                                () -> {
                                    // Task canceled
                                })
                        .addOnFailureListener(
                                e -> {
                                    // Task failed with an exception
                                });
                startActivity(i);
            }

        });
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] serialnuminbytes=tag.getId();
            SharedPreferences sharedPref = getSharedPreferences("KurzusNev", Context.MODE_PRIVATE);
            String kurzus_nev = sharedPref.getString("kurzus_nev", null);
            Intent it=getIntent();
            int position=it.getIntExtra("pos",0);
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
            for(Hallgato hal:KurzusokUtils.getInstance().getHallgatok(kurzus_nev).getValue())
            {
                if(sb.toString().equals(hal.getCardId()))
                {
                    ArrayList<Jelenlet> temp1=new ArrayList<>();
                    temp1.addAll(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().get(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1).getJelenlevok());
                    if(temp1.size()<1)
                    {
                        temp1.add(new Jelenlet(name.getText().toString(), Jelenlet.Status.PRESENT));
                    }
                    else{
                        Jelenlet jel=new Jelenlet(name.getText().toString(), Jelenlet.Status.PRESENT);
                        boolean tt=false;
                        int ind=-1;
                        for (Jelenlet s:temp1
                        ) {
                            if(s.getName().equals(jel.getName()))
                            {
                                tt=true;
                                ind=temp1.indexOf(s);
                                break;
                            }
                        }
                        if(tt)
                        {
                            temp1.set(ind,jel);
                        }
                        else{
                            temp1.add(jel);
                        }
                    }

                    KurzusokUtils.getInstance().updateOra(kurzus_nev,new Ora(KurzusokUtils.getInstance().getCurrentDate(),KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().get(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1).getJelenlevok_szama()+1,temp1),Integer.toString(KurzusokUtils.getInstance().getKurzus(kurzus_nev).getOrak().size()-1));
                    break;
                }

            }

        }
        Intent i=new Intent(this, HallgatokActivity.class);
        startActivity(i);

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