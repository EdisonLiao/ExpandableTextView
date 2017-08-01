package vip.frendy.textview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView



/**
 * Created by iiMedia on 2017/8/1.
 */
class ExpandableTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    internal var mTextView: TextView? = null
    internal var mToggle: ImageButton? = null

    private val LIMIT_LINE = 1

    private var isExpanded = false

    private var collapseDrawable: Drawable? = null
    private var expandDrawable: Drawable? = null
    private var text: String? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView)
        expandDrawable = typedArray.getDrawable(R.styleable.ExpandableTextView_expandDrawable)
        collapseDrawable = typedArray.getDrawable(R.styleable.ExpandableTextView_collapseDrawable)
        text = typedArray.getString(R.styleable.ExpandableTextView_text)

        val view = View.inflate(context, R.layout.text_view_expandable, this)
        mTextView = view.findViewById(R.id.text) as TextView
        mToggle = view.findViewById(R.id.toggle) as ImageButton

        isExpanded = false
        if(text != null) mTextView?.text = text
        setToggleDrawable(isExpanded)

        mTextView?.setOnClickListener(this)
        mToggle?.setOnClickListener(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if(visibility == View.GONE) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }

        mTextView?.maxLines = Int.MAX_VALUE
        mToggle?.visibility = View.GONE

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if(mTextView != null && mTextView!!.lineCount <= LIMIT_LINE) {
            return
        }

        if(!isExpanded) {
            mTextView?.maxLines = LIMIT_LINE
        }
        mToggle?.visibility = View.VISIBLE

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.text, R.id.toggle -> {
                if(isExpanded) {
                    isExpanded = false
                    mTextView?.maxLines = LIMIT_LINE
                    setToggleDrawable(isExpanded)

                    val anim = AnimationUtils.loadAnimation(context, R.anim.tv_fade_in)
                    mTextView?.startAnimation(anim)
                } else {
                    isExpanded = true
                    mTextView?.maxLines = Int.MAX_VALUE
                    setToggleDrawable(isExpanded)

                    val anim = AnimationUtils.loadAnimation(context, R.anim.tv_fade_in)
                    mTextView?.startAnimation(anim)
                }
            }
        }
    }

    private fun setToggleDrawable(expanded: Boolean) {
        if(expanded) {
            if(collapseDrawable != null) {
                mToggle?.setImageDrawable(collapseDrawable)
            } else {
                mToggle?.setImageResource(android.R.drawable.arrow_up_float)
            }
        } else {
            if(expandDrawable != null) {
                mToggle?.setImageDrawable(expandDrawable)
            } else {
                mToggle?.setImageResource(android.R.drawable.arrow_down_float)
            }
        }
    }
}