package com.xiangxue.googleproject.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.xiangxue.googleproject.R;

/**
 *
 */
@SuppressLint("AppCompatCustomView")
public class ProportionImageView extends ImageView{
    private float mWidthProportion;
    private float mHeightProportion;

    public ProportionImageView(Context context) {// 直接在代码里面new的时候调用
        this(context,null);
    }

    public ProportionImageView(Context context, AttributeSet attrs) {// 在我们布局文件layout里面申明的时候会调用
        this(context, attrs, 0);
    }

    public ProportionImageView(Context context, AttributeSet attrs, int defStyleAttr) {// 布局文件里面 但是你又自己定义了style
        super(context, attrs, defStyleAttr);
        initAttribute(context,attrs);
    }

    /**
     * 初始化
     */
    private void initAttribute(Context context, AttributeSet attrs) {
        // 获取属性的数组
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProportionImageView);

        // 获取单个属性值
        mWidthProportion =  array.getFloat(R.styleable.ProportionImageView_widthProportion,0);
        mHeightProportion = array.getFloat(R.styleable.ProportionImageView_heightProportion,0);

        Log.e("TAG",mWidthProportion+" "+mHeightProportion);

        array.recycle();
    }

    // 1.基本控件  extends View TextView Button
    // 2.高级控件  ListView ...
    // 3.自定义控件 就是官方给的View API不能实现的效果
    // 自定义View 都会继承自系统的  View  ViewGroup

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {// 用来测量和显示View的大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 现在把我们之前的图片 改变高度显示
        // 1.获取宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 2.计算高度   假设比例是为  2/1   ? 如果还有一个地方是  3/2
        int height = (int) (width*mHeightProportion/mWidthProportion);
        // 3.设置控件的宽高
        setMeasuredDimension(width,height);
    }
}
