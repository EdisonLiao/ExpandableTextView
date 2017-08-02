package vip.frendy.etv

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        etv.setText("测试")
//        etv.orientation = LinearLayout.VERTICAL
    }

    override fun onClick(v: View) {

    }
}
