package com.example.farrukh.labs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.BoolRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigInteger;

public class DiffieHellman extends AppCompatActivity {

    BigInteger p, g, a, b, A, B, k1, k2;
    private ProgressDialog progressDialog;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffie_hellman);

        final EditText p_edit = (EditText) findViewById(R.id.p_number);
        final EditText g_edit = (EditText) findViewById(R.id.g_number);
        final EditText secret_alice_edit = (EditText) findViewById(R.id.secretAlice);
        final EditText secret_bob_edit = (EditText) findViewById(R.id.secretBob);
        final TextView open_alice_text = (TextView) findViewById(R.id.openAlice);
        final TextView open_bob_text = (TextView) findViewById(R.id.openBob);
        final TextView common_key = (TextView) findViewById(R.id.commonKey);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        assert fab != null;


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(p_edit.getText().toString())) {
                    p_edit.setError("Empty value!");
                    return;
                }
                if (TextUtils.isEmpty(g_edit.getText().toString())) {
                    g_edit.setError("Empty value!");
                    return;
                }
                if (TextUtils.isEmpty(secret_alice_edit.getText().toString())) {
                    secret_alice_edit.setError("Empty value!");
                    return;
                }
                if (TextUtils.isEmpty(secret_bob_edit.getText().toString())) {
                    secret_bob_edit.setError("Empty value!");
                    return;
                }

                p = new BigInteger(p_edit.getText().toString());
                g = new BigInteger(g_edit.getText().toString());
                a = new BigInteger(secret_alice_edit.getText().toString());
                b = new BigInteger(secret_bob_edit.getText().toString());

                primeChecker taskP = new primeChecker(context, p, g);
                taskP.execute(p);
                taskP.listener = new asyncListener() {
                    @Override
                    public void onFinish(boolean resultP, boolean resultG) {

                        if (!resultP) {
                            p_edit.setError("P is not prime!");
                            return;
                        }


                        if (!resultG) {
                            g_edit.setError("G is not prime");
                            return;
                        }

                        if (new BigInteger(g_edit.getText().toString()).compareTo(new BigInteger(p_edit.getText().toString())) == 1) {
                            g_edit.setError("G should be less than P!!");
                            return;
                        }

                        A = g.pow(a.intValue()).mod(p);
                        B = g.pow(b.intValue()).mod(p);
                        k1 = B.pow(a.intValue()).mod(p);
                        k2 = A.pow(b.intValue()).mod(p);

                        open_alice_text.setText(A.toString());
                        open_bob_text.setText(B.toString());

                        if (k1.equals(k2)) {
                            common_key.setText(k1.toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "Something went wrong! Check your keys", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

            }
        });


    }


    private class primeChecker extends AsyncTask<BigInteger, Integer, Boolean[]> {

        private Context ctx;
        private BigInteger number;
        private BigInteger number1;
        private String type;
        private asyncListener listener = null;

        private primeChecker(Context context, BigInteger num, BigInteger num1) {
            number = num;
            number1 = num1;
            ctx = context;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ctx);
            progressDialog.setMessage("Calculating...");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean[] doInBackground(BigInteger... params) {
            Boolean[] primes = new Boolean[2];
            primes[0] = checkPrime(number);
            primes[1] = checkPrime(number1);
            return primes;
        }

        protected void onProgressUpdate(Integer... progress) {
            progressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(Boolean[] result) {
            super.onPostExecute(result);
            listener.onFinish(result[0], result[1]);
            progressDialog.dismiss();
        }

        private boolean checkPrime(BigInteger number) {
            if (!number.isProbablePrime(5))
                return false;

            BigInteger two = new BigInteger("2");
            if (!two.equals(number) && BigInteger.ZERO.equals(number.mod(two)))
                return false;

            for (BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(number) < 1; i = i.add(two)) {
                if (BigInteger.ZERO.equals(number.mod(i)))
                    return false;
                Log.d("diffie", "check num = " + i);
            }
            return true;
        }
    }


    private interface asyncListener {
        void onFinish(boolean resultP, boolean resultG);
    }


}
