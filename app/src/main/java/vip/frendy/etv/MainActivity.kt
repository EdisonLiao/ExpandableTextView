package vip.frendy.etv

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        etv.setText("测试")
        etv.orientation = LinearLayout.VERTICAL
    }

    override fun onClick(v: View) {

    }
}
