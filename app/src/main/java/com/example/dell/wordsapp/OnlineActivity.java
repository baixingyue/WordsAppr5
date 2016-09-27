package com.example.dell.wordsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class OnlineActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btnTranslate;
    private  EditText etResult;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private String from="auto";
    private String to="auto";

    String[] language={"auto","zh","en","yue","wyw","jp","kor","fra","spa","th","ara","ru","pt","de","it","el","nl","pl","bul","est","dan","fin","cs","rom"
            ,"slo","swe","hu","cht"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        etInput = (EditText) findViewById(R.id.et_input);
        btnTranslate = (Button) findViewById(R.id.btn_translate);
        etResult = (EditText) findViewById(R.id.et_result);
        spinnerFrom = (Spinner) findViewById(R.id.spinner_from);
        spinnerTo = (Spinner) findViewById(R.id.spinner_to);

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String request = etInput.getText().toString();
                RequestUtils requestUtils=new RequestUtils();
                if (!request.isEmpty()){
                    try {
                        requestUtils.translate(request, from, to, new HttpCallBack() {
                            @Override
                            public void onSuccess(String result) {
                                etResult.setText(result);
                            }

                            @Override
                            public void onFailure(String exception) {
                                etResult.setText(exception);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(OnlineActivity.this,"请输入要翻译的内容!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from=language[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to=language[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_main:
                Intent intent1=new Intent(OnlineActivity.this,MainActivity.class);
                startActivity(intent1);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

}
