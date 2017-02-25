package wu.wunba.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import wu.wunba.R;

/**
 * Created by Administrator on 2016/10/14.
 */
public class NaviListMenuAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;



    private  String [] naviName;
    private  int [] naviIcon;

    public NaviListMenuAdapter(Context context,String [] naviName,int[] naviIcon){
        this.mContext = context;
        this.naviIcon = naviIcon;
        this.naviName = naviName;
    }

    @Override
    public int getCount() {
        return naviName.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return naviName[position];
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(converView == null){
            inflater = LayoutInflater.from(mContext);
            converView = inflater.inflate(R.layout.item_menu_navi_left_list,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMenuName = (TextView)converView.findViewById(R.id.list_item_navigation_name);
            viewHolder.ivMenuIcon = (ImageView)converView.findViewById(R.id.list_item_navigation_icon);
            converView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)converView.getTag();
        }
        viewHolder.ivMenuIcon.setImageResource(naviIcon[position]);
        viewHolder.tvMenuName.setText(naviName[position]);
        return converView;
    }

    class ViewHolder{
        ImageView ivMenuIcon;
        TextView tvMenuName;
    }

}
