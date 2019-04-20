package in.rittz.retrofitsample.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import in.rittz.retrofitsample.R;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

    }

    public void AddEntry1(View view)
    {
        startActivity( new Intent(SelectionActivity.this,AddEntryActivity.class));
    }

    public void AddEntry2(View view)
    {
        startActivity( new Intent(SelectionActivity.this,AddEntry2Activity.class));
    }

    public void ShowEntry1(View view)
    {
        startActivity( new Intent(SelectionActivity.this,ShowEntryActivity.class));
    }

    public void ShowEntry2(View view)
    {
        startActivity( new Intent(SelectionActivity.this,ShowEntry2Activity.class));
    }

}
