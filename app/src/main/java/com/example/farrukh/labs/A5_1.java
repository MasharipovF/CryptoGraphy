package com.example.farrukh.labs;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class A5_1 extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a5_1);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        getPermision();

    }

    private static final int MY_PERMISSIONS_READ_WRITE = 38;

    private void getPermision() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_READ_WRITE);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_READ_WRITE);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_a5_1, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static String inputFile = "farrukh masharipov123456879";
        private static String keyFile = "FEDCBA9876543210";
        private static String frameNumber = "C45AF";
        private static String encryptedFile;
        private static String decryptedFile;
        private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_a5_1, container, false);
            final EditText msg = (EditText) rootView.findViewById(R.id.msg);
            final TextView msgLen = (TextView) rootView.findViewById(R.id.msgLen);
            final EditText key = (EditText) rootView.findViewById(R.id.key);
            final TextView keyLen = (TextView) rootView.findViewById(R.id.keyLen);
            final EditText frame = (EditText) rootView.findViewById(R.id.frame);
            final TextView frameLen = (TextView) rootView.findViewById(R.id.frameLen);
            final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
            final Context context = getContext();
            final TextView resultTxt = (TextView) rootView.findViewById(R.id.result_txt);

            msg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    msgLen.setText(String.valueOf(s.length()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            key.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    keyLen.setText(String.valueOf(s.length()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            frame.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    frameLen.setText(String.valueOf(s.length()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (key.getText().length() != 8) {
                        key.setError("Key should have 8 symbols!");
                        return;
                    }

                    if (frame.getText().length() != 5) {
                        frame.setError("Frame should have 5 symbols");
                    }

                    if (TextUtils.isEmpty(msg.getText().toString())) {
                        msg.setError("Message is empty!");
                        return;
                    }

                    if (!frame.getText().toString().toUpperCase().matches("[0-9A-F]+")) {
                        frame.setError("Only hex values allowed!");
                        return;
                    }

                    switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                        case 1:
                            inputFile = msg.getText().toString();
                            keyFile = bytesToHex(key.getText().toString().getBytes());
                            frameNumber = frame.getText().toString();

                            encryptedFile = bytesToString(A5_1Engine.encrypt(inputFile, keyFile, frameNumber));
                            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("result", encryptedFile);
                                clipboard.setPrimaryClip(clip);
                                Toast.makeText(getContext(), "Result has been copied to clipboard!", Toast.LENGTH_SHORT).show();
                            }
                            resultTxt.setText(encryptedFile);

                            break;
                        case 2:
                            inputFile = msg.getText().toString();
                            keyFile = bytesToHex(key.getText().toString().getBytes());
                            frameNumber = frame.getText().toString();

                            decryptedFile = bytesToString(A5_1Engine.decrypt(inputFile, keyFile, frameNumber));
                            Log.d("A5", "dec file = " + decryptedFile);
                            resultTxt.setText(decryptedFile);

                            break;
                        default:
                            break;
                    }
                }
            });
            return rootView;
        }

        public static String bytesToString(byte[] bytes) {
            String result = "";
            for (byte b : bytes) {
                result += (char) b;
            }
            return result;
        }

        public static String bytesToHex(byte[] bytes) {
            char[] hexChars = new char[bytes.length * 2];
            for (int j = 0; j < bytes.length; j++) {
                int v = bytes[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ENCRYPTION";
                case 1:
                    return "DECRYPTION";

            }
            return null;
        }
    }
}
