package li.xiangyang.android.midialog;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bac on 16/5/11.
 */
public class LinearNumberSelectDialog extends SelectDialog {

    private IListener listener;
    private List<String> items = new ArrayList<>();

    private boolean floatValue = false;

    public LinearNumberSelectDialog(Context context, IListener lis, String title, String uint, int start, int end, int step, int selection) {
        this(context,lis,title,R.color.midialog_select_color,R.drawable.midialog_select_box,uint,start,end,step,selection);
    }

    public LinearNumberSelectDialog(Context context, IListener lis, String title,int itemTextColor,int bgImage, String uint, int start, int end, int step, int selection) {
        this(context, lis, title,itemTextColor,bgImage, uint, start, end, step, "%.0f", selection);
        floatValue = false;
    }

    public LinearNumberSelectDialog(Context context, IListener lis, String title, String uint, float start, float end, float step, String format, int selection) {
        this(context, lis, title, R.color.midialog_select_color,R.drawable.midialog_select_box, uint, start, end, step, format, selection);
    }

    /**
     * @param context
     * @param lis
     * @param title
     * @param uint
     * @param start
     * @param end
     * @param step
     * @param format    ""
     * @param selection
     */
    public LinearNumberSelectDialog(Context context, IListener lis, String title, int itemTextColor,int bgImage, String uint, float start, float end, float step, String format, int selection) {
        super(context, null, title,itemTextColor,bgImage, uint);
        this.listener = lis;
        super.setListener(new SelectDialog.IListener() {
            @Override
            public void onCancel() {
                if (LinearNumberSelectDialog.this.listener != null) {
                    LinearNumberSelectDialog.this.listener.onCancel();
                }
            }

            @Override
            public void onDone(int selection) {
                if (listener != null) {
                    if (floatValue) {
                        listener.onDone(Float.parseFloat(items.get(selection)));
                    } else {
                        listener.onDone(Integer.parseInt(items.get(selection)));
                    }
                }
            }
        });
        for (float i = start; i < end; i += step) {
            items.add(String.format(format, i));
        }
        super.setItems(items, selection);
        floatValue = true;
    }

    public static interface IListener {
        void onCancel();

        void onDone(Number number);
    }
}
